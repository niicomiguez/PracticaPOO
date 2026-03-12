import java.awt.Color;
import java.util.List;

/**
 * Write a description of class Vehiculo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vehiculo {
    private Color color;
    private int plazas;
    private int taraVehiculo;
    private int pesoMax;
    private Motor motor;
    private Tapiceria tapiceria;
    private List<Rueda> ruedas;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getnPlazas() {
        return plazas;
    }

    public void setnPlazas(int nPlazas) {
        this.plazas = nPlazas;
    }

    public int getTaraVehiculo() {
        return taraVehiculo;
    }

    public void setTaraVehiculo(int taraVehiculo) {
        this.taraVehiculo = taraVehiculo;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public int getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(int pesoMax) {
        this.pesoMax = pesoMax;
    }

    public Tapiceria getTapiceria() {
        return tapiceria;
    }

    public void setTapiceria(Tapiceria tapiceria) {
        this.tapiceria = tapiceria;
    }

    public List<Rueda> getRuedas() {
        return ruedas;
    }

    public void setRuedas(List<Rueda> ruedas) {
        this.ruedas = ruedas;
    }
}

