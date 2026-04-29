import java.util.List;

/**
 * Clase abstracta que define la estructura y el comportamiento de un Vehículo.
 * Representa el "chasis" inicial que evoluciona a medida que atraviesa las
 * distintas estaciones de montaje, incorporando componentes (Motor, Ruedas, Tapicería)
 * y actualizando su estado interno de fabricación.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public abstract class Vehiculo {
    /** Contador estático para garantizar que cada chasis tenga un número de bastidor único. */
    private static int contadorId = 1;

    private final int idVehiculo;
    private String color;
    private int plazas;
    private int taraVehiculo;
    private double pesoMax;

    // Componentes que se instalan durante la simulación
    private Motor motor;
    private Tapiceria tapiceria;
    private List<Rueda> ruedas;

    /** Fase actual del proceso de producción en la que se encuentra el vehículo. */
    private EstadoVehiculo estado;

    /**
     * Constructor para la clase Vehiculo.
     * Inicializa el chasis con sus propiedades físicas y establece el estado inicial como ND.
     * * @param color         Color de la carrocería.
     * @param plazas        Capacidad de pasajeros.
     * @param taraVehiculo  Peso en vacío.
     * @param pesoMax       Peso máximo técnico.
     */
    public Vehiculo(String color, int plazas, int taraVehiculo, double pesoMax) {
        this.idVehiculo = contadorId++;
        this.color = color;
        this.plazas = plazas;
        this.taraVehiculo = taraVehiculo;
        this.pesoMax = pesoMax;
        this.motor = null;
        this.tapiceria = null;
        this.ruedas = new java.util.ArrayList<>();
        this.estado = EstadoVehiculo.ND;
    }

    /**
     * Hace avanzar al vehículo a la siguiente fase del proceso de montaje.
     * Utiliza el orden definido en el enumerado EstadoVehiculo.
     */
    public void siguienteEstado() {
        EstadoVehiculo[] estados = EstadoVehiculo.values();
        int actual = this.estado.ordinal();

        if (actual < estados.length - 1) {
            this.estado = estados[actual + 1];
        }
    }

    // region GETTERS Y SETTERS

    public int getId() { return idVehiculo; }

    public EstadoVehiculo getEstado() { return estado; }

    public void setEstado(EstadoVehiculo estado) { this.estado = estado; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public int getPlazas() { return plazas; }

    public void setPlazas(int plazas) { this.plazas = plazas; }

    public int getTaraVehiculo() { return taraVehiculo; }

    public void setTaraVehiculo(int taraVehiculo) { this.taraVehiculo = taraVehiculo; }

    public Motor getMotor() { return motor; }

    public void setMotor(Motor motor) { this.motor = motor; }

    public double getPesoMax() { return pesoMax; }

    public void setPesoMax(double pesoMax) { this.pesoMax = pesoMax; }

    public Tapiceria getTapiceria() { return tapiceria; }

    public void setTapiceria(Tapiceria tapiceria) { this.tapiceria = tapiceria; }

    public List<Rueda> getRuedas() { return ruedas; }

    public void setRuedas(List<Rueda> ruedas) { this.ruedas = ruedas; }
    // endregion

    /**
     * Proporciona una visión rápida del estado de ensamblaje del vehículo.
     * @return Cadena con el tipo de vehículo, color y componentes instalados.
     */
    @Override
    public String toString() {
        return String.format("[%s] ID: %d | Estado: %-12s | Motor: %-10s | Ruedas: %d/4",
                this.getClass().getSimpleName(),
                idVehiculo,
                estado,
                (motor != null ? motor.getTipoMotor() : "Falta"),
                ruedas.size());
    }
}