
package lab_8_ejercicio4;

public class Main {
    public static void main(String[] args) {
        ProductoModelo modelo = new ProductoModelo();
        ProductoVista vista = new ProductoVista();

        ProductoControlador controlador =
                new ProductoControlador(modelo, vista);

        controlador.iniciar();
    }
}
