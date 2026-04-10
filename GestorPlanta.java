
/**
 * Write a description of class GestorPlanta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GestorPlanta extends Trabajador {

    public GestorPlanta(String nombre, String apellidos, String direccion, String DNI, 
                        int numSeguridadSocial, int salario, String fechaIngreso) {
        
        // Llamada al constructor de la clase abstracta Trabajador
        super(nombre, apellidos, direccion, DNI, numSeguridadSocial,"Gestor Planta", salario, fechaIngreso);
    }
}