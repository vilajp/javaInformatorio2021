/*Dada una lista que contiene números enteros, se deberá generar otra lista que contendrá el resultado
de aplicar la operación de potencia a 2.
Input (Entrada):
List<Integer> palabras = List.of(1, 2, 3, 4, 5);

Output (Salida):
[1, 4, 9, 16, 25]

*/
package EjerciciosComplementariosLevel3;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio2 {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
        List<Integer> potencias = numeros.stream()
                .map(palabrasResultado -> hacerPotencia(palabrasResultado,2))
                .collect(Collectors.toList());
        System.out.println(potencias);
    }

    public static int hacerPotencia(int numero, int potencia){
        int resultado = 1;
        for (int vuelta = 1; vuelta <= potencia; vuelta++){
            resultado *=numero;
        }
        return resultado;
    }
}
