package tarifas.franjas;


import tarifas.Tarifa;
import tarifas.TarifaEntreHoras;

public class Tarde extends TarifaEntreHoras{
	
	private static final long serialVersionUID = -2305879958597704231L;

	public Tarde(Tarifa tarifa, double nuevo) {
		super(tarifa, nuevo, 16, 20);
	}
	
	public String toString() {
		return super.toString() + "Tarde [" + super.getPrecio() + "]";
	}
}
