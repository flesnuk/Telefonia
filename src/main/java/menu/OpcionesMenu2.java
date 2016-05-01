package menu;

import menu.acciones.Accion;
import menu.acciones.cliente.*;

public enum OpcionesMenu2 {
	VOLVER("Volver", new Volver()),
    ANADIR_LLAMADA("Añadir una nueva llamada.", new AddLlamada()),
	LISTAR_LLAMADAS("Listar todas las llamadas del cliente.", new ListarLlamadas()),
	LISTAR_LLAMADAS_ENTRE("Listar todas las llamadas del cliente entre dos fechas.", new ListarLlamadasEntre()),
	EMITIR("Emitir una factura eligiendo el periodo de facturación", new Emitir()),
	LISTAR_FACT("Listar todas las facturas del cliente.", new ListarFacturas()),
	LISTAR_FACT_ENTRE("Listar todas las facturas del cliente entre dos fechas.", new ListarFacturasEntre());	
    
    private String descripcion;
    private Accion accion;
    
    private OpcionesMenu2(String descripcion, Accion accion) {
        this.descripcion = descripcion;
        this.accion = accion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public static OpcionesMenu2 getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(OpcionesMenu2 opcion: OpcionesMenu2.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public void realizaAccion() {
    	accion.accion();
    }
}