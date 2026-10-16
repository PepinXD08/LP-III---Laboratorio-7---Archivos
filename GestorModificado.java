package juancho;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestorModificado {
    private List<Personaje> personajes;
    private final String archivo = "personajes.txt";

    public GestorModificado() {
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

    public boolean modificarPersonaje(String nombre, int vida,
            int ataque, int defensa, int alcance) {
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
        boolean eliminado = personajes.removeIf(
                p -> p.getNombre().equalsIgnoreCase(nombre));

        if (eliminado) {
            guardarArchivo();
        }
        return eliminado;
    }

    // Nueva funcionalidad 1: ordenar por atributo
    public void ordenarPersonajes(int opcion) {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados");
            return;
        }

        List<Personaje> ordenados = new ArrayList<>(personajes);
        Comparator<Personaje> comparador;

        switch (opcion) {
            case 1:
                comparador = Comparator.comparingInt(
                        Personaje::getVida).reversed();
                break;
            case 2:
                comparador = Comparator.comparingInt(
                        Personaje::getAtaque).reversed();
                break;
            case 3:
                comparador = Comparator.comparingInt(
                        Personaje::getDefensa).reversed();
                break;
            case 4:
                comparador = Comparator.comparingInt(
                        Personaje::getAlcance).reversed();
                break;
            default:
                System.out.println("Atributo no válido");
                return;
        }

        ordenados.sort(comparador);

        for (Personaje p : ordenados) {
            System.out.println(p);
        }
    }

    // Nueva funcionalidad 2: estadísticas generales
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados");
            return;
        }

        double vida = 0, ataque = 0, defensa = 0, alcance = 0;

        for (Personaje p : personajes) {
            vida += p.getVida();
            ataque += p.getAtaque();
            defensa += p.getDefensa();
            alcance += p.getAlcance();
        }

        int total = personajes.size();

        System.out.println("ESTADÍSTICAS GENERALES");
        System.out.println("Total de personajes: " + total);
        System.out.printf("Vida promedio: %.2f%n", vida / total);
        System.out.printf("Ataque promedio: %.2f%n", ataque / total);
        System.out.printf("Defensa promedio: %.2f%n", defensa / total);
        System.out.printf("Alcance promedio: %.2f%n", alcance / total);
    }

    public void guardarArchivo() {
        try (PrintWriter pw =
                new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                pw.println(p.getNombre() + "," + p.getVida() + "," + p.getAtaque() + "," + p.getDefensa() + "," + p.getAlcance());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void cargarArchivo() {
        File f = new File(archivo);

        if (f.exists()) {
            try (BufferedReader br =
                    new BufferedReader(new FileReader(archivo))) {
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
                System.out.println("Error al leer: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Datos inválidos: " + e.getMessage());
            }
        }
    }
}
