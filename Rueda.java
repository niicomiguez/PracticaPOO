import java.util.Objects;
/**
 * Write a description of class Rueda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rueda {

    private static int contadorId = 1;
    private TipoRueda tipoRueda;
    private double ancho;
    private double diametro;
    private int indiceCarga;
    private int codigoVelocidad;
    private int id;

    public int getId() {
        return id;
    }

    public TipoRueda getTipoRueda() {
        return tipoRueda;
    }

    public Rueda(TipoRueda tipoRueda, int ancho, int diametro, int indiceCarga, int codigoVelocidad) {
        this.id = contadorId++;
        this.tipoRueda = tipoRueda;
        this.ancho = ancho;
        this.diametro = diametro;
        this.indiceCarga = indiceCarga;
        this.codigoVelocidad = codigoVelocidad;
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

    @Override
    public String toString() {
        return "Rueda{" +
                "tipoRueda=" + tipoRueda +
                ", ancho=" + ancho +
                ", diametro=" + diametro +
                ", indiceCarga=" + indiceCarga +
                ", codigoVelocidad=" + codigoVelocidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rueda rueda = (Rueda) o;
        return ancho == rueda.ancho && diametro == rueda.diametro && indiceCarga == rueda.indiceCarga && codigoVelocidad == rueda.codigoVelocidad && tipoRueda == rueda.tipoRueda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoRueda, ancho, diametro, indiceCarga, codigoVelocidad);
    }
}


