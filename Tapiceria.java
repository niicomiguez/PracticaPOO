/**
 * Clase que representa el kit de Tapicería para el interior de los vehículos.
 * Gestiona la información sobre el material, el color y la cantidad de tela
 * necesaria para completar el acabado interno de cada unidad producida.
 * * Al igual que el resto de componentes, implementa un sistema de ID 
 * autoincremental para asegurar la trazabilidad en el almacén de la fábrica.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public class Tapiceria {

    /** Contador estático para la generación de identificadores únicos secuenciales. */
    private static int contadorId = 1;

    /** Identificador único del kit de tapicería. */
    private final int id;

    /** Material de la tapicería (Tela, Cuero, Alcántara). */
    private TipoTapiceria tipoTapiceria;

    /** Color del acabado interior. */
    private String color;

    /** Cantidad de material disponible o necesario en metros. */
    private int metrosTela;

    /**
     * Constructor para la clase Tapiceria.
     * Incrementa automáticamente el contador global para asignar el ID único.
     * * @param tipoTapiceria Categoría del material.
     * @param color         Nombre del color del acabado.
     * @param metrosTela    Metros de material que componen el kit.
     */
    public Tapiceria(TipoTapiceria tipoTapiceria, String color, int metrosTela) {
        this.id = contadorId++;
        this.tipoTapiceria = tipoTapiceria;
        this.color = color;
        this.metrosTela = metrosTela;
    }

    // region GETTERS Y SETTERS

    /** @return El ID único del componente. */
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
    // endregion

    /**
     * Devuelve una descripción detallada del kit de tapicería.
     * @return Cadena formateada con el ID, material, color y metros.
     */
    @Override
    public String toString() {
        return String.format("Tapicería [ID=%d] | Material: %-10s | Color: %-10s | Metros: %d",
                id, tipoTapiceria, color, metrosTela);
    }
}