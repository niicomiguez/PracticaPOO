import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gestiona el conjunto de estaciones de montaje y el flujo de los vehículos.
 */
public class CadenaMontaje {
    private String id;
    private List<EstacionMontaje> estaciones;
    private boolean averiada;
    private boolean sinLuz;

    public CadenaMontaje(String id) {
        this.id = id;
        this.averiada = false;
        this.sinLuz = false;
        this.estaciones = new ArrayList<>();
        
        // Inicializamos las 4 estaciones (0: CHASIS, 1: MOTOR, 2: TAPICERIA, 3: RUEDAS)
        for (int i = 0; i < 4; i++) {
            estaciones.add(new EstacionMontaje(i));
        }
    }

    /**
     * El Planificador llamará a este método cada segundo.
     * Si la cadena está operativa, todas las estaciones avanzan su trabajo.
     */
    public void avanzarSegundo() {
        if (!averiada && !sinLuz) {
            for (EstacionMontaje estacion : estaciones) {
                if (estacion.getVehiculo() != null && estacion.getOperario() != null) {
                    int tiempoActual = estacion.getTiempoRestante();
                    if (tiempoActual > 0) {
                        estacion.setTiempoRestante(tiempoActual - 1);
                    }
                }
            }
        }
    }

    /**
     * Asigna operarios aleatorios a las estaciones que tengan vehículo pero no trabajador.
     * Cumple con el requisito de selección aleatoria del enunciado.
     */
    public void asignarOperariosAleatorios(GestionFabricaDAO dao, int segundoActual) {
        Random rand = new Random();
        List<Operario> plantilla = dao.obtenerSoloOperarios();
        
        // Filtramos solo los que son de tipo Operario
        List<Operario> operariosDisponibles = new ArrayList<>();
        for (Trabajador t : plantilla) {
            if (t instanceof Operario) {
                operariosDisponibles.add((Operario) t);
            }
        }

        if (operariosDisponibles.isEmpty()) return;

        for (EstacionMontaje est : estaciones) {
            if (est.getVehiculo() != null && est.getOperario() == null) {
                Operario elegido = operariosDisponibles.get(rand.nextInt(operariosDisponibles.size()));
                est.setOperario(elegido);
                
                dao.registrarEvento(segundoActual, "Cadena " + id + ": Operario " + 
                                   elegido.getNombre() + " asignado a fase " + est.getIdFase());
            }
        }
    }

    /**
     * Gestiona el movimiento de los vehículos entre estaciones.
     * Se recorre en orden inverso (de 3 a 0) para permitir el desplazamiento.
     */
    public void moverVehiculos(GestionFabricaDAO dao, int segundoActual) {
        if (averiada || sinLuz) return;

        for (int i = 3; i >= 0; i--) {
            EstacionMontaje estActual = estaciones.get(i);
            
            // Si la estación ha terminado su trabajo (tiempo 0) y tiene un vehículo
            if (estActual.getVehiculo() != null && estActual.getTiempoRestante() <= 0) {
                
                Vehiculo v = estActual.getVehiculo();
                
                if (i == 3) { // Estación de RUEDAS (Última)
                    v.siguienteEstado(); // Pasa a FINALIZADO
                    dao.registrarEvento(segundoActual, "Vehículo " + v.getBastidor() + " terminado y sale de la cadena " + id);
                    estActual.liberarEstacion();
                } else {
                    // Intentamos mover a la siguiente estación (i + 1)
                    EstacionMontaje estSiguiente = estaciones.get(i + 1);
                    
                    if (estSiguiente.estaLibre()) {
                        v.siguienteEstado(); // Cambia al estado de la siguiente fase
                        estSiguiente.recibirVehiculo(v);
                        estActual.liberarEstacion();
                        
                        dao.registrarEvento(segundoActual, "Vehículo " + v.getBastidor() + " avanza a fase " + estSiguiente.getIdFase());
                    }
                }
            }
        }
    }

    // --- Getters y Setters ---

    public String getId() { return id; }
    public List<EstacionMontaje> getEstaciones() { return estaciones; }
    public boolean isAveriada() { return averiada; }
    public void setAveriada(boolean averiada) { this.averiada = averiada; }
    public boolean isSinLuz() { return sinLuz; }
    public void setSinLuz(boolean sinLuz) { this.sinLuz = sinLuz; }
}