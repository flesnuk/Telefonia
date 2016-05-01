package tarifas.franjas;

import tarifas.Tarifa;
import tarifas.TarifaEntreHoras;

public class Noche extends TarifaEntreHoras{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Noche(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, 20, 24);
	}
	
	public String toString() {
		return super.toString() + "Noche [" + super.getPrecio() + "]";
	}
}

