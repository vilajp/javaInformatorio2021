/*
Solicitar por consola el nombre del usuario e imprimir por pantalla el siguiente mensaje “HOLA {USUARIO}!!!”
*/
package ejerciciosComplementariosLevel1;
import java.util.Scanner;


public class ejercicio1 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese un nombre:");
        String nombreUsuario = teclado.nextLine();
        System.out.println("HOLA " + nombreUsuario);
        teclado.close();
    }
}
