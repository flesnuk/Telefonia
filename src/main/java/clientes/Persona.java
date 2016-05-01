package clientes;

import java.io.Serializable;
import java.util.Calendar;

import tarifas.Tarifa;

public class Persona extends Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 299393909430337218L;
	private String apellidos;	
	
	public Persona(){
		super();
	}
	
	public Persona(String nombre) {
		super(nombre);
	}
	
	public Persona(String nif, String nombre, Calendar fechaAlta, Tarifa precio) {
		super(nif, nombre, fechaAlta, precio);
	}
	
	public Persona(String nif, String nombre, String apell , Direccion direccion,
			String email, Calendar fechaAlta, Tarifa precio) {
		super(nif,nombre,direccion,email,fechaAlta,precio);
		apellidos=apell;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String ap) {
		apellidos=ap;
	}

	@Override
	public String toString() {
		return super.toString(apellidos);
	}
	
	
}
	
