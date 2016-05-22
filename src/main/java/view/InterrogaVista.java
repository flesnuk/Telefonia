package view;

import java.util.Calendar;

import llamadas.Llamada;
import tarifas.Tarifa;
import clientes.Cliente;
import excepciones.FechaInvalidaException;

public interface InterrogaVista {

	Cliente getPersona();

	Tarifa getNuevaTarifa();

	String getNIF();

	Calendar getFechaInicio() throws FechaInvalidaException;

	Calendar getFechaFin() throws FechaInvalidaException;

	Llamada getLlamada();

	int getCodFac();

}