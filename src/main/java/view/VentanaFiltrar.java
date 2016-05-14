package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

public class VentanaFiltrar extends JFrame{
		
	private static final long serialVersionUID = 1L;
	private Ventana vista;
	private JRadioButton selectClientes;
	private JRadioButton selectLlamadas;
	private JRadioButton selectFacturas;	
	private JFecha inicio;
	private JFecha fin;
	private JButton filtrarC;
	private JButton filtrarL;
	private JButton filtrarF;
	private JPanel panel;
	
	public VentanaFiltrar(Ventana vista){
		this.vista=vista;
		init();
		super.getContentPane().add(panel);
		super.pack();
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		super.setLocationByPlatform(true);
		super.addWindowListener(new WindowAdapter()
		{
			@Override
		    public void windowClosing(WindowEvent e)
		    {
		        exit();
		    }
		});
	}

	private void init() {
		ButtonGroup buttonGroup = new ButtonGroup();
		selectClientes = new JRadioButton("Clientes",true);
		selectLlamadas = new JRadioButton("Llamadas",false);
		selectFacturas = new JRadioButton("Facturas",false);
		selectClientes.addItemListener(new Escuchador());
		selectLlamadas.addItemListener(new Escuchador());
		selectFacturas.addItemListener(new Escuchador());
		
		buttonGroup.add(selectClientes);
		buttonGroup.add(selectLlamadas);
		buttonGroup.add(selectFacturas);
		inicio = new JFecha();
		fin = new JFecha();
		filtrarC = new JButton("Filtrar");
		filtrarL = new JButton("Filtrar");
		filtrarF = new JButton("Filtrar");
		panel = new JPanel();
		filtrarC.addActionListener(new Escuchador());
		filtrarL.addActionListener(new Escuchador());
		filtrarF.addActionListener(new Escuchador());
		panel.add(selectClientes);
		panel.add(selectLlamadas);
		panel.add(selectFacturas);
		panel.add(inicio);
		panel.add(fin);
		panel.add(filtrarC);		
	}
	
	class Escuchador implements ActionListener, ItemListener {
		@Override
        public void actionPerformed(ActionEvent e) {
          JButton boton = (JButton)e.getSource();
          String texto = boton.getText();
            if(texto.equals("Filtrar")){
            	((AbstractButton) panel.getComponent(5)).setText("Deshacer"); 
            	refresh();
            } else {
            	((AbstractButton) panel.getComponent(5)).setText("Filtrar"); 
            	refresh();
            }
        }
        
        @Override
        public void itemStateChanged(ItemEvent e) {
        	JRadioButton boton = (JRadioButton)e.getSource();
        	String texto = boton.getText();
        	if(texto.equals("Clientes")){
        		if (e.getStateChange() == ItemEvent.SELECTED) {
        			cambiar(filtrarC);
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                }
            } else if(texto.equals("Llamadas")){
        		if (e.getStateChange() == ItemEvent.SELECTED) {
        			cambiar(filtrarL);
        			refresh();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                }
            } else {
            	if (e.getStateChange() == ItemEvent.SELECTED) {
            		cambiar(filtrarF);
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                   
                }
            }
            
        }
        
	}
	
	public void refresh() {
	    super.revalidate();
	    super.pack();
	}
	
	public void cambiar(JButton nuevo) {
		panel.remove(panel.getComponent(5));
		panel.add(nuevo);
		panel.revalidate();
		panel.repaint();
		super.pack();
	}
	
	public void exit() {
	    super.dispose();
	}

}
