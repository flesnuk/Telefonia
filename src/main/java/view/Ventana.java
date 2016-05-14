package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Calendar;
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

import tarifas.Tarifa;
import controlador.Controlador;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.FechaInvalidaException;
import llamadas.Llamada;
import modelo.Modelo;

public class Ventana {
	
	private Modelo modelo;
	private Controlador controlador;
	private DefaultTableModel tablaClientes;
	private JTable tabla;
	private JScrollPane tablaCli; 
	private Escuchador escuchador;
	private FormularioCliente formCliente;
	private VentanaLlamadas abajo;
	private VentanaFacturas facturas;
	private JFrame frame;
	private JButton arriba;
	private AccionesCliente botonesCliente;
	
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
		frame.setLayout(new GridLayout(4,1));
		formCliente = new FormularioCliente(this);
		formCliente.setVisible(false);		
		
		inicializarTablaClientes();
		escuchador = new Escuchador();
		tabla = new JTable(tablaClientes);
		tablaCli = new JScrollPane(tabla);
		tablaCli.setPreferredSize(new Dimension(200, 100));		
		tabla.getSelectionModel().addListSelectionListener(escuchador);
		nuevaEntrada();
		
		arriba = new JButton("Add");
		arriba.addActionListener(escuchador);
		botonesCliente = new AccionesCliente(this);		
		frame.getContentPane().add(botonesCliente, BorderLayout.NORTH);
		frame.getContentPane().add(tablaCli, BorderLayout.CENTER);	
		
		abajo = new VentanaLlamadas(this);
		abajo.setVisible(false);
		
		frame.getContentPane().add(abajo, BorderLayout.SOUTH);
		
		facturas = new VentanaFacturas(this);
		facturas.setVisible(false);
		
		frame.getContentPane().add(facturas, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
		    public void windowClosing(WindowEvent e)
		    {
		        try {
					modelo.escribir();
					exitProcedure();
				} catch (IOException e1) {
					
				}
		    }
		});
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
                //controlador.anyadePersona();
                formCliente.setVisible(true);
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
					facturas.setVisible(true);
					Cliente aux = null;
					try {
						aux = modelo.getCliente((String)tablaClientes.getValueAt(i, 0));
					} catch (ClienteNoEncontradoException e1) {	}
					modelo.selectCliente(aux); 
				}				 	
			 }
			 try {
					abajo.nuevaLlamada();
					facturas.nuevaFactura();
			 } catch (ClienteNoSeleccionadoException e1) { }
		}
    }
	
	public Cliente getPersona(){		
		return botonesCliente.getFormularioCliente().getCliente();
	}
	
	public Tarifa getNuevaTarifa(){
		return botonesCliente.getVentanaCambioTarifa().getNuevaTarifa();
	}
	
	public String getNIF() {
		return botonesCliente.getVentanaBuscarCliente().getNIF();
	}
	
	public Calendar getFechaInicio() throws FechaInvalidaException {
	    return botonesCliente.getVentanaFiltrar().getFechaInicio();
	}
	
	public Calendar getFechaFin() throws FechaInvalidaException {
	    return botonesCliente.getVentanaFiltrar().getFechaFin();
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
	
	public void nuevaFactura() throws ClienteNoSeleccionadoException{
		facturas.nuevaFactura();
	}
	
	public Llamada getLlamada(){
		return abajo.getLlamada();
	}	
	
	public void clearSelection(){
		tabla.clearSelection();
		modelo.selectCliente(null);
		abajo.setVisible(false);
		facturas.setVisible(false);
	}
	
	public void exitProcedure() {
	    frame.dispose();
	    System.exit(0);
	}

	
}
