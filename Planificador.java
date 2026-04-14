import java.util.List;

public class Planificador {
    private int segundoActual;
    private TipoSimulacion tipoSimulacion;
    private List<CadenaMontaje> cadenas;
    private GestionFabricaDAO dao;

    public Planificador(int segundoActual, TipoSimulacion tipoSimulacion, List<CadenaMontaje> cadenas, GestionFabricaDAO dao){
        this.segundoActual = segundoActual;
        this.tipoSimulacion = tipoSimulacion;
        this.cadenas = cadenas;
        this.dao = dao;
    }
}