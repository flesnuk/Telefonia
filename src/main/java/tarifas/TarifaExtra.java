package tarifas;

import java.util.Calendar;

abstract class TarifaExtra extends Tarifa{
	
	private static final long serialVersionUID = 8215983900468070553L;
	
	private Tarifa tar;

	public TarifaExtra(Tarifa tarifa, double nuevo) {
		super(nuevo);
		tar = tarifa;	
	}

	@Override
	public double getPrecio(Calendar fecha){
		return super.getPrecio();
	}

	@Override
	public String toString() {
		return tar.toString() + "\n+ Tarifa ";
	}
	
}
