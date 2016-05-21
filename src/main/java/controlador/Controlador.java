package controlador;

import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.FechaInvalidaException;

public interface Controlador {

	void anyadePersona() throws ClienteYaExisteException;

	void anyadeLlamada() throws ClienteNoSeleccionadoException,
			FechaInvalidaException;

	void cambiarTarifa() throws ClienteNoSeleccionadoException;

	Cliente buscarCliente() throws ClienteNoEncontradoException;

	void borrarCliente() throws ClienteNoEncontradoException,
			ClienteNoSeleccionadoException;

	void escribir();

	void cargar();

	void filtraClientes() throws FechaInvalidaException;

	void filtraLlamadas() throws ClienteNoSeleccionadoException,
			FechaInvalidaException;

	void filtraFacturas() throws ClienteNoSeleccionadoException,
			FechaInvalidaException;

	void deshacerFiltraClientes();

	void deshacerFiltraLlamadas() throws ClienteNoSeleccionadoException;

	void deshacerFiltraFacturas() throws ClienteNoSeleccionadoException;

}