import java.awt.Color;
import java.util.Arrays;
import java.util.List;

/**
 * Write a description of class Vehiculo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Vehiculo {
    private static int contadorId = 1;
    private final int idVehiculo;
    private String color;
    private int plazas;
    private int taraVehiculo;
    private double pesoMax;
    private Motor motor;
    private Tapiceria tapiceria;
    private List<Rueda> ruedas;
    private EstadoVehiculo estado;

    public Vehiculo(String color, int plazas, int taraVehiculo, double pesoMax) {
        this.idVehiculo=contadorId++;
        this.color = color;
        this.plazas = plazas;
        this.taraVehiculo = taraVehiculo;
        this.pesoMax = pesoMax;        
        this.motor = null;
        this.tapiceria = null;
        this.ruedas = new java.util.ArrayList<>();
        this.estado = EstadoVehiculo.ND;
    }
    public void siguienteEstado() {
            EstadoVehiculo[] estados = EstadoVehiculo.values();
            int actual = this.estado.ordinal();
    
            // Comprobamos que no estemos ya en el último estado
            if (actual < estados.length - 1) {
                this.estado = estados[actual + 1];
            }
    }
    public int getId() {
        return idVehiculo;
    }
    public EstadoVehiculo getEstado(){
        return estado;
    }
    
    public void setEstado(EstadoVehiculo estado){
        this.estado=estado;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
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
        return String.format("[%s] Color: %-15s | Motor: %-10s | Tapicería: %-10s | Ruedas: %d",
                this.getClass().getSimpleName(),
                color,
                (motor != null ? motor.getTipoMotor() : "Ninguno"),
                (tapiceria != null ? tapiceria.getTipoTapiceria() : "Ninguna"),
                ruedas.size());
    }
}

