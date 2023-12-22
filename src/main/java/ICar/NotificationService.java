package ICar;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class NotificationService {

    private ArrayList<User> users;
    private final String senderEmail;
    private final String senderPassword;

    public NotificationService(ArrayList<User> users, String senderEmail, String senderPassword) {
        this.users = users;
        this.senderEmail = senderEmail;
        this.senderPassword = senderPassword;
    }

    public void sendOrderConfirmationNotification(User customer, Order order) {
        String subject = "Order Confirmation";
        String message = String.format("Dear %s,\nThank you for your order! Your Order ID is %d.", customer.getName(), order.getOrderID());
        sendNotification(customer.getEmail(), subject, message);
    }

    public void sendInstallationRequestNotification(Installer installer, InstallationRequest request) {
        String subject = "New Installation Request";
        String message = String.format("Dear %s,\nYou have a new installation request. Request ID: %d.", installer.getName(), request.getId());
        sendNotification(installer.getEmail(), subject, message);
    }

    private void sendNotification(String recipientEmail, String subject, String message) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Replace with your actual email credentials
        properties.put("mail.smtp.user", senderEmail);
        properties.put("mail.smtp.password", senderPassword);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.smtp.user"), properties.getProperty("mail.smtp.password"));
            }
        });

        try {
                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(senderEmail));
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(message);

            Transport.send(mimeMessage);
            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
