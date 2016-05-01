package menu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import llamadas.Llamada;

import org.junit.Before;
import org.junit.Test;

import principal.Gestor;
import tarifas.Tarifa;
import clientes.Cliente;
import clientes.Persona;
import excepciones.OrdenFechasException;
import facturas.Factura;

public class ListarEntreTest {
	private Gestor g;
	Cliente a;
	Cliente b;
	Cliente c;
	
	@Before
	 public void init() {
        g = new Gestor();
        a = new Persona("52727272","A",new GregorianCalendar(1995,4,15),new Tarifa(4));
        b = new Persona("57355373","B",new GregorianCalendar(2005,4,15),new Tarifa(5));
        c = new Persona("46433373","C",new GregorianCalendar(2015,4,15),new Tarifa(6));
    }
	
	@Test
	public void testListarClientesEntre() {
		g.add(a);
		g.add(b);
		g.add(c);
		Collection<Cliente> cc = new ArrayList<Cliente>();
		cc.add(b);
		Calendar ini = new GregorianCalendar(2000,4,15);
		Calendar fin = new GregorianCalendar(2010,4,15);
		assertEquals(cc, g.listarClientesEntre(ini, fin));
	}

	@Test
	public void testListarLlamadasEntre() {
		Calendar ini = new GregorianCalendar(2010,4,15);
		Calendar fin = new GregorianCalendar(2020,4,15);
		g.select(c);
		Llamada ax = new Llamada(new GregorianCalendar(2030,4,15), 8);
		Llamada bx = new Llamada(new GregorianCalendar(2000,4,15), 50);
		Llamada cx = new Llamada(new GregorianCalendar(2015,4,15), 45);
		g.addLlamada(ax);
		g.addLlamada(bx);
		g.addLlamada(cx);
		Collection<Llamada> ll = new ArrayList<Llamada>();
		ll.add(cx);
		assertEquals(ll, g.listarLlamadasEntre(ini, fin));
	}
	
	@Test
	public void testListarFacturasEntre() throws OrdenFechasException {
		Llamada ax = new Llamada(new GregorianCalendar(2000,4,15), 5);
		Llamada bx = new Llamada(new GregorianCalendar(2010,1,20), 1);
		Llamada cx = new Llamada(new GregorianCalendar(2030,8,15), 2);
		g.select(b);
		g.addLlamada(ax);
		g.addLlamada(bx);
		g.addLlamada(cx);
		Calendar fecha1 = new GregorianCalendar(2000,4,15);
		Calendar fecha2 = new GregorianCalendar(2010,4,15);
		Calendar fecha3 = new GregorianCalendar(2020,4,15);
		Collection<Factura> facts = new ArrayList<Factura>();
		Factura f = new Factura(2,new Tarifa(5),fecha2,40);
		facts.add(f);
		g.emitir(fecha1, new GregorianCalendar(2000,4,14), new GregorianCalendar(2031,8,15));
		g.emitir(fecha2, new GregorianCalendar(2000,4,14), new GregorianCalendar(2031,8,15));
		g.emitir(fecha3, new GregorianCalendar(2000,4,14), new GregorianCalendar(2031,8,15));
		Collection<Factura> factsEnBBDD = g.listarFacturasEntre( new GregorianCalendar(2005,4,14),
															new GregorianCalendar(2015,8,15));
		assertEquals(facts.toString(),factsEnBBDD.toString());
		
	}
	
}
