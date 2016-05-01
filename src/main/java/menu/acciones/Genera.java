package menu.acciones;

import input.Input;
import input.SimpleInput;
import generadores.GeneradorClientes;

public class Genera implements Accion{
	public void accion() {
		Input i = new SimpleInput();
		System.out.println("Cuantos:");
		new GeneradorClientes(i.getInt());
	}
		
}
