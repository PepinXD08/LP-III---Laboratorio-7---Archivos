package juancho;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Binarios2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FileOutputStream fos = null;
        DataOutputStream salida = null;

        double[][] matriz;
        int filas, columnas, i, j;

        do {
            System.out.print("Número de filas: ");
            filas = sc.nextInt();
        } while (filas <= 0);

        do {
            System.out.print("Número de columnas: ");
            columnas = sc.nextInt();
        } while (columnas <= 0);

        matriz = new double[filas][columnas]; //se crea la matriz[cite: 2]

        for (i = 0; i < filas; i++) { //lectura de datos por teclado[cite: 2]
            for (j = 0; j < columnas; j++) {
                System.out.print("matriz[" + i + "][" + j + "]: ");
                matriz[i][j] = sc.nextDouble();
            }
        }

        try {
            //crear el fichero de salida[cite: 2]
            fos = new FileOutputStream("D:\\LP III\\FASE I\\LPIII_Laboratorio7_Archivos\\archive\\matriz.dat");
            salida = new DataOutputStream(fos);

            //escribir el número de filas y columnas en el fichero[cite: 2]
            salida.writeInt(filas);
            salida.writeInt(columnas);

            //escribir la matriz en el fichero[cite: 2]
            for (i = 0; i < filas; i++) {
                for (j = 0; j < columnas; j++) {
                    salida.writeDouble(matriz[i][j]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}