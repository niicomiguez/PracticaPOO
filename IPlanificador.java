/**
 * Interfaz que define las operaciones del motor de simulación de la fábrica.
 * Permite desacoplar el control de la lógica de producción de su implementación concreta,
 * facilitando la gestión de diferentes niveles de complejidad.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public interface IPlanificador {

    /**
     * Inicia el bucle principal de ejecución de la simulación.
     * Coordina la asignación inicial y el avance temporal segundo a segundo.
     */
    void comenzarSimulacion();

    /**
     * Verifica si la simulación ha completado todas las tareas pendientes.
     * @return true si no quedan vehículos en almacén ni en las cadenas de montaje.
     */
    boolean finalizado();

    /**
     * Ejecuta la lógica de simulación de nivel básico.
     * Gestión secuencial sin averías ni imprevistos.
     */
    void modoSimple();

    /**
     * Ejecuta la lógica de simulación de nivel intermedio.
     * Introduce la gestión de averías mecánicas y reparaciones.
     */
    void modoComplejo();

    /**
     * Ejecuta la lógica de simulación de nivel avanzado.
     * Incluye eventos catastróficos como apagones generales gestionados por administración.
     */
    void modoMuyComplejo();

    /**
     * Distribuye de forma aleatoria u optimizada el personal disponible
     * en las diferentes estaciones de las cadenas de montaje.
     */
    void asignarPersonal();

    /**
     * Introduce nuevos chasis desde el almacén a la primera estación libre
     * de cada cadena de montaje correspondiente.
     */
    void asignarVehiculos();
}