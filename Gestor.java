package juancho;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gestor {
    private List<Personaje> personajes;
    private final String archivo = "personajes.txt";

    public Gestor() {
        personajes = new ArrayList<>();
        cargarArchivo();
    }

    public boolean añadirPersonaje(Personaje p) {
        for (Personaje per : personajes) {
            if (per.getNombre().equalsIgnoreCase(p.getNombre())) {
                return false;
            }
        }
        personajes.add(p);
        guardarArchivo();
        return true;
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados");
        } else {
            for (Personaje p : personajes) {
                System.out.println(p);
            }
        }
    }

    public boolean modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    public boolean borrarPersonaje(String nombre) {
        boolean eliminado = personajes.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
        if (eliminado) {
            guardarArchivo();
        }
        return eliminado;
    }

    public void guardarArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                pw.println(p.getNombre() + "," + p.getVida() + "," + p.getAtaque() + "," + p.getDefensa() + "," + p.getAlcance());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public void cargarArchivo() {
        File f = new File(archivo);
        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 5) {
                        String nombre = partes[0];
                        int vida = Integer.parseInt(partes[1]);
                        int ataque = Integer.parseInt(partes[2]);
                        int defensa = Integer.parseInt(partes[3]);
                        int alcance = Integer.parseInt(partes[4]);
                        personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }
}