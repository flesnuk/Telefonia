package gestion;

import java.io.Serializable;
import java.util.Collection;

import tarifas.Tarifa;
import llamadas.Llamada;
import facturas.*;


public class EmisionSimple implements Emision, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5719330503051154832L;
	private int nGlobal;
	
	public EmisionSimple(){
		nGlobal=1;
	}
	
	public EmisionSimple(int nGlobal){
		this.nGlobal=nGlobal;
	}
	
	public Factura emitir(Tarifa tar, Collection<Llamada> ll) {
		double tarifa = tar.getPrecio();
		double total = 0;
		for(Llamada l : ll){
			total += l.getDuracion()*tarifa;
		}
		return new Factura(nGlobal++,tar,total);
	}

}
