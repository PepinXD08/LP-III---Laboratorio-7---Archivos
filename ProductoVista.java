package lab_8_ejercicio4;
import java.util.Scanner;

public class ProductoVista {
    private final Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n===== GESTION DE PRODUCTOS =====");
        System.out.println("1. Listar todos los productos");
        System.out.println("2. Agregar un producto");
        System.out.println("3. Buscar producto por ID");
        System.out.println("4. Eliminar producto por ID");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opcion: ");

        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Producto pedirProducto() {
        try {
            System.out.print("Ingrese ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine().trim();

            System.out.print("Ingrese precio: ");
            double precio = Double.parseDouble(
                    sc.nextLine().trim().replace(',', '.'));

            if (id <= 0) {
                mostrarMensaje("Error: el ID debe ser positivo.");
                return null;
            }

            if (nombre.isEmpty() || nombre.contains(";")
                    || nombre.contains("\n") || nombre.contains("\r")) {
                mostrarMensaje("Error: el nombre no es valido.");
                return null;
            }

            if (!Double.isFinite(precio) || precio < 0) {
                mostrarMensaje(
                        "Error: el precio debe ser un numero no negativo.");
                return null;
            }

            return new Producto(id, nombre, precio);

        } catch (NumberFormatException e) {
            mostrarMensaje(
                    "Error: el ID debe ser entero y el precio numerico.");
            return null;
        }
    }

    public int pedirId() {
        try {
            System.out.print("Ingrese el ID del producto: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            if (id <= 0) {
                mostrarMensaje("Error: el ID debe ser positivo.");
                return -1;
            }

            return id;

        } catch (NumberFormatException e) {
            mostrarMensaje("Error: ingrese un ID entero valido.");
            return -1;
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrar() {
        sc.close();
    }
}
