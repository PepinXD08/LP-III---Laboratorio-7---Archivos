
package lab_8_ejercicio4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class ProductoModelo {
    private final String archivo = "productos.txt";

    public List<Producto> leerRegistros() throws IOException {
        List<Producto> lista = new ArrayList<>();
        File f = new File(archivo);

        if (!f.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(f))) {

            String linea;
            int numeroLinea = 0;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;

                String[] d = linea.split(";", -1);

                try {
                    if (d.length != 3 || d[1].trim().isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    int id = Integer.parseInt(d[0].trim());
                    String nombre = d[1];
                    double precio = Double.parseDouble(d[2].trim());

                    validarProducto(id, nombre, precio);

                    for (Producto p : lista) {
                        if (p.getId() == id) {
                            throw new IllegalArgumentException();
                        }
                    }

                    lista.add(new Producto(id, nombre, precio));

                } catch (IllegalArgumentException e) {
                    throw new IOException(
                            "Registro incorrecto en la linea "
                            + numeroLinea + ": " + linea, e);
                }
            }
        }

        return lista;
    }

    public void agregarRegistro(Producto producto)
            throws IOException {

        validarProducto(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio());

        List<Producto> lista = leerRegistros();

        for (Producto p : lista) {
            if (p.getId() == producto.getId()) {
                throw new IllegalArgumentException(
                        "Ya existe un producto con ese ID.");
            }
        }

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo, true))) {

            bw.write(producto.getId() + ";"
                    + producto.getNombre() + ";"
                    + producto.getPrecio());
            bw.newLine();
        }
    }

    public Producto buscarRegistro(int id) throws IOException {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser positivo.");
        }

        List<Producto> lista = leerRegistros();

        for (Producto p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    public boolean eliminarRegistro(int id) throws IOException {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser positivo.");
        }

        // Primero se lee todo el archivo correctamente.
        List<Producto> lista = leerRegistros();

        boolean eliminado = lista.removeIf(
                p -> p.getId() == id);

        if (!eliminado) {
            return false;
        }

        File original = new File(archivo);
        File temporal = new File(archivo + ".tmp");

        try {
            // Se escribe la lista sin el producto eliminado.
            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(temporal))) {

                for (Producto p : lista) {
                    bw.write(p.getId() + ";"
                            + p.getNombre() + ";"
                            + p.getPrecio());
                    bw.newLine();
                }
            }

            // Se reemplaza el archivo original.
            try {
                Files.move(
                        temporal.toPath(),
                        original.toPath(),
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.ATOMIC_MOVE);
            } catch (java.nio.file.AtomicMoveNotSupportedException e) {
                Files.move(
                        temporal.toPath(),
                        original.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }

            return true;

        } catch (IOException e) {
            try {
                Files.deleteIfExists(temporal.toPath());
            } catch (IOException errorLimpieza) {
                e.addSuppressed(errorLimpieza);
            }

            throw new IOException(
                    "No se pudo actualizar el archivo al eliminar.", e);
        }
    }

    private void validarProducto(int id, String nombre, double precio) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser un numero entero positivo.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacio.");
        }

        if (nombre.contains(";")
                || nombre.contains("\n")
                || nombre.contains("\r")) {
            throw new IllegalArgumentException(
                    "El nombre contiene caracteres no permitidos.");
        }

        if (!Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser un numero finito no negativo.");
        }
    }
}
