package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import excepciones.ClienteNoSeleccionadoException;
import tarifas.Tarifa;

public class VentanaCambioTarifa extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private FormularioTarifa fTar;
	private JButton cambiar;
	private Ventana vista;
	private JLabel error;
	
	public VentanaCambioTarifa(Ventana vista){		
		init();
		this.vista=vista;
		super.setVisible(false);
		super.setLayout(new FlowLayout());
		super.getContentPane().add(fTar);
		super.getContentPane().add(cambiar);
		super.getContentPane().add(error);
		super.setLocationByPlatform(true);
		super.pack();
	}
	
	public void init(){
		fTar = new FormularioTarifa();
		cambiar = new JButton("Cambiar");
		cambiar.addActionListener(new Escuchador());
		error = new JLabel("                  ");
		error.setForeground(Color.RED);
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Cambiar")){
            	error.setText("                  ");
            	try {
					vista.getControlador().cambiarTarifa();
				} catch (ClienteNoSeleccionadoException e1) {
					error.setText("NoSelect");
				}
            }      
        }
	}
	
	public Tarifa getNuevaTarifa(){
		return fTar.getTarifa();
	}
	
}
