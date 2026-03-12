
/**
 * Write a description of class Rueda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rueda {

    private TipoRueda tipoRueda;
    private int ancho;
    private int diametro;
    private int iCarga;
    private int cVelocidad;

    public TipoRueda getTipoRueda() {
        return tipoRueda;
    }

    public void setTipoRueda(TipoRueda tipoRueda) {
        this.tipoRueda = tipoRueda;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public int getiCarga() {
        return iCarga;
    }

    public void setiCarga(int iCarga) {
        this.iCarga = iCarga;
    }

    public int getcVelocidad() {
        return cVelocidad;
    }

    public void setcVelocidad(int cVelocidad) {
        this.cVelocidad = cVelocidad;
    }
}
