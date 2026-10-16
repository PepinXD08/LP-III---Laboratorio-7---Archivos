package juancho;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gestor gestor = new Gestor();
        int opcion;

        do {
            System.out.println("VIDEOJUEGO");
            System.out.println("1. Añadir personaje");
            System.out.println("2. Mostrar personajes");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Borrar personaje");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Vida (>0): ");
                    int vida = sc.nextInt();
                    System.out.print("Ataque (>0): ");
                    int ataque = sc.nextInt();
                    System.out.print("Defensa (>0): ");
                    int defensa = sc.nextInt();
                    System.out.print("Alcance (>0): ");
                    int alcance = sc.nextInt();

                    try {
                        Personaje p = new Personaje(nombre, vida, ataque, defensa, alcance);
                        if (gestor.añadirPersonaje(p)) {
                            System.out.println("Personaje añadido con éxito");
                        } else {
                            System.out.println("Error: El personaje ya existe en la lista");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    gestor.mostrarPersonajes();
                    break;

                case 3:
                    System.out.print("Ingrese el nombre del personaje a modificar: ");
                    String modNombre = sc.nextLine();
                    System.out.print("Nueva vida: ");
                    int nv = sc.nextInt();
                    System.out.print("Nuevo ataque: ");
                    int na = sc.nextInt();
                    System.out.print("Nueva defensa: ");
                    int nd = sc.nextInt();
                    System.out.print("Nuevo alcance: ");
                    int nal = sc.nextInt();

                    try {
                        if (gestor.modificarPersonaje(modNombre, nv, na, nd, nal)) {
                            System.out.println("Personaje modificado");
                        } else {
                            System.out.println("No se encontró un personaje con ese nombre");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el nombre del personaje a borrar: ");
                    String delNombre = sc.nextLine();
                    if (gestor.borrarPersonaje(delNombre)) {
                        System.out.println("Personaje borrado");
                    } else {
                        System.out.println("No se encontró el personaje");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 5);
        sc.close();
    }
}