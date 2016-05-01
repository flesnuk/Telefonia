package tarifas;

import java.io.Serializable;
import java.util.Calendar;

public class Tarifa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1970044009440149853L;
	private double precio;
	
	
	public Tarifa() {
		super();
		this.precio = 0;
	}
	
	public Tarifa(double precio) {
		super();
		this.precio = precio;
	}
	
	public double getPrecio() {
		return precio;
	}

	public double getPrecio(Calendar fecha) {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Tarifa [" + precio + "]";
	}
	
	
}
