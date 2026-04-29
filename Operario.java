/**
 * Clase que representa a un Operario de línea en la fábrica.
 * El operario es el encargado de ejecutar las tareas de montaje de componentes.
 * Su productividad evoluciona con la experiencia: a partir de un umbral de
 * montajes realizados, reduce significativamente el tiempo necesario para
 * completar su tarea, optimizando la cadena de producción.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class Operario extends Trabajador {
    /** Contador de vehículos cuyo montaje ha sido completado por este operario. */
    private int numMontajes;

    /**
     * Constructor para la clase Operario.
     * * @param nombre              Nombre del operario.
     * @param apellidos           Apellidos del operario.
     * @param direccion           Dirección postal.
     * @param DNI                 Documento Nacional de Identidad.
     * @param numSeguridadSocial  Número de afiliación a la Seguridad Social.
     * @param salario             Sueldo base.
     * @param fechaIngreso        Fecha de incorporación a la fábrica.
     */
    public Operario(String nombre, String apellidos, String direccion, String DNI,
                    int numSeguridadSocial, double salario, String fechaIngreso) {
        // Inicializa al trabajador con el rol fijo de "Operario"
        super(nombre, apellidos, direccion, DNI, numSeguridadSocial, "Operario", salario, fechaIngreso);
        this.numMontajes = 0;
    }

    /**
     * Evalúa si el operario ha alcanzado el estatus de eficiencia.
     * * @return true si ha superado los 10 montajes realizados; false en caso contrario.
     */
    public boolean esEficiente() {
        return numMontajes > 10;
    }

    /**
     * Calcula el tiempo (en segundos de simulación) que el operario tarda en montar un componente.
     * * @return 1 segundo si es eficiente, 3 segundos si aún está en fase de aprendizaje.
     */
    public int getTiempoTarea() {
        return esEficiente() ? 1 : 3;
    }

    /**
     * @return El número total de montajes realizados hasta el momento.
     */
    public int getNumMontajes() {
        return numMontajes;
    }

    /**
     * Incrementa el contador de montajes tras finalizar con éxito la tarea asignada.
     */
    public void incrementarMontajes() {
        this.numMontajes++;
    }
}