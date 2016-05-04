package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clientes.Cliente;
import clientes.FabricaClientes;
import excepciones.FechaInvalidaException;
import tarifas.Tarifa;

public class FormularioCliente extends JPanel{
	
	private static final long serialVersionUID = 4365585455888758995L;
	private Ventana vista;
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan3;
	
	private JComboBox<String> seleccPoE;
	private JTextField JTNIF;
	private JTextField JTnombre; 
	private JTextField JTApellidos;
	private JTextField JTcalle;
	private JTextField JTCodPostal;
	private JTextField JTprovincia;
	private JTextField JTpoblacion;
	private JTextField JTemail;
	private JFecha fechaAlta;
	private JTextField tarifa;
	private Escuchador escuchador;
	private FabricaClientes fabC;
	private JButton anyadir;
	private JLabel errorFormato;

	public FormularioCliente(Ventana vista){
		this.vista=vista;
		escuchador = new Escuchador();
		inicializaVariables();		
		errorFormato.setForeground(Color.RED);
		super.setLayout(new GridLayout(3,1));
		pan1.setLayout(new FlowLayout());
		pan1.add(seleccPoE, null);
		pan1.add(new JLabel("NIF"), null);
		pan1.add(JTNIF, null);
		pan1.add(new JLabel("Nombre"), null);
		pan1.add(JTnombre, null);
		pan1.add(new JLabel("Apellidos"), null);
		pan1.add(JTApellidos, null);
		pan2.add(new JLabel("calle"), null);
		pan2.add(JTcalle, null);
		pan2.add(new JLabel("CodPostal"), null);
		pan2.add(JTCodPostal, null);
		pan2.add(new JLabel("provincia"), null);
		pan2.add(JTprovincia, null);
		pan2.add(new JLabel("poblacion"), null);
		pan2.add(JTpoblacion, null);
		pan3.add(new JLabel("email"), null);
		pan3.add(JTemail, null);
		pan3.add(fechaAlta, null);
		pan3.add(new JLabel("tarifa"), null);
		pan3.add(tarifa, null);
		pan3.add(anyadir, null);
		pan3.add(errorFormato, null);
		super.add(pan1, null);
		super.add(pan2, null);
		super.add(pan3, null);
	}
	
	public void inicializaVariables(){	
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		Vector<String> v = new Vector<String>(2);
		v.add("Persona");
		v.add("Empresa");
		seleccPoE = new JComboBox<String>(v);
		JTNIF = new JTextField(9);
		JTnombre = new JTextField(15);
		JTApellidos = new JTextField(20);
		JTcalle = new JTextField(10);
		JTCodPostal = new JTextField(5);
		JTprovincia = new JTextField(12);
		JTpoblacion = new JTextField(12);
		JTemail = new JTextField(20);
		fechaAlta = new JFecha();
		tarifa = new JTextField(4);
		anyadir = new JButton("Add");
		errorFormato = new JLabel("                                    ");
		anyadir.addActionListener(escuchador);
		fabC = new FabricaClientes();
	}
	
	public Cliente getCliente(){		
		if(seleccPoE.getSelectedItem().equals("Persona")){
			try {
				return fabC.getPersona(JTNIF.getText(),
						JTnombre.getText(),
						JTApellidos.getText(), 
						JTcalle.getText(), 
						Integer.parseInt(JTCodPostal.getText()), 
						JTprovincia.getText(),
						JTpoblacion.getText(), 
						JTemail.getText(),
						fechaAlta.getFecha(), 
						new Tarifa(Double.parseDouble(tarifa.getText())));
			} catch (NumberFormatException e) {
				errorFormato.setText("Número no válido");
			} catch (FechaInvalidaException e) {
				errorFormato.setText("Fecha no válida");
			}
		}
		else {
			try {
				return fabC.getEmpresa(JTNIF.getText(),
						JTnombre.getText(),
						JTcalle.getText(), 
						Integer.parseInt(JTCodPostal.getText()), 
						JTprovincia.getText(),
						JTpoblacion.getText(), 
						JTemail.getText(),
						fechaAlta.getFecha(), 
						new Tarifa(Double.parseDouble(tarifa.getText())));
			} catch (NumberFormatException e) {
				errorFormato.setText("Número no válido");
			} catch (FechaInvalidaException e) {
				errorFormato.setText("Fecha no válida");
			}
		}
		return null;
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Add")){
            	errorFormato.setText("                                    ");
            	vista.getControlador().anyadePersona();
            }
      
        }
	}
	
}
