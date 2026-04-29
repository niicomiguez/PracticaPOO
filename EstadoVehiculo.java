/**
 * Clase de enumeración que define los posibles estados de montaje de un vehículo.
 * Estos estados representan las fases secuenciales por las que debe pasar cada
 * unidad en la línea de producción para garantizar un ensamblaje correcto.
 * * @author Nicolás Míguez Ramos
 * @version 1.0
 */
public enum EstadoVehiculo {
    /** Estado inicial: No Definido. */
    ND,
    /** Fase 1: Estructura del chasis preparada. */
    CHASIS,
    /** Fase 2: Bloque motor instalado. */
    MOTOR,
    /** Fase 3: Interior y tapicería montados. */
    TAPICERIA,
    /** Fase final: Tren de rodaje completado y vehículo listo. */
    RUEDAS
}