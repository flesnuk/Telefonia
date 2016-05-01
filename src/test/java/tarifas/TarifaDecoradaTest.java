package tarifas;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import tarifas.diarias.Domingo;
import tarifas.franjas.Tarde;

public class TarifaDecoradaTest {

	@Test
	public void testGetPrecioCalendar() {
		Tarifa tar = new Tarifa(4);
		tar = new Tarde(tar, 2);
		assertEquals(tar.getPrecio(new GregorianCalendar(2016,3,24,18,00)), 2f, 0);
		tar = new Domingo(tar, 1);		
		assertEquals(tar.getPrecio(new GregorianCalendar(2016,3,24,18,00)), 1f, 0);
		assertEquals(tar.getPrecio(new GregorianCalendar(2016,3,25,18,00)), 2f, 0);
		assertEquals(tar.getPrecio(new GregorianCalendar(2016,3,26,15,00)), 4f, 0);
	}

}
