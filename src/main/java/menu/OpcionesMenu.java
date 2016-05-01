package menu;

import menu.acciones.*;

public enum OpcionesMenu {
	SALIR("Salir", new Salir()),
    ALTAC("Dar de alta un nuevo cliente.", new Alta_nueva()),
	BORRARC("Borrar un cliente.", new Borrar_cliente()),
    CAMBIARTAR("Cambiar la tarifa de un cliente.", new Cambiar_tarifa()),
    MUESTRA("Muestra todos los clientes.", new ShowAllClientes()),
    MUESTRA_ENTRE("Muestra todos los clientes entre dos fechas.", new ClientesEntreFechas()),
    CLIENTENIF("Muestra la información de un cliente.", new Cliente_NIF()),
    GENERA("Genera clientes aleatorios.", new Genera()),
    SELECT("Elegir a un cliente para gestionar llamadas o facturas.", new Select());
    
    private String descripcion;
    private Accion accion;
    
    private OpcionesMenu(String descripcion, Accion accion) {
        this.descripcion = descripcion;
        this.accion = accion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public static OpcionesMenu getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(OpcionesMenu opcion: OpcionesMenu.values()) {
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