package view;

import java.util.Calendar;

import excepciones.ClienteNoSeleccionadoException;
import excepciones.FechaInvalidaException;

public interface InformaVista {

	void nuevoCliente();

	void nuevaLlamada() throws ClienteNoSeleccionadoException;

	void nuevaFactura() throws ClienteNoSeleccionadoException;

	Calendar getFechaInicio() throws FechaInvalidaException;

	Calendar getFechaFin() throws FechaInvalidaException;

}