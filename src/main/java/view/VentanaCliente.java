package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import llamadas.Llamada;
import modelo.Modelo;
import controlador.Controlador;
import excepciones.ClienteNoSeleccionadoException;

public class VentanaCliente extends JPanel{

	private static final long serialVersionUID = -7188331620575302073L;
	
	private JPanel pan;
	
	private Modelo modelo;
	private DefaultTableModel tablaClientes;
	private JTable tabla;
	private JScrollPane tablaCli;
	private JTextField JTLlamada;
	private JTextField JTDuracion;
	private JPanel arriba;
	private Controlador controlador;
	private Escuchador escuchador;

	public VentanaCliente(Ventana v){
		modelo = v.getModelo();
		controlador = v.getControlador();
		escuchador = new Escuchador();
		super.setLayout(new BorderLayout());
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
		super.add(arriba, BorderLayout.NORTH);
		inicializarTablaClientes();
		tabla = new JTable(tablaClientes);
		tablaCli = new JScrollPane(tabla);
		tablaCli.setPreferredSize(new Dimension(200, 100));
		super.add(tablaCli, null);
	}
	
	
	public JPanel getJPanel() {
		return pan;
	}
	
	public void inicializarTablaClientes(){
		tablaClientes = new DefaultTableModel();
		Object[] nombreCol = {"Numero","Duracion"};
		tablaClientes.setColumnIdentifiers(nombreCol);		
	}
	
	public void nuevaLlamada() throws ClienteNoSeleccionadoException{
		Collection<Llamada> clientes = modelo.getLlamadas();		
		inicializarTablaClientes();		
		tabla.setModel(tablaClientes);
		if (clientes==null) return;
		Object[] col = new Object[2];
		for (Llamada l : clientes){
			col[0]=l.getTelefono();
			col[1]=l.getDuracion();
			((DefaultTableModel) tabla.getModel()).addRow(col);
		}
	}
	
	class Escuchador implements ActionListener, ListSelectionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
          int[] x = null; 
            if(texto.equals("Add")){
                try {
					controlador.anyadeLlamada();
				} catch (ClienteNoSeleccionadoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else {
            	x = tabla.getSelectedRows();            	
            }
            if(x!=null)
            for (int a : x)
            	System.out.println(a);
        }

		@Override
		public void valueChanged(ListSelectionEvent e) {
			 if(!e.getValueIsAdjusting())
				System.out.println(tabla.getSelectedRow()+1000);
			
		}
    }
	
	public Llamada getLlamada(){
		return new Llamada(Integer.parseInt(JTLlamada.getText()), Calendar.getInstance(), Double.parseDouble(JTDuracion.getText()));
	}
	
	
}
