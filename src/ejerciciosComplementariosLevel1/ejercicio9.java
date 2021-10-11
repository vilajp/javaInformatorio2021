/*Dado un String de entrada (frase, texto, etc) calcular la cantidad de veces que aparece una letra dada.
        Input (Entrada):
        Hola Informatorio Java 2020.
        a


        Output (Salida):
        4*/

package ejerciciosComplementariosLevel1;
import java.util.Scanner;

public class ejercicio9 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese una palabra o frase:");
        String palabraIngresada = teclado.nextLine();

        System.out.println("Ingrese un caracter para contar:");
        String caracterAContar = teclado.next();

        int cantidadOcurrencias = cuentaCaracter(palabraIngresada, caracterAContar);
        System.out.println(cantidadOcurrencias);
    }

    public static int cuentaCaracter(String palabra, String caracter){
        int caracteresEncontrados = 0;
        for (int cadaPosicion = 0; cadaPosicion<palabra.length();cadaPosicion++){
            if (Character.toString(palabra.charAt(cadaPosicion)).equals(caracter))
                caracteresEncontrados +=1;
        }
        return caracteresEncontrados;
    }
}
