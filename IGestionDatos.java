import java.util.List;

/**
 * Interfaz que define el contrato para la gestión integral de la fábrica.
 * Permite el desacoplamiento entre la lógica de simulación y el sistema de almacenamiento.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public interface IGestionDatos {

    /**
     * Genera un listado de las configuraciones con mayor tasa de ensamblaje.
     * @return Cadena formateada con el ranking de producción.
     */
    String listarConfiguracionesMasEnsambladas();

    /**
     * Lista los vehículos terminados ordenados alfabéticamente por su tipo de clase.
     * @return Cadena con el listado ordenado.
     */
    String listarVehiculosEnsambladosAlfabetico();

    /**
     * Lista todos los vehículos que han finalizado el proceso de montaje.
     * @return Cadena con la información de los vehículos ensamblados.
     */
    String listarVehiculosEnsamblados();

    /**
     * Consulta la producción finalizada en un instante de tiempo concreto.
     * @param fechaBuscada El segundo exacto de la simulación.
     * @return Detalle de los vehículos producidos en ese instante.
     */
    String listarProduccionPorFecha(int fechaBuscada);

    /**
     * Registra un vehículo como finalizado y actualiza los índices de producción.
     * @param nuevoVehiculo El vehículo que sale de la cadena de montaje.
     * @param segundoActual El instante de tiempo de la finalización.
     */
    void anadirVehiculoEnsamblado(Vehiculo nuevoVehiculo, int segundoActual);

    /**
     * Filtra la producción finalizada según el tipo de motor instalado.
     * @param tipoBuscado Categoría de motor.
     * @return Listado de vehículos que coinciden con el criterio.
     */
    String listarVehiculosPorTipoMotor(TipoMotor tipoBuscado);

    /**
     * Filtra la producción finalizada según el tipo de tapicería instalada.
     * @param tipoBuscado Material de la tapicería.
     * @return Listado de vehículos que coinciden con el criterio.
     */
    String listarVehiculosPorTipoTapiceria(TipoTapiceria tipoBuscado);

    /**
     * Filtra la producción finalizada según el tipo de neumáticos instalados.
     * @param tipoBuscado Categoría de la rueda.
     * @return Listado de vehículos que coinciden con el criterio.
     */
    String listarVehiculosPorTipoRueda(TipoRueda tipoBuscado);

    /**
     * Almacena un evento en el historial para garantizar la trazabilidad.
     * @param nuevoEvento El suceso ocurrido.
     */
    void registrarEvento(Evento nuevoEvento);

    /** @return La lista completa de eventos registrados. */
    List<Evento> getHistorial();

    /**
     * Consulta el historial filtrando por el nombre de la clase del componente.
     */
    List<Evento> consultarEventosPorComponente(String tipo);

    /**
     * Consulta los sucesos ocurridos en un instante temporal concreto.
     */
    List<Evento> consultarEventosPorFecha(int fechaBuscada);

    // region GESTIÓN DE COMPONENTES (Motores, Ruedas, Tapicería)
    void anadirMotor(Motor nuevoMotor, int segundoActual);
    List<Motor> getMotores();
    String listarMotores();
    Motor buscarMotorPorId(int idMotor);
    String borrarMotorPorId(int idMotor, int segundoActual);

    void anadirRueda(Rueda nuevaRueda, int segundoActual);
    List<Rueda> getRuedas();
    String listarRuedas();
    Rueda buscarRuedaPorId(int idRueda);
    String borrarRuedaPorId(int idRueda, int segundoActual);

    void anadirTapiceria(Tapiceria nuevaTapiceria, int segundoActual);
    List<Tapiceria> getTapicerias();
    String listarTapicerias();
    Tapiceria buscarTapiceriaPorId(int idTapiceria);
    String borrarTapiceriaPorId(int idTapiceria, int segundoActual);
    // endregion

    // region GESTIÓN TRABAJADORES
    void anadirTrabajador(Trabajador nuevoTrabajador);
    String listarTrabajadores();
    Trabajador buscarTrabajadorPorDNI(String dniBuscado);
    String borrarTrabajadorPorDNI(String dni);

    List<Operario> obtenerSoloOperarios();
    String obtenerOperariosProductividad();
    String obtenerOperariosOrdenAlfabetico();

    List<Administrador> obtenerSoloAdministradores();
    List<GestorPlanta> obtenerSoloGestores();
    List<Mecanico> obtenerSoloMecanicos();
    // endregion

    // region GESTIÓN DE VEHÍCULOS (CHASIS)
    void anadirVehiculo(Vehiculo nuevoVehiculo);
    List<Vehiculo> getVehiculos();
    String listarVehiculos();
    Vehiculo buscarVehiculoPorId(int idVehiculo);
    String borrarVehiculoPorId(int idVehiculo, int segundoActual);

    List<Turismo> obtenerSoloTurismos();
    List<BiplazaDeportivo> obtenerSoloBiplazas();
    List<Furgoneta> obtenerSoloFurgonetas();
    // endregion

    // region VALIDACIÓN DE STOCK
    boolean hayMotor();
    boolean hayTapiceria();
    boolean hayRuedas();
    // endregion

    /**
     * Carga un conjunto de datos iniciales para pruebas.
     */
    void cargarDatosPrueba();
}