package principal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import tarifas.Tarifa;
import excepciones.*;
import facturas.*;
import llamadas.*;
import clientes.*;
import gestion.*;

public class Gestor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9039190881264443870L;
	private GestionClientes gc;
	private GestionFacturas gf;
	private GestionLlamadas gl;
	private Cliente actual;
	public Gestor() {
		super();
		this.gc = new GestionClientes();
		this.gf = new GestionFacturas();
		this.gl = new GestionLlamadas();		
	}
	
	public static <T extends TieneFecha> Collection<T> filtrar(Collection<T> dat, 
			Calendar inicio, Calendar fin){
		Collection<T> ret = new ArrayList<T>();
		if (dat==null)
			return ret;
		for (T e : dat){
			if(e.getFecha().after(inicio)  &&  e.getFecha().before(fin)){
				ret.add(e);
			}
		}
		
		return ret;
	}
	
	public void add(Cliente c) throws ClienteYaExisteException{		
		gc.add(c);		
	}
	
	public Factura emitir(Calendar fecha,Calendar ini,Calendar fin) throws OrdenFechasException{
		if(ini.after(fin)) throw new OrdenFechasException();
		Collection<Llamada> filtrados = filtrar(gl.getLlamadas(actual), ini, fin);
		return gf.emitir(actual, filtrados,fecha,ini,fin);
	}
	
	public Collection<Factura> listarFacturasEntre(Calendar ini,Calendar fin){
		return filtrar(gf.getFacturas(actual),ini,fin);		
	}
	
	public Collection<Llamada> listarLlamadasEntre(Calendar ini,Calendar fin){
		return filtrar(gl.getLlamadas(actual),ini,fin);	
	}
	
	public Collection<Cliente> listarClientesEntre(Calendar ini,Calendar fin){
		return filtrar(gc.getClientes(),ini,fin);	
	}
	
	public void remove(String NIF) throws ClienteNoEncontradoException{
		Cliente c = gc.getCliente(NIF);
		gc.remove(c);
		gf.getFacturas().remove(c);
		gl.getLlamadas().remove(c);
		actual=null;
	}
	
	public void cambiar(String NIF, Tarifa nueva) throws ClienteNoEncontradoException{
		gc.getCliente(NIF).setTarifa(nueva);		
	}
	
	public Cliente cliente(String NIF) throws ClienteNoEncontradoException{
		return gc.getCliente(NIF);		
	}
	
	public Factura factura(int n){
		return gf.getFactura(n);		
	}
	
	public Collection<Cliente> clientes(){
		return gc.getClientes();		
	}
	
	public Collection<Llamada> llamadas() throws ClienteNoSeleccionadoException{
		if (actual == null)
			throw new ClienteNoSeleccionadoException();
		return gl.getLlamadas(actual);		
	}	
	
	public Collection<Factura> facturas() throws ClienteNoSeleccionadoException{
		if (actual == null)
			throw new ClienteNoSeleccionadoException();
		return gf.getFacturas(actual);		
	}
	
	public void select(Cliente c){		
		actual=c;
	}	
	
	public void leave(){		
		actual = null;
	}
	
	public void addLlamada(Llamada l){		
		gl.add(actual, l);
	}

	public Cliente getActual() throws ClienteNoSeleccionadoException {
		if (actual==null)
			throw new ClienteNoSeleccionadoException();
		return actual;
	}
	
	
	
}
