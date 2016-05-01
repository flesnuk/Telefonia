package facturas;

import java.io.Serializable;
import java.util.Calendar;

public class FechaFact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1977686745401137927L;
	private Calendar fecha_emision;
	private Calendar inicio;
	private Calendar fin;
	
	public FechaFact() {
		super();
	}	
	
	public FechaFact(Calendar fecha_emision) {
		super();
		this.fecha_emision = fecha_emision;
	}
	public FechaFact(Calendar fecha_emision, Calendar inicio, Calendar fin) {
		super();
		this.fecha_emision = fecha_emision;
		this.inicio = inicio;
		this.fin = fin;
	}
	public Calendar getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(Calendar fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public Calendar getInicio() {
		return inicio;
	}
	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}
	public Calendar getFin() {
		return fin;
	}
	public void setFin(Calendar fin) {
		this.fin = fin;
	}
	
	
}
