
/**
 * Write a description of class Motor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Motor {

    private static int contadorId = 1;
    private int id;
    private TipoMotor tipoMotor;
    private double cilindrada;
    private double potencia;
    private int cilindros;
    
    public enum TipoMotor {
    ELECTRICO,GASOLINA,HIBRIDO
    }

    public int getId() {
        return id;
    }
    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    public Motor(TipoMotor tipoMotor, double cilindrada, double potencia, int cilindros) {
        this.id = contadorId++;
        this.tipoMotor = tipoMotor;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.cilindros = cilindros;
    }

    public void setTipoMotor(TipoMotor tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public int getCilindros() {
        return cilindros;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    @Override
    public String toString() {
        return "Motor{" +
                "ID ="+ id +
                ", tipoMotor=" + tipoMotor +
                ", cilindrada=" + cilindrada +
                ", potencia=" + potencia +
                ", cilindros=" + cilindros +
                '}';
    }
}
