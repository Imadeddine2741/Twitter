package servicetools;

import java.sql.Timestamp;

import java.util.GregorianCalendar;
import java.util.UUID;


public class Functions {

	public static Timestamp getTimestamap() {
		long time=System.currentTimeMillis();
		return new java.sql.Timestamp(time);
	}
	public static String keySession(){
		UUID uid = UUID.randomUUID(); //permet de generer un key ou id aleatoire ; 
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		return uid.toString() + calendar.getTimeInMillis();	
	}
	public static long getNowMillis() {
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		return calendar.getTimeInMillis();
	}
	public static boolean moreThan30Min(long d) {
		d += 30 * 60 * 1000;
		if (d < getNowMillis())
			return true;
		return false;
	}
}
