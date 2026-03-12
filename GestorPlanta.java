
/**
 * Write a description of class GestorPlanta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GestorPlanta extends Trabajador {

    public GestorPlanta(String nombre, String apellidos, String direccion, String DNI, 
                        int numSeguridadSocial, Puesto puesto, int salario, String fechaIngreso) {
        
        // Llamada al constructor de la clase abstracta Trabajador
        super(nombre, apellidos, direccion, DNI, numSeguridadSocial, puesto, salario, fechaIngreso);
    }
}