import java.util.List;
import java.util.ArrayList;

public class GestionFabricaDAO {
    private List<Motor> motores;
    private List<Rueda> ruedas;
    private List<Tapiceria> tapiceria;
    private List<Trabajador> trabajadores;

    public GestionFabricaDAO() {
        this.motores = new ArrayList<>();
        this.ruedas = new ArrayList<>();
        this.tapiceria = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
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
}