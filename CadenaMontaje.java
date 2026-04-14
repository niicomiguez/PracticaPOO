public class CadenaMontaje{
    private TipoVehiculo tipoVehiculo;
    EstacionMontaje[] estaciones;
    boolean averia;

    public CadenaMontaje(TipoVehiculo tipo) {
        this.tipoVehiculo = tipo;
        this.estaciones = new EstacionMontaje[4];
        this.averia = false;

        for (int i = 0; i < estaciones.length; i++) {
            estaciones[i] = new EstacionMontaje();
        }
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
}