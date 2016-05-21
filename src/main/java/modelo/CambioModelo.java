package modelo;


import java.io.IOException;

import llamadas.Llamada;
import tarifas.Tarifa;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.FechaInvalidaException;

public interface CambioModelo {


	void anyadePersona(Cliente p) throws ClienteYaExisteException;
	
	Cliente getCliente(String NIF) throws ClienteNoEncontradoException;

	void anyadeLlamada(Llamada l) throws ClienteNoSeleccionadoException;

	void cambiarTarifa(Tarifa nuevaTarifa)
			throws ClienteNoSeleccionadoException;

	void borrarCliente() throws ClienteNoEncontradoException,
			ClienteNoSeleccionadoException;

	void filtraClientes() throws FechaInvalidaException;

	void filtraLlamadas() throws ClienteNoSeleccionadoException,
			FechaInvalidaException;

	void filtraFacturas() throws ClienteNoSeleccionadoException,
			FechaInvalidaException;

	void escribir() throws IOException;

	void leer() throws ClassNotFoundException, IOException;

	void deshacerFiltraClientes();

	void deshacerFiltraLlamadas() throws ClienteNoSeleccionadoException;

	void deshacerFiltraFacturas() throws ClienteNoSeleccionadoException;

}