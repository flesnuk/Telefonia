package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import clientes.Cliente;
import excepciones.ClienteNoSeleccionadoException;
import llamadas.Llamada;
import modelo.Modelo;

public class Ventana {
	
	private Modelo modelo;
	private Controlador controlador;
	private DefaultTableModel tablaClientes;
	private JTable tabla;
	private JScrollPane tablaCli; 
	private Escuchador escuchador;
	private FormularioCliente arriba;
	private VentanaLlamadas abajo;
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
	
	public Escuchador getEscuchador() {
		return escuchador;
    }
	
	public void GUI(){
		frame = new JFrame();
		frame.setLayout(new GridLayout(3,1));
		arriba = new FormularioCliente(this);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializarTablaClientes();
		escuchador = new Escuchador();
		JButton boton = new JButton("Add");
		boton.addActionListener(escuchador);	
		tabla = new JTable(tablaClientes);
		tablaCli = new JScrollPane(tabla);
		tablaCli.setPreferredSize(new Dimension(200, 100));
		
		JButton boton2 = new JButton("Deselect");
		boton2.addActionListener(escuchador);
		tabla.getSelectionModel().addListSelectionListener(escuchador);
		frame.getContentPane().add(arriba, BorderLayout.NORTH);
		frame.getContentPane().add(tablaCli, BorderLayout.CENTER);	
		
		abajo = new VentanaLlamadas(this);
		abajo.setVisible(false);
		
		frame.getContentPane().add(abajo, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationByPlatform(true);
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
            	tabla.clearSelection();
            	modelo.selectCliente(null);
            }
            
        }

		@Override
		public void valueChanged(ListSelectionEvent e) {
			//Cuando se cambia la seleccion en la tabla clientes
			 if(!e.getValueIsAdjusting()){
				int i = tabla.getSelectedRow();				
				if(i>=0){		
					//filtramos los eventos que no devuelvan -1
					abajo.setVisible(true);
					Cliente aux = modelo.getCliente((String)tablaClientes.getValueAt(i, 0));
					modelo.selectCliente(aux); 
				}				 	
			 }
			 try {
					abajo.nuevaLlamada();
			 } catch (ClienteNoSeleccionadoException e1) { }
		}
    }
	
	public Cliente getPersona(){		
		return arriba.getCliente();
	}
	
	
	public void nuevaEntrada(){
		Collection<Cliente> clientes = modelo.getClientes();
		inicializarTablaClientes();
		tabla.setModel(tablaClientes);
		//A partir de clientes pasamos a un array de objetos que hará de fila, y añadimos a la tabla
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
