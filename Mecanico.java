
/**
 * Write a description of class Mecanico here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**
 * Clase que representa al Mecánico de Cinta.
 * Su velocidad de reparación depende de la experiencia acumulada.
 */
public class Mecanico extends Trabajador {
    private int reparacionesRealizadas;

    public Mecanico(String nombre, String apellidos, String direccion, String DNI, 
                    int numSS, double salario, String fechaIngreso) {
        super(nombre, apellidos, direccion, DNI, numSS,"Mecanico",salario, fechaIngreso);
        this.reparacionesRealizadas = 0;
    }

    /**
     * Calcula el tiempo de reparación basado en la experiencia.
     * > 20 reparaciones = 1 segundo.
     * <= 20 reparaciones = aleatorio entre 2 y 5 segundos.
     */
    public int calcularTiempoReparacion() {
        if (this.reparacionesRealizadas > 20) {
            return 1; // Mecánico eficiente
        } else {
            // Genera un número aleatorio entre 2 y 5
            return (int) (Math.random() * 4) + 2; 
        }
    }

    public void incrementarReparaciones() {
        this.reparacionesRealizadas++;
    }
}