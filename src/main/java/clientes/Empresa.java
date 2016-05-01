package clientes;

import java.io.Serializable;
import java.util.Calendar;

import tarifas.Tarifa;

public class Empresa extends Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6651667110948476798L;

	public Empresa(){
		super();
	}
	
	public Empresa(String nombre) {
		super(nombre);
	}
	
	public Empresa(String nif, String nombre, Direccion direccion,
			String email, Calendar fechaAlta, Tarifa precio) {
		super(nif,nombre,direccion,email,fechaAlta,precio);		
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
