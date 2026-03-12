import java.util.List;

/**
 * Write a description of class FactoriaCoches here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FactoriaCoches {
    
    public enum TipoVehiculo {
           TURISMO,BIPLAZA,FURGONETA
    }

    /**
     * Crea un vehículo según el tipo indicado.
     * Según el enunciado, cada tipo tiene su propia cadena de montaje.
     */
    public Vehiculo fabricarVehiculo(TipoVehiculo tipo, String color, int plazas, int tara, 
                                     double pesoMax, Motor motor, Tapiceria tapiceria, 
                                     List<Rueda> ruedas) {  
        // Todos empiezan en estado CHASIS
        Vehiculo.EstadoVehiculo estadoInicial = Vehiculo.EstadoVehiculo.CHASIS;

        if (tipo == TipoVehiculo.TURISMO) {
        return new Turismo(color, plazas, tara, pesoMax, motor, tapiceria, ruedas, estadoInicial);
    } 
    else if (tipo == TipoVehiculo.FURGONETA) {
        return new Furgoneta(color, plazas, tara, pesoMax, motor, tapiceria, ruedas, estadoInicial);
    } 
    else if (tipo == TipoVehiculo.BIPLAZA) {
        return new BiplazaDeportivo(color, 2, tara, pesoMax, motor, tapiceria, ruedas, estadoInicial);
    }

        return null;
    }
}