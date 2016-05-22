package excepciones;

public class CodigoFacturaException extends Exception{
	private static final long serialVersionUID = 1L;

	public CodigoFacturaException(){
		super("Codigo de Factura no válido");
	}
}
