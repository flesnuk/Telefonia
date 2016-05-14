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
import excepciones.OrdenFechasException;
import facturas.Factura;
import principal.Gestor;
import tarifas.Tarifa;
import view.Ventana;

public class Modelo {
	private Gestor g ;
	private Ventana vista;
	
	public Modelo() throws ClassNotFoundException, IOException{
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
		}	

	}
	
	public File leerArchivo(){
		JFileChooser file=new JFileChooser();
		file.showOpenDialog(null);
		return file.getSelectedFile();		
	}
	
	public void anyadePersona(Cliente p) throws ClienteYaExisteException{
		g.add(p);		
		vista.nuevaEntrada();
	}
	
	public Cliente getCliente(String NIF) throws ClienteNoEncontradoException{
		return g.cliente(NIF);
	}
	
	public void selectCliente(Cliente c){
		g.select(c);
	}
	
	public void anyadeLlamada(Llamada l) throws ClienteNoSeleccionadoException{
		if(l!=null)
			g.addLlamada(l);
		vista.nuevaLlamada();
	}
	
	public Collection<Llamada> getLlamadas() throws ClienteNoSeleccionadoException{
		return g.llamadas();
	}
	
	public Collection<Cliente> getClientes(){
		return g.clientes();
	}
	
	public Collection<Factura> getFacturas() throws ClienteNoSeleccionadoException{
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
		vista.nuevaEntrada();
	}
	
	
}
