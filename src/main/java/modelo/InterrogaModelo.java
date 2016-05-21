package modelo;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;

import llamadas.Llamada;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.OrdenFechasException;
import facturas.Factura;

public interface InterrogaModelo {

	Collection<Llamada> getLlamadas() throws ClienteNoSeleccionadoException;

	Collection<Cliente> getClientes();

	Collection<Factura> getFacturas() throws ClienteNoSeleccionadoException;

	void escribir() throws IOException;

	void leer() throws ClassNotFoundException, IOException;
	
	void emite(Calendar fecha, Calendar ini, Calendar fin)
			throws OrdenFechasException, ClienteNoSeleccionadoException;	

	
	Cliente getCliente(String NIF) throws ClienteNoEncontradoException;

	void selectCliente(Cliente c);

}