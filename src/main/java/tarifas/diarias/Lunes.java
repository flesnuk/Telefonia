package tarifas.diarias;

import java.util.Calendar;

import tarifas.Tarifa;
import tarifas.TarifaDiaria;

public class Lunes extends TarifaDiaria{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Lunes(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, Calendar.MONDAY);
	}
	
	public String toString() {
		return super.toString() + "Lunes [" + super.getPrecio() + "]";
	}
}
