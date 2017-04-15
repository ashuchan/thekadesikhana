package com.dev.backend.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	public static void sendMail(String name, String email, String paymentId) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.host", "m.outlook.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("sales@thekadesikhana.com","ThekaDes1Kh@n@");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sales@thekadesikhana.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Order From Theka Desi Khana");
			message.setText("Dear " + name +","+
					"\n\n Your order with Theka Desi Khana is confirmed. Click on the link below to check for status."
					+"\n\nhttp://thekadesikhana.com/resources/views/thankYou.html?payment_request_id="+paymentId+
					"\n\n Regards,\nTheka Desi Khana");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
