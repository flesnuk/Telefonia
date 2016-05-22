package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import excepciones.CodigoFacturaException;

public class VentanaBuscarFactura extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField JTCodFac;
	private JButton buscar;
	private Ventana vista;
	private JLabel error;
	private VentanaInfoFactura info;
	
	public VentanaBuscarFactura(Ventana vista){		
		init();
		this.vista=vista;
		super.setVisible(false);
		super.setLayout(new FlowLayout());
		super.getContentPane().add(new JLabel("Codfac:"));
		super.getContentPane().add(JTCodFac);
		super.getContentPane().add(buscar);
		super.getContentPane().add(error);
		super.setLocationByPlatform(true);
		super.pack();
	}
	
	public void init(){
		JTCodFac = new JTextField(10);
		buscar = new JButton("Buscar");
		buscar.addActionListener(new Escuchador());
		error = new JLabel("                            ");
		error.setForeground(Color.RED);
		info = new VentanaInfoFactura();
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Buscar")){
            	error.setText("                         ");
            	try {
					info.setFactura(vista.getControlador().buscarFactura());
					info.pack();
					info.setVisible(true);
				} catch (NumberFormatException e1) {
					error.setText("Número no válido");
				} catch (CodigoFacturaException e1) {
					error.setText("Codfac no válido");
				}
            }      
        }
	}
	
	public int getCodFac(){
		return Integer.parseInt(JTCodFac.getText());
	}
	
}
