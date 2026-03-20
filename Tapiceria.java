
/**
 * Write a description of class Tapiceria here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tapiceria {

    private static int contadorId = 1;
    private int id;
    private TipoTapiceria tipoTapiceria;
    private String color;
    private int metrosTela;
    

    public Tapiceria(TipoTapiceria tipoTapiceria, String color, int metrosTela) {
        this.id = contadorId++;
        this.tipoTapiceria = tipoTapiceria;
        this.color = color;
        this.metrosTela = metrosTela;
    }

    public int getId() {
        return id;
    }
    public TipoTapiceria getTipoTapiceria() {
        return tipoTapiceria;
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
