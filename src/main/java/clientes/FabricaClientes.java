package clientes;

import input.Input;
import input.SimpleInput;

import java.util.Calendar;

import output.*;
import tarifas.CreadorTarifas;
import tarifas.Tarifa;

public class FabricaClientes {
	
	private Input i = new SimpleInput();
	private Output o = new SimpleOutput();
	private String NIF;
	private String nombre;
	private String apellidos;
	private Calendar fecha;
	private String calle;
	private int codPostal;
	private String provincia;
	private String poblacion;
	private String email;
	private Tarifa tar;
	private CreadorTarifas creaTar;
	
	public FabricaClientes(){
		super();
		i = new SimpleInput();
	    o = new SimpleOutput();
	}
	
	public FabricaClientes(Input in, Output o){
		super();
		i = in;
		 if (o == null)
	        	this.o = new SimpleOutput();
	        else 
	        	this.o = o;
	}
	
	public Cliente getPersona(String NIF,
			String nombre,
			String apellidos,
			String calle,
			int codPostal,
			String provincia,
			String poblacion,
			String email,
			Calendar fecha,
			Tarifa tar){
				return new Persona(NIF,nombre,apellidos,
				new Direccion(calle,codPostal,provincia,poblacion),
				email,fecha,tar);		
	}
	
	public Cliente getPersona(){
		creaTar = new CreadorTarifas();
		o.out("NIF");
		NIF = i.get();
		o.out("Nombre");
		nombre = i.get();
		o.out("Apellidos");
		apellidos = i.get();
		o.out("Calle");
		calle = i.get();
		o.out("Cod.Postal");
		codPostal = i.getInt();
		o.out("Provincia");
		provincia = i.get();
		o.out("Poblacion");
		poblacion = i.get();
		o.out("Email");
		email = i.get();
		o.out("Fecha de creación");
		fecha = i.getFechaV2();
		o.out("Tarifa");
		tar = creaTar.getTarifa();
		return new Persona(NIF,nombre,apellidos,
				new Direccion(calle,codPostal,provincia,poblacion),
				email,fecha,tar);		
	}
	
	public Cliente getEmpresa(String NIF,
			String nombre,
			String calle,
			int codPostal,
			String provincia,
			String poblacion,
			String email,
			Calendar fecha,
			Tarifa tar){
				return new Empresa(NIF,nombre,
				new Direccion(calle,codPostal,provincia,poblacion),
				email,fecha,tar);		
	}
	
	public Cliente getEmpresa(){
		creaTar = new CreadorTarifas();
		o.out("NIF");
		NIF = i.get();
		o.out("Nombre");
		nombre = i.get();
		o.out("Calle");
		calle = i.get();
		o.out("Cod.Postal");
		codPostal = i.getInt();
		o.out("Provincia");
		provincia = i.get();
		o.out("Poblacion");
		poblacion = i.get();
		o.out("Email");
		email = i.get();
		o.out("Fecha de creación");
		fecha = i.getFechaV2();
		o.out("Tarifa");
		tar = creaTar.getTarifa();
		return new Empresa(NIF,nombre,
				new Direccion(calle,codPostal,provincia,poblacion),
				email,fecha,tar);
		
	}
}
