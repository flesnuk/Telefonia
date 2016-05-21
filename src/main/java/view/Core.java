package view;

import java.io.IOException;

import modelo.ImplementacionModelo;
import controlador.ImplementacionControlador;

public class Core {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		      	
	        	ImplementacionControlador controlador = new ImplementacionControlador();
	    		Ventana vista = new Ventana();
	    		ImplementacionModelo modelo = new ImplementacionModelo();
	        	
	    		modelo.setVista(vista);
	            vista.setModelo(modelo);
	            vista.setControlador(controlador);	            
	            controlador.setModelo(modelo);
	            controlador.setVista(vista);
	            
	            vista.creaGUI();
	}

}
