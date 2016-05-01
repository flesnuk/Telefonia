package facturas;

import java.io.Serializable;
import java.util.Calendar;

import principal.TieneFecha;
import tarifas.Tarifa;

public class Factura implements TieneFecha, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5016761837960962819L;
	private int codfac;
	private Tarifa tarifa;
	private FechaFact fecha;
	private double precio;	
	
	public Factura(int codfac) {
		super();
		this.codfac = codfac;
	}	
	
	public Factura(int codfac, Tarifa tarifa, double precio) {
		super();
		this.codfac = codfac;
		this.tarifa = tarifa;
		this.precio = precio;
		this.fecha = new FechaFact();
	}

	public Factura(int codfac, Tarifa tarifa, Calendar fecha, double precio) {
		super();
		this.codfac = codfac;
		this.tarifa = tarifa;
		this.fecha = new FechaFact(fecha);
		this.precio = precio;
	}	

	@Override
	public String toString() {
		return "Factura [codfac=" + codfac + ", tarifa=" + tarifa + ", fecha="
				+ fecha.getFecha_emision().get(Calendar.DAY_OF_MONTH) + "/" +
				(fecha.getFecha_emision().get(Calendar.MONTH)+1) + "/" +
				fecha.getFecha_emision().get(Calendar.YEAR)  + ", precio=" + precio + "]";
	}
	
	public String toString2() {
		return "Factura [codfac=" + codfac + ", tarifa=" + tarifa 
				  + ", precio=" + precio + "]";
	}

	public int getCodfac() {
		return codfac;
	}
	public void setCodfac(int codfac) {
		this.codfac = codfac;
	}
	public Tarifa getTarifa() {
		return tarifa;
	}
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	public Calendar getFecha() {
		return fecha.getFecha_emision();
	}
	public void setFecha(Calendar fecha_emision) {
		this.fecha.setFecha_emision(fecha_emision);
	}
	public Calendar getInicio() {
		return fecha.getFecha_emision();
	}
	public void setInicio(Calendar fecha_inicio) {
		this.fecha.setInicio(fecha_inicio);
	}
	public Calendar getFinal() {
		return fecha.getFin();
	}
	public void setFinal(Calendar fecha_final) {
		this.fecha.setFin(fecha_final);
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
