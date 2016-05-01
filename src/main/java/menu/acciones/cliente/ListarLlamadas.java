package menu.acciones.cliente;

import menu.Core;
import menu.acciones.Accion;

public class ListarLlamadas implements Accion{
	public void accion() {
		System.out.println(Core.llamadas());
	}
}
