import java.util.Collections;
import java.util.List;

/**
 * Clase principal encargada de coordinar la producción de la fábrica.
 * Gestiona el paso del tiempo, el movimiento de los vehículos por las estaciones,
 * la asignación de personal y la resolución de conflictos de stock.
 * * @author Nicolás Míguez Ramos
 */
public class Planificador {
    private int segundoActual;
    private TipoSimulacion tipoSimulacion;
    private CadenaMontaje[] cadenas;
    private GestionFabricaDAO dao;

    public Planificador(TipoSimulacion tipoSimulacion, GestionFabricaDAO dao){
        this.segundoActual = 0;
        this.tipoSimulacion = tipoSimulacion;
        this.cadenas = new CadenaMontaje[3];
        this.dao = dao;
        cadenas[0] = new CadenaMontaje(TipoVehiculo.TURISMO);
        cadenas[1] = new CadenaMontaje(TipoVehiculo.BIPLAZA);
        cadenas[2] = new CadenaMontaje(TipoVehiculo.FURGONETA);
    }

    /*
    * Comprueba si la simulación ha acabado
    */
    public boolean finalizado(){
        return cadenas[0].getNumeroVehiculos() == 0 &&
                cadenas[1].getNumeroVehiculos() == 0 &&
                cadenas[2].getNumeroVehiculos() == 0 &&
                dao.getVehiculos().isEmpty();
    }

    /*
    * Comienza la simulación
    */
    public void comenzarSimulacion(){
        System.out.println(">>> COMENZANDO SIMULACIÓN EN MODO " + tipoSimulacion.toString() + " <<<");
        asignarPersonal();
        asignarVehiculos();

        while (!dao.getVehiculos().isEmpty() || !finalizado()) {
            switch (tipoSimulacion){
                    case SIMPLE:
                        modoSimple();
                        break;
                    case COMPLEJA:
                        modoComplejo();
                        break;
                    case MUY_COMPLEJA:
//                        modoMuyCompleja();
                        break;
            }
            if (!dao.getVehiculos().isEmpty()) {
                asignarVehiculos();
            }

            // Para la ejecución 200 ms para poder ver la consola
            try { Thread.sleep(200); } catch (InterruptedException e) {}

            if (segundoActual > 5000) break;
        }
        System.out.println(">>> SIMULACIÓN FINALIZADA EN EL SEGUNDO " + segundoActual + " <<<");
    }

    /*
    * Ejecución simple
    */
    public void modoSimple() {
        segundoActual++;
        System.out.println("\n--- SEGUNDO " + segundoActual + " ---");

        for (CadenaMontaje cadena : cadenas) {
            for (int i = 0; i < cadena.getEstaciones().length; i++) {
                EstacionMontaje estacion = cadena.getEstaciones()[i];
                Vehiculo v = estacion.getVehiculoEnEstacion();

                // Si la estacion tiene vehiculo, operario y su trabajo no esta terminado
                if (v != null && estacion.getOperarioAsignado() != null && !estacion.isTrabajoTerminado()) {

                    Operario op = estacion.getOperarioAsignado();
                    estacion.incrementarTiempoTrabajado();

                    // Entra en el if cuando el operario ha trabajado el tiempo correspondiente
                    if (estacion.getTiempoTrabajado() >= op.getTiempoTarea()) {

                        if (intentarMontarPieza(v.getId(), i,cadena)) {
                            v.siguienteEstado();
                            System.out.println("Cadena " + cadena.getTipoVehiculo() +
                                    ": Estación " + (i+1) + " - Operario " + op.getNombre() +
                                    " terminó montaje en Vehículo ID: " + v.getId() + // <--- AÑADE ESTO
                                    ". Nuevo Estado: " + v.getEstado());
                            estacion.setTrabajoTerminado(true);
                            op.incrementarMontajes();
                        }
                    }
                }
            }
            // Los vehiculos avanzan en la cadena
            avanzarVehiculos(cadena);
        }
    }

    /*
     * Ejecución compleja
     * */
    public void modoComplejo() {
        segundoActual++;
        System.out.println("\n--- SIMULACIÓN COMPLEJA - SEGUNDO " + segundoActual + " ---");

        for (CadenaMontaje cadena : cadenas) {
            // GESTIÓN DE AVERÍAS
            if (cadena.isAveria()) {
                gestionarReparacion(cadena);
                continue;
            }

            // PROBABILIDAD DE AVERÍA (Solo si no está ya averiada)
            // 15% de probabilidad de que se rompa este segundo
            if (Math.random() < 0.15) {
                cadena.setAveria(true);
                System.out.println("[!!!] AVERÍA CRÍTICA en Cadena: " + cadena.getTipoVehiculo());
                gestionarReparacion(cadena);
                continue;
            }

            // 3. EJECUCIÓN NORMAL (Solo si no hubo avería)
            for (int i = 0; i < cadena.getEstaciones().length; i++) {
                EstacionMontaje estacion = cadena.getEstaciones()[i];
                Vehiculo v = estacion.getVehiculoEnEstacion();

                if (v != null && estacion.getOperarioAsignado() != null && !estacion.isTrabajoTerminado()) {
                    Operario op = estacion.getOperarioAsignado();
                    estacion.incrementarTiempoTrabajado();

                    if (estacion.getTiempoTrabajado() >= op.getTiempoTarea()) {
                        if (intentarMontarPieza(v.getId(), i, cadena)) {
                            v.siguienteEstado();
                            System.out.println("Cadena " + cadena.getTipoVehiculo() + ": Estación " + (i+1) + " terminó montaje.");
                            estacion.setTrabajoTerminado(true);
                            op.incrementarMontajes();
                        }
                    }
                }
            }
            avanzarVehiculos(cadena);
        }
    }
    private void gestionarReparacion(CadenaMontaje cadena) {
        // Si no hay mecánico, llamamos a uno del DAO
        if (cadena.getMecanicoAsignado() == null) {
            List<Mecanico> mecanicos = dao.obtenerSoloMecanicos();
            Mecanico m = mecanicos.get((int)(Math.random() * mecanicos.size()));

            cadena.setMecanicoAsignado(m);
            cadena.setTiempoReparacionRestante(m.calcularTiempoReparacion());
            dao.registrarEvento(new Evento(
                    segundoActual,
                    null,
                    -1,
                    "AVERÍA en " + cadena.getTipoVehiculo() + ". Acude: " + m.getNombre()
            ));
            System.out.println("-> Mecánico " + m.getNombre() + " (" +
                    (m.getReparacionesRealizadas() > 20 ? "Efectivo" : "Estándar") +
                    ") acude a la reparación. Tiempo estimado: " + cadena.getTiempoReparacionRestante() + "s");
        }

        // El mecánico trabaja este segundo
        cadena.setTiempoReparacionRestante(cadena.getTiempoReparacionRestante() - 1);

        if (cadena.getTiempoReparacionRestante() <= 0) {
            Mecanico m = cadena.getMecanicoAsignado();
            m.incrementarReparaciones();
            dao.registrarEvento(new Evento(
                    segundoActual,
                    null,
                    -1,
                    "REPARACIÓN COMPLETADA en " + cadena.getTipoVehiculo() + " por " + m.getNombre()
            ));
            cadena.setAveria(false);
            cadena.setMecanicoAsignado(null);
            System.out.println("[OK] Cadena " + cadena.getTipoVehiculo() + " reparada por " + m.getNombre());
        } else {
            System.out.println("... Cadena " + cadena.getTipoVehiculo() + " en reparación (quedan " + cadena.getTiempoReparacionRestante() + "s)");
        }
    }
    /**
     * @param idV Id del vehículo al que se le monta la pieza
     * @param numEstacion Estación donde se monta la pieza
     */
    private boolean intentarMontarPieza(int idV, int numEstacion, CadenaMontaje cadena) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        // Obtenemos el vehículo que está físicamente en la estación actual
        Vehiculo v = cadena.getEstaciones()[numEstacion].getVehiculoEnEstacion();

        switch (numEstacion) {
            case 0 -> { // Estación de Chasis
                dao.registrarEvento(new Evento(segundoActual, v, idV, "Robot: Chasis ensamblado"));
                return true;
            }
            case 1 -> { // Estación de Motores
                while (dao.getMotores().isEmpty()) {
                    System.out.println("\n[!] DETENCIÓN DE LÍNEA: Sin MOTORES para Vehículo " + idV);
                    System.out.println("1. Introducir nuevo motor manualmente\n2. Esperar\n3. Abortar");
                    System.out.print("Seleccione una opción: ");

                    String opcion = sc.nextLine();
                    switch (opcion) {
                        case "1" -> {
                            try {
                                System.out.print("Tipo (GASOLINA, ELECTRICO, HIBRIDO): ");
                                TipoMotor tipo = TipoMotor.valueOf(sc.nextLine().toUpperCase());
                                System.out.print("Cilindrada (ej: 120.3): ");
                                double cil = Double.parseDouble(sc.nextLine());
                                System.out.print("Caballos (ej: 150.0): ");
                                double cv = Double.parseDouble(sc.nextLine());
                                System.out.print("Cilindros (ej: 4): ");
                                int cilindros = Integer.parseInt(sc.nextLine());

                                dao.getMotores().add(new Motor(tipo, cil, cv, cilindros));
                            } catch (Exception e) { System.out.println("Error en datos."); }
                        }
                        case "2" -> { return false; }
                        case "3" -> { System.exit(0); }
                    }
                }
                Motor m = dao.getMotores().removeFirst();
                // --- SETTER MOTOR ---
                v.setMotor(m);
                dao.registrarEvento(new Evento(segundoActual, v, idV, "Motor " + m.getTipoMotor() + " instalado"));
                return true;
            }
            case 2 -> { // Estación de Tapicería
                while (dao.getTapicerias().isEmpty()) {
                    System.out.println("\n[!] DETENCIÓN DE LÍNEA: Sin TAPICERÍA para Vehículo " + idV);
                    System.out.println("1. Introducir nueva tapicería\n2. Esperar\n3. Abortar");
                    System.out.print("Opción: ");

                    String opcion = sc.nextLine();
                    switch (opcion) {
                        case "1" -> {
                            try {
                                System.out.print("Tipo (TELA, CUERO, ALCANTARA): ");
                                TipoTapiceria tipo = TipoTapiceria.valueOf(sc.nextLine().toUpperCase());
                                System.out.print("Color (ej: Negro): ");
                                String color = sc.nextLine();
                                System.out.print("Metros (ej: 5): ");
                                int metros = Integer.parseInt(sc.nextLine());

                                dao.getTapicerias().add(new Tapiceria(tipo, color, metros));
                            } catch (Exception e) { System.out.println("Error en datos."); }
                        }
                        case "2" -> { return false; }
                        case "3" -> { System.exit(0); }
                    }
                }
                Tapiceria t = dao.getTapicerias().removeFirst();
                // --- SETTER TAPICERÍA ---
                v.setTapiceria(t);
                dao.registrarEvento(new Evento(segundoActual, v, idV, "Tapicería " + t.getColor() + " montada"));
                return true;
            }
            case 3 -> { // Estación de Ruedas
                while (dao.getRuedas().size() < 4) {
                    System.out.println("\n[!] DETENCIÓN DE LÍNEA: Ruedas insuficientes (" + dao.getRuedas().size() + ")");
                    System.out.println("1. Introducir lote de 4 ruedas\n2. Esperar\n3. Abortar");
                    System.out.print("Opción: ");

                    String opcion = sc.nextLine();
                    switch (opcion) {
                        case "1" -> {
                            try {
                                System.out.print("Tipo (NORMAL, DEPORTIVO, TODOTERRENO): ");
                                TipoRueda tipo = TipoRueda.valueOf(sc.nextLine().toUpperCase());
                                System.out.print("Anchura (ej: 205): ");
                                int anchura = Integer.parseInt(sc.nextLine());
                                System.out.print("Diámetro (ej: 16): ");
                                int diametro = Integer.parseInt(sc.nextLine());

                                for(int i=0; i<4; i++) {
                                    dao.getRuedas().add(new Rueda(tipo, anchura, diametro, 91, 210));
                                }
                            } catch (Exception e) { System.out.println("Error en datos."); }
                        }
                        case "2" -> { return false; }
                        case "3" -> { System.exit(0); }
                    }
                }

                // Creamos una lista temporal para las 4 ruedas del vehículo
                List<Rueda> ruedasParaVehiculo = new java.util.ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    ruedasParaVehiculo.add(dao.getRuedas().removeFirst());
                }

                // --- SETTER RUEDAS ---
                v.setRuedas(ruedasParaVehiculo);
                dao.registrarEvento(new Evento(segundoActual, v, idV, "4 Ruedas montadas"));
                return true;
            }
        }
        return false;
    }

    /**
    * @param cadena Objeto Cadena de montaje
    * */
    private void avanzarVehiculos(CadenaMontaje cadena) {
        EstacionMontaje[] estaciones = cadena.getEstaciones();

        // Mover entre estaciones (de 3 a 4, de 2 a 3, de 1 a 2)
        for (int i = estaciones.length - 1; i > 0; i--) {
            EstacionMontaje actual = estaciones[i];
            EstacionMontaje anterior = estaciones[i - 1];

            if (anterior.isTrabajoTerminado() && actual.estaLibre()) {
                Vehiculo v = anterior.getVehiculoEnEstacion();
                actual.setVehiculoEnEstacion(v);
                anterior.vaciarEstacion();

                dao.registrarEvento(new Evento(segundoActual, v, v.getId(),
                        "Vehículo avanza a la estación " + (i + 1)));
            }
        }

        // Salida de la fábrica (Estación 4)
        EstacionMontaje ultima = estaciones[estaciones.length - 1];
        if (ultima.isTrabajoTerminado()) {
            Vehiculo vTerminado = ultima.getVehiculoEnEstacion();

            System.out.println("\n[!] SALIDA DE FÁBRICA: El Vehículo ID " + vTerminado.getId() +
                    " (" + cadena.getTipoVehiculo() + ") ha sido COMPLETADO.");

            dao.añadirVehiculoEnsamblado(vTerminado, segundoActual);

            ultima.vaciarEstacion();
        }
    }

    /**
     * Asignar de forma aleatoria el personal disponible a las estaciones
     * */
    public void asignarPersonal(){
        List<Operario> operarios = dao.obtenerSoloOperarios();

        Collections.shuffle(operarios);
        for (CadenaMontaje cadena : cadenas) {
            for (int j = 0; j < cadena.getEstaciones().length; j++) {
                if (!operarios.isEmpty()) {
                    cadena.getEstaciones()[j].setOperarioAsignado(operarios.getFirst());
                    System.out.println("Operario "+ operarios.getFirst().getNombre() + " asignado a estación " + (j+1) + " de cadena " + cadena.getTipoVehiculo());
                    operarios.removeFirst();
                } else {
                    System.out.println("Alerta: No hay suficientes operarios para todas las estaciones.");
                }
            }
        }
    }

    /**
     * Asignar de forma aleatoria los vehículos disponibles a las estaciones
     * */
    public void asignarVehiculos() {
        List<Turismo> turismos = dao.obtenerSoloTurismos();
        List<BiplazaDeportivo> biplazas = dao.obtenerSoloBiplazas();
        List<Furgoneta> furgonetas = dao.obtenerSoloFurgonetas();

        Collections.shuffle(turismos);
        Collections.shuffle(biplazas);
        Collections.shuffle(furgonetas);

        // Cadena 0: TURISMOS
        // Comprobamos si hay turismos en el almacén y si la primera estación de su cadena está vacía
        if (!turismos.isEmpty() && cadenas[0].getEstaciones()[0].estaLibre()) {
            Turismo t = turismos.getFirst();

            // Colocamos el objeto vehículo directamente en la estación
            cadenas[0].getEstaciones()[0].setVehiculoEnEstacion(t);

            // Lo borramos del almacén de entrada
            dao.getVehiculos().remove(t);

            dao.registrarEvento(new Evento(segundoActual, t, t.getId(), "Turismo entra en línea de montaje"));
        }

        // Cadena 1: BIPLAZAS
        if (!biplazas.isEmpty() && cadenas[1].getEstaciones()[0].estaLibre()) {
            BiplazaDeportivo b = biplazas.getFirst();

            cadenas[1].getEstaciones()[0].setVehiculoEnEstacion(b);

            dao.getVehiculos().remove(b);

            dao.registrarEvento(new Evento(segundoActual, b, b.getId(), "Biplaza entra en línea de montaje"));
        }

        // Cadena 2: FURGONETAS
        if (!furgonetas.isEmpty() && cadenas[2].getEstaciones()[0].estaLibre()) {
            Furgoneta f = furgonetas.getFirst();

            cadenas[2].getEstaciones()[0].setVehiculoEnEstacion(f);

            dao.getVehiculos().remove(f);

            dao.registrarEvento(new Evento(segundoActual, f, f.getId(), "Furgoneta entra en línea de montaje"));
        }
    }
}