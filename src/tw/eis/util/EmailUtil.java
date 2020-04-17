package tw.eis.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tw.eis.model.Users;

public class EmailUtil {
	
	private final String FROM = "lilisbg.lee@gmail.com";
	private final String host = "smtp.gmail.com";
	private final int port = 587;
	private final String password = "qogrjraijqorhpyr";//your password
	
    public void sendResetPasswordEmail(Users uBean, String email) {
    	GenerateLinkUtil glu=new GenerateLinkUtil();
		Session session = getSession();
		MimeMessage message = new MimeMessage(session);
		try {
			message.setSubject("��^�K�X");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(FROM));
			message.setRecipient(RecipientType.TO, new InternetAddress(email));
			message.setContent("�n�ϥηs�K�X�A�ШϥΥH�U�s���ҥαK�X:<br/><a href='" + glu.generateResetPwdLink(uBean) +"'>�I�����]�K�X</a>","text/html;charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, FROM, password);
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, password);
			}
			
		});
		return session;
	}
}
