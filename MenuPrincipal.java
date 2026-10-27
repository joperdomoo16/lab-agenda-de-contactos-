import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        boolean ejecutando = true;

        while (ejecutando) {
            mostrarMenu();
            System.out.print("Elige una opcion: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opcion invalida, intenta de nuevo\n");
                scanner.nextLine();
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- AGREGAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine().trim();
                    System.out.print("Telefono: ");
                    String telefono = scanner.nextLine().trim();
                    System.out.print("Email: ");
                    String email = scanner.nextLine().trim();
                    agenda.agregarContacto(new Contacto(nombre, telefono, email));
                    System.out.println();
                    break;

                case 2:
                    System.out.println("\n--- BUSCAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombreBuscar = scanner.nextLine().trim();
                    Contacto encontrado = agenda.buscarContacto(nombreBuscar);
                    if (encontrado != null) {
                        System.out.println(encontrado);
                    } else {
                        System.out.println("Contacto no encontrado: " + nombreBuscar);
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println("\n--- ELIMINAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombreEliminar = scanner.nextLine().trim();
                    boolean eliminado = agenda.eliminarContacto(nombreEliminar);
                    if (eliminado) {
                        System.out.println("Contacto eliminado: " + nombreEliminar);
                    } else {
                        System.out.println("Contacto no encontrado: " + nombreEliminar);
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.println();
                    agenda.listarContactos();
                    System.out.println();
                    break;

                case 5:
                    System.out.println();
                    agenda.listarHistorial();
                    System.out.println();
                    break;

                case 6:
                    System.out.println("\n--- AGREGAR GRUPO ---");
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine().trim();
                    agenda.agregarGrupo(nombreGrupo);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("\n--- AGREGAR CONTACTO A GRUPO ---");
                    System.out.print("Nombre del contacto: ");
                    String contactoGrupo = scanner.nextLine().trim();
                    System.out.print("Nombre del grupo: ");
                    String grupoDestino = scanner.nextLine().trim();
                    agenda.agregarContactoAGrupo(contactoGrupo, grupoDestino);
                    System.out.println();
                    break;

                case 8:
                    System.out.println();
                    agenda.listarGrupos();
                    System.out.println();
                    break;

                case 9:
                    System.out.println("\n--- MOSTRAR CONTACTOS POR GRUPO ---");
                    System.out.print("Nombre del grupo: ");
                    String grupoConsulta = scanner.nextLine().trim();
                    agenda.mostrarContactosPorGrupo(grupoConsulta);
                    System.out.println();
                    break;

                case 10:
                    System.out.println("Saliendo de la agenda...");
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opcion invalida, intenta de nuevo\n");
                    break;
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== AGENDA DE CONTACTOS ===");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Buscar contacto");
        System.out.println("3. Eliminar contacto");
        System.out.println("4. Listar contactos (orden alfabetico)");
        System.out.println("5. Listar historial (orden de insercion)");
        System.out.println("6. Agregar grupo");
        System.out.println("7. Agregar contacto a grupo");
        System.out.println("8. Listar grupos");
        System.out.println("9. Mostrar contactos por grupo");
        System.out.println("10. Salir");
        System.out.println("==========================");
    }
}
