/*
Crear un ArrayList, agregar 5 números enteros. Luego, agregar un número entero al principio de la lista y
otro al final. Por último, iterar e imprimir los elementos de la lista y el tamaño de la misma (antes y
después de agregar a en la primera y última posición).
*/

package ejerciciosComplementariosLevel2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ejercicio2 {
    public static void main(String[] args) {
        System.out.println("Ingrese cinco numero enteros");
        Scanner teclado = new Scanner(System.in);

        List<Integer> numeros = new ArrayList<>();
        List<Integer> numerosAImprimir = new ArrayList<>();


        for (int vueltas = 1; vueltas <= 5; vueltas++){
            System.out.println("Ingrese Nro. para posicion "+vueltas);
            Integer unNumero = teclado.nextInt();
            numeros.add(unNumero);
        }

        System.out.println("Usted ingreso:");

        for (Integer cadaNumero:numeros){
            System.out.println("#" + (numeros.indexOf(cadaNumero)+1)+" - "+cadaNumero);
        }

        System.out.println("Numero de Elementos actual: "+numeros.size());


        System.out.println("Ingrese Nro. para la primera posicion");
        Integer numeroPrimeraPosicion = teclado.nextInt();

        System.out.println("Ingrese Nro. para la ultima posicion");
        Integer numeroUltimaPosicion = teclado.nextInt();

        numeros.add(numeroUltimaPosicion);
        numerosAImprimir.add(numeroPrimeraPosicion);

        for(Integer cadaNumero:numeros){
            numerosAImprimir.add(cadaNumero);
        }

        for (Integer cadaNumero:numerosAImprimir){
            System.out.println("#" + (numerosAImprimir.indexOf(cadaNumero)+1)+" - "+cadaNumero);
        }
        System.out.println("Numero de Elementos actual: "+numerosAImprimir.size());

    }
}
