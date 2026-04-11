import java.util.List;
import java.util.ArrayList;

public class GestionFabricaDAO {
    private List<Motor> motores;
    private List<Rueda> ruedas;
    private List<Tapiceria> tapiceria;
    private List<Trabajador> trabajadores;
    private List<Evento> historialMontaje = new ArrayList<>();
    private List<Vehiculo> vehiculosPrueba;

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

    public GestionFabricaDAO() {
        this.motores = new ArrayList<>();
        this.ruedas = new ArrayList<>();
        this.tapiceria = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
        this.vehiculosPrueba = new ArrayList<>();
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

// endregion
    // endregion

    public void cargarDatosPrueba() {
        // Componentes
        Motor m1 = new Motor(TipoMotor.GASOLINA, 1600.0, 115.0, 4);
        Motor m2 = new Motor(TipoMotor.ELECTRICO, 0.0, 200.0, 0);
        Motor m3 = new Motor(TipoMotor.HIBRIDO, 1800.0, 140.0, 4);
        this.motores.add(m1); this.motores.add(m2); this.motores.add(m3);

        Rueda r1 = new Rueda(TipoRueda.NORMAL, 205, 16, 91, 210);
        Rueda r2 = new Rueda(TipoRueda.DEPORTIVO, 245, 19, 98, 300);
        Rueda r3 = new Rueda(TipoRueda.TODOTERRENO, 265, 17, 112, 190);
        this.ruedas.add(r1); this.ruedas.add(r2); this.ruedas.add(r3);

        Tapiceria t1 = new Tapiceria(TipoTapiceria.TELA, "Gris", 4);
        Tapiceria t2 = new Tapiceria(TipoTapiceria.CUERO, "Beige", 6);
        Tapiceria t3 = new Tapiceria(TipoTapiceria.ALCANTARA, "Negro", 5);
        this.tapiceria.add(t1); this.tapiceria.add(t2); this.tapiceria.add(t3);

        // Trabajadores
        this.añadirTrabajador(new Operario("Juan", "García", "Calle A", "11111111A", 101, 1200, "2026-01-01"));
        this.añadirTrabajador(new Operario("Ana", "Sánchez", "Calle B", "22222222B", 102, 1250, "2026-01-10"));
        this.añadirTrabajador(new Administrador("Luis", "Martín", "Calle C", "33333333C", 103, 1400, "2026-01-05"));
        this.añadirTrabajador(new GestorPlanta("Pedro", "Ruiz", "Calle E", "55555555E", 105, 1300, "2026-02-01"));

        // Vehículos
        this.vehiculosPrueba.add(new Turismo("VIN-TUR-01", "Rojo", 5, 1200, 1700.0));
        this.vehiculosPrueba.add(new Furgoneta("VIN-FUR-02", "Blanco", 3, 1800, 3500.0));
        this.vehiculosPrueba.add(new BiplazaDeportivo("VIN-BIP-03", "Amarillo", 1100, 1400.0));
    }
}