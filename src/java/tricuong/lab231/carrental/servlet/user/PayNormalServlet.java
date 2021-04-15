/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.servlet.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import tricuong.lab231.carrental.database.cartbl.CarTblDTO;
import tricuong.lab231.carrental.database.orderdetailtbl.OrderTblDetailDAO;
import tricuong.lab231.carrental.database.orderdetailtbl.OrderTblDetailDTO;
import tricuong.lab231.carrental.database.ordertbl.OderTblDTO;
import tricuong.lab231.carrental.database.ordertbl.OrderTblDAO;
import tricuong.lab231.carrental.object.CartObject;

/**
 *
 * @author TriCuong
 */
@WebServlet(name = "PayNormalServlet", urlPatterns = {"/PayNormalServlet"})
public class PayNormalServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PayNormalServlet.class);

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
        String url = "";
        try {
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null || cart.getSize() == 0) {
                url = (String) context.getAttribute("userPage");
                request.setAttribute("NOTIFICATION", "Not have car to pay");
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                Date dateCreate = new Date();
                String dateOrder = formatter.format(dateCreate);
                OrderTblDAO orderDAO = new OrderTblDAO();
                int maxid = orderDAO.getMaxId();
                String idOrder = "OD-" + String.valueOf(maxid);
                float total = (float) session.getAttribute("total");
                String email = (String) session.getAttribute("EMAIL");
                OderTblDTO orderDTO = new OderTblDTO(idOrder, email, total, dateOrder);
                orderDAO.createBill(orderDTO);
                Map<CarTblDTO, Integer> items = cart.getItems();
                Set<CarTblDTO> listCar = items.keySet();

                String rentDate = (String) session.getAttribute("dateRent");
                String returnDate = (String) session.getAttribute("dateReturn");
                long numberDays = (long) session.getAttribute("numberDays");
                int count = 1;
                OrderTblDetailDTO dtoDetail;
                OrderTblDetailDAO daoDetail = new OrderTblDetailDAO();
                for (CarTblDTO car : listCar) {
                    String idOderDetail = idOrder + "-" + String.valueOf(count);
                    count++;
                    String idCar = car.getCarID();
                    int quantity = items.get(car);
                    float priceBillDT = (float) quantity * (float) numberDays * (float) car.getPrice();
                    dtoDetail = new OrderTblDetailDTO(idOderDetail, idCar, quantity, priceBillDT, idOrder, rentDate, returnDate);
                    daoDetail.createBillDetail(dtoDetail);
                }
                //Xóa giỏ hàng và tổng giỏ hàng
                session.removeAttribute("CART");
                session.removeAttribute("total");
                //Truyền thông tin sang orderSuccess
                request.setAttribute("orderID", idOrder);
                url = context.getAttribute("completeOrder").toString();
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
