package menu.acciones;

import java.util.Calendar;

import input.Input;
import input.SimpleInput;
import menu.Core;

public class ClientesEntreFechas implements Accion {
	public void accion() {
		Calendar ini, fin;
		Input i = new SimpleInput();
		ini = i.getFechaV2();
		fin = i.getFechaV2();
		System.out.println(Core.clientesEntre(ini, fin));
	}
}
