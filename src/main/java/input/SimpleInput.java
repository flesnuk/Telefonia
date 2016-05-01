package input;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class SimpleInput implements Input {
	private Scanner sc;
	
	public String get(){
		sc=new Scanner(System.in);
		return sc.nextLine();
	}
	
	public int getInt(){
		sc=new Scanner(System.in);
		return sc.nextInt();
	}
	
	public byte getByte(){
		sc=new Scanner(System.in);
		return sc.nextByte();
	}
	
	public double getDouble(){
		sc=new Scanner(System.in);
		return sc.nextDouble();
	}
	
	public Calendar getFecha(){
		sc=new Scanner(System.in);
		Calendar calen = new GregorianCalendar();
		int y,m,d;
		System.out.println("Año:");
		y = sc.nextInt();
		System.out.println("Mes:");
		m = sc.nextInt();
		System.out.println("Dia:");
		d = sc.nextInt();
		calen.set(y,m-1,d);
		return calen;
	}
	
	public Calendar getFechaV2(){
		sc=new Scanner(System.in);
		String[] aux = new String[3];
		int y,m,d;
		Calendar calen = new GregorianCalendar();
		System.out.println("Indica fecha en formato DD/MM/AAAA");
		aux = sc.nextLine().split("/");
		d=Integer.parseInt(aux[0]);
		m=Integer.parseInt(aux[1]);
		y=Integer.parseInt(aux[2]);
		calen.set(y,m-1,d);
		return calen;
	}
	
}
