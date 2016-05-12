package view;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import clientes.Cliente;

public class VentanaInfoCliente extends JFrame{	
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private JTextArea texto;
	
	public VentanaInfoCliente(){
		texto = new JTextArea(5, 20);	
		super.getContentPane().add(texto, null);
	}
	
	public void setCliente(Cliente c){
		cliente=c;
		texto.setText(toString());
	}
	
	
	public String toString(){
		String ret;
		StringBuilder sb = new StringBuilder();
		sb.append(cliente.toString());
		ret = sb.toString().replaceAll("(?<=[,])", "\n");
		return ret;
	}
	
	
	
}