package gestion;

import java.io.Serializable;
import java.util.Collection;

import llamadas.Llamada;
import tarifas.Tarifa;
import facturas.Factura;

public class EmisionCompuesta implements Emision, Serializable {

	private static final long serialVersionUID = 2660816827266781168L;
	private int nGlobal;
	
	public EmisionCompuesta(){
		nGlobal=1;
	}
	
	public EmisionCompuesta(int nGlobal){
		this.nGlobal=nGlobal;
	}
	
	public Factura emitir(Tarifa tar, Collection<Llamada> ll) {
		double tarifa;
		double total = 0;
		for(Llamada l : ll){
			tarifa = tar.getPrecio(l.getFecha());
			total += l.getDuracion()*tarifa;
		}
		return new Factura(nGlobal++,tar,total);
	}

}
