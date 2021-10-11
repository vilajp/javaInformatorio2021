/*Confeccionar un programa que dado un número entero como dato de entrada imprima la secuencia de
números (incrementos de 1) de la siguiente forma:
        Input (Entrada):
        5

        Output (Salida):
        1
        1 2
        1 2 3
        1 2 3 4
        1 2 3 4 5*/

package ejerciciosComplementariosLevel1;
import java.util.Scanner;

public class ejercicio3 {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese un numero:");
        int numero = teclado.nextInt();
        for (int cadaNumero = 1;cadaNumero <= numero; cadaNumero++){
            for (int primerNumero = 1; primerNumero <= cadaNumero; primerNumero++){
                System.out.print(primerNumero+" ");
            }
            System.out.println();
        }
    }
}
