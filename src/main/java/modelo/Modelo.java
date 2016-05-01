package modelo;

import java.util.Collection;

import llamadas.Llamada;
import clientes.Cliente;
import excepciones.ClienteNoSeleccionadoException;
import principal.Gestor;
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
	
	public void anyadePersona(Cliente p){
		g.add(p);
		vista.nuevaEntrada();
	}
	
	public Cliente getCliente(String NIF){
		return g.cliente(NIF);
	}
	
	public void selectCliente(Cliente c){
		g.select(c);
	}
	
	public void anyadeLlamada(Llamada l) throws ClienteNoSeleccionadoException{
		g.addLlamada(l);
		vista.nuevaLlamada();
	}
	
	public Collection<Llamada> getLlamadas() throws ClienteNoSeleccionadoException{
		return g.llamadas();
	}
	
	public Collection<Cliente> getClientes(){
		return g.clientes();
	}
	
	
}
