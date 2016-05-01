package tarifas.diarias;

import java.util.Calendar;

import tarifas.Tarifa;
import tarifas.TarifaDiaria;

public class Jueves extends TarifaDiaria{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Jueves(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, Calendar.THURSDAY);
	}
	
	public String toString() {
		return super.toString() + "Jueves [" + super.getPrecio() + "]";
	}
}
