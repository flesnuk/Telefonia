package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFecha extends JPanel {
	
	private static final long serialVersionUID = 2691828617156084732L;
	private JLabel txtFecha;
	private JTextField dia;
	private JLabel fstSlash;
	private JTextField mes;
	private JLabel sndSlash;
	private JTextField anyo;
	
	public JFecha(){
		txtFecha = new JLabel("Fecha");
		dia = new JTextField(2);
		fstSlash = new JLabel("/");
		mes = new JTextField(2);
		sndSlash = new JLabel("/");
		anyo = new JTextField(4);
		super.add(txtFecha, null);
		super.add(dia, null);
		super.add(fstSlash, null);
		super.add(mes, null);
		super.add(sndSlash, null);
		super.add(anyo, null);
	}
}
