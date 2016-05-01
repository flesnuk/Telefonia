package excepciones;

public class OrdenFechasException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrdenFechasException(){
		super("Fecha final no puede ser anterior a la inicial");
	}
}
