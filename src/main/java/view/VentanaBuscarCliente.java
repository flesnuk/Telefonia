package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import excepciones.ClienteNoEncontradoException;

public class VentanaBuscarCliente extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField JTNIF;
	private JButton buscar;
	private Ventana vista;
	private JLabel error;
	private VentanaInfoCliente info;
	
	public VentanaBuscarCliente(Ventana vista){		
		init();
		this.vista=vista;
		super.setVisible(false);
		super.setLayout(new FlowLayout());
		super.getContentPane().add(new JLabel("NIF:"));
		super.getContentPane().add(JTNIF);
		super.getContentPane().add(buscar);
		super.getContentPane().add(error);
		super.setLocationByPlatform(true);
		super.pack();
	}
	
	public void init(){
		JTNIF = new JTextField(10);
		buscar = new JButton("Buscar");
		buscar.addActionListener(new Escuchador());
		error = new JLabel("                            ");
		error.setForeground(Color.RED);
		info = new VentanaInfoCliente();
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Buscar")){
            	error.setText("                         ");
            	try {
					info.setCliente(vista.getControlador().buscarCliente());
					info.pack();
					info.setVisible(true);
				} catch (ClienteNoEncontradoException e1) {
					error.setText("NoEncontrado");
				}
            }      
        }
	}
	
	public String getNIF(){
		return JTNIF.getText();
	}
	
}
