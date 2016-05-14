package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;

public class AccionesCliente extends JPanel{
		
	private static final long serialVersionUID = 1L;
	private Ventana vista;
	private Escuchador escuchador;
	private JButton deseleccionar;
	private JButton anyade;
	private JButton borrar;
	private JButton buscarNIF;
	private JButton cambiarTarifa;
	private JButton JBfiltrar;
	private JButton cargar;
	private JButton guardar;
	private FormularioCliente formCliente;
	private VentanaCambioTarifa formTarifa;	
	private VentanaBuscarCliente buscarCliente;
	private VentanaFiltrar filtrar;
	
	public AccionesCliente(Ventana vista){
		this.vista=vista;
		inicializa();
		super.add(deseleccionar);
		super.add(anyade);
		super.add(borrar);
		super.add(buscarNIF);
		super.add(cambiarTarifa);	
		super.add(JBfiltrar);	
		super.add(guardar);	
		super.add(cargar);	
	}
	
	
	private void inicializa(){
		escuchador = new Escuchador();
		deseleccionar = new JButton("Deseleccionar");
		anyade = new JButton("Añadir cliente...");
		borrar = new JButton("Borrar cliente");
		buscarNIF = new JButton("Buscar cliente...");
		cambiarTarifa = new JButton("Cambiar tarifa...");
		JBfiltrar = new JButton("Filtrar...");
		guardar = new JButton("Guardar");
		cargar = new JButton("Cargar...");
		deseleccionar.addActionListener(escuchador);
		anyade.addActionListener(escuchador);
		borrar.addActionListener(escuchador);
		buscarNIF.addActionListener(escuchador);
		cambiarTarifa.addActionListener(escuchador);
		JBfiltrar.addActionListener(escuchador);
		guardar.addActionListener(escuchador);
		cargar.addActionListener(escuchador);
		formCliente = new FormularioCliente(vista);
		formCliente.setVisible(false);
		formTarifa = new VentanaCambioTarifa(vista);
		buscarCliente = new VentanaBuscarCliente(vista);
		buscarCliente.setVisible(false);
		filtrar = new VentanaFiltrar(vista);
		filtrar.setVisible(false);
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
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
            } else if(texto.equals("Cambiar tarifa...")){
            	formTarifa.setVisible(true);
            } else if(texto.equals("Filtrar...")){
            	filtrar.setVisible(true);
            } else if(texto.equals("Guardar")){
            	vista.getControlador().escribir();
            } else if(texto.equals("Cargar...")){
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
	
}
