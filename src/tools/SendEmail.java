/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entities.Equipement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tools.SendEmail;

/**
 *
 * @author Mondher bha
 */
public class SendEmail {
   
        
        public static void mail() throws SQLException, MessagingException {
        
            
      //ps.supprimerEquipement(6);

        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
      //  String myAccountEmail = "mondher.benhajammar@@esprit.tn";
        //Your gmail password
        String password = "213JMT5843";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mondher.benhajammar@@esprit.tn", password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, "mondher"+"."+"benhajammar"+"@"+"esprit"+"."+"tn", "mondher.1857@gmail.com");

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mondher"+"."+"benhajammar"+"@"+"esprit"+"."+"tn"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("mondher.1857@gmail.com"));
            message.setSubject("Information");
            String htmlCode = "<h1> un Equipement a été bien engistrer dans le Base de données de DevPros</h1> <br/>  ";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
}


  
}