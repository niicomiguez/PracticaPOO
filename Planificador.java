import java.util.Collections;
import java.util.List;

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

    public void comenzarSimulacion(){
        System.out.println(">>> COMENZANDO SIMULACIÓN EN MODO " + tipoSimulacion.toString() + " <<<");
        asignarPersonal();
        asignarVehiculos();

        while (!dao.getVehiculos().isEmpty()) {
            switch (tipoSimulacion){
                    case SIMPLE:
                        modoSimple();
                        break;
                    case COMPLEJA:
//                        modoCompleja();
                        break;
                    case MUY_COMPLEJA:
//                        modoMuyCompleja();
                        break;
            }
            asignarVehiculos();

            try { Thread.sleep(200); } catch (InterruptedException e) {}

            if (segundoActual > 5000) break;
        }
        System.out.println(">>> SIMULACIÓN FINALIZADA EN EL SEGUNDO " + segundoActual + " <<<");
    }

    public void modoSimple() {
        segundoActual++;
        System.out.println("\n--- SEGUNDO " + segundoActual + " ---");

        for (CadenaMontaje cadena : cadenas) {
            for (int i = 0; i < cadena.getEstaciones().length; i++) {
                EstacionMontaje estacion = cadena.getEstaciones()[i];

                // 1. Obtenemos el vehículo directamente de la estación
                Vehiculo v = estacion.getVehiculoEnEstacion();

                // 2. Comprobamos si hay alguien trabajando y hay coche
                if (v != null && estacion.getOperarioAsignado() != null) {
                    Operario op = estacion.getOperarioAsignado();
                    estacion.incrementarTiempoTrabajado();

                    if (!estacion.isTrabajoTerminado() && estacion.getTiempoTrabajado() >= op.getTiempoTarea()) {

                        if (intentarMontarPieza(v.getId(), i)) {
                            // ¡Ya no necesitas buscar en el DAO! 'v' es el coche
                            v.siguienteEstado();

                            System.out.println("Cadena " + cadena.getTipoVehiculo() +
                                    ": Estación " + (i+1) + " - Operario " + op.getNombre() +
                                    " terminó montaje. Estado: " + v.getEstado());

                            estacion.setTrabajoTerminado(true);
                            op.incrementarMontajes();
                        }
                    }
                }
            }
            avanzarVehiculos(cadena);
        }
    }
    private boolean intentarMontarPieza(int idV, int numEstacion) {
        // numEstacion 0: Motor, 1: Ruedas, 2: Tapiceria, 3: Pruebas
        switch (numEstacion) {
            case 0 -> {
                // Verificamos si hay motores y extraemos el primero
                if (dao.getMotores().isEmpty()) return false;

                Motor m = dao.getMotores().removeFirst(); // SACAMOS el motor del almacén
                dao.registrarEvento(new Evento(segundoActual, null, idV, "Motor " + m.getTipoMotor() + " montado"));
                return true;
            }
            case 1 -> {
                // Usamos tu método de extraer ruedas (asegúrate de que el DAO las borre de su lista)
                if (dao.getRuedas().size() < 4) return false;

                for (int i = 0; i < 4; i++) {
                    dao.getRuedas().removeFirst();
                }
                dao.registrarEvento(new Evento(segundoActual, null, idV, "4 Ruedas montadas"));
                return true;
            }
            case 2 -> {
                // Verificamos y extraemos tapicería
                if (dao.getTapicerias().isEmpty()) return false;

                Tapiceria t = dao.getTapicerias().removeFirst(); // SACAMOS la tapicería
                dao.registrarEvento(new Evento(segundoActual, null, idV, "Tapicería " + t.getColor() + " montada"));
                return true;
            }
            case 3 -> {
                // Control de calidad: No consume piezas, solo registra el éxito
                dao.registrarEvento(new Evento(segundoActual, null, idV, "Pruebas finalizadas. Vehículo listo para entrega."));
                return true;
            }
        }
        return false;
    }
    private void avanzarVehiculos(CadenaMontaje cadena) {
        EstacionMontaje[] estaciones = cadena.getEstaciones();

        // 1. Mover entre estaciones (de 3 a 4, de 2 a 3, de 1 a 2)
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

        // 2. Salida de la fábrica (Estación 4)
        EstacionMontaje ultima = estaciones[estaciones.length - 1];
        if (ultima.isTrabajoTerminado()) {
            Vehiculo vTerminado = ultima.getVehiculoEnEstacion();

            // Es vital registrar el evento ANTES de vaciar
            dao.registrarEvento(new Evento(segundoActual, vTerminado, vTerminado.getId(),
                    "Vehículo COMPLETADO y sale de la cadena"));

            ultima.vaciarEstacion();
        }
    }
    public void asignarPersonal(){
        List<Operario> operarios = dao.obtenerSoloOperarios();

        Collections.shuffle(operarios);
        for (CadenaMontaje cadena : cadenas) {
            for (int j = 0; j < cadena.getEstaciones().length; j++) {
                if (!operarios.isEmpty()) {
                    cadena.getEstaciones()[j].setOperarioAsignado(operarios.getFirst());
                    operarios.removeFirst();
                } else {
                    System.out.println("Alerta: No hay suficientes operarios para todas las estaciones.");
                }
            }
        }
    }
    public void asignarVehiculos() {
        List<Turismo> turismos = dao.obtenerSoloTurismos();
        List<BiplazaDeportivo> biplazas = dao.obtenerSoloBiplazas();
        List<Furgoneta> furgonetas = dao.obtenerSoloFurgonetas();

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