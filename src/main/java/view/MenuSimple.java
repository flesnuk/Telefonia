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
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.getAccessibleContext().setAccessibleDescription(
		        "Archivo");
		
		JMenu menuCliente = new JMenu("Cliente");
		menuCliente.getAccessibleContext().setAccessibleDescription(
		        "Cliente");		
		
		JMenu menuFacturas = new JMenu("Facturas");
		menuFacturas.getAccessibleContext().setAccessibleDescription(
		        "Buscar");
		
		JMenu menuFiltrar = new JMenu("Filtrar");
		menuFiltrar.getAccessibleContext().setAccessibleDescription(
		        "Filtrar");
		
		JMenuItem menuItemDeselect = new JMenuItem("Deseleccionar");
		menuItemDeselect.addActionListener(new Escuchador());
		JMenuItem menuItemAnadirCli = new JMenuItem("Añadir cliente...");
		menuItemAnadirCli.addActionListener(new Escuchador());
		JMenuItem menuItemBorrarCli = new JMenuItem("Borrar cliente");
		menuItemBorrarCli.addActionListener(new Escuchador());
		JMenuItem menuItemBuscarCli = new JMenuItem("Buscar cliente...");
		menuItemBuscarCli.addActionListener(new Escuchador());
		JMenuItem menuItemBuscarFac = new JMenuItem("Buscar factura...");
		menuItemBuscarFac.addActionListener(new Escuchador());
		JMenuItem menuItemCambiarTar = new JMenuItem("Cambiar tarifa...");
		menuItemCambiarTar.addActionListener(new Escuchador());
		JMenuItem menuItemFiltrar = new JMenuItem("Filtrar...");
		menuItemFiltrar.addActionListener(new Escuchador());
		JMenuItem menuItemGuardar = new JMenuItem("Guardar");
		menuItemGuardar.addActionListener(new Escuchador());
		JMenuItem menuItemCargar = new JMenuItem("Cargar");
		menuItemCargar.addActionListener(new Escuchador());
		JMenuItem menuItemNuevo = new JMenuItem("Nuevo");
		menuItemNuevo.addActionListener(new Escuchador());
		
		
		menuCliente.add(menuItemDeselect);
		menuCliente.add(menuItemAnadirCli);
		menuCliente.add(menuItemBorrarCli);
		menuCliente.add(menuItemBuscarCli);
		menuCliente.add(menuItemCambiarTar);
		menuFacturas.add(menuItemBuscarFac);
		menuFiltrar.add(menuItemFiltrar);

		menuArchivo.add(menuItemNuevo);
		menuArchivo.add(menuItemGuardar);
		menuArchivo.add(menuItemCargar);
		
		menuBar.add(menuArchivo);
		menuBar.add(menuCliente);
		menuBar.add(menuFacturas);
		menuBar.add(menuFiltrar);
		
		
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
	            } else if(texto.equals("Nuevo")){
	            	vista.getControlador().nuevo();
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
