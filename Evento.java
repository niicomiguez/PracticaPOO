/**
 * Clase que representa un registro histórico de una acción en la fábrica.
 * Permite cumplir con el requisito de trazabilidad por componente y fecha.
 */
public class Evento {
    private final int fecha; // Representa el segundo/tick de la simulación
    private final Object componente; // El objeto real (Motor, Rueda, Tapiceria)
    private final int idVehiculo; // ID del vehículo donde se montó
    private final String descripcion; // Mensaje detallado del suceso
    private final String tipoComponente; // Para facilitar filtrados rápidos

    /**
     * Constructor para un nuevo evento de montaje o reparación.
     * @param fecha Segundo actual de la simulación.
     * @param componente El objeto físico que ha sido manipulado.
     * @param idVehiculo El coche asociado a la acción.
     * @param descripcion Texto informativo para el Dashboard.
     */
    public Evento(int fecha, Object componente, int idVehiculo, String descripcion) {
        this.fecha = fecha;
        this.componente = componente;
        this.idVehiculo = idVehiculo;
        this.descripcion = descripcion;
        
        // Extraemos el nombre de la clase automáticamente (ej: "Motor")
        // Si el componente es nulo (ej: una avería general), evitamos error.
        this.tipoComponente = (componente != null) ? 
                               componente.getClass().getSimpleName() : "General";
    }

    // --- GETTERS (Necesarios para las consultas del DAO y Dashboard) ---

    public int getFecha() {
        return fecha;
    }

    public Object getComponente() {
        return componente;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    /**
     * Facilita la impresión de logs rápidos en consola si fuera necesario.
     */
    @Override
    public String toString() {
        return String.format("[%d s] Componente: %s | Vehículo: %d | Detalle: %s", 
                fecha, tipoComponente, idVehiculo, descripcion);
    }
}