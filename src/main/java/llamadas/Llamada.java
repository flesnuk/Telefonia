package llamadas;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

import principal.TieneFecha;

public class Llamada implements TieneFecha, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3426109678693481121L;
	private int telefono;
	private Calendar fecha;
	private double duracion;
	
	public Llamada(Calendar fecha, double duracion) {
		super();
		Random r = new Random();
		this.telefono = r.nextInt(100000000) + 600000000;
		this.fecha = fecha;
		this.duracion = duracion;
	}

	public Llamada(int telefono, Calendar fecha, double duracion) {
		super();
		this.telefono = telefono;
		this.fecha = fecha;
		this.duracion = duracion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {	
		this.fecha = fecha;	
	}
	public int getHora() {
		return fecha.get(Calendar.HOUR_OF_DAY);
	}
	public int getMinuto() {
		return fecha.get(Calendar.MINUTE);
	}
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Llamada [telefono=" + telefono + ", fecha=" + 
				fecha.get(Calendar.DAY_OF_MONTH) + "/" +
				(fecha.get(Calendar.MONTH)+1) + "/" +
				fecha.get(Calendar.YEAR) 
				+ ", duracion=" + duracion + "]";
	}
	
	
	
}
