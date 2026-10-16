package juancho;

import java.util.Scanner;

public class MainModificado {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorModificado gestor = new GestorModificado();
        int opcion;
        do {
            System.out.println("VIDEOJUEGO");
            System.out.println("1. Añadir personaje");
            System.out.println("2. Mostrar personajes");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Borrar personaje");
            System.out.println("5. Ordenar personajes");
            System.out.println("6. Mostrar estadísticas");
            System.out.println("7. Salir");
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
                    sc.nextLine();

                    try {
                        Personaje p = new Personaje(nombre, vida, ataque, defensa, alcance);

                        if (gestor.añadirPersonaje(p)) {
                            System.out.println("Personaje añadido con éxito");
                        } else {
                            System.out.println("El personaje ya existe");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    gestor.mostrarPersonajes();
                    break;

                case 3:
                    System.out.print("Nombre del personaje: ");
                    String modNombre = sc.nextLine();
                    System.out.print("Nueva vida: ");
                    int nv = sc.nextInt();
                    System.out.print("Nuevo ataque: ");
                    int na = sc.nextInt();
                    System.out.print("Nueva defensa: ");
                    int nd = sc.nextInt();
                    System.out.print("Nuevo alcance: ");
                    int nal = sc.nextInt();
                    sc.nextLine();

                    try {
                        if (gestor.modificarPersonaje(modNombre, nv, na, nd, nal)) {
                            System.out.println("Personaje modificado");
                        } else {
                            System.out.println("Personaje no encontrado");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Nombre del personaje a borrar: ");
                    String delNombre = sc.nextLine();

                    if (gestor.borrarPersonaje(delNombre)) {
                        System.out.println("Personaje borrado");
                    } else {
                        System.out.println("Personaje no encontrado");
                    }
                    break;

                case 5:
                    System.out.println("ORDENAR POR:");
                    System.out.println("1. Vida");
                    System.out.println("2. Ataque");
                    System.out.println("3. Defensa");
                    System.out.println("4. Alcance");
                    System.out.print("Elija un atributo: ");
                    int atributo = sc.nextInt();
                    sc.nextLine();

                    gestor.ordenarPersonajes(atributo);
                    break;

                case 6:
                    gestor.mostrarEstadisticas();
                    break;

                case 7:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 7);

        sc.close();
    }
}

