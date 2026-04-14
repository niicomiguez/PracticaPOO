
/**
 * Write a description of class factory_main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.List;
import java.util.Scanner;

public class factory_main {
    private static int segundoActual = 0;
    public static void main(String[] args) {
        GestionFabricaDAO dao = new GestionFabricaDAO();
        // Cargar datos iniciales para no empezar de cero
        dao.cargarDatosPrueba();

        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- FACTORÍA DE COCHES 2026 ---");
            System.out.println("1. Gestión de Almacén (Componentes)");
            System.out.println("2. Gestión de Trabajadores");
            System.out.println("3. Planificador (Simulación)");
            System.out.println("4. Consultar Registros");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1: menuAlmacen(dao, sc); break;
                case 2: menuTrabajadores(dao, sc); break;
                case 3: ejecutarPlanificador(dao, sc); break;
                case 4: menuRegistros(dao, sc); break;
                case 0: salir = true; break;
                default: System.out.println("Opción no válida.");
            }
        }
    }
    public static void menuAlmacen(GestionFabricaDAO dao,Scanner sc){
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- GESTIÓN DE ALMACÉN ---");
            System.out.println("1. Gestión de Motores");
            System.out.println("2. Gestión de Tapicerías");
            System.out.println("3. Gestión de Ruedas");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();

            switch (opcion) {
                case 1: menuMotores(dao, sc); break;
                case 2: menuTapicerias(dao,sc); break;
                case 3: menuRuedas(dao,sc); break;
                case 0: salir = true; break;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    public static void menuMotores(GestionFabricaDAO dao, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE MOTORES ---");
            System.out.println("1. Listar motores en stock");
            System.out.println("2. Añadir nuevo motor");
            System.out.println("3. Borrar motor por ID");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println(dao.listarMotores());
                    break;
                case 2:
                    crearNuevoMotor(dao, sc);
                    break;
                case 3:
                    System.out.print("Introduce el ID del motor a borrar: ");
                    int idBorrar = sc.nextInt();
                    System.out.println(dao.borrarMotorPorId(idBorrar, 0));
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    private static void crearNuevoMotor(GestionFabricaDAO dao, Scanner sc) {
        System.out.println("\n-- Seleccione Tipo de Motor --");
        System.out.println("1. GASOLINA | 2. ELECTRICO | 3. HIBRIDO");
        int tipoInt = sc.nextInt();

        TipoMotor tipo = TipoMotor.GASOLINA; // Valor por defecto
        switch(tipoInt) {
            case 2: tipo = TipoMotor.ELECTRICO; break;
            case 3: tipo = TipoMotor.HIBRIDO; break;
        }

        System.out.print("Cilindrada (ej: 1600,0): ");
        double cilindrada = sc.nextDouble();

        System.out.print("Potencia (CV): ");
        double potencia = sc.nextDouble();

        System.out.print("Número de cilindros: ");
        int cilindros = sc.nextInt();

        Motor nuevo = new Motor(tipo, cilindrada, potencia, cilindros);

        dao.añadirMotor(nuevo, 0);

        System.out.println("\n Motor creado y registrado correctamente.");
    }
    public static void menuTapicerias(GestionFabricaDAO dao, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE TAPICERÍAS ---");
            System.out.println("1. Listar tapicerías en stock");
            System.out.println("2. Añadir nueva tapicería");
            System.out.println("3. Borrar tapicería por ID");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println(dao.listarTapicerias());
                    break;
                case 2:
                    crearNuevaTapiceria(dao, sc);
                    break;
                case 3:
                    System.out.print("Introduce el ID de la tapicería a borrar: ");
                    int idBorrar = sc.nextInt();
                    System.out.println(dao.borrarTapiceriaPorId(idBorrar, 0));
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void crearNuevaTapiceria(GestionFabricaDAO dao, Scanner sc) {
        System.out.println("\n-- Seleccione Tipo de Tapicería --");
        System.out.println("1. TELA | 2. CUERO | 3. ALCANTARA");
        int tipoInt = sc.nextInt();

        TipoTapiceria tipo = TipoTapiceria.TELA;
        switch(tipoInt) {
            case 2: tipo = TipoTapiceria.CUERO; break;
            case 3: tipo = TipoTapiceria.ALCANTARA; break;
        }

        System.out.print("Color de la tapicería: ");
        String color = sc.next();

        System.out.print("Número de plazas/asientos: ");
        int plazas = sc.nextInt();

        Tapiceria nueva = new Tapiceria(tipo, color, plazas);

        dao.añadirTapiceria(nueva, 0);

        System.out.println("\n Tapicería creada y registrada correctamente.");
    }

    public static void menuRuedas(GestionFabricaDAO dao, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE RUEDAS ---");
            System.out.println("1. Listar ruedas en stock");
            System.out.println("2. Añadir nueva rueda");
            System.out.println("3. Borrar rueda por ID");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println(dao.listarRuedas());
                    break;
                case 2:
                    crearNuevaRueda(dao, sc);
                    break;
                case 3:
                    System.out.print("Introduce el ID de la rueda a borrar: ");
                    int idBorrar = sc.nextInt();
                    System.out.println(dao.borrarRuedaPorId(idBorrar, 0));
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void crearNuevaRueda(GestionFabricaDAO dao, Scanner sc) {
        System.out.println("\n-- Seleccione Tipo de Rueda --");
        System.out.println("1. NORMAL | 2. DEPORTIVO | 3. TODOTERRENO ");
        int tipoInt = sc.nextInt();

        TipoRueda tipo = TipoRueda.NORMAL;
        switch(tipoInt) {
            case 2: tipo = TipoRueda.DEPORTIVO; break;
            case 3: tipo = TipoRueda.TODOTERRENO; break;
        }

        System.out.print("Anchura (mm): ");
        int anchura = sc.nextInt();

        System.out.print("Diámetro (pulgadas): ");
        int diametro = sc.nextInt();

        System.out.print("Índice de carga: ");
        int indiceCarga = sc.nextInt();

        System.out.print("Código de velocidad (máx km/h): ");
        int velMax = sc.nextInt();

        Rueda nueva = new Rueda(tipo, anchura, diametro, indiceCarga, velMax);

        dao.añadirRueda(nueva, 0);

        System.out.println("\n Rueda creada y registrada correctamente.");
    }

    public static void menuTrabajadores(GestionFabricaDAO dao, Scanner sc) {
        boolean volver = false;
        String dniAux;

        while (!volver) {
            System.out.println("\n--- GESTIÓN DE TRABAJADORES ---");
            System.out.println("1. Consultar plantilla");
            System.out.println("2. Consultar trabajador por DNI");
            System.out.println("3. Añadir nuevo trabajador");
            System.out.println("4. Borrar trabajador por DNI");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    menuPlantilla(dao, sc);
                    break;
                case 2:
                    System.out.print("Introduce el DNI del trabajador a consultar: ");
                    dniAux = sc.nextLine();
                    Trabajador t = dao.buscarTrabajadorPorDNI(dniAux);
                    System.out.println(t != null ? t : "Trabajador no encontrado.");
                    break;
                case 3:
                    menuAltaTrabajador(dao, sc);
                    break;
                case 4:
                    System.out.print("Introduce el DNI del trabajador a borrar: ");
                    dniAux = sc.nextLine();
                    System.out.println(dao.borrarTrabajadorPorDNI(dniAux));
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public static void menuPlantilla(GestionFabricaDAO dao, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- CONSULTA DE PLANTILLA ---");
            System.out.println("1. Plantilla completa");
            System.out.println("2. Solo Operarios");
            System.out.println("3. Solo Administradores");
            System.out.println("4. Solo Gestores de Planta");
            System.out.println("5. Solo Mecánicos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println(dao.listarTrabajadores());
                    break;
                case 2:
                    List<Operario> ops = dao.obtenerSoloOperarios();
                    imprimirLista(ops, "Operarios");
                    break;
                case 3:
                    List<Administrador> admins = dao.obtenerSoloAdministradores();
                    imprimirLista(admins, "Administradores");
                    break;
                case 4:
                    List<GestorPlanta> gestores = dao.obtenerSoloGestores();
                    imprimirLista(gestores, "Gestores de Planta");
                    break;
                case 5:
                    List<Mecanico> mecanicos = dao.obtenerSoloMecanicos();
                    imprimirLista(mecanicos, "Mecánicos");
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public static void menuAltaTrabajador(GestionFabricaDAO dao, Scanner sc) {
        System.out.println("\n--- ALTA DE NUEVO TRABAJADOR ---");
        System.out.println("Seleccione el tipo de perfil:");
        System.out.println("1. Operario");
        System.out.println("2. Administrador");
        System.out.println("3. Gestor de Planta");
        System.out.println("4. Mecánico");
        System.out.print("Opción: ");

        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Sueldo Base: ");
        double sueldo = sc.nextDouble();
        sc.nextLine();

        System.out.print("Fecha de ingreso/alta (AAAA-MM-DD): ");
        String fecha = sc.nextLine();

        switch (tipo) {
            case 1:
                dao.añadirTrabajador(new Operario(nombre, apellidos, direccion, dni, 0, sueldo, fecha));
                break;
            case 2:
                dao.añadirTrabajador(new Administrador(nombre, apellidos, direccion, dni, 0, sueldo, fecha));
                break;
            case 3:
                dao.añadirTrabajador(new GestorPlanta(nombre, apellidos, direccion, dni, 0, sueldo, fecha));
                break;
            case 4:
                dao.añadirTrabajador(new Mecanico(nombre, apellidos, direccion, dni, 0, sueldo, fecha));
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        System.out.println("\n Trabajador registrado correctamente en el sistema.");
    }
    public static void menuRegistros(GestionFabricaDAO dao, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- CONSULTA DE REGISTROS (DASHBOARD) ---");
            System.out.println("1. Ver Historial Completo");
            System.out.println("2. Consultar por Tipo de Componente");
            System.out.println("3. Consultar por Segundo (Fecha)");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    List<Evento> historial = dao.getHistorial();
                    imprimirEventos(historial, "Historial Completo");
                    break;

                case 2:
                    System.out.print("Introduce tipo (Motor, Rueda, Tapiceria): ");
                    String tipo = sc.nextLine();
                    List<Evento> porComponente = dao.consultarEventosPorComponente(tipo);
                    imprimirEventos(porComponente, "Eventos de tipo: " + tipo);
                    break;

                case 3:
                    System.out.print("Introduce el segundo exacto a consultar: ");
                    int seg = sc.nextInt();
                    List<Evento> porFecha = dao.consultarEventosPorFecha(seg);
                    imprimirEventos(porFecha, "Eventos en el segundo: " + seg);
                    break;

                case 0:
                    volver = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public static void ejecutarPlanificador(GestionFabricaDAO dao, Scanner sc) {
        int ops = dao.obtenerSoloOperarios().size();
        int mecs = dao.obtenerSoloMecanicos().size();
        int ads = dao.obtenerSoloAdministradores().size();

        // Necesitamos 12 operarios (4 por cada una de las 3 cadenas), 1 mecánico y 1 administrador
        if (ops < 12 || mecs < 1 || ads < 1) {
            System.out.println("\n ERROR: PLANTILLA INCOMPLETA PARA SIMULACIÓN");
            System.out.println("--------------------------------------------------");
            System.out.println("Estado actual: " + ops + " Operarios, " + mecs + " Mecánicos, " + ads + " Administradores.");
            System.out.println("Requisito mín: 12 Operarios, 1 Mecánico, 1 Administrador.");
            System.out.println("--------------------------------------------------");
            System.out.println("Vuelva al menú de trabajadores para completar la plantilla.");
            return;
        }
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- TIPO DE SIMULACIÓN ---");
            System.out.println("1. Simple");
            System.out.println("2. Compleja");
            System.out.println("3. Muy Compleja");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            if (opcion == 0) {
                volver = true;
                continue;
            }

            if (opcion < 1 || opcion > 3) {
                System.out.println("Opción no válida.");
                continue;
            }

            // Determinar el nombre del modo seleccionado
            String modo = "";
            switch (opcion) {
                case 1: modo = "SIMPLE"; break;
                case 2: modo = "COMPLEJA"; break;
                case 3: modo = "MUY COMPLEJA"; break;
            }

            // Solicitar confirmación
            System.out.print("¿Desea empezar la simulación en modo " + modo + "? (S/N): ");
            String confirmacion = sc.nextLine().trim().toUpperCase();

            if (confirmacion.equals("S")) {
                System.out.println("Iniciando simulación " + modo + "...");

                // Aquí irá la lógica de cada simulación
                switch (opcion) {
                    case 1:
                        // Ejemplo: simularMontajeSimple(dao);
                        break;
                    case 2:
                        // Ejemplo: simularMontajeComplejo(dao);
                        break;
                    case 3:
                        // Ejemplo: simularMontajeMuyComplejo(dao);
                        break;
                }

                System.out.println("Simulación finalizada con éxito.");
            } else if (confirmacion.equals("N")) {
                System.out.println("Simulación cancelada. Volviendo al menú...");
            } else {
                System.out.println(" Opción no válida (debe introducir S o N).");
            }
        }
    }
    /**
     * Método auxiliar genérico para evitar repetir el bucle for en cada case.
     * Usa comodines (?) para aceptar cualquier lista de objetos que hereden de Trabajador.
     */
    private static void imprimirLista(List<? extends Trabajador> lista, String titulo) {
        System.out.println("\n--- " + titulo.toUpperCase() + " ---");
        if (lista.isEmpty()) {
            System.out.println("No hay " + titulo.toLowerCase() + " registrados en el sistema.");
        } else {
            for (Trabajador t : lista) {
                System.out.println(t.toString());
            }
        }
    }
    private static void imprimirEventos(List<Evento> eventos, String titulo) {
        System.out.println("\n=== " + titulo.toUpperCase() + " ===");
        if (eventos.isEmpty()) {
            System.out.println("No se han encontrado registros para esta consulta.");
        } else {
            for (Evento e : eventos) {
                System.out.println(e.toString());
            }
        }
    }
}