
/**
 * Write a description of class Operario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Operario extends Trabajador {
    private int numMontajes; 

    public Operario(String nombre, String apellidos, String direccion, String DNI, 
                    int numSeguridadSocial, Puesto puesto, int salario, String fechaIngreso) {
        super(nombre, apellidos, direccion, DNI, numSeguridadSocial, puesto, salario, fechaIngreso);
        this.numMontajes = 0; 
    }

    public boolean esEficiente() {
        return numMontajes > 10;
    }

    public int getNumMontajes() { return numMontajes; }
    public void incrementarMontajes() { this.numMontajes++; }
}