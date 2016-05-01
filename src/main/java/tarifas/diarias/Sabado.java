package tarifas.diarias;

import java.util.Calendar;

import tarifas.Tarifa;
import tarifas.TarifaDiaria;

public class Sabado extends TarifaDiaria{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Sabado(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, Calendar.SATURDAY);
	}
	
	public String toString() {
		return super.toString() + "Sabado [" + super.getPrecio() + "]";
	}
}

