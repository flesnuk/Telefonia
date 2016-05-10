package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AccionesCliente extends JPanel{
		
	private static final long serialVersionUID = 1L;
	private Ventana vista;
	private Escuchador escuchador;
	private JButton anyade;
	private JButton borrar;
	private JButton buscarNIF;
	private JButton cambiarTarifa;
	private FormularioCliente formCliente;
	private VentanaCambioTarifa formTarifa;	
	
	public AccionesCliente(Ventana vista){
		this.vista=vista;
		inicializa();
		super.add(anyade);
		super.add(borrar);
		super.add(buscarNIF);
		super.add(cambiarTarifa);		
	}
	
	
	private void inicializa(){
		escuchador = new Escuchador();
		anyade = new JButton("Añadir cliente...");
		borrar = new JButton("Borrar cliente...");
		buscarNIF = new JButton("Buscar cliente...");
		cambiarTarifa = new JButton("Cambiar tarifa...");
		anyade.addActionListener(escuchador);
		borrar.addActionListener(escuchador);
		buscarNIF.addActionListener(escuchador);
		cambiarTarifa.addActionListener(escuchador);
		formCliente = new FormularioCliente(vista);
		formCliente.setVisible(false);
		formTarifa = new VentanaCambioTarifa(vista);
		
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Añadir cliente...")){
            	formCliente.setVisible(true);
            } else if(texto.equals("Borrar cliente...")){
            	
            } else if(texto.equals("Buscar cliente...")){
            	
            } else {
            	formTarifa.setVisible(true);
            }
            
      
        }
	}
	
	public FormularioCliente getFormularioCliente(){
		return formCliente;
	}
	
	public VentanaCambioTarifa getVentanaCambioTarifa(){
		return formTarifa;
	}
	
}
