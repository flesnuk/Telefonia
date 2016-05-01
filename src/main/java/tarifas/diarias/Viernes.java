package tarifas.diarias;

import java.util.Calendar;

import tarifas.Tarifa;
import tarifas.TarifaDiaria;

public class Viernes extends TarifaDiaria{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Viernes(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, Calendar.FRIDAY);
	}
	
	public String toString() {
		return super.toString() + "Viernes [" + super.getPrecio() + "]";
	}
}
