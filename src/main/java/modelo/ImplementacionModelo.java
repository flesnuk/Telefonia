package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Collection;

import llamadas.Llamada;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.CodigoFacturaException;
import excepciones.FechaInvalidaException;
import excepciones.OrdenFechasException;
import facturas.Factura;
import principal.Gestor;
import tarifas.Tarifa;
import view.InformaVista;

public class ImplementacionModelo implements CambioModelo, InterrogaModelo {
	private Gestor g ;
	private InformaVista vista;
	private Collection<Cliente> clientesFiltrados;
	private Collection<Llamada> llamadasFiltrados;
	private Collection<Factura> facturasFiltrados;
	
	public ImplementacionModelo() throws ClassNotFoundException, IOException{
		clientesFiltrados=null;
		llamadasFiltrados=null;
		facturasFiltrados=null;
		leer();		
	}
	
	public void setVista(InformaVista vista) {
        this.vista = vista;
    }
	
	@Override
	public void escribir() throws IOException{
		FileOutputStream fos = new FileOutputStream("gestor.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(g);
		oos.close();
		
	}
	
	@Override
	public void leer() throws ClassNotFoundException, IOException{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("gestor.bin");
		} catch (FileNotFoundException e) {
			g = new Gestor();
		}
		if(fis!=null){
			ObjectInputStream ois = new ObjectInputStream(fis);
			g = (Gestor)ois.readObject();			
			ois.close();	
			g.select(null);
		}	

	}
	
	@Override
	public void anyadePersona(Cliente p) throws ClienteYaExisteException{
		g.add(p);		
		vista.nuevoCliente();
	}
	
	@Override
	public Cliente getCliente(String NIF) throws ClienteNoEncontradoException{
		return g.cliente(NIF);
	}

	@Override
	public Factura getFactura(int codFac) throws CodigoFacturaException {
		return g.factura(codFac);
	}
	
	@Override
	public void selectCliente(Cliente c){
		g.select(c);
		if(llamadasFiltrados!=null)
			try {
				filtraLlamadas();
			} catch (ClienteNoSeleccionadoException | FechaInvalidaException e) {
				e.printStackTrace();
			}
		if(facturasFiltrados!=null)
			try {
				filtraFacturas();
			} catch (ClienteNoSeleccionadoException | FechaInvalidaException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void anyadeLlamada(Llamada l) throws ClienteNoSeleccionadoException{
		if(l!=null)
			g.addLlamada(l);
		vista.nuevaLlamada();
	}
	
	@Override
	public Collection<Llamada> getLlamadas() throws ClienteNoSeleccionadoException{
		if(llamadasFiltrados!=null)
			return llamadasFiltrados;
		return g.llamadas();
	}
	
	@Override
	public Collection<Cliente> getClientes(){
		if(clientesFiltrados!=null)
			return clientesFiltrados;
		return g.clientes();
	}
	
	@Override
	public Collection<Factura> getFacturas() throws ClienteNoSeleccionadoException{
		if(facturasFiltrados!=null)
			return facturasFiltrados;
		return g.facturas();
	}
	
	@Override
	public void emite(Calendar fecha, Calendar ini, Calendar fin) throws OrdenFechasException, ClienteNoSeleccionadoException{
		g.emitir(fecha, ini, fin);
		vista.nuevaFactura();
	}

	@Override
	public void cambiarTarifa(Tarifa nuevaTarifa) throws ClienteNoSeleccionadoException {
		g.getActual().setTarifa(nuevaTarifa);	
		vista.nuevoCliente();
	}

	@Override
	public void borrarCliente() throws ClienteNoEncontradoException, ClienteNoSeleccionadoException {
		g.remove(g.getActual().getNIF());	
		vista.nuevoCliente();
	}

	@Override
	public void filtraClientes() throws FechaInvalidaException {
		clientesFiltrados=g.listarClientesEntre(vista.getFechaInicio(), vista.getFechaFin());
		vista.nuevoCliente();
	}

	@Override
	public void filtraLlamadas() throws ClienteNoSeleccionadoException, FechaInvalidaException {
		llamadasFiltrados=g.listarLlamadasEntre(vista.getFechaInicio(), vista.getFechaFin());	
		vista.nuevaLlamada();
	}

	@Override
	public void filtraFacturas() throws ClienteNoSeleccionadoException, FechaInvalidaException {
		facturasFiltrados=g.listarFacturasEntre(vista.getFechaInicio(), vista.getFechaFin());
		vista.nuevaFactura();
	}

	@Override
	public void deshacerFiltraClientes() {
		clientesFiltrados=null;
		vista.nuevoCliente();
	}

	@Override
	public void deshacerFiltraLlamadas() throws ClienteNoSeleccionadoException {
		llamadasFiltrados=null;
		vista.nuevaLlamada();
	}

	@Override
	public void deshacerFiltraFacturas() throws ClienteNoSeleccionadoException {
		facturasFiltrados=null;
		vista.nuevaFactura();
	}
	
	
}
