package controlador;

import llamadas.Llamada;
import modelo.Modelo;
import view.Ventana;
import clientes.Cliente;
import excepciones.ClienteNoSeleccionadoException;
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
	
	public void anyadePersona() {			
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
}
