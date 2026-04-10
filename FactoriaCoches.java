import java.util.List;

/**
 * Factoría encargada de instanciar los diferentes tipos de vehículos.
 */
public class FactoriaCoches {

    /**
     * Crea un vehículo según el tipo indicado.
     * Se elimina el paso de componentes (motor, tapiceria, ruedas) porque 
     * el vehículo comienza en fase CHASIS y está vacío.
     */
    public Vehiculo fabricarVehiculo(TipoVehiculo tipo, String bastidor, String color, 
                                     int plazas, int tara, double pesoMax) {  

        if (tipo == TipoVehiculo.TURISMO) {
            // El constructor de Turismo ahora debe aceptar el bastidor y no los componentes
            return new Turismo(bastidor, color, plazas, tara, pesoMax);
        } 
        else if (tipo == TipoVehiculo.FURGONETA) {
            return new Furgoneta(bastidor, color, plazas, tara, pesoMax);
        } 
        else if (tipo == TipoVehiculo.BIPLAZA) {
            // Forzamos 2 plazas por ser biplaza deportivo
            return new BiplazaDeportivo(bastidor, color, tara, pesoMax);
        }

        return null;
    }
}