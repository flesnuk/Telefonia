package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;

public class MenuSimple {
	
	private Ventana vista;
	private JMenuBar menuBar;
	private FormularioCliente formCliente;
	private VentanaCambioTarifa formTarifa;	
	private VentanaBuscarCliente buscarCliente;
	private VentanaBuscarFactura ventanaBuscarFactura;
	private VentanaFiltrar filtrar;
	
	public MenuSimple(Ventana vista){
		this.vista=vista;
		inicializa();
		
	}
	
	public JMenuBar getMenu(){		
		return menuBar;
				
	}
	
	private void inicializa(){
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.getAccessibleContext().setAccessibleDescription(
		        "Hola");
		JMenuItem menuItem = new JMenuItem("Deseleccionar");
		menuItem.addActionListener(new Escuchador());
		JMenuItem menuItem2 = new JMenuItem("Añadir cliente...");
		menuItem2.addActionListener(new Escuchador());
		JMenuItem menuItem3 = new JMenuItem("Borrar cliente");
		menuItem3.addActionListener(new Escuchador());
		JMenuItem menuItem4 = new JMenuItem("Buscar cliente...");
		menuItem4.addActionListener(new Escuchador());
		JMenuItem menuItem5 = new JMenuItem("Buscar factura...");
		menuItem5.addActionListener(new Escuchador());
		JMenuItem menuItem6 = new JMenuItem("Cambiar tarifa...");
		menuItem6.addActionListener(new Escuchador());
		JMenuItem menuItem7 = new JMenuItem("Filtrar...");
		menuItem7.addActionListener(new Escuchador());
		JMenuItem menuItem8 = new JMenuItem("Guardar");
		menuItem8.addActionListener(new Escuchador());
		JMenuItem menuItem9 = new JMenuItem("Cargar");
		menuItem9.addActionListener(new Escuchador());
		
		
		menu.add(menuItem);
		menu.add(menuItem2);
		menu.add(menuItem3);
		menu.add(menuItem4);
		menu.add(menuItem5);
		menu.add(menuItem6);
		menu.add(menuItem7);
		menu.add(menuItem8);
		menu.add(menuItem9);
		menuBar.add(menu);
		
		
		formCliente = new FormularioCliente(vista);
		formCliente.setVisible(false);
		formTarifa = new VentanaCambioTarifa(vista);
		buscarCliente = new VentanaBuscarCliente(vista);
		buscarCliente.setVisible(false);
		ventanaBuscarFactura = new VentanaBuscarFactura(vista);
		ventanaBuscarFactura.setVisible(false);
		filtrar = new VentanaFiltrar(vista);
		filtrar.setVisible(false);
	}
	
	class Escuchador implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				JMenuItem boton = (JMenuItem)e.getSource();
			 	String texto = boton.getText();
		        if(texto.equals("Añadir cliente...")){
	            	formCliente.setVisible(true);
	            } else if(texto.equals("Borrar cliente")){
	            	try {
						vista.getControlador().borrarCliente();
						vista.clearSelection();
					} catch (ClienteNoEncontradoException e1) {						
					} catch (ClienteNoSeleccionadoException e1) {
						
					}
	            } else if(texto.equals("Buscar cliente...")){
	            	buscarCliente.setVisible(true);
	            } else if(texto.equals("Buscar factura...")){
	            	ventanaBuscarFactura.setVisible(true);
	            } else if(texto.equals("Cambiar tarifa...")){
	            	formTarifa.setVisible(true);
	            } else if(texto.equals("Filtrar...")){
	            	filtrar.setVisible(true);
	            } else if(texto.equals("Guardar")){
	            	vista.getControlador().escribir();
	            } else if(texto.equals("Cargar")){
	            	vista.getControlador().cargar();
	            } else {
	            	vista.clearSelection();
	            }
		}
	}
	
	public FormularioCliente getFormularioCliente(){
		return formCliente;
	}
	
	public VentanaCambioTarifa getVentanaCambioTarifa(){
		return formTarifa;
	}
	
	public VentanaBuscarCliente getVentanaBuscarCliente(){
		return buscarCliente;
	}
	
	public VentanaBuscarFactura getVentanaBuscarFactura(){
		return ventanaBuscarFactura;
	}
	
	public VentanaFiltrar getVentanaFiltrar(){
		return filtrar;
	}
}
