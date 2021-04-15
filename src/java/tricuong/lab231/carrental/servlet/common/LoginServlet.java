/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.servlet.common;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tricuong.lab231.carrental.database.registrationtbl.RegistrationTblDAO;
import tricuong.lab231.carrental.recapcha.VerifyUtils;

/**
 *
 * @author richardnguyen3599
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

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
        String userID = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");
        HttpSession session = request.getSession();
        String url = "login1.jsp";
        ServletContext context = request.getServletContext();
        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");
        boolean verify = VerifyUtils.verify(gRecaptchaResponse);
        RegistrationTblDAO dao = new RegistrationTblDAO();
        try {
//            int code = ThreadLocalRandom.current().nextInt(10000, 99999);
//            JavaMailUtil.sendMail("anhntse140215@fpt.edu.vn",String.valueOf(code) );
            if (verify) {
                if (dao.checkLoginByID(userID, password)) {
                    String name = dao.getName(userID);
                    session.setAttribute("NAME", name);
                    session.setAttribute("EMAIL", userID);
                    //kiem tra tra role
                    //neu ma new
                    //trang xac thuc
                    String role = dao.checkRole(userID);
                    session.setAttribute("ROLE", role);
                    if (role.equals("New")) {
                        url = context.getAttribute("verifyPage").toString();
                    }
                    if (role.equals("Admin")) {
                        url = context.getAttribute("setTime").toString();
                    }
                    if (role.equals("Active")) {
//                        url = context.getAttribute("userPage").toString();
                        url = context.getAttribute("setTime").toString();
                    }
                } else {
                    request.setAttribute("NOTIFICATION", "Login fail");
                }
            } else {
                request.setAttribute("VERYFY", "You missed the Captcha.");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
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
