public class CadenaMontaje{
    private TipoVehiculo tipoVehiculo;
    private int idVehiculoActual;
    EstacionMontaje[] estaciones;
    boolean averia;

    public CadenaMontaje(TipoVehiculo tipo) {
        this.tipoVehiculo = tipo;
        this.estaciones = new EstacionMontaje[4];
        this.averia = false;
    }

}