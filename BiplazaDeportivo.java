
/**
 * Write a description of class BiplazaDeportivo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;

public class BiplazaDeportivo extends Vehiculo {

    public BiplazaDeportivo(String color, int plazas, int taraVehiculo, double pesoMax, 
                            Motor motor, Tapiceria tapiceria, List<Rueda> ruedas, 
                            EstadoVehiculo estado) {
        
        super(color, 2, taraVehiculo, pesoMax, motor, tapiceria, ruedas, estado);
    }
}