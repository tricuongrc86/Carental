/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.servlet.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tricuong.lab231.carrental.common.Ultilities;
import tricuong.lab231.carrental.database.cartbl.CarTblDAO;
import tricuong.lab231.carrental.object.CartObject;

/**
 *
 * @author TriCuong
 */
@WebServlet(name = "SetTimeServlet", urlPatterns = {"/SetTimeServlet"})
public class SetTimeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SetTimeServlet.class);

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String url = "error.jsp";
        ServletContext context = request.getServletContext();
        try {
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart != null) {
                session.removeAttribute("CART");
                session.removeAttribute("total");
            }
            String rentDate = request.getParameter("txtRentDate");
            String returnDate = request.getParameter("txtReturnDate");
            if (rentDate == null || returnDate == null) {
                rentDate = Ultilities.formatDate(new Date());
                returnDate = Ultilities.plusDate(rentDate);
//                request.setAttribute("DIS", "display");
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRent = simpleDateFormat.parse(rentDate);
            Date dateReturn = simpleDateFormat.parse(returnDate);

            long timeRent = dateRent.getTime();
            long timeReturn = dateReturn.getTime();
            long tmp = Math.abs(timeReturn - timeRent);

            long result = tmp / (24 * 60 * 60 * 1000);
            session.setAttribute("numberDays", result);
            session.setAttribute("dateRent", rentDate);
            session.setAttribute("dateReturn", returnDate);
            CarTblDAO dao = new CarTblDAO();
            List<String> listType = dao.getAllTypeCar();
            session.setAttribute("listType", listType);
            url = (String) context.getAttribute("search");
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
