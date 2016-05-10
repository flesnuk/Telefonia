package clientes;

import java.io.Serializable;
import java.util.Calendar;

import principal.TieneFecha;
import tarifas.Tarifa;
import utilidades.FechaToString;


public abstract class Cliente implements TieneFecha, Serializable, Comparable<Cliente>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6053944691362840530L;
	private String NIF;
	private String nombre;	
	private Direccion direccion;
	private String email;
	private Calendar fechaAlta;
	private Tarifa precio;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Cliente(String nif, String nombre, Calendar fechaAlta, Tarifa precio) {
		super();
		NIF = nif;
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.precio = precio;
	}

	public Cliente(String nif, String nombre, Direccion direccion,
			String email, Calendar fechaAlta, Tarifa precio) {
		super();
		NIF = nif;
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getNIF() {
		return NIF;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Calendar getFecha() {
		return fechaAlta;
	}
	
	public Tarifa getTarifa() {
		return precio;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFechaAlta(Calendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setTarifa(Tarifa precio) {
		this.precio = precio;
	}
	
	@Override
	public int compareTo(Cliente otro) {
	    return this.NIF.compareTo(otro.NIF);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NIF == null) ? 0 : NIF.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (NIF == null) {
			if (other.NIF != null)
				return false;
		} else if (!NIF.equals(other.NIF))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Cliente [NIF=" + NIF + ", nombre=" + nombre + ", direccion="
				+ direccion + ", email=" + email + ", fechaAlta=" + 
				FechaToString.toString(fechaAlta)
				+ ", tarifa=" + precio + "]";
	}
	
	public String toString(String apell) {
		return "Cliente [NIF=" + NIF + ", nombre=" + nombre + ", apellidos=" + apell + ", direccion="
				+ direccion + ", email=" + email + ", fechaAlta=" + FechaToString.toString(fechaAlta)
				+ ", tarifa=" + precio + "]";
	}
	
}
