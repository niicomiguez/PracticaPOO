/**
 * Clase que representa un registro histórico de una acción o suceso en la fábrica.
 * Permite cumplir con el requisito de trazabilidad por componente, fecha y vehículo,
 * almacenando la información necesaria para auditar el proceso productivo.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class Evento {
    /** Segundo o tick exacto de la simulación en el que ocurre el suceso. */
    private final int fecha;
    /** Referencia al objeto físico involucrado (Motor, Rueda, Tapicería, etc.). */
    private final Object componente;
    /** Identificador único del vehículo asociado a la acción. */
    private final int idVehiculo;
    /** Mensaje descriptivo detallado del suceso para el usuario. */
    private final String descripcion;
    /** Identificador textual del tipo de clase del componente para filtrado. */
    private final String tipoComponente;

    /**
     * Constructor para registrar un nuevo evento de montaje, reparación o incidencia.
     * * @param fecha        Segundo actual de la simulación.
     * @param componente   El objeto físico que ha sido manipulado.
     * @param idVehiculo   El código de identificación del coche asociado.
     * @param descripcion  Texto informativo detallado para el registro.
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

    // --- GETTERS ---

    /**
     * @return El instante de tiempo (en segundos) del evento.
     */
    public int getFecha() {
        return fecha;
    }

    /**
     * @return El objeto componente relacionado con el evento.
     */
    public Object getComponente() {
        return componente;
    }

    /**
     * @return El identificador del vehículo implicado.
     */
    public int getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * @return La descripción textual del suceso.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return El nombre simple de la clase del componente.
     */
    public String getTipoComponente() {
        return tipoComponente;
    }

    /**
     * Genera una representación textual formateada del evento para logs o consola.
     * * @return Cadena con el formato [tiempo] Componente | Vehículo | Detalle.
     */
    @Override
    public String toString() {
        return String.format("[%d s] Componente: %s | Vehículo: %d | Detalle: %s",
                fecha, tipoComponente, idVehiculo, descripcion);
    }
}