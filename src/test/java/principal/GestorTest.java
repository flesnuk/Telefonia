package principal;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import llamadas.Llamada;

import org.junit.Test;
import org.junit.BeforeClass;

import tarifas.Tarifa;
import es.uji.www.*;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.CodigoFacturaException;
import excepciones.OrdenFechasException;
import facturas.Factura;
import clientes.Cliente;
import clientes.Direccion;
import clientes.Persona;

public class GestorTest {
	static GeneradorDatosINE nie;
	static Cliente c1, c2, c3;
	static Llamada l1, l2;
	static Gestor g;
	static Collection<Cliente> cs;
	static Collection<Llamada> ls;
	@BeforeClass
	public static void init() {
		g = new Gestor();
		nie = new GeneradorDatosINE();		
		String provincia = nie.getProvincia();
		c1 = new Persona("1111X",
				nie.getNombreHombre(),
				nie.getApellido(),
				new Direccion("N",12550,provincia,nie.getPoblacion(provincia)),
				"a@ej.com",
				new GregorianCalendar(1995,4,15),
				new Tarifa(4)
				);
		c2 = new Persona("2222D",
				nie.getNombreHombre(),
				nie.getApellido(),
				new Direccion("2",15015,provincia,nie.getPoblacion(provincia)),
				"b@ej.com",
				new GregorianCalendar(2001,8,25),
				new Tarifa(10)
				);
		c3 = new Persona(nie.getNIF(),
				nie.getNombreHombre(),
				nie.getApellido(),
				new Direccion("3",45550,provincia,nie.getPoblacion(provincia)),
				"c@ej.com",
				new GregorianCalendar(2010,5,22),
				new Tarifa(15)
				);
		cs = new ArrayList<Cliente>();
		cs.add(c1);cs.add(c2);cs.add(c3);
		l1 = new Llamada(new GregorianCalendar(2010,5,22), 27);
		l2 = new Llamada(new GregorianCalendar(2005,5,22), 27);
		ls = new ArrayList<Llamada>();
		ls.add(l1);ls.add(l2);
	}
	
	
	@Test
	public void testAdd() throws ClienteYaExisteException {
		
		g.add(c1);
		g.add(c2);
		g.add(c3);
		if (! cs.containsAll(g.clientes()))
			fail();
	}

	@Test
	public void testRemove() throws ClienteNoEncontradoException {
		g.remove("1111X");
		cs.remove(c1);
		if (! cs.containsAll(g.clientes()))
			fail();
	}

	@Test
	public void testCambiar() throws ClienteNoEncontradoException {
		c2.setTarifa(new Tarifa(3));
		g.cambiar("2222D", new Tarifa(3));
		if (! cs.containsAll(g.clientes()))
			fail();
	}

	@Test
	public void testCliente() throws ClienteNoEncontradoException {
		assertEquals(g.cliente("2222D").toString(),c2.toString());
	}

	@Test
	public void testFactura() throws OrdenFechasException {
		g.select(c3);
		g.addLlamada(l1);
		g.addLlamada(l2);
		Factura f =
				g.emitir(new GregorianCalendar(2005,4,15), new GregorianCalendar(1995,4,15), new GregorianCalendar(2015,4,15));
		try {
			assertEquals(f, g.factura(1));
		} catch (CodigoFacturaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testClientes() throws ClienteYaExisteException {
		g.add(c1);
		g.add(c2);
		g.add(c3);		
		if (! cs.containsAll(g.clientes()))
			fail();
	}

	@Test
	public void testLlamadas() throws ClienteNoSeleccionadoException {
		g.select(c3);
		g.addLlamada(l1);
		g.addLlamada(l2);
		if (! ls.containsAll(g.llamadas()))
			fail();
	}

	@Test
	public void testFacturas() throws OrdenFechasException {
		g.select(c3);
		g.addLlamada(l1);
		g.addLlamada(l2);
		g.emitir(new GregorianCalendar(2005,4,15), new GregorianCalendar(1995,4,15), new GregorianCalendar(2015,4,15));
		
	}

	@Test
	public void testSelect() throws ClienteNoSeleccionadoException {
		g.select(c3);
		assertEquals(c3, g.getActual());	
	}

	@Test
	public void testAddLlamada() throws ClienteNoSeleccionadoException {
		g.select(c3);
		g.addLlamada(l1);
		g.addLlamada(l2);
		if (! ls.containsAll(g.llamadas()))
			fail();
		
	}

}
