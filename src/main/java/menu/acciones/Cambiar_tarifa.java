package menu.acciones;

import input.*;
import menu.Core;
public class Cambiar_tarifa implements Accion {
	
	public void accion() {
		Input i = new SimpleInput();
		String nif;
		System.out.println("NIF del cliente:");
		nif = i.get();
		System.out.println("Nueva tarifa:");
		Core.cambiar(nif, i.getDouble());
	}
}
