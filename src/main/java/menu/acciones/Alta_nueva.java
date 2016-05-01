package menu.acciones;
import clientes.FabricaClientes;
import menu.Core;
import input.*;

public class Alta_nueva implements Accion  {
	public void accion() {
		String aux;
		boolean esPersona=false;
		Input i = new SimpleInput();
		FabricaClientes fabC = new FabricaClientes();
		System.out.println("¿Persona o empresa?(P/E)");
		aux=i.get();
		switch(aux){
			case "P":
				esPersona=true;
				break;
			case "p":
				esPersona=true;
				break;
		}
		if(esPersona){
			Core.add(fabC.getPersona());
		} else {	
			Core.add(fabC.getEmpresa());
			
		}
		
	}
}
