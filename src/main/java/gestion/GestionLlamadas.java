package gestion;

import clientes.Cliente;
import llamadas.Llamada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GestionLlamadas implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 687106363748309831L;
	private HashMap<Cliente, Collection<Llamada>> llamadas;

	public GestionLlamadas(HashMap<Cliente, Collection<Llamada>> llamadas) {
		super();
		this.llamadas = llamadas;
	}
	
	public GestionLlamadas() {
		super();
		this.llamadas = new HashMap<Cliente, Collection<Llamada>>();
	}
	
	public void add(Cliente c, Llamada l){
		Collection<Llamada> ll = new ArrayList<Llamada>();
		if(llamadas.get(c)==null){
			ll.add(l);
			llamadas.put(c, ll);
		} 
		else {
			llamadas.get(c).add(l);
		}
		
	}
	
	public Collection<Llamada> getLlamadas(Cliente c) {
		return llamadas.get(c);
	}
	
	public String toString(Cliente c) {
		StringBuilder ret = new StringBuilder();
		Collection<Llamada>	ll = llamadas.get(c);
		if(ll==null)
			ret.append("No hay llamadas\n");
		else{
			for(Llamada l : ll){
				ret.append(l.toString());
				ret.append("\n");
			}
		}
		return ret.toString();
	}

	public HashMap<Cliente, Collection<Llamada>> getLlamadas() {
		return llamadas;
	}

	public void setLlamadas(HashMap<Cliente, Collection<Llamada>> llamadas) {
		this.llamadas = llamadas;
	}
	
	
	
}
