/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.sendmail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author nds72
 */
public class JavaMailUtil {

    private static final Logger LOGGER = Logger.getLogger(JavaMailUtil.class);
    
    public static void sendMail(String recepient, String code) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountMail = "gadochannel2504@gmail.com";
        String password = "94269800Aa";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountMail, password);
            }
        });
        Message message = prepareMessage(session, myAccountMail, recepient, code);
        Transport.send(message);
        System.out.println("success!");
    }
    
    private static Message prepareMessage(Session session, String myAccountMail, String recepient, String code) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountMail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Welcome to RentalCar");
//            message.setText("Your Code : google.com.vn " + code);
            message.setContent("<h1 style=\"color:greenyellow\">\"Welcome to Rental Car</h1>\n"
                    + "        <span>Your code: " + code + "</span>", "text/html");
            return message;
        } catch (MessagingException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
}
