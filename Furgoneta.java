
/**
 * Write a description of class Furgoneta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;

public class Furgoneta extends Vehiculo {

    public Furgoneta(String color, int plazas, int taraVehiculo, double pesoMax, Motor motor, Tapiceria tapiceria, List<Rueda> ruedas, double volumenCarga) {
        super(color, plazas, taraVehiculo,  pesoMax, motor, tapiceria, ruedas);
    }


}