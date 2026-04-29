/**
 * Clase que representa a un Gestor de Planta dentro de la fábrica.
 * El Gestor de Planta es responsable de supervisar el flujo operativo,
 * coordinar los recursos y asegurar que los objetivos de producción se cumplan
 * según el cronograma de la simulación.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class GestorPlanta extends Trabajador {

    /**
     * Constructor para la clase GestorPlanta.
     * * @param nombre              Nombre de pila del gestor.
     * @param apellidos           Apellidos del gestor.
     * @param direccion           Dirección postal de residencia.
     * @param DNI                 Documento Nacional de Identidad.
     * @param numSeguridadSocial  Número de afiliación a la Seguridad Social.
     * @param salario             Sueldo bruto asignado.
     * @param fechaIngreso        Fecha de contratación en la fábrica.
     */
    public GestorPlanta(String nombre, String apellidos, String direccion, String DNI,
                        int numSeguridadSocial, double salario, String fechaIngreso) {

        // Llamada al constructor de la clase abstracta Trabajador definiendo el rol específico
        super(nombre, apellidos, direccion, DNI, numSeguridadSocial, "Gestor Planta", salario, fechaIngreso);
    }
}