package tarifas;

import java.util.Calendar;

public class TarifaEntreHoras extends TarifaExtra {
	private static final long serialVersionUID = 8215983900468070553L;
	
	private Tarifa tar;
	private int hora_inicio;
	private int hora_final;

	public TarifaEntreHoras(Tarifa tarifa, double nuevo, int hora_inicio, int hora_final) {
		super(tarifa,nuevo);
		tar = tarifa;	
		this.hora_inicio=hora_inicio;
		this.hora_final=hora_final;
	}

	@Override
	public double getPrecio(Calendar fecha) {
		if(! (fecha.get(Calendar.HOUR_OF_DAY)>=hora_inicio && fecha.get(Calendar.HOUR_OF_DAY)<hora_final))
			return tar.getPrecio(fecha);
		if (tar.getPrecio() < super.getPrecio())
			return tar.getPrecio(fecha);
		else
			return super.getPrecio();
	}

}

