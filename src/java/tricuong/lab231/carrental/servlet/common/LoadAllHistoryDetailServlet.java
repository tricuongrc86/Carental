/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.servlet.common;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tricuong.lab231.carrental.database.orderdetailtbl.OrderTblDetailDAO;
import tricuong.lab231.carrental.database.orderdetailtbl.OrderTblDetailDTO;
import tricuong.lab231.carrental.database.ordertbl.OderTblDTO;
import tricuong.lab231.carrental.database.ordertbl.OrderTblDAO;

/**
 *
 * @author TriCuong
 */
@WebServlet(name = "LoadAllHistoryDetailServlet", urlPatterns = {"/LoadAllHistoryDetailServlet"})
public class LoadAllHistoryDetailServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoadAllHistoryDetailServlet.class);

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
        String url = context.getAttribute("userPage").toString();
        try {
            String idOrder = request.getParameter("txtIdOrder");
            OrderTblDetailDAO dao = new OrderTblDetailDAO();
            OrderTblDAO orderDAO = new OrderTblDAO();
            OderTblDTO orderDTO = orderDAO.getBill(idOrder);
            List<OrderTblDetailDTO> list = dao.getAllHistory(idOrder);
            if (list.isEmpty()) {
                request.setAttribute("NOTIFICATION", "Not have record!!!");
            } else {
                url = context.getAttribute("historyDetail").toString();
                request.setAttribute("HISTORYHD", list);
                request.setAttribute("OD", orderDTO);

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
