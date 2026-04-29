/**
 * Clase que representa una unidad de trabajo individual dentro de una cadena de montaje.
 * Gestiona la interacción entre el vehículo que se está procesando, el operario
 * asignado y el progreso temporal de la tarea de ensamblaje correspondiente.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class EstacionMontaje {
    /** Operario asignado actualmente a esta estación de trabajo. */
    private Operario operarioAsignado;
    /** Vehículo que se encuentra actualmente en la estación para su procesamiento. */
    private Vehiculo vehiculoEnEstacion;
    /** Tiempo acumulado de trabajo realizado sobre el vehículo actual. */
    private int tiempoTrabajado;
    /** Indica si la tarea de montaje prevista en esta estación ha finalizado. */
    private boolean trabajoTerminado;

    /**
     * Constructor de la clase EstacionMontaje.
     * Inicializa la estación en estado vacío y sin personal asignado.
     */
    public EstacionMontaje() {
        this.operarioAsignado = null;
        this.vehiculoEnEstacion = null; // null significa que la estación está vacía
        this.tiempoTrabajado = 0;
        this.trabajoTerminado = false;
    }

    // --- MÉTODOS DE ESTADO ---

    /**
     * Verifica si la estación se encuentra disponible para recibir un nuevo vehículo.
     * @return true si no hay ningún vehículo en la estación, false en caso contrario.
     */
    public boolean estaLibre() {
        return this.vehiculoEnEstacion == null;
    }

    /**
     * Resetea el estado de la estación, eliminando el vehículo y reiniciando contadores.
     */
    public void vaciarEstacion() {
        this.vehiculoEnEstacion = null;
        this.tiempoTrabajado = 0;
        this.trabajoTerminado = false;
    }

    // --- MÉTODOS DE TIEMPO ---

    /**
     * Incrementa en una unidad el contador de tiempo dedicado al montaje actual.
     */
    public void incrementarTiempoTrabajado() {
        this.tiempoTrabajado++;
    }

    // --- GETTERS Y SETTERS ---

    /**
     * @return El objeto Vehiculo presente en la estación, o null si está vacía.
     */
    public Vehiculo getVehiculoEnEstacion() {
        return vehiculoEnEstacion;
    }

    /**
     * Asigna un vehículo a la estación y resetea automáticamente los contadores de progreso.
     * @param v El vehículo que entra en la estación.
     */
    public void setVehiculoEnEstacion(Vehiculo v) {
        this.vehiculoEnEstacion = v;
        this.tiempoTrabajado = 0;
        this.trabajoTerminado = false;
    }

    /**
     * @return El tiempo que se ha trabajado en la fase actual.
     */
    public int getTiempoTrabajado() {
        return tiempoTrabajado;
    }

    /**
     * @return true si la tarea de la estación ha finalizado, false en caso contrario.
     */
    public boolean isTrabajoTerminado() {
        return trabajoTerminado;
    }

    /**
     * @param trabajoTerminado Estado de finalización de la tarea a establecer.
     */
    public void setTrabajoTerminado(boolean trabajoTerminado) {
        this.trabajoTerminado = trabajoTerminado;
    }

    /**
     * @return El operario que está trabajando actualmente en esta estación.
     */
    public Operario getOperarioAsignado() {
        return operarioAsignado;
    }

    /**
     * @param operarioAsignado El operario que se vincula a esta estación.
     */
    public void setOperarioAsignado(Operario operarioAsignado) {
        this.operarioAsignado = operarioAsignado;
    }
}