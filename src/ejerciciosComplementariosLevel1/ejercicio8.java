/*Crear una aplicación que solicite de entrada los datos de una persona en este orden:
        Nombre y Apellido
        Edad
        Dirección
        Ciudad
        Luego imprimirá el siguiente mensaje:
        {Ciudad} - {Dirección} - {Edad} - {Nombre y Apellido}
        Input (Entrada):
        Homero Simpson
        48
        Calle Falsa 1234
        Springfield

        Output (Salida):
        Springfield - Calle Falsa 1234 - 48 - Homero Simpson*/

package ejerciciosComplementariosLevel1;
import java.util.Scanner;

public class ejercicio8 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese Nombre y Apellido:");
        String nombreYApellido = teclado.nextLine();
        System.out.println("Ingrese Edad:");
        String edad = teclado.nextLine();
        System.out.println("Ingrese Direccion:");
        String direccion = teclado.nextLine();
        System.out.println("Ingrese Ciudad:");
        String ciudad = teclado.nextLine();

        System.out.println(ciudad+" - "+direccion+" - "+edad+" - "+nombreYApellido);
    }
    }
