public class EstacionMontaje {
    private Operario operarioAsignado;
    private Vehiculo vehiculoEnEstacion; // Guardamos el objeto completo
    private int tiempoTrabajado;
    private boolean trabajoTerminado;

    public EstacionMontaje() {
        this.operarioAsignado = null;
        this.vehiculoEnEstacion = null; // null significa que la estación está vacía
        this.tiempoTrabajado = 0;
        this.trabajoTerminado = false;
    }

    // --- MÉTODOS DE ESTADO ---

    public boolean estaLibre() {
        return this.vehiculoEnEstacion == null;
    }

    public void vaciarEstacion() {
        this.vehiculoEnEstacion = null;
        this.tiempoTrabajado = 0;
        this.trabajoTerminado = false;
    }

    // --- MÉTODOS DE TIEMPO ---

    public void incrementarTiempoTrabajado() {
        this.tiempoTrabajado++;
    }

    // --- GETTERS Y SETTERS ---

    public Vehiculo getVehiculoEnEstacion() {
        return vehiculoEnEstacion;
    }

    /**
     * Al meter un vehículo, reseteamos automáticamente los contadores
     */
    public void setVehiculoEnEstacion(Vehiculo v) {
        this.vehiculoEnEstacion = v;
        this.tiempoTrabajado = 0;
        this.trabajoTerminado = false;
    }

    public int getTiempoTrabajado() {
        return tiempoTrabajado;
    }

    public boolean isTrabajoTerminado() {
        return trabajoTerminado;
    }

    public void setTrabajoTerminado(boolean trabajoTerminado) {
        this.trabajoTerminado = trabajoTerminado;
    }

    public Operario getOperarioAsignado() {
        return operarioAsignado;
    }

    public void setOperarioAsignado(Operario operarioAsignado) {
        this.operarioAsignado = operarioAsignado;
    }
}