package controlador;

import java.io.IOException;

import llamadas.Llamada;
import modelo.Modelo;
import view.Ventana;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.FechaInvalidaException;

public class Controlador {
	
	private Ventana vista;
	private Modelo modelo;
	
	public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
	
	public void setVista(Ventana vista) {
        this.vista = vista;
    }
	
	public void anyadePersona() throws ClienteYaExisteException {			
	    Cliente p = null;
	    p = vista.getPersona();
	    if (p!=null)
	    	modelo.anyadePersona(p);
	}
	
	public void anyadeLlamada() throws ClienteNoSeleccionadoException, FechaInvalidaException {	
		Llamada l = null;
		l = vista.getLlamada();
		if (l!=null)
			modelo.anyadeLlamada(l); 			
	}
	
	public void cambiarTarifa() throws ClienteNoSeleccionadoException{
		modelo.cambiarTarifa(vista.getNuevaTarifa());
		
	}

	public Cliente buscarCliente() throws ClienteNoEncontradoException {
		return modelo.getCliente(vista.getNIF());
	}

	public void borrarCliente() throws ClienteNoEncontradoException, ClienteNoSeleccionadoException {
		modelo.borrarCliente();
	}

	public void escribir() {
		try {
			modelo.escribir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

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
	
	
}
