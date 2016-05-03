package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clientes.Cliente;
import clientes.FabricaClientes;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.FechaInvalidaException;
import tarifas.Tarifa;
import view.Ventana.Escuchador;

public class FormularioCliente extends JPanel{
	
	private static final long serialVersionUID = 4365585455888758995L;
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
		//escuchador=vista.getEscuchador();
		escuchador = new Escuchador();
		inicializaVariables();
		super.setLayout(new FlowLayout());
		super.add(seleccPoE, null);
		super.add(new JLabel("NIF"), null);
		super.add(JTNIF, null);
		super.add(new JLabel("Nombre"), null);
		super.add(JTnombre, null);
		super.add(new JLabel("Apellidos"), null);
		super.add(JTApellidos, null);
		super.add(new JLabel("calle"), null);
		super.add(JTcalle, null);
		super.add(new JLabel("CodPostal"), null);
		super.add(JTCodPostal, null);
		super.add(new JLabel("provincia"), null);
		super.add(JTprovincia, null);
		super.add(new JLabel("poblacion"), null);
		super.add(JTpoblacion, null);
		super.add(new JLabel("email"), null);
		super.add(JTemail, null);
		super.add(new JLabel("fechaAlta"), null);
		super.add(fechaAlta, null);
		super.add(new JLabel("tarifa"), null);
		super.add(tarifa, null);
		super.add(anyadir, null);
		super.add(errorFormato, null);
	}
	
	public void inicializaVariables(){		
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
		errorFormato = new JLabel("                  ");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FechaInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FechaInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Add")){
            	System.out.println("H");
            }
      
        }
	}
	
}
