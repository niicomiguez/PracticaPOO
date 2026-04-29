/**
 * Clase que representa al Administrador del Sistema dentro de la factoría.
 * * Se encarga de la gestión técnica y de la restauración operativa de las
 * cadenas de montaje ante fallos críticos de software o sistemas eléctricos.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class Administrador extends Trabajador {

    /**
     * Constructor para la clase Administrador.
     * * @param nombre             Nombre del administrador.
     * @param apellidos          Apellidos del administrador.
     * @param direccion          Dirección de residencia.
     * @param DNI                Documento Nacional de Identidad.
     * @param numSeguridadSocial Número de la Seguridad Social.
     * @param salario            Salario base del trabajador.
     * @param fechaIngreso       Fecha de incorporación a la empresa.
     */
    public Administrador(String nombre, String apellidos, String direccion, String DNI,
                         int numSeguridadSocial, double salario, String fechaIngreso) {

        // Enviamos los datos comunes a la clase padre Trabajador
        super(nombre, apellidos, direccion, DNI, numSeguridadSocial, "Administrador", salario, fechaIngreso);
    }
}