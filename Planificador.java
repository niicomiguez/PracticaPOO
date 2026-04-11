/*
import java.util.List;
import java.util.ArrayList;

*/
/**
 * Clase encargada de coordinar el tiempo y las acciones de la fábrica.
 * Actúa como el motor de la simulación.
 *//*

public class Planificador {
    private int segundoActual;
    private TipoSimulacion modo;
    private List<CadenaMontaje> cadenas;
    private GestionFabricaDAO dao;
    private int contadorBastidor = 1;

    */
/**
     * Constructor del Planificador.
     * @param modo El modo de simulación (SIMPLE, COMPLEJO, etc.)
     * @param dao El objeto de acceso a datos para registrar eventos y consultar plantilla.
     *//*

    public Planificador(TipoSimulacion modo, GestionFabricaDAO dao) {
        this.segundoActual = 0;
        this.modo = modo;
        this.dao = dao;
        this.cadenas = new ArrayList<>();
        
    }

    */
/**
     * Avanza un segundo en la simulación y ejecuta la lógica de todas las cadenas.
     *//*

    public void tick() {
        this.segundoActual++;
        
        for (CadenaMontaje cadena : cadenas) {
            // 1. Mover vehículos entre estaciones (o sacarlos si han terminado)
            cadena.moverVehiculos(dao, segundoActual);

            // 2. Asignar operarios aleatorios a las estaciones que lo necesiten
            cadena.asignarOperariosAleatorios(dao, segundoActual);

            // 3. Ejecutar el trabajo (descontar tiempo en las estaciones operativas)
            cadena.avanzarSegundo();
            
            // 4. Intentar introducir un nuevo vehículo si la primera estación está vacía
            introducirNuevoVehiculo(cadena);
        }
    }

    */
/**
     * Lógica interna para crear un vehículo y meterlo en la cadena.
     *//*

    private void introducirNuevoVehiculo(CadenaMontaje cadena) {
        // Obtenemos la primera estación (Fase 0: CHASIS)
        EstacionMontaje primeraEstacion = cadena.getEstaciones().get(0);
        
        if (primeraEstacion.estaLibre()) {
            // Generamos un bastidor único para trazabilidad
            String bastidor = "VIN-" + String.format("%04d", contadorBastidor++);
            
            // Fabricamos el vehículo (por defecto Turismo, o según lógica de negocio)

        }
    }

    */
/**
     * Añade una cadena de montaje al sistema de planificación.
     *//*

    public void añadirCadena(CadenaMontaje cadena) {
        if (cadena != null) {
            this.cadenas.add(cadena);
        }
    }

    // --- Getters y Setters ---

    public int getSegundoActual() {
        return segundoActual;
    }

    public TipoSimulacion getModo() {
        return modo;
    }

    public void setModo(TipoSimulacion modo) {
        this.modo = modo;
    }

    public List<CadenaMontaje> getCadenas() {
        return cadenas;
    }
}*/
