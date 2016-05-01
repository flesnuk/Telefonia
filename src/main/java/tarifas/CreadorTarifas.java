package tarifas;

import output.Output;
import output.SimpleOutput;
import input.Input;
import input.SimpleInput;

public class CreadorTarifas {
	
	private Tarifa tar;
	private Input in;
	private Output o;
	private FabricaTarifas fabTar = new FabricaTarifas();
	
	public CreadorTarifas() {
        super();
        in = new SimpleInput();
        o = new SimpleOutput();
    }
	
	public CreadorTarifas(Input in, Output o) {
        super();
        this.in = in;
        if (o == null)
        	this.o = new SimpleOutput();
        else 
        	this.o = o;
    }
	
	private double precio(){
	    o.out("Introduce el precio: ");
	    return in.getDouble();
	}

	    
	public Tarifa getTarifa() {
		TipoTarifa opcion;
		o.out("Tarifa base: ");
		tar = new Tarifa(precio());
		do {
			o.out(TipoTarifa.getTarifas());
			opcion = solicitaOpcion();
			fabTar.getTarifa(tar,precio(), opcion);
					
			} while(opcion != TipoTarifa.SALIR);
		return tar;
	}
	
	private TipoTarifa solicitaOpcion() {
		o.out("Elije una opción: ");
		byte opcion = in.getByte();
		return TipoTarifa.getOpcion(opcion);
	}
}
