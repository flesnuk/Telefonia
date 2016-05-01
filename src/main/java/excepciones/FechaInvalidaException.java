package excepciones;

public class FechaInvalidaException extends Exception{
	private static final long serialVersionUID = 1L;

	public FechaInvalidaException(){
		super("Fecha Invalida");
	}
}
