/*Realizar un programa que solicite por consola 2 números enteros. Para luego imprimir el resultado de la suma, resta, multiplicación, división y módulo (resto de la división) de ambos números.
        Input (Entrada):
        5
        4

        Output (Salida):
        5 + 4 = 9
        5 - 4 = 1
        5 * 4 = 20
        5 / 4 = 1
        5 % 4 = 1*/

package ejerciciosComplementariosLevel1;

import java.util.Scanner;

public class ejercicio2 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese primer numero:");
        Integer primerNumero = teclado.nextInt();
        System.out.println("Ingrese segundo numero:");
        Integer segundoNumero = teclado.nextInt();

        System.out.println(primerNumero+" + "+segundoNumero+" = "+(primerNumero+segundoNumero));
        System.out.println(primerNumero+" - "+segundoNumero+" = "+(primerNumero-segundoNumero));
        System.out.println(primerNumero+" * "+segundoNumero+" = "+(primerNumero*segundoNumero));
        System.out.println(primerNumero+" / "+segundoNumero+" = "+(primerNumero/segundoNumero));
        System.out.println(primerNumero+" % "+segundoNumero+" = "+(primerNumero%segundoNumero));
        teclado.close();
    }
}
