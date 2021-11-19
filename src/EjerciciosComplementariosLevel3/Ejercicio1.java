/*Dada una lista que contiene valores de tipo String, se deberá filtrar todos los valores que NO sean
null o vacío ("").
Input (Entrada):
List<String> palabras = new ArrayList<>();
        palabras.add("Hola");
        palabras.add(null);
        palabras.add("Informatorio");
        palabras.add("");

Output (Salida):
["Hola", "Informatorio"]

*/
package EjerciciosComplementariosLevel3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio1 {
    public static void main(String[] args) {
        List<String> palabras = new ArrayList<>();
        palabras.add("Hola");
        palabras.add(null);
        palabras.add("Informatorio");
        palabras.add("");

        List<String> cadaPalabra = palabras.stream()
                .filter(palabrasResultado-> palabrasResultado!=null&& !palabrasResultado.equals(""))
                .collect(Collectors.toList());

        System.out.println(cadaPalabra);
    }
}
