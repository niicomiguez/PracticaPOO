
/**
 * Write a description of class DashBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DashBoard
{
    public GestionFabricaDAO almacen;

    public DashBoard (GestionFabricaDAO dao){
        this.almacen=dao;
    }

    public void mostrarBalanceAlmacen() {
        System.out.println("========================================");
        System.out.println("       ESTADO DEL ALMACÉN (Stock)       ");
        System.out.println("========================================");

        // Consultamos al DAO para saber los tamaños de las listas
        System.out.println("Motores disponibles: " + almacen.listarMotores());
        System.out.println("Ruedas disponibles: " + almacen.listarRuedas());
        System.out.println("Tapicerías disponibles: " + almacen.listarTapicerias());
        System.out.println("========================================\n");
    }
    
        public void consultarHistorialPorSegundo(int segundoBuscado) {
        System.out.println("--- Eventos ocurridos en el segundo " + segundoBuscado + " ---");
        boolean encontrado = false;
        
        for (String registro : almacen.getHistorial()) {
            // Buscamos si el String empieza por "Segundo X:"
            if (registro.startsWith("Segundo " + segundoBuscado + ":")) {
                System.out.println(registro);
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            System.out.println("No hubo actividad en este segundo.");
        }
    }
}