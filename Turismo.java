/**
 * Clase que representa un vehículo de tipo Turismo dentro de la fábrica.
 * El Turismo es el modelo estándar de producción, caracterizado por una
 * configuración de plazas y dimensiones equilibradas para el uso particular.
 * * Hereda toda la estructura funcional de la clase abstracta Vehiculo,
 * permitiendo su paso por las distintas estaciones de la cadena de montaje.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class Turismo extends Vehiculo {

    /**
     * Constructor para la clase Turismo.
     * * @param color         Color de la carrocería del chasis.
     * @param plazas        Número de asientos disponibles.
     * @param taraVehiculo  Peso del vehículo en vacío (kg).
     * @param pesoMax       Peso máximo autorizado (kg).
     */
    public Turismo(String color, int plazas, int taraVehiculo, double pesoMax) {
        // Llama al constructor de la superclase Vehiculo para inicializar el chasis
        super(color, plazas, taraVehiculo, pesoMax);
    }
}