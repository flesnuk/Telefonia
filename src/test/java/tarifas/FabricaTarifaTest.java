package tarifas;

import static org.junit.Assert.*;

import org.junit.Test;

import tarifas.diarias.Domingo;
import tarifas.franjas.Tarde;

public class FabricaTarifaTest {

	@Test
	public void testGetTarifa() {
		assertEquals(new Tarde(new Tarifa(4),2).toString(),
				new FabricaTarifas().getTarifa(new Tarifa(4),2,TipoTarifa.TARDE).toString());
		
		assertEquals(new Domingo(new Tarifa(6),3).toString(),
				new FabricaTarifas().getTarifa(new Tarifa(6),3,TipoTarifa.DOMINGO).toString());
	}

}
