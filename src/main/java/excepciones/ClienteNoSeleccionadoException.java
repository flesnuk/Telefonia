package excepciones;

public class ClienteNoSeleccionadoException extends Exception{
	private static final long serialVersionUID = 1L;

	public ClienteNoSeleccionadoException(){
		super("Cliente no seleccionado");
	}
}
