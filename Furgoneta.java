/**
 * Clase que representa un vehículo de tipo Furgoneta.
 * Especialización de la clase Vehiculo diseñada para el transporte de carga
 * o pasajeros, permitiendo configurar el número de plazas según el modelo.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */

public class Furgoneta extends Vehiculo {

    /**
     * Constructor para la clase Furgoneta.
     * * @param color        Color de la carrocería.
     * @param plazas       Número de asientos configurados para el vehículo.
     * @param taraVehiculo Peso del vehículo en vacío (kg).
     * @param pesoMax      Peso máximo autorizado (kg).
     */
    public Furgoneta(String color, int plazas, int taraVehiculo, double pesoMax) {
        // Se transfieren los parámetros a la superclase Vehiculo
        super(color, plazas, taraVehiculo, pesoMax);
    }
}