import java.util.Objects;

/**
 * Clase que representa una Rueda (neumático) en el inventario de la fábrica.
 * Contiene las especificaciones técnicas necesarias para el montaje y la
 * validación de seguridad de los vehículos.
 * * Al igual que otros componentes, dispone de un identificador único
 * autoincremental para su trazabilidad individual.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class Rueda {

    /** Contador estático para la generación de identificadores únicos. */
    private static int contadorId = 1;

    /** Identificador único de la rueda. */
    private final int id;

    /** Categoría de la rueda (Normal, Deportivo, Todoterreno). */
    private TipoRueda tipoRueda;

    /** Anchura del neumático en milímetros. */
    private double ancho;

    /** Diámetro de la llanta en pulgadas. */
    private double diametro;

    /** Código numérico que indica el peso máximo que puede soportar. */
    private int indiceCarga;

    /** Valor numérico (o mapeo) que indica la velocidad máxima permitida. */
    private int codigoVelocidad;

    /**
     * Constructor para la clase Rueda.
     * * @param tipoRueda       Uso previsto del neumático.
     * @param ancho           Anchura en mm.
     * @param diametro        Diámetro en pulgadas.
     * @param indiceCarga     Capacidad de carga.
     * @param codigoVelocidad Límite de velocidad.
     */
    public Rueda(TipoRueda tipoRueda, int ancho, int diametro, int indiceCarga, int codigoVelocidad) {
        this.id = contadorId++;
        this.tipoRueda = tipoRueda;
        this.ancho = ancho;
        this.diametro = diametro;
        this.indiceCarga = indiceCarga;
        this.codigoVelocidad = codigoVelocidad;
    }

    // region GETTERS Y SETTERS

    /** @return ID único del componente. */
    public int getId() {
        return id;
    }

    public TipoRueda getTipoRueda() {
        return tipoRueda;
    }

    public void setTipoRueda(TipoRueda tipoRueda) {
        this.tipoRueda = tipoRueda;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    public int getIndiceCarga() {
        return indiceCarga;
    }

    public void setIndiceCarga(int indiceCarga) {
        this.indiceCarga = indiceCarga;
    }

    public int getCodigoVelocidad() {
        return codigoVelocidad;
    }

    public void setCodigoVelocidad(int codigoVelocidad) {
        this.codigoVelocidad = codigoVelocidad;
    }
    // endregion

    /**
     * Representación formateada de los datos de la rueda.
     * @return Cadena con las dimensiones y tipo de neumático.
     */
    @Override
    public String toString() {
        return String.format("Rueda [ID=%d] | Tipo: %-12s | Dimensiones: %.0f/%.0f | IC: %d | CV: %d",
                id, tipoRueda, ancho, diametro, indiceCarga, codigoVelocidad);
    }

    /**
     * Compara si dos ruedas son técnicamente idénticas (mismas especificaciones).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rueda rueda = (Rueda) o;
        return Double.compare(rueda.ancho, ancho) == 0 &&
                Double.compare(rueda.diametro, diametro) == 0 &&
                indiceCarga == rueda.indiceCarga &&
                codigoVelocidad == rueda.codigoVelocidad &&
                tipoRueda == rueda.tipoRueda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoRueda, ancho, diametro, indiceCarga, codigoVelocidad);
    }
}