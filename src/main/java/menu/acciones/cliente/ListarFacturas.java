package menu.acciones.cliente;

import menu.Core;
import menu.acciones.Accion;

public class ListarFacturas implements Accion{
	public void accion() {
		System.out.println(Core.facturas());
	}
}
