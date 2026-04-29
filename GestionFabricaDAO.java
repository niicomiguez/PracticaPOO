/**
 * Clase de Objeto de Acceso a Datos (DAO) para la gestión integral de la fábrica.
 * Centraliza el almacenamiento y manipulación de componentes, trabajadores,
 * vehículos y el registro histórico de eventos (trazabilidad).
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
import java.util.List;
import java.util.ArrayList;

public class GestionFabricaDAO {
    private final List<Motor> motores;
    private final List<Rueda> ruedas;
    private final List<Tapiceria> tapiceria;
    private final List<Trabajador> trabajadores;
    private final List<Evento> historialMontaje = new ArrayList<>();
    private final List<Vehiculo> vehiculos;
    private final List<Vehiculo> vehiculosEnsamblados;
    private final java.util.Map<Integer, List<Vehiculo>> produccionPorFecha = new java.util.HashMap<>();

    public GestionFabricaDAO() {
        this.motores = new ArrayList<>();
        this.ruedas = new ArrayList<>();
        this.tapiceria = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.vehiculosEnsamblados = new ArrayList<>();
    }

    /**
     * Genera un listado de las configuraciones con mayor tasa de ensamblaje.
     * Analiza los vehículos terminados para agruparlos por tipo, motor y tapicería.
     * @return Cadena formateada con el ranking de producción.
     */
    public String listarConfiguracionesMasEnsambladas() {
        if (vehiculosEnsamblados.isEmpty()) return "No hay datos de producción para generar estadísticas.";

        java.util.Map<String, Integer> conteo = new java.util.HashMap<>();

        for (Vehiculo v : vehiculosEnsamblados) {
            String config = v.getClass().getSimpleName() + " [" +
                    (v.getMotor() != null ? v.getMotor().getTipoMotor() : "Sin Motor") + " / " +
                    (v.getTapiceria() != null ? v.getTapiceria().getTipoTapiceria() : "Sin Tapicería") + "]";

            conteo.put(config, conteo.getOrDefault(config, 0) + 1);
        }

        List<java.util.Map.Entry<String, Integer>> ranking = new ArrayList<>(conteo.entrySet());
        ranking.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        StringBuilder sb = new StringBuilder("--- TASA DE ENSAMBLAJE POR CONFIGURACIÓN ---\n");
        for (java.util.Map.Entry<String, Integer> entrada : ranking) {
            sb.append(String.format("%-45s | Unidades: %d\n", entrada.getKey(), entrada.getValue()));
        }

        return sb.toString();
    }

    /**
     * Lista los vehículos terminados ordenados alfabéticamente por su tipo de clase.
     * @return Cadena con el listado ordenado.
     */
    public String listarVehiculosEnsambladosAlfabetico() {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados.";

        List<Vehiculo> lista = new ArrayList<>(vehiculosEnsamblados);
        lista.sort((v1, v2) -> v1.getClass().getSimpleName().compareToIgnoreCase(v2.getClass().getSimpleName()));

        StringBuilder sb = new StringBuilder("--- LISTADO ALFABÉTICO POR TIPO DE VEHÍCULO ---\n");
        for (Vehiculo v : lista) {
            sb.append(v.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Lista todos los vehículos que han finalizado el proceso de montaje.
     * @return Cadena con la información de los vehículos ensamblados.
     */
    public String listarVehiculosEnsamblados() {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados disponibles";

        StringBuilder listado = new StringBuilder("--- Listado de Vehículos Ensamblados ---\n");
        for (Vehiculo v : this.vehiculosEnsamblados) {
            listado.append(v.toString()).append("\n");
        }
        return listado.toString();
    }

    /**
     * Consulta la producción finalizada en un instante de tiempo concreto.
     * @param fechaBuscada El segundo exacto de la simulación.
     * @return Detalle de los vehículos producidos en ese instante.
     */
    public String listarProduccionPorFecha(int fechaBuscada) {
        List<Vehiculo> producidos = produccionPorFecha.get(fechaBuscada);

        if (producidos == null || producidos.isEmpty()) {
            return "No hubo producción en el segundo " + fechaBuscada;
        }

        StringBuilder sb = new StringBuilder("--- PRODUCCIÓN EN FECHA: " + fechaBuscada + " ---\n");
        for (Vehiculo v : producidos) {
            sb.append(v.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Registra un vehículo como finalizado y actualiza los índices de producción.
     * @param nuevoVehiculo El vehículo que sale de la cadena de montaje.
     * @param segundoActual El instante de tiempo de la finalización.
     */
    public void anadirVehiculoEnsamblado(Vehiculo nuevoVehiculo, int segundoActual) {
        if (nuevoVehiculo != null) {
            this.vehiculosEnsamblados.add(nuevoVehiculo);
            this.produccionPorFecha.putIfAbsent(segundoActual, new java.util.ArrayList<>());
            this.produccionPorFecha.get(segundoActual).add(nuevoVehiculo);
            registrarEvento(new Evento(segundoActual, nuevoVehiculo, nuevoVehiculo.getId(), "Vehículo "+nuevoVehiculo.getId()+" ensamblado"));
        }
    }

    /**
     * Filtra la producción finalizada según el tipo de motor instalado.
     * @param tipoBuscado Categoría de motor (Gasolina, Eléctrico, Híbrido).
     * @return Listado de vehículos que coinciden con el criterio.
     */
    public String listarVehiculosPorTipoMotor(TipoMotor tipoBuscado) {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados disponibles";

        StringBuilder listado = new StringBuilder("--- Vehículos con Motor: " + tipoBuscado + " ---\n");
        boolean encontrado = false;

        for (Vehiculo v : this.vehiculosEnsamblados) {
            if (v.getMotor() != null && v.getMotor().getTipoMotor() == tipoBuscado) {
                listado.append(v).append("\n");
                encontrado = true;
            }
        }
        return encontrado ? listado.toString() : "No hay vehículos con motor " + tipoBuscado;
    }

    /**
     * Filtra la producción finalizada según el tipo de tapicería instalada.
     * @param tipoBuscado Material de la tapicería.
     * @return Listado de vehículos que coinciden con el criterio.
     */
    public String listarVehiculosPorTipoTapiceria(TipoTapiceria tipoBuscado) {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados disponibles";

        StringBuilder listado = new StringBuilder("--- Vehículos con Tapicería: " + tipoBuscado + " ---\n");
        boolean encontrado = false;

        for (Vehiculo v : this.vehiculosEnsamblados) {
            if (v.getTapiceria() != null && v.getTapiceria().getTipoTapiceria() == tipoBuscado) {
                listado.append(v).append("\n");
                encontrado = true;
            }
        }
        return encontrado ? listado.toString() : "No hay vehículos con tapicería " + tipoBuscado;
    }

    /**
     * Filtra la producción finalizada según el tipo de neumáticos instalados.
     * @param tipoBuscado Categoría de la rueda.
     * @return Listado de vehículos que coinciden con el criterio.
     */
    public String listarVehiculosPorTipoRueda(TipoRueda tipoBuscado) {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados disponibles";

        StringBuilder listado = new StringBuilder("--- Vehículos con Ruedas: " + tipoBuscado + " ---\n");
        boolean encontrado = false;

        for (Vehiculo v : this.vehiculosEnsamblados) {
            if (v.getRuedas() != null && !v.getRuedas().isEmpty()) {
                if (v.getRuedas().get(0).getTipoRueda() == tipoBuscado) {
                    listado.append(v).append("\n");
                    encontrado = true;
                }
            }
        }
        return encontrado ? listado.toString() : "No hay vehículos con ruedas de tipo " + tipoBuscado;
    }

    /**
     * Almacena un evento en el historial para garantizar la trazabilidad del sistema.
     * @param nuevoEvento El suceso ocurrido.
     */
    public void registrarEvento(Evento nuevoEvento) {
        if (nuevoEvento != null) {
            this.historialMontaje.add(nuevoEvento);
        }
    }

    /**
     * @return La lista completa de eventos registrados.
     */
    public List<Evento> getHistorial() {
        return historialMontaje;
    }

    /**
     * Consulta el historial filtrando por el nombre de la clase del componente.
     * @param tipo Nombre del componente (ej: "Motor").
     * @return Lista de eventos asociados a ese componente.
     */
    public List<Evento> consultarEventosPorComponente(String tipo) {
        List<Evento> filtrados = new ArrayList<>();
        for (Evento e : historialMontaje) {
            if (e.getTipoComponente().equalsIgnoreCase(tipo)) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }

    /**
     * Consulta los sucesos ocurridos en un instante temporal concreto.
     * @param fechaBuscada Segundo de la simulación.
     * @return Lista de eventos que ocurrieron en ese segundo.
     */
    public List<Evento> consultarEventosPorFecha(int fechaBuscada) {
        List<Evento> filtrados = new ArrayList<>();
        for (Evento e : historialMontaje) {
            if (e.getFecha() == fechaBuscada) {
                filtrados.add(e);
            }
        }
        return filtrados;
    }

    // region GESTIÓN DE MOTORES
    /**
     * Añade un motor al stock y registra su entrada.
     * @param nuevoMotor El motor a añadir.
     * @param segundoActual Tiempo de la acción.
     */
    public void anadirMotor(Motor nuevoMotor, int segundoActual) {
        if (nuevoMotor != null) {
            this.motores.add(nuevoMotor);
            this.historialMontaje.add(new Evento(segundoActual, nuevoMotor, -1, "Motor añadido"));
        }
    }

    /** @return Lista de motores en almacén. */
    public List<Motor> getMotores() { return motores; }

    /** @return Representación textual de los motores disponibles. */
    public String listarMotores() {
        if (motores.isEmpty()) return "No hay motores disponibles";
        StringBuilder listado = new StringBuilder("--- Listado de Motores ---\n");
        for (Motor m : this.motores) {
            listado.append(m.toString()).append("\n");
        }
        return listado.toString();
    }

    /** @param idMotor ID del motor. @return El motor o null si no existe. */
    public Motor buscarMotorPorId(int idMotor) {
        for (Motor m : this.motores) {
            if (m.getId() == idMotor) return m;
        }
        return null;
    }

    /**
     * Elimina un motor del stock.
     * @param idMotor ID del motor a borrar.
     * @param segundoActual Tiempo de la acción.
     * @return Mensaje de estado.
     */
    public String borrarMotorPorId(int idMotor, int segundoActual) {
        Motor motorEncontrado = buscarMotorPorId(idMotor);
        if (motorEncontrado != null) {
            this.motores.remove(motorEncontrado);
            this.historialMontaje.add(new Evento(segundoActual, motorEncontrado, -1, "Motor borrado"));
            return "Motor con id " + idMotor + " borrado correctamente";
        }
        return "No existe un motor con id " + idMotor + " en la fábrica";
    }
    // endregion

    // region GESTIÓN DE RUEDAS
    /**
     * Añade una rueda al stock y registra el evento.
     * @param nuevaRueda La rueda a añadir.
     * @param segundoActual Tiempo de la acción.
     */
    public void anadirRueda(Rueda nuevaRueda, int segundoActual) {
        if (nuevaRueda != null) {
            this.ruedas.add(nuevaRueda);
            this.historialMontaje.add(new Evento(segundoActual, nuevaRueda, -1, "Rueda añadida"));
        }
    }

    /** @return Lista de ruedas en almacén. */
    public List<Rueda> getRuedas() { return ruedas; }

    /** @return Representación textual de las ruedas disponibles. */
    public String listarRuedas() {
        if (ruedas.isEmpty()) return "No hay ruedas disponibles";
        StringBuilder listado = new StringBuilder("--- Listado de Ruedas ---\n");
        for (Rueda r : this.ruedas) {
            listado.append(r.toString()).append("\n");
        }
        return listado.toString();
    }

    /** @param idRueda ID de la rueda. @return La rueda o null. */
    public Rueda buscarRuedaPorId(int idRueda) {
        for (Rueda r : this.ruedas) {
            if (r.getId() == idRueda) return r;
        }
        return null;
    }

    /** @return Mensaje de confirmación o error. */
    public String borrarRuedaPorId(int idRueda, int segundoActual) {
        Rueda ruedaEncontrada = buscarRuedaPorId(idRueda);
        if (ruedaEncontrada != null) {
            this.ruedas.remove(ruedaEncontrada);
            this.historialMontaje.add(new Evento(segundoActual, ruedaEncontrada, -1, "Rueda borrada"));
            return "Rueda con id " + idRueda + " borrada correctamente";
        }
        return "No existe rueda con id " + idRueda;
    }
    // endregion

    // region GESTIÓN DE TAPICERÍA
    /**
     * Añade un kit de tapicería al stock.
     * @param nuevaTapiceria El objeto a añadir.
     * @param segundoActual Tiempo de la acción.
     */
    public void anadirTapiceria(Tapiceria nuevaTapiceria, int segundoActual) {
        if (nuevaTapiceria != null) {
            this.tapiceria.add(nuevaTapiceria);
            this.historialMontaje.add(new Evento(segundoActual, nuevaTapiceria, -1, "Tapicería añadida"));
        }
    }

    /** @return Lista de tapicerías. */
    public List<Tapiceria> getTapicerias() { return tapiceria; }

    /** @return Listado textual de tapicerías. */
    public String listarTapicerias() {
        if (tapiceria.isEmpty()) return "No hay tapicerías disponibles";
        StringBuilder listado = new StringBuilder("--- Listado de Tapicerías ---\n");
        for (Tapiceria t : this.tapiceria) {
            listado.append(t.toString()).append("\n");
        }
        return listado.toString();
    }

    /** @param idTapiceria ID del kit. @return El objeto o null. */
    public Tapiceria buscarTapiceriaPorId(int idTapiceria) {
        for (Tapiceria t : this.tapiceria) {
            if (t.getId() == idTapiceria) return t;
        }
        return null;
    }

    /** @return Mensaje de confirmación o error. */
    public String borrarTapiceriaPorId(int idTapiceria, int segundoActual) {
        Tapiceria tapiceriaEncontrada = buscarTapiceriaPorId(idTapiceria);
        if (tapiceriaEncontrada != null) {
            this.tapiceria.remove(tapiceriaEncontrada);
            this.historialMontaje.add(new Evento(segundoActual, tapiceriaEncontrada, -1, "Tapicería borrada"));
            return "Tapicería con id " + idTapiceria + " borrada correctamente";
        }
        return "No existe tapicería con id " + idTapiceria;
    }
    // endregion

    // region GESTION TRABAJADORES
    /**
     * Registra un nuevo trabajador en la plantilla de la fábrica.
     * @param nuevoTrabajador Trabajador de cualquier perfil (Operario, Mecánico, etc.).
     */
    public void anadirTrabajador(Trabajador nuevoTrabajador) {
        if (nuevoTrabajador != null) {
            this.trabajadores.add(nuevoTrabajador);
        }
    }

    /** @return Listado completo de la plantilla. */
    public String listarTrabajadores() {
        if (trabajadores.isEmpty()) return "No hay trabajadores registrados.";
        StringBuilder listado = new StringBuilder("--- Plantilla de Trabajadores ---\n");
        for (Trabajador t : this.trabajadores) {
            listado.append(t.toString()).append("\n");
        }
        return listado.toString();
    }

    /** @param dniBuscado DNI identificador. @return Trabajador o null. */
    public Trabajador buscarTrabajadorPorDNI(String dniBuscado) {
        for (Trabajador t : this.trabajadores) {
            if (t.getDNI().equalsIgnoreCase(dniBuscado)) return t;
        }
        return null;
    }

    /** @return Mensaje de confirmación o error. */
    public String borrarTrabajadorPorDNI(String dni) {
        Trabajador trabajadorEncontrado = buscarTrabajadorPorDNI(dni);
        if (trabajadorEncontrado != null) {
            this.trabajadores.remove(trabajadorEncontrado);
            return "Trabajador con DNI " + dni + " eliminado.";
        }
        return "No existe trabajador con DNI " + dni;
    }

    // region FILTRADO DE TRABAJADORES POR TIPO

    /** @return Sublista con todos los operarios registrados. */
    public List<Operario> obtenerSoloOperarios() {
        List<Operario> lista = new ArrayList<>();
        for (Trabajador t : trabajadores) {
            if (t instanceof Operario) {
                lista.add((Operario) t);
            }
        }
        return lista;
    }

    /**
     * Genera un ranking de operarios basado en el número de montajes finalizados.
     * @return Listado ordenado por productividad.
     */
    public String obtenerOperariosProductividad() {
        List<Operario> lista = obtenerSoloOperarios();
        if (lista.isEmpty()) return "No hay operarios disponibles.";

        lista.sort((o1, o2) -> Integer.compare(o2.getNumMontajes(), o1.getNumMontajes()));

        StringBuilder sb = new StringBuilder("--- RANKING DE PRODUCTIVIDAD (OPERARIOS) ---\n");
        for (Operario o : lista) {
            sb.append(String.format("[%d montajes] - %s %s (DNI: %s)\n",
                    o.getNumMontajes(), o.getNombre(), o.getApellidos(), o.getDNI()));
        }
        return sb.toString();
    }

    /**
     * Obtiene el listado de operarios ordenado alfabéticamente por apellidos.
     * @return Listado ordenado alfabéticamente.
     */
    public String obtenerOperariosOrdenAlfabetico() {
        List<Operario> lista = obtenerSoloOperarios();
        if (lista.isEmpty()) return "No hay operarios disponibles.";

        lista.sort((o1, o2) -> o1.getApellidos().compareToIgnoreCase(o2.getApellidos()));

        StringBuilder sb = new StringBuilder("--- OPERARIOS POR ORDEN ALFABÉTICO ---\n");
        for (Operario o : lista) {
            sb.append(String.format("%-20s %-15s | DNI: %-10s | Montajes: %d\n",
                    o.getApellidos(), o.getNombre(), o.getDNI(), o.getNumMontajes()));
        }
        return sb.toString();
    }

    /** @return Sublista con todos los administradores. */
    public List<Administrador> obtenerSoloAdministradores() {
        List<Administrador> lista = new ArrayList<>();
        for (Trabajador t : trabajadores) {
            if (t instanceof Administrador) {
                lista.add((Administrador) t);
            }
        }
        return lista;
    }

    /** @return Sublista con todos los gestores de planta. */
    public List<GestorPlanta> obtenerSoloGestores() {
        List<GestorPlanta> lista = new ArrayList<>();
        for (Trabajador t : trabajadores) {
            if (t instanceof GestorPlanta) {
                lista.add((GestorPlanta) t);
            }
        }
        return lista;
    }

    /** @return Sublista con todos los mecánicos. */
    public List<Mecanico> obtenerSoloMecanicos() {
        List<Mecanico> lista = new ArrayList<>();
        for (Trabajador t : trabajadores) {
            if (t instanceof Mecanico) {
                lista.add((Mecanico) t);
            }
        }
        return lista;
    }

    // endregion

    // region FILTRADO DE VEHÍCULOS POR TIPO
    /** @return Sublista con todos los chasis de tipo Turismo. */
    public List<Turismo> obtenerSoloTurismos() {
        List<Turismo> lista = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v instanceof Turismo) {
                lista.add((Turismo) v);
            }
        }
        return lista;
    }

    /** @return Sublista con todos los chasis de tipo Biplaza. */
    public List<BiplazaDeportivo> obtenerSoloBiplazas() {
        List<BiplazaDeportivo> lista = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v instanceof BiplazaDeportivo) {
                lista.add((BiplazaDeportivo) v);
            }
        }
        return lista;
    }

    /** @return Sublista con todos los chasis de tipo Furgoneta. */
    public List<Furgoneta> obtenerSoloFurgonetas() {
        List<Furgoneta> lista = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v instanceof Furgoneta) {
                lista.add((Furgoneta) v);
            }
        }
        return lista;
    }
    // endregion

    /** @return true si hay al menos un motor disponible. */
    public boolean hayMotor() { return !motores.isEmpty(); }

    /** @return true si hay al menos una tapicería disponible. */
    public boolean hayTapiceria() { return !tapiceria.isEmpty(); }

    /** @return true si hay al menos 4 ruedas disponibles. */
    public boolean hayRuedas() { return ruedas.size() >= 4; }

    // region GESTIÓN DE VEHÍCULOS (CHASIS)
    /**
     * Añade un nuevo chasis al almacén de producción.
     * @param nuevoVehiculo El vehículo a añadir.
     */
    public void anadirVehiculo(Vehiculo nuevoVehiculo) {
        if (nuevoVehiculo != null) {
            this.vehiculos.add(nuevoVehiculo);
        }
    }

    /** @return La lista de chasis en stock. */
    public List<Vehiculo> getVehiculos() { return vehiculos; }

    /** @return Listado textual de los chasis disponibles. */
    public String listarVehiculos() {
        if (vehiculos.isEmpty()) return "No hay vehículos en el almacén";
        StringBuilder listado = new StringBuilder("--- Almacén de Vehículos (Chasis) ---\n");
        for (Vehiculo v : this.vehiculos) {
            listado.append("ID: ").append(v.getId())
                    .append(" | Tipo: ").append(v.getClass().getSimpleName())
                    .append(" | Color: ").append(v.getColor())
                    .append(" | Estado: ").append(v.getEstado()).append("\n");
        }
        return listado.toString();
    }

    /** @param idVehiculo ID del vehículo. @return El vehículo o null. */
    public Vehiculo buscarVehiculoPorId(int idVehiculo) {
        for (Vehiculo v : this.vehiculos) {
            if (v.getId() == idVehiculo) return v;
        }
        return null;
    }

    /** @return Mensaje de confirmación o error. */
    public String borrarVehiculoPorId(int idVehiculo, int segundoActual) {
        Vehiculo v = buscarVehiculoPorId(idVehiculo);
        if (v != null) {
            this.vehiculos.remove(v);
            this.historialMontaje.add(new Evento(segundoActual, v, idVehiculo, "Vehículo eliminado del almacén"));
            return "Vehículo con id " + idVehiculo + " borrado correctamente";
        }
        return "No existe un vehículo con id " + idVehiculo;
    }
    // endregion

    /**
     * Carga un conjunto de datos iniciales para facilitar las pruebas de simulación.
     * Incluye componentes, trabajadores de todos los perfiles y chasis base.
     */
    public void cargarDatosPrueba() {
        // Lógica de carga de componentes (Motores, Ruedas, Tapicerías)
        for (int i = 0; i < 3; i++) {
            this.motores.add(new Motor(TipoMotor.GASOLINA, 1600.0, 115.0, 4));
            this.motores.add(new Motor(TipoMotor.ELECTRICO, 0.0, 200.0, 0));
            this.motores.add(new Motor(TipoMotor.HIBRIDO, 1800.0, 140.0, 4));
        }

        for (int i = 0; i < 15; i++) {
            this.ruedas.add(new Rueda(TipoRueda.NORMAL, 205, 16, 91, 210));
            this.ruedas.add(new Rueda(TipoRueda.DEPORTIVO, 245, 19, 98, 300));
            this.ruedas.add(new Rueda(TipoRueda.TODOTERRENO, 265, 17, 112, 190));
        }
        this.ruedas.add(new Rueda(TipoRueda.NORMAL, 205, 16, 91, 210));
        this.ruedas.add(new Rueda(TipoRueda.DEPORTIVO, 245, 19, 98, 300));

        for (int i = 0; i < 4; i++) {
            this.tapiceria.add(new Tapiceria(TipoTapiceria.TELA, "Gris", 4));
            this.tapiceria.add(new Tapiceria(TipoTapiceria.CUERO, "Beige", 6));
            this.tapiceria.add(new Tapiceria(TipoTapiceria.ALCANTARA, "Negro", 5));
        }

        // Carga de la plantilla humana
        this.anadirTrabajador(new Operario("Juan", "García", "Calle A", "11111111A", 101, 1200, "2026-01-01"));
        this.anadirTrabajador(new Operario("Ana", "Sánchez", "Calle B", "22222222B", 102, 1250, "2026-01-10"));
        this.anadirTrabajador(new Operario("Carlos", "Gómez", "Calle F", "44444444D", 104, 1200, "2026-02-10"));
        this.anadirTrabajador(new Operario("Marta", "López", "Calle G", "66666666F", 106, 1200, "2026-02-15"));
        this.anadirTrabajador(new Operario("Elena", "Marín", "Calle H", "77777777G", 107, 1250, "2026-03-01"));
        this.anadirTrabajador(new Operario("Diego", "Pérez", "Calle I", "88888888H", 108, 1200, "2026-03-05"));
        this.anadirTrabajador(new Operario("Julia", "Castro", "Calle J", "99999999I", 109, 1200, "2026-03-10"));
        this.anadirTrabajador(new Operario("Roberto", "Sanz", "Calle K", "10101010J", 110, 1250, "2026-03-12"));
        this.anadirTrabajador(new Operario("Lucía", "Fernández", "Calle L", "12121212K", 111, 1200, "2026-03-15"));
        this.anadirTrabajador(new Operario("Marcos", "Jiménez", "Calle M", "13131313L", 112, 1200, "2026-03-18"));
        this.anadirTrabajador(new Operario("Sofía", "Vargas", "Calle N", "14141414M", 113, 1250, "2026-03-20"));
        this.anadirTrabajador(new Operario("Iván", "Cano", "Calle O", "15151515N", 114, 1200, "2026-03-22"));

        this.anadirTrabajador(new Administrador("Luis", "Martín", "Calle C", "33333333C", 103, 1400, "2026-01-05"));
        this.anadirTrabajador(new GestorPlanta("Pedro", "Ruiz", "Calle E", "55555555E", 105, 1300, "2026-02-01"));

        Mecanico m1 = new Mecanico("Andrés", "Gómez", "Avenida Galicia 12", "45678912A", 120, 1450, "2025-11-15");
        Mecanico m2 = new Mecanico("Andrea", "Vázquez", "Calle Mayor 45", "78912345B", 121, 1380, "2026-02-05");
        Mecanico m3 = new Mecanico("Marcos", "López", "Rúa da Rosa 8", "32165498C", 122, 1500, "2024-05-20");
        Mecanico m4 = new Mecanico("Daniel", "Sanz", "Paseo del Prado 2", "15975346D", 123, 1420, "2026-03-10");

        this.anadirTrabajador(m1); this.anadirTrabajador(m2); this.anadirTrabajador(m3); this.anadirTrabajador(m4);

        // Carga de chasis base
        this.vehiculos.add(new Turismo("Azul Marino", 5, 1250, 1800.0));
        this.vehiculos.add(new Turismo("Blanco Perlado", 5, 1200, 1750.0));
        this.vehiculos.add(new Turismo("Gris Metalizado", 5, 1300, 1900.0));
        this.vehiculos.add(new Turismo("Negro Mate", 4, 1150, 1650.0));
        this.vehiculos.add(new BiplazaDeportivo("Rojo Ferrari", 1050, 1350.0));
        this.vehiculos.add(new BiplazaDeportivo("Naranja Sunset", 1100, 1400.0));
        this.vehiculos.add(new Furgoneta("Blanco Empresa", 3, 1900, 3500.0));
        this.vehiculos.add(new Furgoneta("Amarillo Correos", 2, 1850, 3300.0));
    }
}