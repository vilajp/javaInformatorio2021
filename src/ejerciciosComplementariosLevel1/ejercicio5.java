/*
Se desea una aplicación que solicite 2 números enteros y realice la operación de multiplicación por sumas sucesivas (sin uso de librerías).
        Input (Entrada):
        2
        3

        Output (Salida):
        2 x 3 = 6
*/

package ejerciciosComplementariosLevel1;

import java.util.Scanner;

public class ejercicio5 {


    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese primer numero:");
        int primerNumero = teclado.nextInt();
        System.out.println("Ingrese segundo numero:");
        int segundoNumero = teclado.nextInt();
        int producto = multiplicoNumeros(primerNumero, segundoNumero);
        System.out.println(primerNumero + " x " + segundoNumero + " = " + producto);
    }

    public static int multiplicoNumeros(int primerNumero, int segundoNumero) {
        int producto = 0;
        for (int cadaNumero = 1; cadaNumero <= segundoNumero; cadaNumero++)
            producto += primerNumero;

        return producto;
    }
}
