import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Agenda {
    private Map<String, Contacto> contactos;
    private Set<String> grupos;
    private Map<String, Set<String>> contactosPorGrupo;
    private List<Contacto> historial;

    public Agenda() {
        this.contactos = new TreeMap<>();
        this.grupos = new TreeSet<>();
        this.contactosPorGrupo = new TreeMap<>();
        this.historial = new ArrayList<>();
    }

    public void agregarContacto(Contacto nuevoContacto) {
        if (contactos.containsKey(nuevoContacto.getNombre())) {
            System.out.println("Error: Ya existe un contacto con el nombre '" + nuevoContacto.getNombre() + "'");
            return;
        }

        contactos.put(nuevoContacto.getNombre(), nuevoContacto);
        historial.add(nuevoContacto);
        System.out.println("Contacto agregado: " + nuevoContacto.getNombre());
    }

    public Contacto buscarContacto(String nombre) {
        return contactos.get(nombre);
    }

    public boolean eliminarContacto(String nombre) {
        if (!contactos.containsKey(nombre)) {
            return false;
        }

        contactos.remove(nombre);
        historial.removeIf(contacto -> contacto.getNombre().equals(nombre));

        for (Set<String> miembros : contactosPorGrupo.values()) {
            miembros.remove(nombre);
        }

        return true;
    }

    public void listarContactos() {
        System.out.println("--- LISTA DE CONTACTOS (orden alfabetico) ---");
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }

        for (Contacto contacto : contactos.values()) {
            System.out.println(contacto);
        }
    }

    public void listarHistorial() {
        System.out.println("--- HISTORIAL (orden de insercion) ---");
        if (historial.isEmpty()) {
            System.out.println("No hay contactos en el historial.");
            return;
        }

        for (Contacto contacto : historial) {
            System.out.println(contacto);
        }
    }

    public void agregarGrupo(String nombreGrupo) {
        if (grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' ya existe");
            return;
        }

        grupos.add(nombreGrupo);
        contactosPorGrupo.put(nombreGrupo, new TreeSet<>());
        System.out.println("Grupo '" + nombreGrupo + "' creado");
    }

    public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
        if (!contactos.containsKey(nombreContacto)) {
            System.out.println("Error: El contacto '" + nombreContacto + "' no existe");
            return;
        }

        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
            return;
        }

        Set<String> miembros = contactosPorGrupo.get(nombreGrupo);
        if (miembros != null) {
            miembros.add(nombreContacto);
            System.out.println("Contacto agregado al grupo '" + nombreGrupo + "'");
        }
    }

    public void listarGrupos() {
        System.out.println("--- LISTA DE GRUPOS ---");
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos registrados.");
            return;
        }

        for (String grupo : grupos) {
            System.out.println("- " + grupo);
        }
    }

    public void mostrarContactosPorGrupo(String nombreGrupo) {
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
            return;
        }

        Set<String> miembros = contactosPorGrupo.get(nombreGrupo);
        if (miembros == null || miembros.isEmpty()) {
            System.out.println("El grupo '" + nombreGrupo + "' no tiene contactos");
            return;
        }

        System.out.println("Contactos en '" + nombreGrupo + "':");
        for (String nombreContacto : miembros) {
            Contacto contacto = contactos.get(nombreContacto);
            if (contacto != null) {
                System.out.println("- " + contacto);
            }
        }
    }
}
