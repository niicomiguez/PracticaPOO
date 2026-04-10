import java.util.Random;

/**
 * Representa cada uno de los 4 puestos de trabajo dentro de una cadena de montaje.
 */
public class EstacionMontaje {
    private int idFase; // 0: CHASIS, 1: MOTOR, 2: TAPICERIA, 3: RUEDAS
    private Operario operario;
    private Vehiculo vehiculo;
    private int tiempoRestante;

    /**
     * Constructor de la estación.
     * @param idFase El número de fase (0-3) que le corresponde a esta estación.
     */
    public EstacionMontaje(int idFase) {
        this.idFase = idFase;
        this.operario = null;
        this.vehiculo = null;
        this.tiempoRestante = 0;
    }

    /**
     * Comprueba si la estación no tiene ningún vehículo.
     */
    public boolean estaLibre() {
        return this.vehiculo == null;
    }

    /**
     * Asigna un vehículo a la estación si está libre y el estado es correcto.
     * @param v Vehículo a procesar.
     * @return true si se pudo recibir, false en caso contrario.
     */
    public boolean recibirVehiculo(Vehiculo v) {
        // Suponiendo que Vehiculo tiene un método getEstadoOrdinal() 
        // que devuelve 0 para CHASIS, 1 para MOTOR, etc.
        if (estaLibre() && v != null && v.getEstado().ordinal() == this.idFase) {
            this.vehiculo = v;
            return true;
        }
        return false;
    }

    /**
     * Libera la estación de vehículo y operario al terminar la fase.
     */
    public void liberarEstacion() {
        this.vehiculo = null;
        this.operario = null;
        this.tiempoRestante = 0;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdFase() {
        return idFase;
    }

    public Operario getOperario() {
        return operario;
    }

    /**
     * Al asignar el operario, automáticamente cargamos el tiempoRestante
     * basándonos en la eficiencia del trabajador.
     */
    public void setOperario(Operario operario) {
        this.operario = operario;
        if (operario != null) {
            this.tiempoRestante = operario.getTiempoTarea();
        }
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }
}