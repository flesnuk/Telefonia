package tarifas;

import java.util.GregorianCalendar;

import tarifas.diarias.Domingo;
import tarifas.franjas.Tarde;

public class Testfecha {

	public static void main(String[] args) {
		Tarifa tar = new Tarifa(4);
		tar = new Tarde(tar, 2);
		tar = new Domingo(tar, 1);
		System.out.println(tar);
		System.out.println(tar.getPrecio(new GregorianCalendar(2016,3,24,16,00)));
	}

}
