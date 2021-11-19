/*Se dispone de una lista de Integer, de la cual queremos obtener otra lista aplicando la operación de
factorial pero no se desean valores repetidos.

Input (Entrada)
List<Integer> palabras = List.of(1, 2, 4, 4, 4);

Output (Salida):
[1, 2, 24]

*/
package EjerciciosComplementariosLevel3;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio4 {
    public static void main(String[] args) {
        List<Integer> palabras = List.of(1, 2, 4, 4, 4);
        List<Integer> factoriales = palabras.stream()
                .map(cadaFactorial -> factorial(cadaFactorial))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(factoriales);
    }
    public static int factorial(int number) {
        if (number < 0 ){
            // Debería lanzarse un error
            System.out.print("No existe el factorial de numeros negativos. ");
            return 0;
        }
        else if (number == 0) {
            return 1;
        } else {
            return number *= factorial(number - 1);
        }
    }

}

