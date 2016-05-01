package menu.acciones;

import menu.Core;
import input.*;

public class Select implements Accion {

	public void accion() {
		Input i = new SimpleInput();		
		System.out.println("NIF del cliente:");		
		Core.select(i.get());
	}

}
