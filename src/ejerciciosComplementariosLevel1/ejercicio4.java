/*Realizar un programa que calcule el factorial de un número:
        n! = 1 x 2 x 3 x 4 x 5 x … x (n-1) x n.
        Sin hacer uso de librerías.
        Input (Entrada):
        5

        Output (Salida):
        El factorial de 5 es: 120*/

package ejerciciosComplementariosLevel1;
import java.util.Scanner;

public class ejercicio4 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese un numero:");
        int numero = teclado.nextInt();
        int factorial = calculoFactorial(numero);
        System.out.println("El factorial de "+numero+" es: "+factorial);
    }

    public static int calculoFactorial(int numero) {
        int factorial = numero;
        if(numero > 1) {
           for(int cadaNumero=numero-1;cadaNumero>0;cadaNumero--)
                factorial *= cadaNumero;
        } else {
            factorial = 1;
        }

        return factorial;
    }


}
