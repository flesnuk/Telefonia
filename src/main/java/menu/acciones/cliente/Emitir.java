package menu.acciones.cliente;

import input.*;

import java.util.Calendar;
import menu.Core;
import menu.acciones.Accion;

public class Emitir implements Accion{
	public void accion() {
		Input i = new SimpleInput();
		System.out.println("Fecha emision:");
		Calendar fecha = i.getFechaV2();
		System.out.println("Fecha inicio:");
		Calendar ini = i.getFechaV2();
		System.out.println("Fecha final:");
		Calendar fin = i.getFechaV2();		
		Core.emitir(fecha,ini,fin);
		
	}
}
