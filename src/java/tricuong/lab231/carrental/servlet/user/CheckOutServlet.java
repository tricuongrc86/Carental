/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.servlet.user;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tricuong.lab231.carrental.database.cartbl.CarTblDAO;
import tricuong.lab231.carrental.database.cartbl.CarTblDTO;
import tricuong.lab231.carrental.object.CartObject;

/**
 *
 * @author TriCuong
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CheckOutServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        String url = (String) context.getAttribute("userPage");
        boolean check = true;
        try {
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null || cart.getSize() == 0) {
                url = (String) context.getAttribute("userPage");
                request.setAttribute("NOTIFICATION", "Not have car to pay");
            } else {
                Map<CarTblDTO, Integer> items = cart.getItems();
                Set<CarTblDTO> listCar = items.keySet();
                CarTblDAO dao = new CarTblDAO();
                String rentDate = (String) session.getAttribute("dateRent");
                String returnDate = (String) session.getAttribute("dateReturn");
                int availble;
                for (CarTblDTO car : listCar) {
                    availble = dao.getAvailbleCar(car.getCarID(), rentDate, returnDate);
                    if (availble < items.get(car)) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    String action = request.getParameter("btAction");
                    if (action.equals("PayNormal")) {
                        url = (String) context.getAttribute("PayNormal");
                    }
                    if (action.equals("ZaloPay")) {
                        url = (String) context.getAttribute("ZaloPage");
                    }
                } else {
                    url = (String) context.getAttribute("userPage");
                    request.setAttribute("NOTIFICATION", "One in all car out of stock !CART will be reset!!!");
                    session.removeAttribute("CART");
                    session.removeAttribute("total");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
