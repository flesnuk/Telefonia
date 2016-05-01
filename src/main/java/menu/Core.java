package menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import principal.Gestor;
import tarifas.Tarifa;
import utilidades.CollectionToString;
import clientes.Cliente;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.OrdenFechasException;
import facturas.Factura;
import llamadas.Llamada;
import menu.AccionesMenu;


public class Core {
	private static Gestor g = new Gestor();
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		leer();
		new AccionesMenu().ejecuta();
		escribir();
	}
	
	public static void emitir(Calendar fecha, Calendar ini, Calendar fin){
		try {
			g.emitir(fecha,ini,fin);
		} catch (OrdenFechasException e) {
			e.printStackTrace();
		}
	}
	
	public static void add(Cliente c){
		g.add(c);
	}
	
	public static void remove(String NIF){
		g.remove(NIF);
	}
	
	public static void cambiar(String NIF, double nueva){
		g.cambiar(NIF, new Tarifa(nueva));
	}
	
	public static Cliente cliente(String NIF){
		return g.cliente(NIF);
	}
	
	public static Factura factura(int n){
		return g.factura(n);
	}
	
	public static String clientes(){
		return CollectionToString.toString(g.clientes());
	}
	
	public static void select(String NIF){
		g.select(g.cliente(NIF));
	}
	
	public static void addLlamada(Llamada l){
		g.addLlamada(l);
	}
	
	public static String llamadas(){
		try {
			return CollectionToString.toString(g.llamadas());
		} catch (ClienteNoSeleccionadoException e) {			
			return "Cliente no seleccionado";
		}
	}
	
	public static String facturas(){
		try {
			return CollectionToString.toString(g.facturas());
		} catch (ClienteNoSeleccionadoException e) {
			return "Cliente no seleccionado";
		}
	}
	
	public static String llamadasEntre(Calendar ini, Calendar fin){
		return CollectionToString.toString(g.listarLlamadasEntre(ini, fin));
	}
	
	public static String facturasEntre(Calendar ini, Calendar fin){
		return CollectionToString.toString(g.listarFacturasEntre(ini, fin));
	}
	
	public static String clientesEntre(Calendar ini, Calendar fin){
		return CollectionToString.toString(g.listarClientesEntre(ini, fin));
	}
	
	public static void escribir() throws IOException{
		FileOutputStream fos = new FileOutputStream("gestor.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(g);
		oos.close();
		
	}
	
	public static void leer() throws ClassNotFoundException, IOException{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("gestor.bin");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero aún no generado");
		}
		if(fis!=null){
			ObjectInputStream ois = new ObjectInputStream(fis);
			g = (Gestor)ois.readObject();
			ois.close();	
		}	

	}
	
	
	public static void leave(){
		g.leave();
	}
	
}
