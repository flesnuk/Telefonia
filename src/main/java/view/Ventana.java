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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import tarifas.Tarifa;
import utilidades.FechaToString;
import controlador.Controlador;
import clientes.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.ClienteNoSeleccionadoException;
import excepciones.FechaInvalidaException;
import llamadas.Llamada;
import modelo.InterrogaModelo;

public class Ventana implements InformaVista, InterrogaVista {
	
	private InterrogaModelo modelo;
	private Controlador controlador;
	private DefaultTableModel tablaClientes;
	private JTable tabla;
	private JScrollPane tablaCli; 
	private Escuchador escuchador;
	private FormularioCliente formCliente;
	private VentanaLlamadas ventanaLlamadas;
	private VentanaFacturas ventanaFacturas;
	private JFrame frame;
	private JButton arriba;
	private AccionesCliente botonesCliente;
	
	public Ventana(){
		
	}
	
	public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }
	
	public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
	
	public InterrogaModelo getModelo() {
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
		JPanel panelTablas = new JPanel();
		panelTablas.setLayout(new GridLayout(3,1));
		formCliente = new FormularioCliente(this);
		formCliente.setVisible(false);		
		
		inicializarTablaClientes();
		escuchador = new Escuchador();
		tabla = new JTable(tablaClientes);
		tablaCli = new JScrollPane(tabla);
		tablaCli.setPreferredSize(new Dimension(200, 100));		
		tabla.getSelectionModel().addListSelectionListener(escuchador);
		nuevoCliente();
		
		arriba = new JButton("Add");
		arriba.addActionListener(escuchador);
		botonesCliente = new AccionesCliente(this);		
		frame.getContentPane().add(botonesCliente, BorderLayout.NORTH);			
		
		ventanaLlamadas = new VentanaLlamadas(this);
		ventanaLlamadas.setVisible(false);
		
		ventanaFacturas = new VentanaFacturas(this);
		ventanaFacturas.setVisible(false);
		
		panelTablas.add(tablaCli);
		panelTablas.add(ventanaLlamadas);
		panelTablas.add(ventanaFacturas);
		
		frame.getContentPane().add(panelTablas, BorderLayout.CENTER);
		
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
					ventanaLlamadas.setVisible(true);
					ventanaFacturas.setVisible(true);
					Cliente aux = null;
					try {
						aux = modelo.getCliente((String)tablaClientes.getValueAt(i, 0));
					} catch (ClienteNoEncontradoException e1) {	}
					modelo.selectCliente(aux); 
				}				 	
			 }
			 try {
					ventanaLlamadas.nuevaLlamada();
					ventanaFacturas.nuevaFactura();
			 } catch (ClienteNoSeleccionadoException e1) { }
		}
    }
	
	@Override
	public Cliente getPersona(){		
		return botonesCliente.getFormularioCliente().getCliente();
	}
	
	@Override
	public Tarifa getNuevaTarifa(){
		return botonesCliente.getVentanaCambioTarifa().getNuevaTarifa();
	}
	
	@Override
	public String getNIF() {
		return botonesCliente.getVentanaBuscarCliente().getNIF();
	}
	
	@Override
	public int getCodFac() {
		return botonesCliente.getVentanaBuscarFactura().getCodFac();
	}
	
	@Override
	public Calendar getFechaInicio() throws FechaInvalidaException {
	    return botonesCliente.getVentanaFiltrar().getFechaInicio();
	}
	
	@Override
	public Calendar getFechaFin() throws FechaInvalidaException {
	    return botonesCliente.getVentanaFiltrar().getFechaFin();
	}
	
	
	@Override
	public void nuevoCliente(){
		Collection<Cliente> clientes = modelo.getClientes();
		inicializarTablaClientes();
		tabla.setModel(tablaClientes);
		//A partir de clientes pasamos a un array de objetos que hará de fila, y añadimos a la tabla
		Object[] col = new Object[5];
		for (Cliente c : clientes){
			col[0]=c.getNIF();
			col[1]=c.getNombre();
			col[2]=c.getEmail();
			col[3]=FechaToString.toString(c.getFecha());
			col[4]=c.getTarifa();
			((DefaultTableModel) tabla.getModel()).addRow(col);
		}
	}
	
	public void inicializarTablaClientes(){
		tablaClientes = new DefaultTableModel();
		Object[] nombreCol = {"NIF","Nombre","Email","Fecha alta","Tarifa"};
		tablaClientes.setColumnIdentifiers(nombreCol);			
	}
		
	@Override
	public void nuevaLlamada() throws ClienteNoSeleccionadoException{
		ventanaLlamadas.nuevaLlamada();
	}
	
	@Override
	public void nuevaFactura() throws ClienteNoSeleccionadoException{
		ventanaFacturas.nuevaFactura();
	}
	
	@Override
	public Llamada getLlamada(){
		return ventanaLlamadas.getLlamada();
	}	
	
	public void clearSelection(){
		tabla.clearSelection();
		modelo.selectCliente(null);
		ventanaLlamadas.setVisible(false);
		ventanaFacturas.setVisible(false);
	}
	
	public void exitProcedure() {
	    frame.dispose();
	    System.exit(0);
	}


	
}
