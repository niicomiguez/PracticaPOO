
/**
 * Write a description of class BiplazaDeportivo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;

// Las plazas siempre son 2

public class BiplazaDeportivo extends Vehiculo {

    public BiplazaDeportivo(String bastidor,String color, int taraVehiculo, double pesoMax) {
        
        super(bastidor,color,2, taraVehiculo, pesoMax);
    }
}