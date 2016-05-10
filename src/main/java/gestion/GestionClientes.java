package gestion;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import tarifas.Tarifa;
import clientes.Cliente;
import excepciones.ClienteYaExisteException;

public class GestionClientes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2313938271631342148L;
	private Collection<Cliente> clientes;

	public GestionClientes(Collection<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}
	
	public GestionClientes() {
		super();
		this.clientes = new TreeSet<Cliente>();
	}

	public boolean add(Cliente cl) throws ClienteYaExisteException{	
		if(clientes.contains(cl))
			throw new ClienteYaExisteException();
		return clientes.add(cl);
	}
	
	public boolean remove(Cliente cl){
		return clientes.remove(cl);
	}
	
	public void cambiarTarifa(Cliente cl, double nueva){
		cl.setTarifa(new Tarifa(nueva));
	}
	
	public Cliente getCliente(String NIF) {
		Cliente ret;
		Iterator<Cliente> i = clientes.iterator();
		while(i.hasNext()){
			ret=i.next();
			if(ret.getNIF().equals(NIF))
				return ret;
		} 
		throw new NoSuchElementException();
	}
	
	public String toString() {
		StringBuilder ret = new StringBuilder();
		Collection<Cliente>	cs = clientes;
		if(cs==null)
			ret.append("No hay clientes\n");
		else{
			for(Cliente c : cs){
				ret.append(c.toString());
				ret.append("\n");
			}
		}
		return ret.toString();
	}

	public void setClientes(Collection<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}
	
	
	
	
}
