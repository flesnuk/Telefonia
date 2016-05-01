package tarifas;

import tarifas.franjas.*;
import tarifas.diarias.*;

public class FabricaTarifas {

    public FabricaTarifas() {
        super();
    }  
    
    public Tarifa getTarifa(Tarifa tar,double precio, TipoTarifa tipo) {
    	switch (tipo){
			case SALIR:
				break;
			case LUNES:
				tar = new Lunes(tar,precio);
				break;
			case MARTES:
				tar = new Martes(tar,precio);
				break;
			case MIERCOLES:
				tar = new Miercoles(tar,precio);
				break;
			case JUEVES:
				tar = new Jueves(tar,precio);
				break;
			case VIERNES:
				tar = new Viernes(tar,precio);
				break;
			case SABADO:
				tar = new Sabado(tar,precio);
				break;
			case DOMINGO:
				tar = new Domingo(tar,precio);
				break;
			case MAÑANA:
				tar = new Mañana(tar,precio);
				break;
			case TARDE:
				tar = new Tarde(tar,precio);
				break;
			case NOCHE:
				tar = new Noche(tar,precio);
				break;
		}
    	return tar;
    }
    
   
    
   
}
