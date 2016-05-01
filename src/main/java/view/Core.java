package view;

import modelo.Modelo;
import controlador.Controlador;

public class Core {

	public static void main(String[] args) {
		
		      	
	        	Controlador controlador = new Controlador();
	    		Ventana vista = new Ventana();
	    		Modelo modelo = new Modelo();
	        	
	    		modelo.setVista(vista);
	            vista.setModelo(modelo);
	            vista.setControlador(controlador);
	            controlador.setModelo(modelo);
	            controlador.setVista(vista);
	            
	            vista.creaGUI();
	         
	}

}
