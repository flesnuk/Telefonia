package utilidades;

import java.util.Calendar;

public class FechaToString {
	public static  String toString(Calendar cal) {
		return cal.get(Calendar.DAY_OF_MONTH) + "/" +
		(cal.get(Calendar.MONTH)+1) + "/" +
		cal.get(Calendar.YEAR);
	}
}
