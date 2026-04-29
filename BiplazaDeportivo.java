/**
 * Clase que representa un vehículo de tipo Biplaza Deportivo.
 * Especialización de la clase Vehiculo que se caracteriza por tener
 * una configuración fija de dos plazas para la simulación.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class BiplazaDeportivo extends Vehiculo {

    /**
     * Constructor para la clase BiplazaDeportivo.
     * * @param color        Color de la carrocería del vehículo.
     * @param taraVehiculo Peso del vehículo en vacío (kg).
     * @param pesoMax      Peso máximo autorizado (kg).
     */
    public BiplazaDeportivo(String color, int taraVehiculo, double pesoMax) {

        // Las plazas siempre son 2
        super(color, 2, taraVehiculo, pesoMax);
    }
}