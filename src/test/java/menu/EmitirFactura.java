package menu;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import llamadas.Llamada;

import org.junit.Before;
import org.junit.Test;

import principal.Gestor;
import clientes.Cliente;
import clientes.Persona;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.OrdenFechasException;
import facturas.Factura;
import tarifas.Tarifa;
import utilidades.CollectionToString;

public class EmitirFactura {
	Cliente c;
	Gestor g;
	@Before
	public void init() {
		g = new Gestor();
		c = new Persona("463737","Juan",Calendar.getInstance(),new Tarifa(1));
		try {
			g.add(c);
		} catch (ClienteYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmitir() throws OrdenFechasException, ClienteNoSeleccionadoException, ClienteNoEncontradoException {
		g.select(g.cliente("463737"));
		g.addLlamada(new Llamada(new GregorianCalendar(2015,5,15), 4));
		g.addLlamada(new Llamada(new GregorianCalendar(2010,5,15), 3));
		g.addLlamada(new Llamada(new GregorianCalendar(2005,5,15), 2));
		g.addLlamada(new Llamada(new GregorianCalendar(2000,5,15), 1));
		g.emitir(Calendar.getInstance(),new GregorianCalendar(2001,5,15), new GregorianCalendar(2011,5,15));
		Factura f = new Factura(1,new Tarifa(1),Calendar.getInstance(),5);
		assertEquals(CollectionToString.toString(g.facturas()),f.toString()+"\n");
	}

}
