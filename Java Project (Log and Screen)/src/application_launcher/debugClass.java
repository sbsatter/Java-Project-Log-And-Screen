package application_launcher;

import java.util.Calendar;

public class debugClass {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String time=Calendar.getInstance().getTime().toString();
		time=time.replace(' ', '_');
		time=time.replace(':', '-');
		System.out.println(time);
		Thread.sleep(3000);
		time=Calendar.getInstance().getTime().toString();
		time=time.replace(' ', '_');
		time=time.replace(':', '-');
		System.out.println(time);
	}

}
