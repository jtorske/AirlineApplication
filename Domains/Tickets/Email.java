package Domains.Tickets;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
    private String subject;
    private String body;

    public Email(String subjectLine, String body){
        this.subject = subjectLine;
        this.body = body;
    }

    public void send(String recipient) throws MessagingException{
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String accountEmail = "group19airlines@gmail.com";
        String accountPassword = "lgfh ihkj kjvi zuoq";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accountEmail, accountPassword);
            }
        });

        Message message = createMessage(session, accountEmail, recipient);

        Transport.send(message);
        System.out.println("Success!");
    }

    private Message createMessage(Session session, String fromAddress, String toAddress){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject(this.subject);
            message.setText(this.body);
            return message;
        } catch (MessagingException mex){
            mex.printStackTrace();
        }
        return null;
    }
}
