package menu.acciones;

import input.*;
import menu.Core;

public class Borrar_cliente implements Accion  {
	
	public void accion() {
		Input i = new SimpleInput();
		System.out.println("NIF del cliente a eliminar:");
		Core.remove(i.get());
	}
}
