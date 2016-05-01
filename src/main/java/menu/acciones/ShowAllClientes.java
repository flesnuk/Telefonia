package menu.acciones;

import menu.Core;

public class ShowAllClientes implements Accion {
	
	public void accion() {		
		System.out.println(Core.clientes());	
	}
}
