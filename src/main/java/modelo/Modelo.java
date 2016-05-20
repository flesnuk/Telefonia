package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.JFileChooser;

import llamadas.Llamada;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.ClienteYaExisteException;
import excepciones.FechaInvalidaException;
import excepciones.OrdenFechasException;
import facturas.Factura;
import principal.Gestor;
import tarifas.Tarifa;
import view.Ventana;

public class Modelo {
	private Gestor g ;
	private Ventana vista;
	private Collection<Cliente> clientesFiltrados;
	private Collection<Llamada> llamadasFiltrados;
	private Collection<Factura> facturasFiltrados;
	
	public Modelo() throws ClassNotFoundException, IOException{
		clientesFiltrados=null;
		llamadasFiltrados=null;
		facturasFiltrados=null;
		leer();		
	}
	
	public void setVista(Ventana vista) {
        this.vista = vista;
    }
	
	public void escribir() throws IOException{
		FileOutputStream fos = new FileOutputStream("gestor.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(g);
		oos.close();
		
	}
	
	public void leer() throws ClassNotFoundException, IOException{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("gestor.bin");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero aún no generado");
			g = new Gestor();
		}
		if(fis!=null){
			ObjectInputStream ois = new ObjectInputStream(fis);
			g = (Gestor)ois.readObject();			
			ois.close();	
			g.select(null);
		}	

	}
	
	public File leerArchivo(){
		JFileChooser file=new JFileChooser();
		file.showOpenDialog(null);
		return file.getSelectedFile();		
	}
	
	public void anyadePersona(Cliente p) throws ClienteYaExisteException{
		g.add(p);		
		vista.nuevoCliente();
	}
	
	public Cliente getCliente(String NIF) throws ClienteNoEncontradoException{
		return g.cliente(NIF);
	}
	
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
	
	public void anyadeLlamada(Llamada l) throws ClienteNoSeleccionadoException{
		if(l!=null)
			g.addLlamada(l);
		vista.nuevaLlamada();
	}
	
	public Collection<Llamada> getLlamadas() throws ClienteNoSeleccionadoException{
		if(llamadasFiltrados!=null)
			return llamadasFiltrados;
		return g.llamadas();
	}
	
	public Collection<Cliente> getClientes(){
		if(clientesFiltrados!=null)
			return clientesFiltrados;
		return g.clientes();
	}
	
	public Collection<Factura> getFacturas() throws ClienteNoSeleccionadoException{
		if(facturasFiltrados!=null)
			return facturasFiltrados;
		return g.facturas();
	}
	
	public void emite(Calendar fecha, Calendar ini, Calendar fin) throws OrdenFechasException, ClienteNoSeleccionadoException{
		g.emitir(fecha, ini, fin);
		vista.nuevaFactura();
	}

	public void cambiarTarifa(Tarifa nuevaTarifa) throws ClienteNoSeleccionadoException {
		g.getActual().setTarifa(nuevaTarifa);		
	}

	public void borrarCliente() throws ClienteNoEncontradoException, ClienteNoSeleccionadoException {
		g.remove(g.getActual().getNIF());	
		vista.nuevoCliente();
	}

	public void filtraClientes() throws FechaInvalidaException {
		clientesFiltrados=g.listarClientesEntre(vista.getFechaInicio(), vista.getFechaFin());
		vista.nuevoCliente();
	}

	public void filtraLlamadas() throws ClienteNoSeleccionadoException, FechaInvalidaException {
		llamadasFiltrados=g.listarLlamadasEntre(vista.getFechaInicio(), vista.getFechaFin());	
		vista.nuevaLlamada();
	}

	public void filtraFacturas() throws ClienteNoSeleccionadoException, FechaInvalidaException {
		facturasFiltrados=g.listarFacturasEntre(vista.getFechaInicio(), vista.getFechaFin());
		vista.nuevaFactura();
	}

	public void deshacerFiltraClientes() {
		clientesFiltrados=null;
		vista.nuevoCliente();
	}

	public void deshacerFiltraLlamadas() throws ClienteNoSeleccionadoException {
		llamadasFiltrados=null;
		vista.nuevaLlamada();
	}

	public void deshacerFiltraFacturas() throws ClienteNoSeleccionadoException {
		facturasFiltrados=null;
		vista.nuevaFactura();
	}
	
	
}
