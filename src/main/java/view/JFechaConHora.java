package view;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JTextField;

import excepciones.FechaInvalidaException;

public class JFechaConHora extends JFecha{	
	private static final long serialVersionUID = 1L;
	private JLabel txtHora;
	private JTextField JThora;
	private JLabel txtMin;
	private JTextField JTmin;
	
	
	public JFechaConHora(){
		super();
		txtHora = new JLabel("Hora");
		JThora = new JTextField(2);
		txtMin = new JLabel(":");
		JTmin = new JTextField(2);
		super.addComponent(txtHora);
		super.addComponent(JThora);
		super.addComponent(txtMin);
		super.addComponent(JTmin);
	}
	
	@Override
	public Calendar getFecha() throws FechaInvalidaException{
		Calendar cal = new GregorianCalendar(Integer.parseInt(super.getAnyo().getText()),
				Integer.parseInt(super.getMes().getText())-1,
				Integer.parseInt(super.getDia().getText()),
				Integer.parseInt(JThora.getText()),
				Integer.parseInt(JTmin.getText()));
		cal.setLenient(false);
		try {
		    cal.getTime();
		}
		catch (Exception e) {
		  throw new FechaInvalidaException();
		}
		return cal;
	}	
}
