
package orozcomiguel;

import orozcomiguel.model.Aterrizaje;
import orozcomiguel.model.Pasajero;
import orozcomiguel.model.Vuelo;
import orozcomiguel.repository.*;
import orozcomiguel.service.*;
import java.util.List;
import java.util.Scanner;

public class App {

    private static Scanner teclado = new Scanner(System.in);

    private static long siguienteLlegada = 1;

    private static PasajeroRepository pasajeros = new PasajeroServiceImpl();
    private static RutaRepository ruta = new RutaServiceImpl();
    private static CheckInRepository checkIn = new CheckInServiceImpl();
    private static DestinoRepository destinos = new DestinoServiceImpl();
    private static AterrizajeRepository aterrizajes = new AterrizajeServiceImpl();
    private static FilaCheckInRepository fila = new FilaCheckInServiceImpl();
    private static VueloRepository vuelos = new VueloServiceImpl();
    private static CacheVuelosRepository cache = new CacheVuelosServiceImpl(vuelos, 3);

    public static void main(String[] args) {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println();
            System.out.println("===== AEROPUERTO =====");
            System.out.println("1. Pasajeros del vuelo (ArrayList)");
            System.out.println("2. Ruta de escalas (LinkedList)");
            System.out.println("3. Check-in de pasaportes (HashSet)");
            System.out.println("4. Destinos del dia (LinkedHashSet)");
            System.out.println("5. Aterrizajes por prioridad (PriorityQueue)");
            System.out.println("6. Fila de check-in (ArrayDeque)");
            System.out.println("7. Vuelos por codigo (HashMap)");
            System.out.println("8. Cache de consultas (LinkedHashMap)");
            System.out.println("0. Salir");

            opcion = leerNumero("Elige una opcion: ");

            switch (opcion) {
                case 1: menuPasajeros(); break;
                case 2: menuRuta(); break;
                case 3: menuCheckIn(); break;
                case 4: menuDestinos(); break;
                case 5: menuAterrizajes(); break;
                case 6: menuFila(); break;
                case 7: menuVuelos(); break;
                case 8: menuCache(); break;
                case 0: System.out.println("Hasta luego."); break;

                default: System.out.println("Opcion no valida."); break;
            }
        }
    }

    private static void menuPasajeros() {
        int op = -1;

        while (op != 0) {
            System.out.println();
            System.out.println("--- 1. PASAJEROS DEL VUELO (ArrayList) ---");
            System.out.println("1. Agregar pasajero");
            System.out.println("2. Listar por apellido");
            System.out.println("3. Eliminar en posicion");
            System.out.println("4. Total de pasajeros");
            System.out.println("0. Volver");

            op = leerNumero("Opcion: ");

            if (op == 1) {
                String pasaporte = leerTexto("Pasaporte: ");
                String nombre = leerTexto("Nombre: ");
                String apellido = leerTexto("Apellido: ");
                pasajeros.agregar(new Pasajero(pasaporte, nombre, apellido));
                System.out.println("Pasajero agregado: " + nombre + " " + apellido);
            } else if (op == 2) {
                List<Pasajero> lista = pasajeros.listarPorApellido();
                if (lista.isEmpty()) {
                    System.out.println("No hay pasajeros.");
                }
                for (Pasajero p : lista) {
                    System.out.println(" - " + p.apellido() + ", " + p.nombre() + " (" + p.pasaporte() + ")");
                }
            } else if (op == 3) {
                int posicion = leerNumero("Posicion (la primera es 0): ");
                Pasajero eliminado = pasajeros.eliminarEnPosicion(posicion);
                if (eliminado != null) {
                    System.out.println("Eliminado: " + eliminado.nombre() + " " + eliminado.apellido());
                } else {
                    System.out.println("Esa posicion no existe (devolvio null).");
                }
            } else if (op == 4) {
                System.out.println("Total de pasajeros: " + pasajeros.total());
            } else if (op != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private static void menuRuta() {
        int op = -1;

        while (op != 0) {
            System.out.println();
            System.out.println("--- 2. RUTA DE ESCALAS (LinkedList) ---");
            System.out.println("1. Agregar escala");
            System.out.println("2. Insertar escala despues de otra");
            System.out.println("3. Eliminar escala");
            System.out.println("4. Listar ruta");
            System.out.println("0. Volver");

            op = leerNumero("Opcion: ");

            if (op == 1) {
                String ciudad = leerTexto("Ciudad: ");
                ruta.agregarEscala(ciudad);
                System.out.println("Escala agregada al final: " + ciudad);
            } else if (op == 2) {
                String existente = leerTexto("Ciudad que ya esta en la ruta: ");
                String nueva = leerTexto("Nueva ciudad: ");
                if (ruta.insertarEscalaDespuesDe(existente, nueva)) {
                    System.out.println(nueva + " se inserto despues de " + existente);
                } else {
                    System.out.println("false: " + existente + " no esta en la ruta.");
                }
            } else if (op == 3) {
                String ciudad = leerTexto("Ciudad a eliminar: ");
                if (ruta.eliminarEscala(ciudad)) {
                    System.out.println("Escala eliminada: " + ciudad);
                } else {
                    System.out.println("false: " + ciudad + " no estaba en la ruta.");
                }
            } else if (op == 4) {
                List<String> lista = ruta.listarRuta();
                if (lista.isEmpty()) {
                    System.out.println("La ruta esta vacia.");
                } else {
                    System.out.println("Ruta: " + lista);
                }
            } else if (op != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private static void menuCheckIn() {
        int op = -1;

        while (op != 0) {
            System.out.println();
            System.out.println("--- 3. CHECK-IN DE PASAPORTES (HashSet) ---");
            System.out.println("1. Registrar pasaporte");
            System.out.println("2. Esta registrado?");
            System.out.println("3. Total registrados");
            System.out.println("0. Volver");

            op = leerNumero("Opcion: ");

            if (op == 1) {
                String pasaporte = leerTexto("Pasaporte: ");
                if (checkIn.registrar(pasaporte)) {
                    System.out.println("true: check-in registrado.");
                } else {
                    System.out.println("false: ese pasaporte ya habia hecho check-in.");
                }
            } else if (op == 2) {
                String pasaporte = leerTexto("Pasaporte: ");
                if (checkIn.estaRegistrado(pasaporte)) {
                    System.out.println("Si, ya hizo check-in.");
                } else {
                    System.out.println("No, todavia no esta registrado.");
                }
            } else if (op == 3) {
                System.out.println("Total registrados: " + checkIn.total());
            } else if (op != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private static void menuDestinos() {
        int op = -1;

        while (op != 0) {
            System.out.println();
            System.out.println("--- 4. DESTINOS DEL DIA (LinkedHashSet) ---");
            System.out.println("1. Registrar destino");
            System.out.println("2. Listar en orden de registro");
            System.out.println("3. Total de destinos");
            System.out.println("0. Volver");

            op = leerNumero("Opcion: ");

            if (op == 1) {
                String destino = leerTexto("Destino: ");
                if (destinos.registrarDestino(destino)) {
                    System.out.println("true: " + destino + " aparece por primera vez.");
                } else {
                    System.out.println("false: " + destino + " ya estaba registrado.");
                }
            } else if (op == 2) {
                List<String> lista = destinos.listarEnOrden();
                if (lista.isEmpty()) {
                    System.out.println("No hay destinos.");
                } else {
                    System.out.println("Destinos: " + lista);
                }
            } else if (op == 3) {
                System.out.println("Total de destinos distintos: " + destinos.total());
            } else if (op != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private static void menuAterrizajes() {
        int op = -1;

        while (op != 0) {
            System.out.println();
            System.out.println("--- 5. ATERRIZAJES POR PRIORIDAD (PriorityQueue) ---");
            System.out.println("1. Solicitar aterrizaje");
            System.out.println("2. Ver siguiente");
            System.out.println("3. Autorizar siguiente");
            System.out.println("4. Ver pendientes");
            System.out.println("0. Volver");

            op = leerNumero("Opcion: ");

            if (op == 1) {
                String codigo = leerTexto("Codigo del vuelo: ");
                int prioridad = leerNumero("Prioridad (1=emergencia, 2=combustible bajo, 3=normal): ");
                if (prioridad < 1 || prioridad > 3) {
                    System.out.println("La prioridad debe ser 1, 2 o 3.");
                } else {
                    aterrizajes.solicitar(new Aterrizaje(codigo, prioridad, siguienteLlegada));
                    System.out.println("Solicitud registrada: " + codigo + " (llegada #" + siguienteLlegada + ")");
                    siguienteLlegada++;
                }
            } else if (op == 2) {
                Aterrizaje a = aterrizajes.verSiguiente();
                if (a != null) {
                    System.out.println("El siguiente es: " + a.codigoVuelo() + " (prioridad " + a.prioridad() + ")");
                } else {
                    System.out.println("No hay solicitudes (null).");
                }
            } else if (op == 3) {
                Aterrizaje a = aterrizajes.autorizarSiguiente();
                if (a != null) {
                    System.out.println("Aterrizaje AUTORIZADO: " + a.codigoVuelo() + " (prioridad " + a.prioridad() + ")");
                } else {
                    System.out.println("No hay solicitudes (null).");
                }
            } else if (op == 4) {
                System.out.println("Solicitudes pendientes: " + aterrizajes.pendientes());
            } else if (op != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private static void menuFila() {
        int op = -1;

        while (op != 0) {
            System.out.println();
            System.out.println("--- 6. FILA DE CHECK-IN (ArrayDeque) ---");
            System.out.println("1. Llegar a la fila");
            System.out.println("2. Atender");
            System.out.println("3. Retirar al ultimo");
            System.out.println("4. Ver siguiente");
            System.out.println("5. Personas en fila");
            System.out.println("0. Volver");

            op = leerNumero("Opcion: ");

            if (op == 1) {
                String pasaporte = leerTexto("Pasaporte: ");
                String nombre = leerTexto("Nombre: ");
                String apellido = leerTexto("Apellido: ");
                fila.llegar(new Pasajero(pasaporte, nombre, apellido));
                System.out.println(nombre + " " + apellido + " entro al final de la fila.");
            } else if (op == 2) {
                Pasajero p = fila.atender();
                if (p != null) {
                    System.out.println("Atendido: " + p.nombre() + " " + p.apellido());
                } else {
                    System.out.println("La fila esta vacia (null).");
                }
            } else if (op == 3) {
                Pasajero p = fila.retirarUltimo();
                if (p != null) {
                    System.out.println("Abandono la fila: " + p.nombre() + " " + p.apellido());
                } else {
                    System.out.println("La fila esta vacia (null).");
                }
            } else if (op == 4) {
                Pasajero p = fila.verSiguiente();
                if (p != null) {
                    System.out.println("El siguiente es: " + p.nombre() + " " + p.apellido());
                } else {
                    System.out.println("La fila esta vacia (null).");
                }
            } else if (op == 5) {
                System.out.println("Personas en fila: " + fila.enFila());
            } else if (op != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private static void menuVuelos() {
        int op = -1;

        while (op != 0) {
            System.out.println();
            System.out.println("--- 7. VUELOS POR CODIGO (HashMap) ---");
            System.out.println("1. Registrar vuelo");
            System.out.println("2. Buscar por codigo");
            System.out.println("3. Eliminar vuelo");
            System.out.println("4. Listar vuelos");
            System.out.println("0. Volver");

            op = leerNumero("Opcion: ");

            if (op == 1) {
                String codigo = leerTexto("Codigo: ");
                String destino = leerTexto("Destino: ");
                String puerta = leerTexto("Puerta: ");
                if (vuelos.registrar(new Vuelo(codigo, destino, puerta))) {
                    System.out.println("true: vuelo " + codigo + " registrado.");
                } else {
                    System.out.println("false: ya existe el vuelo " + codigo + " (no se reemplazo).");
                }
            } else if (op == 2) {
                Vuelo v = vuelos.buscar(leerTexto("Codigo: "));
                if (v != null) {
                    System.out.println("Encontrado: " + v.codigo() + " -> " + v.destino() + ", puerta " + v.puerta());
                } else {
                    System.out.println("Ese vuelo no existe (null).");
                }
            } else if (op == 3) {
                Vuelo v = vuelos.eliminar(leerTexto("Codigo: "));
                if (v != null) {
                    System.out.println("Eliminado: " + v.codigo());
                } else {
                    System.out.println("Ese vuelo no existia (null).");
                }
            } else if (op == 4) {
                List<Vuelo> lista = vuelos.listar();
                if (lista.isEmpty()) {
                    System.out.println("No hay vuelos.");
                }
                for (Vuelo v : lista) {
                    System.out.println(" - " + v.codigo() + " -> " + v.destino() + ", puerta " + v.puerta());
                }
            } else if (op != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private static void menuCache() {
        int op = -1;

        while (op != 0) {
            System.out.println();
            System.out.println("--- 8. CACHE DE CONSULTAS (LinkedHashMap) ---");
            System.out.println("1. Consultar vuelo");
            System.out.println("2. Ver codigos en cache");
            System.out.println("0. Volver");

            op = leerNumero("Opcion: ");

            if (op == 1) {
                String codigo = leerTexto("Codigo: ");
                Vuelo v = cache.consultar(codigo);
                if (v != null) {
                    System.out.println("Vuelo: " + v.codigo() + " -> " + v.destino() + ", puerta " + v.puerta());
                } else {
                    System.out.println("El vuelo " + codigo + " no existe (null).");
                }
            } else if (op == 2) {
                List<String> codigos = cache.codigosEnCache();
                if (codigos.isEmpty()) {
                    System.out.println("La cache esta vacia.");
                } else {
                    System.out.println("En cache (del menos al mas reciente): " + codigos);
                }
            } else if (op != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    private static int leerNumero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Debes escribir un NUMERO.");
            return -1;
        }
    }
}
