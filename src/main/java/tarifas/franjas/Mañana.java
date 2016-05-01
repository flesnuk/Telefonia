package tarifas.franjas;

import tarifas.Tarifa;
import tarifas.TarifaEntreHoras;

public class Mañana extends TarifaEntreHoras{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Mañana(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, 0, 14);
	}
	
	public String toString() {
		return super.toString() + "Mañana [" + super.getPrecio() + "]";
	}
}

