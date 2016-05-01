package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import tarifas.Tarifa;
import clientes.Cliente;
import clientes.Persona;
import excepciones.ClienteNoSeleccionadoException;
import llamadas.Llamada;
import modelo.Modelo;

public class Ventana {
	
	private Modelo modelo;
	private Controlador controlador;
	private DefaultTableModel tablaClientes;
	private JTextField JTNIF;
	private JTextField JTnombre; 
	private JTable tabla;
	private JScrollPane tablaCli; 
	private Escuchador escuchador;
	private JPanel arriba;
	private VentanaCliente abajo;
	private JFrame frame;
	
	public Ventana(){
		
	}
	
	public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
	
	public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
	
	public Modelo getModelo() {
        return modelo;
    }
	
	public Controlador getControlador() {
		return controlador;
    }
	
	public void GUI(){
		frame = new JFrame();
		frame.setLayout(new GridLayout(3,1));
		arriba = new JPanel();		
		arriba.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializarTablaClientes();
		escuchador = new Escuchador();
		JButton boton = new JButton("Add");
		boton.addActionListener(escuchador);
		JTNIF = new JTextField(10);
		JTnombre = new JTextField(10);		
		tabla = new JTable(tablaClientes);
		tablaCli = new JScrollPane(tabla);
		tablaCli.setPreferredSize(new Dimension(200, 100));
		arriba.add(boton, null);
		
		JButton boton2 = new JButton("Deselect");
		boton2.addActionListener(escuchador);
		arriba.add(boton2, null);
		
		arriba.add(new JLabel("NIF"), null);
		arriba.add(JTNIF, null);
		arriba.add(new JLabel("Nombre"), null);
		arriba.add(JTnombre, null);
		tabla.getSelectionModel().addListSelectionListener(escuchador);
		frame.getContentPane().add(arriba, BorderLayout.NORTH);
		arriba.add(new JFecha(), null);
		frame.getContentPane().add(tablaCli, BorderLayout.CENTER);	
		abajo = new VentanaCliente(this);
		frame.getContentPane().add(abajo, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void creaGUI() {
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					System.out.println("L&F");
				}
				GUI();
			}
		});
    }
	
	class Escuchador implements ActionListener, ListSelectionListener {
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Add")){
                controlador.anyadePersona();
            }
            else {   
            	
            }
            
        }

		@Override
		public void valueChanged(ListSelectionEvent e) {
			 if(!e.getValueIsAdjusting()){
				Cliente aux = modelo.getCliente((String)tablaClientes.getValueAt(tabla.getSelectedRow(), 0));
				modelo.selectCliente(aux);  	
			 }
			 try {
					abajo.nuevaLlamada();
			 } catch (ClienteNoSeleccionadoException e1) {
					
			 }
				
				//System.out.println(tabla.getSelectedRow()+1000);
			
		}
    }
	
	public Cliente getPersona(){		
		return new Persona(JTNIF.getText(),JTnombre.getText(),Calendar.getInstance(),new Tarifa(4));
	}
	
	
	public void nuevaEntrada(){
		Collection<Cliente> clientes = modelo.getClientes();
		inicializarTablaClientes();
		tabla.setModel(tablaClientes);
		Object[] col = new Object[2];
		for (Cliente c : clientes){
			col[0]=c.getNIF();
			col[1]=c.getNombre();
			((DefaultTableModel) tabla.getModel()).addRow(col);
		}
	}
	
	public void inicializarTablaClientes(){
		tablaClientes = new DefaultTableModel();
		Object[] nombreCol = {"NIF","Nombre"};
		tablaClientes.setColumnIdentifiers(nombreCol);		
	}
		
	public void nuevaLlamada() throws ClienteNoSeleccionadoException{
		abajo.nuevaLlamada();
	}
	
	public Llamada getLlamada(){
		return abajo.getLlamada();
	}
	
}
