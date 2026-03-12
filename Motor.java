
/**
 * Write a description of class Motor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Motor {

    private TipoMotor tipoMotor;
    private double cilindrada;
    private double potencia;
    private int cilindros;
    
    public enum TipoMotor {
    ELECTRICO,GASOLINA,HIBRIDO
    }


    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    public Motor(TipoMotor tipoMotor, int cilindrada, double potencia, int cilindros) {
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
                "tipoMotor=" + tipoMotor +
                ", cilindrada=" + cilindrada +
                ", potencia=" + potencia +
                ", cilindros=" + cilindros +
                '}';
    }
}
