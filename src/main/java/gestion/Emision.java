package gestion;

import java.util.Collection;

import tarifas.Tarifa;
import llamadas.Llamada;
import facturas.Factura;

public interface Emision {
	Factura emitir(Tarifa tar, Collection<Llamada> ll);
}
