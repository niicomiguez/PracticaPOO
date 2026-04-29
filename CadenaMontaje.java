/**
 * Clase que representa una línea de producción específica en la fábrica.
 * Gestiona un conjunto secuencial de estaciones de montaje y controla los
 * estados de operatividad, incluyendo averías mecánicas y fallos eléctricos.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class CadenaMontaje {
    /** Tipo de vehículo que se fabrica en esta línea. */
    private TipoVehiculo tipoVehiculo;
    /** Array que contiene las cuatro estaciones de trabajo de la cadena. */
    private EstacionMontaje[] estaciones;
    /** Indica si la cadena sufre una avería mecánica. */
    private boolean averia;
    /** Mecánico asignado actualmente a la reparación de la cadena. */
    private Mecanico mecanicoAsignado;
    /** Segundos restantes para finalizar la reparación mecánica. */
    private int tiempoReparacionRestante;
    /** Indica si la cadena está detenida por un fallo en el suministro eléctrico. */
    private boolean caidaLuz;
    /** Segundos restantes para restaurar el sistema eléctrico. */
    private int tiempoArregloLuz;
    /** Administrador asignado a la restauración del sistema. */
    private Administrador adminAsignado;

    /**
     * Constructor de la clase CadenaMontaje.
     * Inicializa las estaciones de trabajo y resetea los estados de error.
     * * @param tipo El tipo de vehículo que procesará esta cadena.
     */
    public CadenaMontaje(TipoVehiculo tipo) {
        this.tipoVehiculo = tipo;
        this.estaciones = new EstacionMontaje[4];
        this.averia = false;
        this.mecanicoAsignado = null;
        this.tiempoReparacionRestante = 0;
        this.caidaLuz = false;
        this.tiempoArregloLuz = 0;
        this.adminAsignado = null;

        for (int i = 0; i < estaciones.length; i++) {
            estaciones[i] = new EstacionMontaje();
        }
    }

    /**
     * Calcula el número total de vehículos presentes en la línea.
     * Recorre todas las estaciones para contabilizar aquellas que tienen un vehículo asignado.
     * * @return El número total de vehículos en curso.
     */
    public int getNumeroVehiculos() {
        int contador = 0;
        for (EstacionMontaje estacion : estaciones) {
            if (estacion.getVehiculoEnEstacion() != null) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * @return true si hay caída de luz, false en caso contrario.
     */
    public boolean isCaidaLuz() {
        return caidaLuz;
    }

    /**
     * @param caidaLuz Estado del suministro eléctrico a establecer.
     */
    public void setCaidaLuz(boolean caidaLuz) {
        this.caidaLuz = caidaLuz;
    }

    /**
     * @return Segundos restantes de la reparación eléctrica.
     */
    public int getTiempoArregloLuz() {
        return tiempoArregloLuz;
    }

    /**
     * @param tiempoArregloLuz Segundos a establecer para el arreglo eléctrico.
     */
    public void setTiempoArregloLuz(int tiempoArregloLuz) {
        this.tiempoArregloLuz = tiempoArregloLuz;
    }

    /**
     * @return El administrador encargado de la restauración actual.
     */
    public Administrador getAdminAsignado() {
        return adminAsignado;
    }

    /**
     * @param adminAsignado El administrador que se asigna a la cadena.
     */
    public void setAdminAsignado(Administrador adminAsignado) {
        this.adminAsignado = adminAsignado;
    }

    /**
     * @return El tipo de vehículo asociado a esta cadena.
     */
    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * @param tipoVehiculo Tipo de vehículo a establecer para esta línea.
     */
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    /**
     * @return El array completo de estaciones de montaje.
     */
    public EstacionMontaje[] getEstaciones() {
        return estaciones;
    }

    /**
     * @param estaciones El conjunto de estaciones a asignar a la cadena.
     */
    public void setEstaciones(EstacionMontaje[] estaciones) {
        this.estaciones = estaciones;
    }

    /**
     * @return true si existe avería mecánica, false en caso contrario.
     */
    public boolean isAveria() {
        return averia;
    }

    /**
     * @param averia Estado de avería mecánica a establecer.
     */
    public void setAveria(boolean averia) {
        this.averia = averia;
    }

    /**
     * @return El mecánico que está reparando la cadena.
     */
    public Mecanico getMecanicoAsignado() {
        return mecanicoAsignado;
    }

    /**
     * @param mecanicoAsignado El mecánico que se asigna para la reparación.
     */
    public void setMecanicoAsignado(Mecanico mecanicoAsignado) {
        this.mecanicoAsignado = mecanicoAsignado;
    }

    /**
     * @return El tiempo que falta para que la cadena vuelva a estar operativa.
     */
    public int getTiempoReparacionRestante() {
        return tiempoReparacionRestante;
    }

    /**
     * @param tiempoReparacionRestante Segundos a establecer para la reparación.
     */
    public void setTiempoReparacionRestante(int tiempoReparacionRestante) {
        this.tiempoReparacionRestante = tiempoReparacionRestante;
    }
}