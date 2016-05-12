package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VentanaFiltrar extends JFrame{
		
	private static final long serialVersionUID = 1L;
	private JTabbedPane pestañas;
	private JFecha inicioC;
	private JFecha finC;
	private JFecha inicioL;
	private JFecha finL;
	private JFecha inicioF;
	private JFecha finF;
	private JPanel filtrarClientesTab;
	private JPanel filtrarLlamadasTab;
	private JPanel filtrarFacturasTab;	
	
	public VentanaFiltrar(){
		init();
		super.getContentPane().add(pestañas);
		super.pack();
	}

	private void init() {
		pestañas = new JTabbedPane();
		inicioC = new JFecha();
		finC = new JFecha();
		inicioL = new JFecha();
		finL = new JFecha();
		inicioF = new JFecha();
		finF = new JFecha();
		filtrarClientesTab = new JPanel();
		filtrarLlamadasTab = new JPanel();
		filtrarFacturasTab = new JPanel();
		filtrarClientesTab.add(inicioC);
		filtrarClientesTab.add(finC);
		filtrarLlamadasTab.add(inicioL);
		filtrarLlamadasTab.add(finL);
		filtrarFacturasTab.add(inicioF);
		filtrarFacturasTab.add(finF);
		pestañas.addTab("Filtrar clientes", filtrarClientesTab);
		pestañas.addTab("Filtrar llamadas", filtrarLlamadasTab);
		pestañas.addTab("Filtrar facturas", filtrarFacturasTab);
	}
	
	

}
