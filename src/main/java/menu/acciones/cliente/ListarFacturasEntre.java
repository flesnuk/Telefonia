package menu.acciones.cliente;

import input.Input;
import input.SimpleInput;

import java.util.Calendar;

import menu.Core;
import menu.acciones.Accion;

public class ListarFacturasEntre implements Accion{
	public void accion() {
		Calendar ini, fin;
		Input i = new SimpleInput();
		ini = i.getFechaV2();
		fin = i.getFechaV2();
		System.out.println(Core.facturasEntre(ini, fin));
	}
}
