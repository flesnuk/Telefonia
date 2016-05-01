package clientes;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import tarifas.Tarifa;

public class FabricaClientesTest {

	@Test
	public void testGetPersona() {
		Cliente p1 = new Persona("3","Hector","Garcia",new Direccion("46",1351,"Huesca","Att"),"a",new GregorianCalendar(),new Tarifa(4));
		Cliente p2 = new FabricaClientes().getPersona("3","Hector","Garcia","46",1351,"Huesca","Att","a",new GregorianCalendar(),new Tarifa(4));
		assertEquals(p1.toString(), p2.toString());
	}

	@Test
	public void testGetEmpresa() {
		Cliente e1 = new Empresa("3","Hector",new Direccion("46",1351,"Huesca","Att"),"a",new GregorianCalendar(),new Tarifa(4));
		Cliente e2 = new FabricaClientes().getEmpresa("3","Hector","46",1351,"Huesca","Att","a",new GregorianCalendar(),new Tarifa(4));
		assertEquals(e1.toString(), e2.toString());
	}

}
