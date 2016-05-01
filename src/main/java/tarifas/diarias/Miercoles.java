package tarifas.diarias;

import java.util.Calendar;

import tarifas.Tarifa;
import tarifas.TarifaDiaria;

public class Miercoles extends TarifaDiaria{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Miercoles(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, Calendar.WEDNESDAY);
	}
	
	public String toString() {
		return super.toString() + "Miercoles [" + super.getPrecio() + "]";
	}
}