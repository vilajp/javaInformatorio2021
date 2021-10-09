package ejerciciosPropuestos;
import java.util.Scanner;

public class ejercicio1ConScanner {
    public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);

            System.out.println("Enter number");
            float firstNumber = teclado.nextFloat();

            System.out.println("Enter another number");
            float secondNumber = teclado.nextFloat();

            float sum = firstNumber + secondNumber;

            System.out.println("La suma de ambos numeros es: " + sum);
        }
    }

