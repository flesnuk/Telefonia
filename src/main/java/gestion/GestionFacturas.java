package gestion;

import clientes.Cliente;
import facturas.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

import llamadas.Llamada;

public class GestionFacturas implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5626469625125022780L;
	private HashMap<Cliente, Collection<Factura>> facturas;
	private Emision emision;

	public GestionFacturas(HashMap<Cliente, Collection<Factura>> facturas) {
		super();
		this.facturas = facturas;
		emision = new EmisionCompuesta();
	}
	
	public GestionFacturas() {
		super();
		this.facturas = new HashMap<Cliente, Collection<Factura>>();
		emision = new EmisionCompuesta();
	}
	
	public Factura emitir(Cliente c,Collection<Llamada> ll,Calendar fecha , Calendar ini,Calendar fin){
		Factura f = emision.emitir(c.getTarifa(), ll);
		f.setFecha(fecha);
		f.setInicio(ini);
		f.setFinal(fin);
		Collection<Factura> fs = facturas.get(c);
		if (fs == null){
			fs = new ArrayList<Factura>();
			fs.add(f);
			facturas.put(c, fs);
		} else {
			fs.add(f);
		}
		return f;
	}
	
	public Factura getFactura(int nf){
		Collection<Collection<Factura>> temp = facturas.values();
		Collection<Factura> facs;
		Iterator<Collection<Factura>> i = temp.iterator();
		while(i.hasNext()){
			facs=i.next();
			for(Factura f : facs){
				if (f.getCodfac()==nf)
					return f;
			}
		} 
		return null;
	}
	
	public Collection<Factura> getFacturas(Cliente c) {
		return facturas.get(c);
	}

	public String toString(Cliente c) {
		StringBuilder ret = new StringBuilder();
		Collection<Factura>	fs = facturas.get(c);
		if(fs==null)
			ret.append("No hay facturas\n");
		else{
			for(Factura f : fs){
				ret.append(f.toString());
				ret.append("\n");
			}
		}
		return ret.toString();
	}
	
	public HashMap<Cliente, Collection<Factura>> getFacturas() {
		return facturas;
	}

	public void setFacturas(HashMap<Cliente, Collection<Factura>> facturas) {
		this.facturas = facturas;
	}
}
