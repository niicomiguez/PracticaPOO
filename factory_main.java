
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
            System.out.println("1. Gestión de Almacén");
            System.out.println("2. Gestión de Trabajadores");
            System.out.println("3. Gestión de Vehículos");
            System.out.println("4. Planificador (Simulación)");
            System.out.println("5. Consultar Registros");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1: menuAlmacen(dao, sc); break;
                case 2: menuTrabajadores(dao, sc); break;
                case 3: menuVehiculos(dao, sc); break;
                case 4: ejecutarPlanificador(dao, sc); break;
                case 5: menuRegistros(dao, sc); break;
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
            if (opcion == 0) salir = true;
            else if (opcion >= 1 && opcion <= 3) {
                menuGestionAlmacen(dao, sc, opcion);
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
    public static void menuGestionAlmacen(GestionFabricaDAO dao, Scanner sc, int tipoComponente) {
        // 1: Motor, 2: Tapicería, 3: Rueda
        String[] nombres = {"", "MOTORES", "TAPICERÍAS", "RUEDAS"};
        String nombre = nombres[tipoComponente];

        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE " + nombre + " ---");
            System.out.println("1. Listar " + nombre.toLowerCase());
            System.out.println("2. Añadir nuevo/a " + nombre.toLowerCase());
            System.out.println("3. Borrar por ID");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    if (tipoComponente == 1) System.out.println(dao.listarMotores());
                    else if (tipoComponente == 2) System.out.println(dao.listarTapicerias());
                    else System.out.println(dao.listarRuedas());
                }
                case 2 -> crearComponente(dao, sc, tipoComponente);
                case 3 -> {
                    System.out.print("Introduce ID a borrar: ");
                    int id = sc.nextInt();
                    String resultado = switch (tipoComponente) {
                        case 1 -> dao.borrarMotorPorId(id, segundoActual);
                        case 2 -> dao.borrarTapiceriaPorId(id, segundoActual);
                        default -> dao.borrarRuedaPorId(id, segundoActual);
                    };
                    System.out.println(resultado);
                }
                case 0 -> volver = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }
    private static void crearComponente(GestionFabricaDAO dao, Scanner sc, int tipo) {
        System.out.println("\n-- Creando nuevo componente --");

        switch (tipo) {
            case 1 -> { // MOTORES
                System.out.println("1. GASOLINA | 2. ELECTRICO | 3. HIBRIDO");
                int tInt = sc.nextInt();
                TipoMotor tm = (tInt == 2) ? TipoMotor.ELECTRICO : (tInt == 3) ? TipoMotor.HIBRIDO : TipoMotor.GASOLINA;
                System.out.print("Cilindrada: "); double cil = sc.nextDouble();
                System.out.print("Potencia: "); double pot = sc.nextDouble();
                System.out.print("Cilindros: "); int cils = sc.nextInt();
                dao.añadirMotor(new Motor(tm, cil, pot, cils), segundoActual);
            }
            case 2 -> { // TAPICERÍA
                System.out.println("1. TELA | 2. CUERO | 3. ALCANTARA");
                int tInt = sc.nextInt();
                TipoTapiceria tt = (tInt == 2) ? TipoTapiceria.CUERO : (tInt == 3) ? TipoTapiceria.ALCANTARA : TipoTapiceria.TELA;
                System.out.print("Color: "); String col = sc.next();
                System.out.print("Plazas: "); int plz = sc.nextInt();
                dao.añadirTapiceria(new Tapiceria(tt, col, plz), segundoActual);
            }
            case 3 -> { // RUEDAS
                System.out.println("1. NORMAL | 2. DEPORTIVO | 3. TODOTERRENO");
                int tInt = sc.nextInt();
                TipoRueda tr = (tInt == 2) ? TipoRueda.DEPORTIVO : (tInt == 3) ? TipoRueda.TODOTERRENO : TipoRueda.NORMAL;
                System.out.print("Anchura: "); int anc = sc.nextInt();
                System.out.print("Diámetro: "); int diam = sc.nextInt();
                System.out.print("Carga: "); int carg = sc.nextInt();
                System.out.print("Vel. Máx: "); int vel = sc.nextInt();
                dao.añadirRueda(new Rueda(tr, anc, diam, carg, vel), segundoActual);
            }
        }
        System.out.println("Componente registrado con éxito.");
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
            System.out.println("2. Operarios");
            System.out.println("3. Administradores");
            System.out.println("4. Gestores de Planta");
            System.out.println("5. Mecánicos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println(dao.listarTrabajadores());
                    break;
                case 2:
                    menuOperariosAvanzado(dao, sc);
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

    private static void menuOperariosAvanzado(GestionFabricaDAO dao, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE OPERARIOS ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Ordenar alfabéticamente (por Apellido)");
            System.out.println("3. Ordenar por productividad (Ranking montajes)");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    imprimirLista(dao.obtenerSoloOperarios(), "Operarios - Orden General");
                    break;
                case 2:
                    System.out.println(dao.obtenerOperariosOrdenAlfabetico());
                    break;
                case 3:
                    System.out.println(dao.obtenerOperariosProductividad());
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
                case 1: modo = "SIMPLE";  break;
                case 2: modo = "COMPLEJA"; break;
                case 3: modo = "MUY COMPLEJA"; break;
            }

            // Solicitar confirmación
            System.out.print("¿Desea empezar la simulación en modo " + modo + "? (S/N): ");
            String confirmacion = sc.nextLine().trim().toUpperCase();

            if (confirmacion.equals("S")) {
                System.out.println("Iniciando simulación " + modo + "...");
                Planificador planificador;

                // Aquí irá la lógica de cada simulación
                switch (opcion) {
                    case 1:
                        planificador=new Planificador(TipoSimulacion.SIMPLE,dao);
                        planificador.comenzarSimulacion();
                        break;
                    case 2:
                        planificador=new Planificador(TipoSimulacion.COMPLEJA,dao);
                        break;
                    case 3:
                        planificador=new Planificador(TipoSimulacion.MUY_COMPLEJA,dao);
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
    public static void menuVehiculos(GestionFabricaDAO dao, Scanner sc) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- GESTIÓN DE VEHÍCULOS ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Turismos");
            System.out.println("3. Biplazas Deportivos");
            System.out.println("4. Furgonetas");
            System.out.println("5. Vehículos Ensamblados");
            System.out.println("0. Volver");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> System.out.println(dao.listarVehiculos());
                case 2 -> menuGestionVehiculos(dao, sc, 1);
                case 3 -> menuGestionVehiculos(dao, sc, 2);
                case 4 -> menuGestionVehiculos(dao, sc, 3);
                case 5 -> menuGestionVehiculosEnsamblados(dao, sc);
                case 0 -> salir = true;
            }
        }
    }
    public static void menuGestionVehiculosEnsamblados(GestionFabricaDAO dao, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- VEHÍCULOS ENSAMBLADOS (HISTORIAL) ---");
            System.out.println("1. Listar todos (Orden cronológico)");
            System.out.println("2. Ordenar alfabéticamente por tipo");
            System.out.println("3. Filtrar por componentes");
            System.out.println("4. Tasas de montaje (Configuraciones más exitosas)");
            System.out.println("5. Filtrar por fecha (segundo de simulación)");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> System.out.println(dao.listarVehiculosEnsamblados());
                case 2 -> System.out.println(dao.listarVehiculosEnsambladosAlfabético());
                case 3 -> menuFiltradoComponentes(dao, sc);
                case 4 -> System.out.println(dao.listarConfiguracionesMasEnsambladas());
                case 5 -> {
                    System.out.print("Introduzca el segundo exacto de la simulación: ");
                    int fecha = sc.nextInt();
                    System.out.println(dao.listarProduccionPorFecha(fecha));
                }
                case 0 -> volver = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void menuFiltradoComponentes(GestionFabricaDAO dao, Scanner sc) {
        System.out.println("\n--- FILTRAR POR COMPONENTE ---");
        System.out.println("1. Por Motor");
        System.out.println("2. Por Tapicería");
        System.out.println("3. Por Ruedas");
        System.out.println("0. Cancelar");
        System.out.print("Seleccione componente: ");

        int comp = sc.nextInt();
        switch (comp) {
            case 1 -> {
                System.out.println("Seleccione tipo de Motor: 1. ELÉCTRICO | 2. GASOLINA | 3. HÍBRIDO");
                int tipo = sc.nextInt();
                TipoMotor tm = (tipo == 1) ? TipoMotor.ELECTRICO : (tipo == 3) ? TipoMotor.HIBRIDO : TipoMotor.GASOLINA;
                System.out.println(dao.listarVehiculosPorTipoMotor(tm));
            }
            case 2 -> {
                System.out.println("Seleccione tipo de Tapicería: 1. TELA | 2. CUERO | 3. ALCANTARA");
                int tipo = sc.nextInt();
                TipoTapiceria tt = (tipo == 2) ? TipoTapiceria.CUERO : (tipo == 3) ? TipoTapiceria.ALCANTARA : TipoTapiceria.TELA;
                System.out.println(dao.listarVehiculosPorTipoTapiceria(tt));
            }
            case 3 -> {
                System.out.println("Seleccione tipo de Rueda: 1. NORMAL | 2. DEPORTIVO | 3. TODOTERRENO");
                int tipo = sc.nextInt();
                TipoRueda tr = (tipo == 2) ? TipoRueda.DEPORTIVO : (tipo == 3) ? TipoRueda.TODOTERRENO : TipoRueda.NORMAL;
                System.out.println(dao.listarVehiculosPorTipoRueda(tr));
            }
            case 0 -> {}
            default -> System.out.println("Opción no válida.");
        }
    }
    public static void menuGestionVehiculos(GestionFabricaDAO dao, Scanner sc, int tipoVehiculo) {
        // tipoVehiculo: 1=Turismo, 2=Deportivo, 3=Furgoneta
        String[] nombres = {"", "TURISMOS", "BIPLAZAS", "FURGONETAS"};
        String nombre = nombres[tipoVehiculo];

        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE " + nombre + " ---");
            System.out.println("1. Listar " + nombre.toLowerCase() + " en stock");
            System.out.println("2. Añadir nuevo " + nombre.toLowerCase());
            System.out.println("3. Borrar por ID");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    Object listaFiltrada = switch (tipoVehiculo) {
                        case 1 -> dao.obtenerSoloTurismos();
                        case 2 -> dao.obtenerSoloBiplazas();
                        case 3 -> dao.obtenerSoloFurgonetas();
                        default -> null;
                    };

                    System.out.println("\n--- LISTADO  DE " + nombre + " ---");
                    if (listaFiltrada != null && !((List<?>)listaFiltrada).isEmpty()) {
                        ((List<?>)listaFiltrada).forEach(System.out::println);
                    } else {
                        System.out.println("No hay existencias de este tipo.");
                    }
                }
                case 2 -> crearVehiculo(dao, sc, tipoVehiculo);
                case 3 -> {
                    System.out.print("ID a borrar: ");
                    int id = sc.nextInt();
                    System.out.println(dao.borrarVehiculoPorId(id, segundoActual));
                }
                case 0 -> volver = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }
    private static void crearVehiculo(GestionFabricaDAO dao, Scanner sc, int tipo) {
        System.out.println("\n-- Datos del nuevo chasis --");

        System.out.print("Color: ");
        String color = sc.nextLine();

        int plazas = 2; // Valor por defecto para Biplazas
        if (tipo != 2) {
            System.out.print("Número de plazas: ");
            plazas = sc.nextInt();
        }

        System.out.print("Tara (kg): ");
        int tara = sc.nextInt();

        System.out.print("Peso Máximo (kg): ");
        double pesoMax = sc.nextDouble();

        Vehiculo nuevo = null;
        switch (tipo) {
            case 1 -> nuevo = new Turismo(color, plazas, tara, pesoMax);
            case 2 -> nuevo = new BiplazaDeportivo(color, tara, pesoMax);
            case 3 -> nuevo = new Furgoneta(color, plazas, tara, pesoMax);
        }

        if (nuevo != null) {
            dao.añadirVehiculo(nuevo);
            System.out.println("\n Registrado con ID: " + nuevo.getId());
        }
    }
    
    
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