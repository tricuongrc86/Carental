/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.servlet.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
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
import tricuong.lab231.carrental.database.registrationtbl.RegistrationTblDAO;
import tricuong.lab231.carrental.database.registrationtbl.RegistrationTblDTO;
import tricuong.lab231.carrental.object.RegistrationError;
import tricuong.lab231.carrental.sendmail.JavaMailUtil;

/**
 *
 * @author TriCuong
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SignUpServlet.class);

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
        String url = "xacthuc.jsp";
        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        String phone = request.getParameter("txtPhone");
        String password = request.getParameter("txtPassword");
        String repassword = request.getParameter("txtRePassword");
        String address = request.getParameter("txtAddress");
        ServletContext context = request.getServletContext();
        try {
            RegistrationError err = new RegistrationError();
            RegistrationTblDAO dao = new RegistrationTblDAO();
            boolean checkErr = false;

            if (email.isEmpty()) {
                err.setEmailEmpty("*Email must be fill");
                checkErr = true;
            } else if (!Ultilities.checkEmailFormat(email)) {
                err.setEmailFormat("*Email not valid!Format example: tricuong@example.com");
                checkErr = true;
            } else if (dao.checkExistedEmail(email)) {
                err.setEmailExisted("*Email existed");
                checkErr = true;
            }
            if (address.isEmpty()) {
                err.setAddressEmpty("*Address must be fill");
                checkErr = true;
            }
            if (name.isEmpty()) {
                err.setNameEmpty("*Name must be fill");
                checkErr = true;
            }
            if (phone.isEmpty()) {
                err.setPhoneEmpty("*Phone must be fill");
                checkErr = true;
            }
            if (!Ultilities.checkPhone(phone)) {
                err.setPhoneFormat("*Phone must be have 10 number");
                checkErr = true;
            }
            if (password.isEmpty() || repassword.isEmpty()) {
                err.setPasswordErr("*Password and re-password must be fill");
                checkErr = true;
            } else if (!password.equals(repassword)) {
                err.setPasswordErr("*Password and re-password must be same");
                checkErr = true;
            }

            if (checkErr) {
                request.setAttribute("ERR", err);
                request.setAttribute("NOTIFICATION", "Error!");
                url = context.getAttribute("loginPage").toString();
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                Date dateCreate = new Date();
                String strdateCreate = formatter.format(dateCreate);
                int code = ThreadLocalRandom.current().nextInt(10000, 99999);
                RegistrationTblDTO dto = new RegistrationTblDTO(email, password, name, phone, address, true, "New", strdateCreate, String.valueOf(code));
                dao.createAcc(dto);
                JavaMailUtil.sendMail(email, String.valueOf(code));
                //Luu ma code trong database vs trang thai la new
                url = context.getAttribute("verifyPage").toString();
                request.setAttribute("signup", "signup");
                HttpSession session = request.getSession();
                session.setAttribute("ROLE", "New");
                session.setAttribute("NAME", name);
                session.setAttribute("EMAIL", email);
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
