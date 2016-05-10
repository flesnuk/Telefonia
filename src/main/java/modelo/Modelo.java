package modelo;

import java.util.Calendar;
import java.util.Collection;

import llamadas.Llamada;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.OrdenFechasException;
import facturas.Factura;
import principal.Gestor;
import tarifas.Tarifa;
import view.Ventana;

public class Modelo {
	private Gestor g ;
	private Ventana vista;
	
	public Modelo(){
		g = new Gestor();
	}
	
	public void setVista(Ventana vista) {
        this.vista = vista;
    }
	
	public void anyadePersona(Cliente p) throws ClienteYaExisteException{
		g.add(p);		
		vista.nuevaEntrada();
	}
	
	public Cliente getCliente(String NIF) throws ClienteNoEncontradoException{
		return g.cliente(NIF);
	}
	
	public void selectCliente(Cliente c){
		g.select(c);
	}
	
	public void anyadeLlamada(Llamada l) throws ClienteNoSeleccionadoException{
		if(l!=null)
			g.addLlamada(l);
		vista.nuevaLlamada();
	}
	
	public Collection<Llamada> getLlamadas() throws ClienteNoSeleccionadoException{
		return g.llamadas();
	}
	
	public Collection<Cliente> getClientes(){
		return g.clientes();
	}
	
	public Collection<Factura> getFacturas() throws ClienteNoSeleccionadoException{
		return g.facturas();
	}
	
	public void emite(Calendar fecha, Calendar ini, Calendar fin) throws OrdenFechasException, ClienteNoSeleccionadoException{
		g.emitir(fecha, ini, fin);
		vista.nuevaFactura();
	}

	public void cambiarTarifa(Tarifa nuevaTarifa) throws ClienteNoSeleccionadoException {
		g.getActual().setTarifa(nuevaTarifa);		
	}

	public void borrarCliente() throws ClienteNoEncontradoException, ClienteNoSeleccionadoException {
		g.remove(g.getActual().getNIF());	
		vista.nuevaEntrada();
	}
	
	
}
