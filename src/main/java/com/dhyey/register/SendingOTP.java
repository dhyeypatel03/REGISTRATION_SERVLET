package com.dhyey.register;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class SendingOTP {
	
	

    private static final String FROM_EMAIL = "they032003@gmail.com"; // Replace with your email address
    private static final String PASSWORD = "nfvzgtuohkepohmn"; // Replace with your email password

    public static void sendEmail(String uemail, String subject, String body) {
    	
    	subject = "OTP Verification";
    	 
       
        
    	
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP host
        props.put("mail.smtp.port", "587"); // Replace with your SMTP port

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(uemail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully to " + uemail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending email to " + uemail);
        }
    }
}
