package view;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import facturas.Factura;

public class VentanaInfoFactura extends JFrame{	
	private static final long serialVersionUID = 1L;
	private Factura Factura;
	private JTextArea texto;
	
	public VentanaInfoFactura(){
		texto = new JTextArea(5, 20);	
		super.getContentPane().add(texto, null);
	}
	
	public void setFactura(Factura f){
		Factura=f;
		texto.setText(toString());
	}
	
	
	public String toString(){
		String ret;
		StringBuilder sb = new StringBuilder();
		sb.append(Factura.toString());
		ret = sb.toString().replaceAll("(?<=[,])", "\n");
		return ret;
	}
	
	
	
}