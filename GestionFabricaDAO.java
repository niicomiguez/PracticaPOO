import java.util.List;
import java.util.ArrayList;

public class GestionFabricaDAO {
    private List<Motor> motores;
    private List<Rueda> ruedas;
    private List<Tapiceria> tapiceria;
    private List<Trabajador> trabajadores;
    private List<Evento> historialMontaje = new ArrayList<>();
    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosEnsamblados;
    private java.util.Map<Integer, List<Vehiculo>> produccionPorFecha = new java.util.HashMap<>();

    public GestionFabricaDAO() {
        this.motores = new ArrayList<>();
        this.ruedas = new ArrayList<>();
        this.tapiceria = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.vehiculosEnsamblados=new ArrayList<>();
    }

    /**
     * Genera un listado de las configuraciones más fabricadas.
     * Cumple con el requisito: "Listado de configuraciones con mayor tasa de ensamblaje".
     */
    public String listarConfiguracionesMasEnsambladas() {
        if (vehiculosEnsamblados.isEmpty()) return "No hay datos de producción para generar estadísticas.";

        // Mapa para contar: Llave = Configuración, Valor = Cantidad
        java.util.Map<String, Integer> conteo = new java.util.HashMap<>();

        for (Vehiculo v : vehiculosEnsamblados) {
            // Creamos una cadena que represente la configuración única
            String config = v.getClass().getSimpleName() + " [" +
                    (v.getMotor() != null ? v.getMotor().getTipoMotor() : "Sin Motor") + " / " +
                    (v.getTapiceria() != null ? v.getTapiceria().getTipoTapiceria() : "Sin Tapicería") + "]";

            // Si ya existe, suma 1; si no, empieza en 1
            conteo.put(config, conteo.getOrDefault(config, 0) + 1);
        }

        // Pasamos el mapa a una lista para poder ordenarla por el valor (tasa de ensamblaje)
        List<java.util.Map.Entry<String, Integer>> ranking = new ArrayList<>(conteo.entrySet());
        ranking.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())); // Orden descendente

        StringBuilder sb = new StringBuilder("--- TASA DE ENSAMBLAJE POR CONFIGURACIÓN ---\n");
        for (java.util.Map.Entry<String, Integer> entrada : ranking) {
            sb.append(String.format("%-45s | Unidades: %d\n", entrada.getKey(), entrada.getValue()));
        }

        return sb.toString();
    }
    /**
     * Genera el listado de vehículos ensamblados ordenados alfabéticamente por tipo.
     */
    public String listarVehiculosEnsambladosAlfabético() {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados.";

        List<Vehiculo> lista = new ArrayList<>(vehiculosEnsamblados);
        // Ordenamos por el nombre de la clase (Biplaza, Furgoneta, Turismo...)
        lista.sort((v1, v2) -> v1.getClass().getSimpleName().compareToIgnoreCase(v2.getClass().getSimpleName()));

        StringBuilder sb = new StringBuilder("--- LISTADO ALFABÉTICO POR TIPO DE VEHÍCULO ---\n");
        for (Vehiculo v : lista) {
            sb.append(v.toString()).append("\n"); // Ahora el toString empieza por el Tipo
        }
        return sb.toString();
    }

    /**
     * Genera el listado de vehículos ensamblados siguiendo el formato estándar del DAO.
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
     * Lista los vehículos producidos en una fecha (segundo) específica.
     * Muestra el vehículo y todos sus componentes asociados.
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
     * Añadir vehículo ensamblado a la lista
     * */
    public void añadirVehiculoEnsamblado(Vehiculo nuevoVehiculo, int segundoActual) {
        if (nuevoVehiculo != null) {
            this.vehiculosEnsamblados.add(nuevoVehiculo);

            this.produccionPorFecha.putIfAbsent(segundoActual, new java.util.ArrayList<>());
            this.produccionPorFecha.get(segundoActual).add(nuevoVehiculo);

            registrarEvento(new Evento(segundoActual, nuevoVehiculo, nuevoVehiculo.getId(), "Vehículo "+nuevoVehiculo.getId()+" ensamblado"));
        }
    }

    public String listarVehiculosPorTipoMotor(TipoMotor tipoBuscado) {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados disponibles";

        StringBuilder listado = new StringBuilder("--- Vehículos con Motor: " + tipoBuscado + " ---\n");
        boolean encontrado = false;

        for (Vehiculo v : this.vehiculosEnsamblados) {
            // Accedemos al motor del vehículo y comparamos su tipo
            if (v.getMotor() != null && v.getMotor().getTipoMotor() == tipoBuscado) {
                listado.append(v.toString()).append("\n");
                encontrado = true;
            }
        }

        return encontrado ? listado.toString() : "No hay vehículos con motor " + tipoBuscado;
    }

    public String listarVehiculosPorTipoTapiceria(TipoTapiceria tipoBuscado) {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados disponibles";

        StringBuilder listado = new StringBuilder("--- Vehículos con Tapicería: " + tipoBuscado + " ---\n");
        boolean encontrado = false;

        for (Vehiculo v : this.vehiculosEnsamblados) {
            if (v.getTapiceria() != null && v.getTapiceria().getTipoTapiceria() == tipoBuscado) {
                listado.append(v.toString()).append("\n");
                encontrado = true;
            }
        }

        return encontrado ? listado.toString() : "No hay vehículos con tapicería " + tipoBuscado;
    }

    /**
     * Genera un listado de vehículos ensamblados que usan un tipo de rueda específico.
     */
    public String listarVehiculosPorTipoRueda(TipoRueda tipoBuscado) {
        if (vehiculosEnsamblados.isEmpty()) return "No hay vehículos ensamblados disponibles";

        StringBuilder listado = new StringBuilder("--- Vehículos con Ruedas: " + tipoBuscado + " ---\n");
        boolean encontrado = false;

        for (Vehiculo v : this.vehiculosEnsamblados) {
            // Comprobamos si el vehículo tiene ruedas y si la primera coincide (todas suelen ser iguales)
            if (v.getRuedas() != null && !v.getRuedas().isEmpty()) {
                if (v.getRuedas().get(0).getTipoRueda() == tipoBuscado) {
                    listado.append(v.toString()).append("\n");
                    encontrado = true;
                }
            }
        }

        return encontrado ? listado.toString() : "No hay vehículos con ruedas de tipo " + tipoBuscado;
    }

    /**
     * Registra un evento de forma estructurada en el historial.
     */
    public void registrarEvento(Evento nuevoEvento) {
        if (nuevoEvento != null) {
            this.historialMontaje.add(nuevoEvento);
        }
    }
    
    /**
     * Devuelve el historial completo de eventos.
     */
    public List<Evento> getHistorial() {
        return historialMontaje;
    }

    /**
     * Consulta el historial filtrando por un tipo de componente específico.
     * Cumple con el requisito: "consultado a nivel de componente".
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
     * Consulta el historial filtrando por el segundo (fecha) de la simulación.
     * Cumple con el requisito: "consultado por fecha".
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
    public void añadirMotor(Motor nuevoMotor,int segundoActual) {
        if (nuevoMotor != null) {
            this.motores.add(nuevoMotor);

            // Registramos el evento en el historial
            this.historialMontaje.add(new Evento(segundoActual,nuevoMotor,-1,"Motor añadido"));
        }
    }

    public List<Motor> getMotores() { return motores; }

    public String listarMotores() {
        if (motores.isEmpty()) return "No hay motores disponibles";
        StringBuilder listado = new StringBuilder("--- Listado de Motores ---\n");
        for (Motor m : this.motores) {
            listado.append(m.toString()).append("\n");
        }
        return listado.toString();
    }

    public Motor buscarMotorPorId(int idMotor) {
        for (Motor m : this.motores) {
            if (m.getId() == idMotor) return m;
        }
        return null;
    }

    public String borrarMotorPorId(int idMotor,int segundoActual) {
        Motor motorEncontrado = buscarMotorPorId(idMotor);
        if (motorEncontrado != null) {
            this.motores.remove(motorEncontrado);
            // Registramos el evento en el historial
            this.historialMontaje.add(new Evento(segundoActual,motorEncontrado,-1,"Motor borrado"));

            return "Motor con id " + idMotor + " borrado correctamente";
        }
        return "No existe un motor con id " + idMotor + " en la fábrica";
    }
    // endregion

    // region GESTIÓN DE RUEDAS
    public void añadirRueda(Rueda nuevaRueda,int segundoActual) {
        if (nuevaRueda != null) {
            this.ruedas.add(nuevaRueda);
            this.historialMontaje.add(new Evento(segundoActual,nuevaRueda,-1,"Rueda añadida"));

        }
    }

    public List<Rueda> getRuedas() { return ruedas; }

    public String listarRuedas() {
        if (ruedas.isEmpty()) return "No hay ruedas disponibles";
        StringBuilder listado = new StringBuilder("--- Listado de Ruedas ---\n");
        for (Rueda r : this.ruedas) {
            listado.append(r.toString()).append("\n");
        }
        return listado.toString();
    }

    public Rueda buscarRuedaPorId(int idRueda) {
        for (Rueda r : this.ruedas) {
            if (r.getId() == idRueda) return r;
        }
        return null;
    }

    public String borrarRuedaPorId(int idRueda,int segundoActual) {
        Rueda ruedaEncontrada = buscarRuedaPorId(idRueda);
        if (ruedaEncontrada != null) {
            this.ruedas.remove(ruedaEncontrada);
            this.historialMontaje.add(new Evento(segundoActual,ruedaEncontrada,-1,"Rueda borrada"));

            return "Rueda con id " + idRueda + " borrada correctamente";
        }
        return "No existe rueda con id " + idRueda;
    }
    // endregion

    // region GESTIÓN DE TAPICERÍA
    public void añadirTapiceria(Tapiceria nuevaTapiceria,int segundoActual) {
        if (nuevaTapiceria != null) {
            this.tapiceria.add(nuevaTapiceria);
            this.historialMontaje.add(new Evento(segundoActual,nuevaTapiceria,-1,"Tapicería añadida"));
        }
    }

    public List<Tapiceria> getTapicerias() { return tapiceria; }

    public String listarTapicerias() {
        if (tapiceria.isEmpty()) return "No hay tapicerías disponibles";
        StringBuilder listado = new StringBuilder("--- Listado de Tapicerías ---\n");
        for (Tapiceria t : this.tapiceria) {
            listado.append(t.toString()).append("\n");
        }
        return listado.toString();
    }

    public Tapiceria buscarTapiceriaPorId(int idTapiceria) {
        for (Tapiceria t : this.tapiceria) {
            if (t.getId() == idTapiceria) return t;
        }
        return null;
    }

    public String borrarTapiceriaPorId(int idTapiceria,int segundoActual) {
        Tapiceria tapiceriaEncontrada = buscarTapiceriaPorId(idTapiceria);
        if (tapiceriaEncontrada != null) {
            this.tapiceria.remove(tapiceriaEncontrada);
            this.historialMontaje.add(new Evento(segundoActual,tapiceriaEncontrada,-1,"Tapicería borrada"));

            return "Tapicería con id " + idTapiceria + " borrada correctamente";
        }
        return "No existe tapicería con id " + idTapiceria;
    }
    // endregion

    // region GESTION TRABAJADORES
    public void añadirTrabajador(Trabajador nuevoTrabajador) {
        if (nuevoTrabajador != null) {
            this.trabajadores.add(nuevoTrabajador);
        }
    }

    public String listarTrabajadores() {
        if (trabajadores.isEmpty()) return "No hay trabajadores registrados.";
        StringBuilder listado = new StringBuilder("--- Plantilla de Trabajadores ---\n");
        for (Trabajador t : this.trabajadores) {
            listado.append(t.toString()).append("\n");
        }
        return listado.toString();
    }


    public Trabajador buscarTrabajadorPorDNI(String dniBuscado) {
        for (Trabajador t : this.trabajadores) {
            if (t.getDNI().equalsIgnoreCase(dniBuscado)) return t;
        }
        return null;
    }

    public String borrarTrabajadorPorDNI(String dni) {
        Trabajador trabajadorEncontrado = buscarTrabajadorPorDNI(dni);
        if (trabajadorEncontrado != null) {
            this.trabajadores.remove(trabajadorEncontrado);
            return "Trabajador con DNI " + dni + " eliminado.";
        }
        return "No existe trabajador con DNI " + dni;
    }
    // region FILTRADO DE TRABAJADORES POR TIPO

    /**
     * Filtra y devuelve solo los trabajadores de tipo Operario.
     */
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
     * Filtar Operarios por productividad
     * */
    public String obtenerOperariosProductividad() {
        List<Operario> lista = obtenerSoloOperarios();
        if (lista.isEmpty()) return "No hay operarios disponibles.";

        // Ordenamos de mayor a menor montajes
        lista.sort((o1, o2) -> Integer.compare(o2.getNumMontajes(), o1.getNumMontajes()));

        StringBuilder sb = new StringBuilder("--- RANKING DE PRODUCTIVIDAD (OPERARIOS) ---\n");
        for (Operario o : lista) {
            // Ponemos los montajes al principio para que se vea claro
            sb.append(String.format("[%d montajes] - %s %s (DNI: %s)\n",
                    o.getNumMontajes(), o.getNombre(), o.getApellidos(), o.getDNI()));
        }
        return sb.toString();
    }

    /**
     * Filtar Operarios por orden alfabético
     * */
    public String obtenerOperariosOrdenAlfabetico() {
        List<Operario> lista = obtenerSoloOperarios();
        if (lista.isEmpty()) return "No hay operarios disponibles.";

        // Ordenamos por apellidos ignorando mayúsculas/minúsculas
        lista.sort((o1, o2) -> o1.getApellidos().compareToIgnoreCase(o2.getApellidos()));

        StringBuilder sb = new StringBuilder("--- OPERARIOS POR ORDEN ALFABÉTICO ---\n");
        for (Operario o : lista) {
            // Formato limpio: Apellidos primero para verificar el orden fácilmente
            sb.append(String.format("%-20s %-15s | DNI: %-10s | Montajes: %d\n",
                    o.getApellidos(), o.getNombre(), o.getDNI(), o.getNumMontajes()));
        }
        return sb.toString();
    }


    /**
     * Filtra y devuelve solo los trabajadores de tipo Administrador.
     */
    public List<Administrador> obtenerSoloAdministradores() {
        List<Administrador> lista = new ArrayList<>();
        for (Trabajador t : trabajadores) {
            if (t instanceof Administrador) {
                lista.add((Administrador) t);
            }
        }
        return lista;
    }

    /**
     * Busca trabajadores cuyo nombre contenga el texto buscado.
     */
    public List<Trabajador> buscarTrabajadorPorNombre(String nombreBuscado) {
        List<Trabajador> encontrados = new ArrayList<>();
        for (Trabajador t : trabajadores) {
            if (t.getNombre().toLowerCase().contains(nombreBuscado.toLowerCase())) {
                encontrados.add(t);
            }
        }
        return encontrados;
    }
    /**
     * Filtra y devuelve solo los trabajadores de tipo Gestor de Planta.
     */
    public List<GestorPlanta> obtenerSoloGestores() {
        List<GestorPlanta> lista = new ArrayList<>();
        for (Trabajador t : trabajadores) {
            if (t instanceof GestorPlanta) {
                lista.add((GestorPlanta) t);
            }
        }
        return lista;
    }

    /**
     * Filtra y devuelve solo los trabajadores de tipo Mecánico.
     */
    public List<Mecanico> obtenerSoloMecanicos() {
        List<Mecanico> lista = new ArrayList<>();
        for (Trabajador t : trabajadores) {
            if (t instanceof Mecanico) {
                lista.add((Mecanico) t);
            }
        }
        return lista;
    }
    public List<Turismo> obtenerSoloTurismos() {
        List<Turismo> lista = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v instanceof Turismo) {
                lista.add((Turismo) v);
            }
        }
        return lista;
    }

    public List<BiplazaDeportivo> obtenerSoloBiplazas() {
        List<BiplazaDeportivo> lista = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v instanceof BiplazaDeportivo) {
                lista.add((BiplazaDeportivo) v);
            }
        }
        return lista;
    }

    public List<Furgoneta> obtenerSoloFurgonetas() {
        List<Furgoneta> lista = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v instanceof Furgoneta) {
                lista.add((Furgoneta) v);
            }
        }
        return lista;
    }
    public void consumirMotor(int idVehiculo, int segundoActual) {
        if (!motores.isEmpty()) {
            Motor m = motores.removeFirst();
            this.historialMontaje.add(new Evento(segundoActual, m, idVehiculo, "Motor"+(m.getId())+ "instalado"));
        }
    }

    public void consumirTapiceria(int idVehiculo, int segundoActual) {
        if (!tapiceria.isEmpty()) {
            Tapiceria t = tapiceria.removeFirst();
            this.historialMontaje.add(new Evento(segundoActual, t, idVehiculo, "Tapicería"+(t.getId())+"instalada"));
        }
    }

    public void consumirRuedas(int idVehiculo, int segundoActual) {
        for (int i = 0; i < 4; i++) {
            if (!ruedas.isEmpty()) {
                Rueda r = ruedas.removeFirst();
                this.historialMontaje.add(new Evento(segundoActual, r, idVehiculo, "Rueda " + (r.getId()) + " instalada"));
            }
        }
    }
    public boolean hayMotor() {
        return !motores.isEmpty();
    }

    public boolean hayTapiceria() {
        return !tapiceria.isEmpty();
    }

    public boolean hayRuedas() {
        return ruedas.size() >= 4;
    }

    public boolean hayStockParaVehiculo() {
        return hayMotor() && hayTapiceria() && hayRuedas();
    }

    // region GESTIÓN DE VEHÍCULOS
    public void añadirVehiculo(Vehiculo nuevoVehiculo) {
        if (nuevoVehiculo != null) {
            this.vehiculos.add(nuevoVehiculo);
        }
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

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

    public Vehiculo buscarVehiculoPorId(int idVehiculo) {
        for (Vehiculo v : this.vehiculos) {
            if (v.getId() == idVehiculo) return v;
        }
        return null;
    }

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

    // endregion

    public void cargarDatosPrueba() {
        // Componentes
        // 9 Motores
        for (int i = 0; i < 3; i++) {
            this.motores.add(new Motor(TipoMotor.GASOLINA, 1600.0, 115.0, 4));
            this.motores.add(new Motor(TipoMotor.ELECTRICO, 0.0, 200.0, 0));
            this.motores.add(new Motor(TipoMotor.HIBRIDO, 1800.0, 140.0, 4));
        }

        // 47 Ruedas
        for (int i = 0; i < 15; i++) {
            this.ruedas.add(new Rueda(TipoRueda.NORMAL, 205, 16, 91, 210));
            this.ruedas.add(new Rueda(TipoRueda.DEPORTIVO, 245, 19, 98, 300));
            this.ruedas.add(new Rueda(TipoRueda.TODOTERRENO, 265, 17, 112, 190));
        }
        // Añadimos 2 más para llegar a las 47 exactas (15*3 = 45 + 2 = 47)
        this.ruedas.add(new Rueda(TipoRueda.NORMAL, 205, 16, 91, 210));
        this.ruedas.add(new Rueda(TipoRueda.DEPORTIVO, 245, 19, 98, 300));

        // 12 Tapicerías
        for (int i = 0; i < 4; i++) {
            this.tapiceria.add(new Tapiceria(TipoTapiceria.TELA, "Gris", 4));
            this.tapiceria.add(new Tapiceria(TipoTapiceria.CUERO, "Beige", 6));
            this.tapiceria.add(new Tapiceria(TipoTapiceria.ALCANTARA, "Negro", 5));
        }

        // Trabajadores
        this.añadirTrabajador(new Operario("Juan", "García", "Calle A", "11111111A", 101, 1200, "2026-01-01"));
        this.añadirTrabajador(new Operario("Ana", "Sánchez", "Calle B", "22222222B", 102, 1250, "2026-01-10"));
        this.añadirTrabajador(new Operario("Carlos", "Gómez", "Calle F", "44444444D", 104, 1200, "2026-02-10"));
        this.añadirTrabajador(new Operario("Marta", "López", "Calle G", "66666666F", 106, 1200, "2026-02-15"));
        this.añadirTrabajador(new Operario("Elena", "Marín", "Calle H", "77777777G", 107, 1250, "2026-03-01"));
        this.añadirTrabajador(new Operario("Diego", "Pérez", "Calle I", "88888888H", 108, 1200, "2026-03-05"));
        this.añadirTrabajador(new Operario("Julia", "Castro", "Calle J", "99999999I", 109, 1200, "2026-03-10"));
        this.añadirTrabajador(new Operario("Roberto", "Sanz", "Calle K", "10101010J", 110, 1250, "2026-03-12"));
        this.añadirTrabajador(new Operario("Lucía", "Fernández", "Calle L", "12121212K", 111, 1200, "2026-03-15"));
        this.añadirTrabajador(new Operario("Marcos", "Jiménez", "Calle M", "13131313L", 112, 1200, "2026-03-18"));
        this.añadirTrabajador(new Operario("Sofía", "Vargas", "Calle N", "14141414M", 113, 1250, "2026-03-20"));
        this.añadirTrabajador(new Operario("Iván", "Cano", "Calle O", "15151515N", 114, 1200, "2026-03-22"));

        this.añadirTrabajador(new Administrador("Luis", "Martín", "Calle C", "33333333C", 103, 1400, "2026-01-05"));
        this.añadirTrabajador(new GestorPlanta("Pedro", "Ruiz", "Calle E", "55555555E", 105, 1300, "2026-02-01"));
        this.añadirTrabajador(new Mecanico("Andrés", "Molina", "Calle P", "16161616O", 115, 1350, "2026-01-20"));
        // Vehículos
        // Turismos
        this.vehiculos.add(new Turismo("Azul Marino", 5, 1250, 1800.0));
        this.vehiculos.add(new Turismo("Blanco Perlado", 5, 1200, 1750.0));
        this.vehiculos.add(new Turismo("Gris Metalizado", 5, 1300, 1900.0));
        this.vehiculos.add(new Turismo("Negro Mate", 4, 1150, 1650.0));

        // Deportivos
        this.vehiculos.add(new BiplazaDeportivo("Rojo Ferrari", 1050, 1350.0));
        this.vehiculos.add(new BiplazaDeportivo("Naranja Sunset", 1100, 1400.0));
        this.vehiculos.add(new BiplazaDeportivo("Verde British", 1080, 1380.0));

        // Furgonetas
        this.vehiculos.add(new Furgoneta("Blanco Empresa", 3, 1900, 3500.0));
        this.vehiculos.add(new Furgoneta("Amarillo Correos", 2, 1850, 3300.0));
        this.vehiculos.add(new Furgoneta("Azul Eléctrico", 9, 2100, 3800.0));
    }
}