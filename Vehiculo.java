import java.awt.Color;
import java.util.List;

/**
 * Write a description of class Vehiculo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vehiculo {
    private String color;
    private int plazas;
    private int taraVehiculo;
    private double pesoMax;
    private Motor motor;
    private Tapiceria tapiceria;
    private List<Rueda> ruedas;

    public Vehiculo(String color, int plazas, int taraVehiculo, double pesoMax, Motor motor, Tapiceria tapiceria, List<Rueda> ruedas) {
        this.color = color;
        this.plazas = plazas;
        this.taraVehiculo = taraVehiculo;
        this.pesoMax = pesoMax;
        this.motor = motor;
        this.tapiceria = tapiceria;
        this.ruedas = ruedas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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

    public double getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(double pesoMax) {
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

    @Override
    public String toString() {
        return "Vehiculo{" +
                "color=" + color +
                ", plazas=" + plazas +
                ", taraVehiculo=" + taraVehiculo +
                ", pesoMax=" + pesoMax +
                ", motor=" + motor +
                ", tapiceria=" + tapiceria +
                ", ruedas=" + ruedas +
                '}';
    }

}

