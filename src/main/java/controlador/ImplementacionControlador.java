package controlador;

import java.io.IOException;

import llamadas.Llamada;
import modelo.CambioModelo;
import modelo.ImplementacionModelo;
import view.InterrogaVista;
import view.Ventana;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.FechaInvalidaException;

public class ImplementacionControlador implements Controlador {
	
	private InterrogaVista vista;
	private CambioModelo modelo;
	
	public void setModelo(ImplementacionModelo modelo) {
        this.modelo = modelo;
    }
		
	public void setVista(Ventana vista) {
        this.vista = vista;
    }
	
	@Override
	public void anyadePersona() throws ClienteYaExisteException {			
	    Cliente p = null;
	    p = vista.getPersona();
	    if (p!=null)
	    	modelo.anyadePersona(p);
	}
	
	@Override
	public void anyadeLlamada() throws ClienteNoSeleccionadoException, FechaInvalidaException {	
		Llamada l = null;
		l = vista.getLlamada();
		if (l!=null)
			modelo.anyadeLlamada(l); 			
	}
	
	@Override
	public void cambiarTarifa() throws ClienteNoSeleccionadoException{
		modelo.cambiarTarifa(vista.getNuevaTarifa());
		
	}

	@Override
	public Cliente buscarCliente() throws ClienteNoEncontradoException {
		return modelo.getCliente(vista.getNIF());
	}

	@Override
	public void borrarCliente() throws ClienteNoEncontradoException, ClienteNoSeleccionadoException {
		modelo.borrarCliente();
	}

	@Override
	public void escribir() {
		try {
			modelo.escribir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void cargar() {
		try {
			modelo.leer();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void filtraClientes() throws FechaInvalidaException {
		modelo.filtraClientes();		
	}

	@Override
	public void filtraLlamadas() throws ClienteNoSeleccionadoException, FechaInvalidaException {
		modelo.filtraLlamadas();
	}

	@Override
	public void filtraFacturas() throws ClienteNoSeleccionadoException, FechaInvalidaException {
		modelo.filtraFacturas();
	}

	@Override
	public void deshacerFiltraClientes() {
		modelo.deshacerFiltraClientes();
	}

	@Override
	public void deshacerFiltraLlamadas() throws ClienteNoSeleccionadoException {
		modelo.deshacerFiltraLlamadas();
	}

	@Override
	public void deshacerFiltraFacturas() throws ClienteNoSeleccionadoException {
		modelo.deshacerFiltraFacturas();
	}

	
	
	
}
