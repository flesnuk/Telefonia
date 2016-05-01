package generadores;

import java.util.Calendar;
import java.util.Random;





import tarifas.Tarifa;
import clientes.Cliente;
import clientes.Persona;
import es.uji.www.*;
import menu.Core;

public class GeneradorClientes {
	public GeneradorClientes(int n) {
		super();
		ejecuta(n);
	}

	private void ejecuta(int n) {
		GeneradorDatosINE nie;
		Random r = new Random();
		for(int i = 0; i<n;i++){
			Calendar cal = Calendar.getInstance();
			nie = new GeneradorDatosINE();	
			Cliente c = new Persona(nie.getNIF(), nie.getNombre(), cal, new Tarifa(r.nextDouble()*25));
			Core.add(c);
		}
	}
}