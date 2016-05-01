package tarifas;

import java.util.Calendar;

public class TarifaDiaria extends Tarifa {
	private static final long serialVersionUID = 8215983900468070553L;
	
	private Tarifa tar;
	private int dia;

	public TarifaDiaria(Tarifa tarifa, double nuevo, int dia) {
		super(nuevo);
		tar = tarifa;	
		this.dia=dia;
	}

	@Override
	public double getPrecio(Calendar fecha) {
		if(fecha.get(Calendar.DAY_OF_WEEK)!=dia)
			return tar.getPrecio(fecha);
		if (tar.getPrecio() < super.getPrecio())
			return tar.getPrecio(fecha);
		else
			return super.getPrecio();
	}

	@Override
	public String toString() {
		return tar.toString() + "\n+ Tarifa ";
	}

}
