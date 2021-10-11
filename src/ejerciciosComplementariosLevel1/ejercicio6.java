/*
Se desea una aplicación que solicite 2 números enteros y realice la operación de potencia (sin uso de librerías).
        Input (Entrada):
        3
        3

        Output (Salida):
        3 elevado a 3 = 27
*/

package ejerciciosComplementariosLevel1;
import java.util.Scanner;

public class ejercicio6 {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese primer numero:");
        int primerNumero = teclado.nextInt();
        System.out.println("Ingrese segundo numero:");
        int segundoNumero = teclado.nextInt();
        int producto = potenciaNumeros(primerNumero, segundoNumero);
        System.out.println(primerNumero + " elevado a " + segundoNumero + " = " + producto);
    }

    public static int potenciaNumeros(int primerNumero, int segundoNumero) {
        int potencia= 1;
        for (int cadaNumero = 1; cadaNumero <= segundoNumero; cadaNumero++)
            potencia *= primerNumero;

        return potencia;
    }
}


