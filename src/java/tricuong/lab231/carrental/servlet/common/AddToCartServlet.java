/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.servlet.common;

import java.io.IOException;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddToCartServlet.class);

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
        String url = (String) context.getAttribute("search");
        String idCar = request.getParameter("txtId");
        String txtSearch = request.getParameter("txtSearch");
        String txtTypeCar = request.getParameter("txtTypeCar");
        String strAmount = request.getParameter("txtAmount");
        String strIndex = request.getParameter("index");
        String urlReWriting = "";
        int amount;
        int index;
        if (strAmount == null || strAmount.isEmpty()) {
            amount = 0;
        } else {
            amount = Integer.parseInt(strAmount);
        }
        if (strIndex == null || strIndex.isEmpty()) {
            index = 1;
        } else {
            index = Integer.parseInt(strIndex);
        }
        if (txtSearch == null) {
            txtSearch = "";
            txtTypeCar = "";
        }
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("ROLE") != null) {
                String rentDate = (String) session.getAttribute("dateRent");
                String returnDate = (String) session.getAttribute("dateReturn");
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObject();
                }
                CarTblDAO dao = new CarTblDAO();
                CarTblDTO dto = dao.getInfoCar(idCar, rentDate, returnDate);
                cart.addItemToCart(dto);
                session.setAttribute("CART", cart);
                long numberDays = (long) session.getAttribute("numberDays");
                float total = cart.getPrice(numberDays);
                session.setAttribute("total", total);
                request.setAttribute("MESSGAE", "Product added!!!");
                urlReWriting = "SearchServlet?txtSearch=" + txtSearch + "&txtTypeCar=" + txtTypeCar + "&txtAmount=" + amount + "&index=";
                request.setAttribute("urlReWriting", urlReWriting);
            } else {
                url = context.getAttribute("loginPage").toString();
                request.setAttribute("NOTIFICATION", "Must be login!!!");
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
