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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import llamadas.Llamada;
import modelo.InterrogaModelo;
import controlador.Controlador;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.FechaInvalidaException;
import utilidades.FechaToString;

public class VentanaLlamadas extends JPanel{

	private static final long serialVersionUID = -7188331620575302073L;
	
	private JPanel pan;
	
	private InterrogaModelo modelo;
	private DefaultTableModel tablaLlamadas;
	private JTable tabla;
	private JScrollPane tablaLlam;
	private JTextField JTLlamada;
	private JTextField JTDuracion;
	private JPanel arriba;
	private Controlador controlador;
	private Escuchador escuchador;
	private JLabel error;
	private JFechaConHora fecha;

	public VentanaLlamadas(Ventana v){
		modelo = v.getModelo();
		controlador = v.getControlador();
		escuchador = new Escuchador();
		super.setLayout(new BorderLayout());
		error = new JLabel("                                    ");
		error.setForeground(Color.RED);
		fecha = new JFechaConHora();
		arriba = new  JPanel();
		JTLlamada = new JTextField(10);
		JTDuracion = new JTextField(10);	
		JButton boton = new JButton("Add");
		boton.addActionListener(escuchador);
		arriba.add(boton, BorderLayout.NORTH);
		arriba.add(new JLabel("Num"), BorderLayout.NORTH);
		arriba.add(JTLlamada, BorderLayout.NORTH);
		arriba.add(new JLabel("Duracion"), BorderLayout.NORTH);
		arriba.add(JTDuracion, BorderLayout.NORTH);
		arriba.add(fecha, null);
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
		Object[] nombreCol = {"Numero","Duracion","Fecha"};
		tablaLlamadas.setColumnIdentifiers(nombreCol);		
	}
	
	public void nuevaLlamada() throws ClienteNoSeleccionadoException{
		Collection<Llamada> llamadas = modelo.getLlamadas();		
		inicializarTablaClientes();		
		tabla.setModel(tablaLlamadas);
		if (llamadas==null) return;
		Object[] col = new Object[3];
		for (Llamada l : llamadas){			
			col[0]=l.getTelefono();
			col[1]=l.getDuracion();
			col[2]=FechaToString.toString(l.getFecha());
			((DefaultTableModel) tabla.getModel()).addRow(col);			
		}
	}
	
	class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Add")){
                try {
                	error.setText("                 ");
					controlador.anyadeLlamada();										
				} catch (ClienteNoSeleccionadoException e1) {
					error.setText("NoSelect");
				} catch (FechaInvalidaException e2) {
					error.setText("NoFecha");
				}
            }         
        }
		
    }
	
	public Llamada getLlamada(){
		Llamada l = null;
		try {
			l = new Llamada(Integer.parseInt(JTLlamada.getText()), 
					fecha.getFecha(), 
					Double.parseDouble(JTDuracion.getText()));
			
		} catch (NumberFormatException e) {
			error.setText("Número no válido");
		} catch (FechaInvalidaException e) {
			error.setText("Fecha no válida");
		}
		return l;
	}
	
	
}
