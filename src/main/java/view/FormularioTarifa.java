package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tarifas.FabricaTarifas;
import tarifas.Tarifa;
import tarifas.TipoTarifa;

public class FormularioTarifa extends JPanel{
	
	private static final long serialVersionUID = -7693259168237678247L;
	JPanel tarifas;
	final static String TAR_SIMPLE = "Tarifa Simple";
	final static String TAR_AVANZADA = "Tarifa Avanzada";
	JComboBox<Object> cbTarifaDia;
	JComboBox<Object> cbTarifaFranja;
	private JTextField tar_avanzBasica;
	private JTextField tar_avanzDia;
	private JTextField tar_avanzFranja;
	private JTextField tar_simple;
	private JComboBox<String> cb;
	private CardLayout cl;
	
	public FormularioTarifa(){			
		
		JPanel formularioTarifaSimple = new JPanel();
		tar_simple = new JTextField(7);
		formularioTarifaSimple.add(tar_simple);
		JPanel formularioTarifaAvanzada = getFormularioTarifaAvanzada();

		cl = new CardLayout();
		tarifas = new JPanel(cl);
		tarifas.add(formularioTarifaSimple, TAR_SIMPLE);
		tarifas.add(formularioTarifaAvanzada, TAR_AVANZADA);
		
		JPanel seleccionarTar = new JPanel(); 
		String tipoTarSeleccionada[] = { TAR_SIMPLE, TAR_AVANZADA };
		cb = new JComboBox<String>(tipoTarSeleccionada);
		cb.setEditable(false);
		cb.addItemListener(new Escuchador());
		seleccionarTar.add(cb);
			
		super.add(seleccionarTar, null);
		super.add(tarifas, BorderLayout.CENTER);		
	}
	
	
	class Escuchador implements ItemListener {
        public void itemStateChanged(ItemEvent evt) {
            cl.show(tarifas, (String)evt.getItem());
        }
        
	}
	
	public JPanel getFormularioTarifaSimple(){
		JPanel simple = new JPanel();
		tar_simple = new JTextField(7);
		simple.add(tar_simple);
		return simple;
	}
	
	public JPanel getFormularioTarifaAvanzada(){
		JPanel avanz = new JPanel();
		
		tar_avanzBasica = new JTextField(7);
		tar_avanzDia = new JTextField(7);
		tar_avanzFranja = new JTextField(7);
		
		avanz.add(tar_avanzBasica);
		
		Object tarSeleccionadaDia[] = new Object[8];
		tarSeleccionadaDia[0] = "No select";
		for (int i=1; i<8; i++ )
			tarSeleccionadaDia[i]=TipoTarifa.getOpcion(i);
		cbTarifaDia = new JComboBox<Object>(tarSeleccionadaDia);
		avanz.add(cbTarifaDia);
		avanz.add(tar_avanzDia);
		
		Object tarSeleccionadaFranja[] = new Object[4];
		tarSeleccionadaFranja[0] = "No select";
		for (int i=1; i<4; i++ )
			tarSeleccionadaFranja[i]=TipoTarifa.getOpcion(i+7);
		cbTarifaFranja = new JComboBox<Object>(tarSeleccionadaFranja);
		avanz.add(cbTarifaFranja);
		avanz.add(tar_avanzFranja);
		
		return avanz;
	}
	
	public Tarifa getTarifa(){
		
		if(cb.getSelectedIndex()==0)			
			return new Tarifa(getDouble(tar_simple));
		
		Tarifa basica = new Tarifa(getDouble(tar_avanzBasica));
		FabricaTarifas fabT = new FabricaTarifas();
		
		int selectDia = cbTarifaDia.getSelectedIndex();
		int selectFranja = cbTarifaFranja.getSelectedIndex();
		
		if (selectDia > 0){
			TipoTarifa tipoDia = (TipoTarifa) cbTarifaDia.getItemAt(selectDia);
			basica = fabT.getTarifa(basica, getDouble(tar_avanzDia), tipoDia);					
		}
		
		if (selectFranja > 0){
			TipoTarifa tipoFranja = (TipoTarifa) cbTarifaFranja.getItemAt(selectFranja);
			basica = fabT.getTarifa(basica, getDouble(tar_avanzFranja), tipoFranja);					
		}	
		
		return basica;
	}
	
	public double getDouble(JTextField campo){
		return Double.parseDouble(campo.getText());			
	}
	
	
	
	
}
