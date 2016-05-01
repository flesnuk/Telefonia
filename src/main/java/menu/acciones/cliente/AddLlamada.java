package menu.acciones.cliente;

import java.util.Calendar;
import java.util.GregorianCalendar;

import input.*;
import llamadas.Llamada;
import menu.Core;
import menu.acciones.Accion;

public class AddLlamada implements Accion{
	public void accion() {
		int telefono;
		Calendar fecha = new GregorianCalendar();
		Input i = new SimpleInput();
		System.out.println("Telefono");
		telefono = i.getInt();
		fecha = i.getFechaV2();
		System.out.println("Indica la duracion");
		Llamada l = new Llamada(telefono,fecha,i.getInt());
		Core.addLlamada(l);
	}
}


