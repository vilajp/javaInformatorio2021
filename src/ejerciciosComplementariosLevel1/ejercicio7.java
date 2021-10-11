/*Realizar un programa que dado un String de entrada en minúsculas lo convierta por completo a mayúsculas. Sin uso de métodos o librerías que realicen toUppercase().
        Input (Entrada):
        informatorio

        Output (Salida):
        INFORMATORIO*/

package ejerciciosComplementariosLevel1;
import java.util.Scanner;

public class ejercicio7 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese una palabra:");
        String palabraIngresada = teclado.nextLine();
        String palabraMayuscula = conviertoPalabra(palabraIngresada);
        System.out.println(palabraMayuscula);
        teclado.close();
    }

    public static String conviertoPalabra(String palabra){
        String minusculas = "abcdefghijklmnñopqrstuvwxyz";
        String mayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        StringBuilder palabraEnMayuscula = new StringBuilder();
        for (int cadaPosicion = 0; cadaPosicion <palabra.length(); cadaPosicion ++) {
            char unCaracter = palabra.charAt(cadaPosicion);

            for (int cadaPosicionMinusculas=0;
                 cadaPosicionMinusculas<minusculas.length();
                 cadaPosicionMinusculas++)
                if (unCaracter == minusculas.charAt(cadaPosicionMinusculas))
                    palabraEnMayuscula.append(mayusculas.charAt(cadaPosicionMinusculas));
        }
        return palabraEnMayuscula.toString();
    }
}
