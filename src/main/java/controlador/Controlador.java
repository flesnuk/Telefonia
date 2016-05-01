package controlador;

import llamadas.Llamada;
import modelo.Modelo;
import view.Ventana;
import clientes.Cliente;
import excepciones.ClienteNoSeleccionadoException;

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
	    Cliente p = vista.getPersona();
		modelo.anyadePersona(p);
	}
	
	public void anyadeLlamada() throws ClienteNoSeleccionadoException {	
		Llamada l = vista.getLlamada();
		modelo.anyadeLlamada(l);
	}
}
