
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
    private int numReparaciones; 

    public Mecanico(String nombre, String apellidos, String direccion, String DNI, 
                    int numSeguridadSocial, Puesto puesto, int salario, String fechaIngreso) {
        
        super(nombre, apellidos, direccion, DNI, numSeguridadSocial, puesto, salario, fechaIngreso);
        this.numReparaciones = 0; 
    }

    /**
     * Un mecánico es eficiente si ha realizado más de 20 reparaciones.
     * @return true si es eficiente, false si es estándar.
     */
    public boolean esEficiente() {
        return numReparaciones > 20;
    }

    public int getNumReparaciones() {
        return numReparaciones;
    }

    public void incrementarReparaciones() {
        this.numReparaciones++;
    }
}