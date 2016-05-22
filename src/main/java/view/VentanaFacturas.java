package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.InterrogaModelo;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.FechaInvalidaException;
import excepciones.OrdenFechasException;
import facturas.Factura;
import utilidades.FechaToString;

public class VentanaFacturas extends JPanel{

	private static final long serialVersionUID = -7188331620575302073L;
	
	private JPanel pan;
	
	private InterrogaModelo modelo;
	private DefaultTableModel tablaLlamadas;
	private JTable tabla;
	private JScrollPane tablaLlam;
	private JPanel arriba;
	//private Controlador controlador;
	private Escuchador escuchador;
	private JLabel error;
	private JFecha fecha;
	private JFecha fechaInicio;
	private JFecha fechaFinal;

	public VentanaFacturas(Ventana v){
		modelo = v.getModelo();
		//controlador = v.getControlador();
		escuchador = new Escuchador();
		super.setLayout(new BorderLayout());
		error = new JLabel("                                    ");
		error.setForeground(Color.RED);
		fecha = new JFecha();
		fechaInicio = new JFecha();
		fechaInicio.setTextoFecha("Fecha Inicio");
		fechaFinal = new JFecha();
		fechaFinal.setTextoFecha("Fecha Final");
		arriba = new  JPanel();
		JButton boton = new JButton("Emite");
		boton.addActionListener(escuchador);
		arriba.add(boton, BorderLayout.NORTH);
		arriba.add(fecha, null);
		arriba.add(fechaInicio, null);
		arriba.add(fechaFinal, null);
		arriba.add(error, BorderLayout.NORTH);
		super.add(arriba, BorderLayout.NORTH);
		inicializarTablaClientes();
		tabla = new JTable(tablaLlamadas);
		tablaLlam = new JScrollPane(tabla);
		tablaLlam.setPreferredSize(new Dimension(200, 100));
		super.add(tablaLlam, null);		
	}
	
	
	public JPanel getJPanel() {
		return pan;
	}
	
	public void inicializarTablaClientes(){
		tablaLlamadas = new DefaultTableModel();
		Object[] nombreCol = {"Numero","Fecha","Total"};
		tablaLlamadas.setColumnIdentifiers(nombreCol);		
	}
	
	public void nuevaFactura() throws ClienteNoSeleccionadoException{
		Collection<Factura> facturas = modelo.getFacturas();		
		inicializarTablaClientes();		
		tabla.setModel(tablaLlamadas);
		if (facturas==null) return;
		Object[] col = new Object[3];
		for (Factura f : facturas){			
			col[0]=f.getCodfac();
			col[1]=FechaToString.toString(f.getFecha());
			col[2]=f.getPrecio();
			((DefaultTableModel) tabla.getModel()).addRow(col);			
		}
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Emite")){
                try {
                	error.setText("                 ");
					emite();										
				} catch (ClienteNoSeleccionadoException e1) {
					error.setText("NoSelect");
				} 
            }         
        }
		
    }
	
	public void emite() throws ClienteNoSeleccionadoException{
		try {
			modelo.emite( fecha.getFecha(),
					fechaInicio.getFecha(),
					fechaFinal.getFecha());
			
		} catch (NumberFormatException e) {
			error.setText("Número no válido");
		} catch (FechaInvalidaException e) {
			error.setText("Fecha no válida");
		} catch (OrdenFechasException e) {
			error.setText("Orden Fechas");
		}
	}
	
	
}
