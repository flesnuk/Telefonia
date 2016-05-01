package clientes;

import java.io.Serializable;

public class Direccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7162683277410255325L;
	private String calle;
	private int CodPostal;
	private String provincia;
	private String poblacion;	
	
	
	public Direccion(String calle, int codPostal, String provincia,
			String poblacion) {
		super();
		this.calle = calle;
		CodPostal = codPostal;
		this.provincia = provincia;
		this.poblacion = poblacion;
	}
	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", CodPostal=" + CodPostal
				+ ", provincia=" + provincia + ", poblacion=" + poblacion + "]";
	}
	public int getCodPostal(){return CodPostal;}
	public String getProvincia(){return provincia;}
	public String getPoblacion(){return poblacion;}
	public String getCalle() {return calle;}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public void setCodPostal(int codPostal) {
		CodPostal = codPostal;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	
}
