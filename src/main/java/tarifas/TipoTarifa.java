package tarifas;

public enum TipoTarifa {
	SALIR("Finalizar."),
	LUNES("Tarifa de LUNES."),
	MARTES("Tarifa de MARTES."),
	MIERCOLES("Tarifa de MIERCOLES."),
	JUEVES("Tarifa de JUEVES."),
	VIERNES("Tarifa de VIERNES."),
	SABADO("Tarifa de SABADO."),
	DOMINGO("Tarifa de DOMINGO."),
	MAÑANA("Tarifa de tarde."),
	TARDE("Tarifa de tarde."),
	NOCHE("Tarifa de noche.");
	
	private String descripcion;
	
	private TipoTarifa(String descripcion) {
        this.descripcion = descripcion;
    }
	
	 public String getDescripcion() {
	        return descripcion;
	    }
    
	public static TipoTarifa getOpcion(int posicion) {
        return values()[posicion];
    }
    
    public static String getTarifas() {
        StringBuilder sb = new StringBuilder();
        for(TipoTarifa opcion: TipoTarifa.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }
	
}
