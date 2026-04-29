/**
 * Clase que representa al Mecánico de Cinta dentro de la fábrica.
 * El mecánico es el encargado de solventar las averías en las líneas de montaje.
 * Su eficiencia está ligada a la experiencia acumulada: a mayor número de
 * reparaciones realizadas, menor es el tiempo necesario para completar una intervención.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class Mecanico extends Trabajador {
    /** Contador de intervenciones técnicas completadas por el mecánico. */
    private int reparacionesRealizadas;

    /**
     * Constructor para la clase Mecanico.
     * * @param nombre              Nombre del mecánico.
     * @param apellidos           Apellidos del mecánico.
     * @param direccion           Dirección de residencia.
     * @param DNI                 Documento Nacional de Identidad.
     * @param numSS               Número de la Seguridad Social.
     * @param salario             Sueldo asignado.
     * @param fechaIngreso        Fecha de alta en la empresa.
     */
    public Mecanico(String nombre, String apellidos, String direccion, String DNI,
                    int numSS, double salario, String fechaIngreso) {
        // Inicializa el trabajador con el rol específico de "Mecanico"
        super(nombre, apellidos, direccion, DNI, numSS, "Mecanico", salario, fechaIngreso);
        this.reparacionesRealizadas = 0;
    }

    /**
     * Determina el tiempo de inactividad de la cinta durante una reparación.
     * La lógica de negocio establece que:
     * - Si ha realizado más de 20 reparaciones: se considera veterano (1 segundo).
     * - Si ha realizado 20 o menos: es junior (aleatorio entre 2 y 5 segundos).
     * * @return int Segundos que durará la reparación.
     */
    public int calcularTiempoReparacion() {
        if (this.reparacionesRealizadas > 20) {
            return 1; // Mecánico experto: reparación inmediata
        } else {
            // Mecánico en formación: genera un número aleatorio entre 2 y 5
            return (int) (Math.random() * 4) + 2;
        }
    }

    /**
     * Incrementa en una unidad el historial de reparaciones del mecánico.
     * Este método debe llamarse cada vez que una avería sea solventada con éxito.
     */
    public void incrementarReparaciones() {
        this.reparacionesRealizadas++;
    }

    /**
     * @return El número total de reparaciones acumuladas.
     */
    public int getReparacionesRealizadas() {
        return reparacionesRealizadas;
    }

    /**
     * Permite ajustar manualmente el número de reparaciones (útil para carga de datos).
     * @param reparacionesRealizadas Nuevo valor para el contador de experiencia.
     */
    public void setReparacionesRealizadas(int reparacionesRealizadas) {
        this.reparacionesRealizadas = reparacionesRealizadas;
    }
}