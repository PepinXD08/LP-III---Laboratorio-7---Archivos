
package lab_8_ejercicio4;

import java.io.IOException;
import java.util.List;

public class ProductoControlador {
    private final ProductoModelo modelo;
    private final ProductoVista vista;

    public ProductoControlador(
            ProductoModelo modelo, ProductoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarMensaje("Registros guardados al iniciar:");

        try {
            listar();
        } catch (IOException e) {
            vista.mostrarMensaje(
                    "Error al leer los registros: " + e.getMessage());
        }

        int opcion;

        do {
            opcion = vista.mostrarMenu();

            try {
                switch (opcion) {
                    case 1:
                        listar();
                        break;

                    case 2:
                        Producto producto = vista.pedirProducto();

                        if (producto != null) {
                            modelo.agregarRegistro(producto);
                            vista.mostrarMensaje(
                                    "Producto agregado correctamente.");
                        }
                        break;

                    case 3:
                        int idBuscar = vista.pedirId();

                        if (idBuscar > 0) {
                            Producto encontrado =
                                    modelo.buscarRegistro(idBuscar);

                            if (encontrado != null) {
                                vista.mostrarMensaje(
                                        "Producto encontrado:");
                                vista.mostrarMensaje(
                                        encontrado.toString());
                            } else {
                                vista.mostrarMensaje(
                                        "No se encontro el producto.");
                            }
                        }
                        break;

                    case 4:
                        int idEliminar = vista.pedirId();

                        if (idEliminar > 0) {
                            boolean eliminado =
                                    modelo.eliminarRegistro(idEliminar);

                            if (eliminado) {
                                vista.mostrarMensaje(
                                        "Producto eliminado correctamente.");
                            } else {
                                vista.mostrarMensaje(
                                        "No se encontro el producto.");
                            }
                        }
                        break;

                    case 5:
                        vista.mostrarMensaje(
                                "Programa finalizado.");
                        break;

                    default:
                        vista.mostrarMensaje(
                                "Opcion no valida. Intente nuevamente.");
                }

            } catch (IOException e) {
                vista.mostrarMensaje(
                        "Error de archivo: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(
                        "Error: " + e.getMessage());
            }

        } while (opcion != 5);

        vista.cerrar();
    }

    private void listar() throws IOException {
        List<Producto> productos = modelo.leerRegistros();

        if (productos.isEmpty()) {
            vista.mostrarMensaje("No hay productos registrados.");
            return;
        }

        vista.mostrarMensaje("\n--- LISTA DE PRODUCTOS ---");

        for (Producto p : productos) {
            vista.mostrarMensaje(p.toString());
        }
    }
}
