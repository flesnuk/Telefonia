package menu.acciones;

import input.*;
import menu.Core;

public class Cliente_NIF implements Accion {
	
	public void accion() {
		Input i = new SimpleInput();
		System.out.println("NIF del cliente");
		System.out.println(Core.cliente(i.get()));
	}
}
