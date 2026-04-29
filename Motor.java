/**
 * Clase que representa un Motor en el sistema de fabricación.
 * Cada motor cuenta con un identificador único generado automáticamente
 * y especificaciones técnicas que definen su rendimiento y compatibilidad
 * con los diferentes tipos de vehículos.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class Motor {

    /** Contador estático para asignar identificadores únicos de forma secuencial. */
    private static int contadorId = 1;

    /** Identificador único del motor. */
    private final int id;

    /** Categoría del motor (Gasolina, Eléctrico, Híbrido). */
    private TipoMotor tipoMotor;

    /** Volumen total de los cilindros (en cc o litros). */
    private double cilindrada;

    /** Potencia máxima del motor (en CV o kW). */
    private double potencia;

    /** Número de cilindros (0 en caso de motores eléctricos). */
    private int cilindros;

    /**
     * Constructor para la clase Motor.
     * Incrementa automáticamente el contador global para asignar el ID.
     * * @param tipoMotor  Tipo de combustible o energía que utiliza.
     * @param cilindrada Volumen de desplazamiento del motor.
     * @param potencia   Fuerza del motor.
     * @param cilindros  Cantidad de cilindros instalados.
     */
    public Motor(TipoMotor tipoMotor, double cilindrada, double potencia, int cilindros) {
        this.id = contadorId++;
        this.tipoMotor = tipoMotor;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.cilindros = cilindros;
    }

    // region GETTERS Y SETTERS

    /** @return El ID único del componente. */
    public int getId() {
        return id;
    }

    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(TipoMotor tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public int getCilindros() {
        return cilindros;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }
    // endregion

    /**
     * Devuelve una representación textual de las especificaciones del motor.
     * @return Cadena con los datos técnicos y el ID.
     */
    @Override
    public String toString() {
        return String.format("Motor [ID=%d] | Tipo: %-10s | Potencia: %6.1f | Cilindrada: %6.1f | Cilindros: %d",
                id, tipoMotor, potencia, cilindrada, cilindros);
    }
}