public class CadenaMontaje{
    private TipoVehiculo tipoVehiculo;
    private EstacionMontaje[] estaciones;
    private boolean averia;
    private Mecanico mecanicoAsignado;
    private int tiempoReparacionRestante;

    public CadenaMontaje(TipoVehiculo tipo) {
        this.tipoVehiculo = tipo;
        this.estaciones = new EstacionMontaje[4];
        this.averia = false;
        this.mecanicoAsignado=null;
        this.tiempoReparacionRestante=0;
        for (int i = 0; i < estaciones.length; i++) {
            estaciones[i] = new EstacionMontaje();
        }
    }
    public int getNumeroVehiculos(){
        int contador=0;
        for (EstacionMontaje estacion : estaciones) {
            if (estacion.getVehiculoEnEstacion() != null){
                contador++;
            }
        }
        return contador;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public EstacionMontaje[] getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(EstacionMontaje[] estaciones) {
        this.estaciones = estaciones;
    }

    public boolean isAveria() {
        return averia;
    }

    public void setAveria(boolean averia) {
        this.averia = averia;
    }

    public Mecanico getMecanicoAsignado() {
        return mecanicoAsignado;
    }

    public void setMecanicoAsignado(Mecanico mecanicoAsignado) {
        this.mecanicoAsignado = mecanicoAsignado;
    }

    public int getTiempoReparacionRestante() {
        return tiempoReparacionRestante;
    }

    public void setTiempoReparacionRestante(int tiempoReparacionRestante) {
        this.tiempoReparacionRestante = tiempoReparacionRestante;
    }
}