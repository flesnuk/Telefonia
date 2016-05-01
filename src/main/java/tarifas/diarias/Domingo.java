package tarifas.diarias;

import java.util.Calendar;

import tarifas.Tarifa;
import tarifas.TarifaDiaria;

public class Domingo extends TarifaDiaria{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Domingo(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, Calendar.SUNDAY);
	}
	
	public String toString() {
		return super.toString() + "Domingo [" + super.getPrecio() + "]";
	}
}
