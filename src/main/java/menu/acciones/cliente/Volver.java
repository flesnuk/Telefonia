package menu.acciones.cliente;

import menu.Core;
import menu.acciones.Accion;

public class Volver implements Accion{
	public void accion() {
		Core.leave();
	}
}
