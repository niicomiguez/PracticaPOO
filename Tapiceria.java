
/**
 * Write a description of class Tapiceria here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tapiceria {

    private TipoTapiceria tipoTapiceria;
    private String color;
    private int metrosTela;
    
    public enum TipoTapiceria {
    TELA,CUERO,ALCANTARA
    }


    public TipoTapiceria getTipoTapiceria() {
        return tipoTapiceria;
    }

    public Tapiceria(TipoTapiceria tipoTapiceria, String color, int metrosTela) {
        this.tipoTapiceria = tipoTapiceria;
        this.color = color;
        this.metrosTela = metrosTela;
    }

    public void setTipoTapiceria(TipoTapiceria tipoTapiceria) {
        this.tipoTapiceria = tipoTapiceria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMetrosTela() {
        return metrosTela;
    }

    public void setMetrosTela(int metrosTela) {
        this.metrosTela = metrosTela;
    }

    @Override
    public String toString() {
        return "Tapiceria{" +
                "tipoTapiceria=" + tipoTapiceria +
                ", color=" + color +
                ", metrosTela=" + metrosTela +
                '}';
    }
}
