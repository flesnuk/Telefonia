package tarifas.diarias;

import java.util.Calendar;

import tarifas.Tarifa;
import tarifas.TarifaDiaria;

public class Martes extends TarifaDiaria{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Martes(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, Calendar.TUESDAY);
	}
	
	public String toString() {
		return super.toString() + "Martes [" + super.getPrecio() + "]";
	}
}
