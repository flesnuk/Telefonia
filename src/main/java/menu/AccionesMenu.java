package menu;

import java.util.Scanner;

public class AccionesMenu {
	private Scanner scanner = new Scanner(System.in);
	
	public void ejecuta() {
		OpcionesMenu opcion;
		OpcionesMenu2 opcion2;
		do {
			System.out.println(OpcionesMenu.getMenu());
			opcion = solicitaOpcion();
			opcion.realizaAccion();
			if (opcion == OpcionesMenu.SELECT){
				do {					
					System.out.println(OpcionesMenu2.getMenu());
					opcion2 = solicitaOpcion2();
					opcion2.realizaAccion();
				}	 while(opcion2 != OpcionesMenu2.VOLVER);
			}				
				
		} while(opcion != OpcionesMenu.SALIR);
	}

	private OpcionesMenu solicitaOpcion() {
		System.out.print("Elije una opción:");
		byte opcion = scanner.nextByte();
		return OpcionesMenu.getOpcion(opcion);
	}
	
	private OpcionesMenu2 solicitaOpcion2() {
		System.out.print("Elije una opción:");
		byte opcion = scanner.nextByte();
		return OpcionesMenu2.getOpcion(opcion);
	}
}
