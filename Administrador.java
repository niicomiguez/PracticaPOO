
/**
 * Write a description of class Administrador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**
 * Clase que representa al Administrador del Sistema.
 * Encargado de restaurar el software y las cadenas tras errores o apagones.
 */
public class Administrador extends Trabajador {

    public Administrador(String nombre, String apellidos, String direccion, String DNI, 
                                int numSeguridadSocial, double salario, String fechaIngreso) {
        
        // Enviamos los datos comunes a la clase padre Trabajador
        super(nombre, apellidos, direccion, DNI, numSeguridadSocial,"Administrador", salario, fechaIngreso);
    }
}