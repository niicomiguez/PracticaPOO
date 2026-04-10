import java.util.List;
import java.util.ArrayList;

public class GestionFabricaDAO {
    private List<Motor> motores;
    private List<Rueda> ruedas;
    private List<Tapiceria> tapiceria;
    private List<Trabajador> trabajadores;
    private List<String> historialMontaje = new ArrayList<>();
    private List<Vehiculo> vehiculosPrueba;

    public void registrarEvento(int segundo, String detalle) {
        String registro = "Segundo " + segundo + ": " + detalle;
        historialMontaje.add(registro);
    }
    
    public List<String> getHistorial() {
        return historialMontaje;
    }

    public GestionFabricaDAO() {
        this.motores = new ArrayList<>();
        this.ruedas = new ArrayList<>();
        this.tapiceria = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
        this.vehiculosPrueba = new ArrayList<>();
    }

    // region GESTIÓN DE MOTORES
    public void añadirMotor(Motor nuevoMotor) {
        if (nuevoMotor != null) {
            this.motores.add(nuevoMotor);
        }
    }

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

    public String borrarMotorPorId(int idMotor) {
        Motor motorEncontrado = buscarMotorPorId(idMotor);
        if (motorEncontrado != null) {
            this.motores.remove(motorEncontrado);
            return "Motor con id " + idMotor + " borrado correctamente";
        }
        return "No existe un motor con id " + idMotor + " en la fábrica";
    }
    // endregion

    // region GESTIÓN DE RUEDAS
    public void añadirRueda(Rueda nuevaRueda) {
        if (nuevaRueda != null) {
            this.ruedas.add(nuevaRueda);
        }
    }

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

    public String borrarRuedaPorId(int idRueda) {
        Rueda ruedaEncontrada = buscarRuedaPorId(idRueda);
        if (ruedaEncontrada != null) {
            this.ruedas.remove(ruedaEncontrada);
            return "Rueda con id " + idRueda + " borrada correctamente";
        }
        return "No existe rueda con id " + idRueda;
    }
    // endregion

    // region GESTIÓN DE TAPICERÍA
    public void añadirTapiceria(Tapiceria nuevaTapiceria) {
        if (nuevaTapiceria != null) {
            this.tapiceria.add(nuevaTapiceria);
        }
    }

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

    public String borrarTapiceriaPorId(int idTapiceria) {
        Tapiceria tapiceriaEncontrada = buscarTapiceriaPorId(idTapiceria);
        if (tapiceriaEncontrada != null) {
            this.tapiceria.remove(tapiceriaEncontrada);
            return "Tapicería con id " + idTapiceria + " borrada correctamente";
        }
        return "No existe tapicería con id " + idTapiceria;
    }
    // endregion

    // region REGION TRABAJADORES
    public void añadirTrabajador(Trabajador nuevoTrabajador) {
        if (nuevoTrabajador != null) {
            this.trabajadores.add(nuevoTrabajador);
        }
    }

    public String listarTrabajadores() {
        if (trabajadores.isEmpty()) {
            return "No hay trabajadores registrados en la fábrica.";
        }

        StringBuilder listado = new StringBuilder("--- Plantilla de Trabajadores ---\n");
        for (Trabajador t : this.trabajadores) {
            listado.append(t.toString()).append("\n");
        }
        return listado.toString();
    }
    public List<Operario> obtenerSoloOperarios() {
    List<Operario> soloOperarios = new ArrayList<>();
    
    for (Trabajador t : this.trabajadores) {
        // Comprobamos si el trabajador es una instancia de Operario
        if (t instanceof Operario) {
            // Hacemos el casting y lo añadimos a la nueva lista
            soloOperarios.add((Operario) t);
        }
    }
    
    return soloOperarios;
}

    public Trabajador buscarTrabajadorPorDNI(String dniBuscado) {
        for (Trabajador t : this.trabajadores) {
            // Usamos equalsIgnoreCase para evitar fallos por mayúsculas/minúsculas
            if (t.getDNI().equalsIgnoreCase(dniBuscado)) {
                return t;
            }
        }
        return null;
    }

    public String borrarTrabajadorPorDNI(String dni) {
        Trabajador trabajadorEncontrado = buscarTrabajadorPorDNI(dni);

        if (trabajadorEncontrado != null) {
            this.trabajadores.remove(trabajadorEncontrado);
            return "Trabajador con DNI " + dni + " eliminado correctamente";
        }
        return "No existe un trabajador con el DNI " + dni;
    }
    // endregion
    
    /**
 * Genera datos iniciales para poder probar la simulación sin insertarlos manualmente.
 * Crea 6 trabajadores, componentes variados y 3 vehículos de prueba.
 */
public void cargarDatosPrueba() {
    // 1. CARGAR COMPONENTES (Necesarios para que los vehículos tengan objetos válidos)
    // Motores
    Motor m1 = new Motor(TipoMotor.GASOLINA, 1600.0, 115.0, 4);
    Motor m2 = new Motor(TipoMotor.ELECTRICO, 0.0, 200.0, 0);
    Motor m3 = new Motor(TipoMotor.HIBRIDO, 1800.0, 140.0, 4);
    this.motores.add(m1);
    this.motores.add(m2);
    this.motores.add(m3);

    // Ruedas
    Rueda r1 = new Rueda(TipoRueda.NORMAL, 205, 16, 91, 210);
    Rueda r2 = new Rueda(TipoRueda.DEPORTIVO, 245, 19, 98, 300);
    Rueda r3 = new Rueda(TipoRueda.TODOTERRENO, 265, 17, 112, 190);
    this.ruedas.add(r1);
    this.ruedas.add(r2);
    this.ruedas.add(r3);

    // Tapicerías
    Tapiceria t1 = new Tapiceria(TipoTapiceria.TELA, "Gris", 4);
    Tapiceria t2 = new Tapiceria(TipoTapiceria.CUERO, "Beige", 6);
    Tapiceria t3 = new Tapiceria(TipoTapiceria.ALCANTARA, "Negro", 5);
    this.tapiceria.add(t1);
    this.tapiceria.add(t2);
    this.tapiceria.add(t3);

    // 2. CARGAR TRABAJADORES (6 en total: 2 Operarios, 2 Administrativos, 2 Mecánicos/otros)
    // Ajusta las clases hijas según las que tengas creadas (ej: Administrativo, Directivo)
    this.añadirTrabajador(new Operario("Juan", "García", "Calle A", "11111111A", 101, 1200, "2026-01-01"));
    this.añadirTrabajador(new Operario("Ana", "Sánchez", "Calle B", "22222222B", 102, 1250, "2026-01-10"));
    this.añadirTrabajador(new Operario("Alberto", "Ruiz", "Calle E", "55555555E", 105, 1300, "2026-02-01"));
    this.añadirTrabajador(new Operario("Benjamin", "Vila", "Calle F", "66666666F", 106, 1350, "2026-02-05"));
    
    this.añadirTrabajador(new Administrador("Luis", "Martín", "Calle C", "33333333C", 103, 1400, "2026-01-05"));
    this.añadirTrabajador(new Administrador("Marta", "Gómez", "Calle D", "44444444D", 104, 1450, "2026-01-12"));
    
    this.añadirTrabajador(new GestorPlanta("Pedro", "Ruiz", "Calle E", "55555555E", 105, 1300, "2026-02-01"));
    this.añadirTrabajador(new GestorPlanta("Lucía", "Vila", "Calle F", "66666666F", 106, 1350, "2026-02-05"));

    // 3. CARGAR VEHÍCULOS (1 de cada tipo con componentes distintos)
    // Usamos los componentes creados arriba
    this.vehiculosPrueba.add(new Turismo("VIN-TUR-01", "Rojo", 5, 1200, 1700.0));
    this.vehiculosPrueba.add(new Furgoneta("VIN-FUR-02", "Blanco", 3, 1800, 3500.0));
    this.vehiculosPrueba.add(new BiplazaDeportivo("VIN-BIP-03", "Amarillo", 1100, 1400.0));
}
}