import java.util.List;
import java.util.ArrayList;

public class GestionFabricaDAO {
    private List<Motor> motores;
    private List<Rueda> ruedas;
    private List<Tapiceria> tapiceria;

    public GestionFabricaDAO() {
        this.motores = new ArrayList<>();
        this.ruedas = new ArrayList<>();
        this.tapiceria = new ArrayList<>();
    }

    // region GESTIÓN DE MOTORES
    public void añadirMotor(Motor nuevoMotor) {
        if (nuevoMotor != null) {
            this.motores.add(nuevoMotor);
        }
    }

    public String listarMotores() {
        if (motores.isEmpty()) {
            return "No hay motores disponibles";
        }

        StringBuilder listado = new StringBuilder("--- Listado de Motores ---\n");
        for (Motor m : this.motores) {
            listado.append(m.toString()).append("\n");
        }
        return listado.toString();
    }

    public Motor buscarMotorPorId(int idMotor) {
        for (Motor m : this.motores) {
            if (m.getId() == idMotor) {
                return m;
            }
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


}