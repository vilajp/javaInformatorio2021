/*Crear un Stream vacío*/

package PracticaGuiadaOptionalStreams;

import java.util.stream.Stream;

import static java.util.stream.Stream.*;

public class Ejercicio1 {
    public static void main(String[] args) {
        Stream <String> streamVacio = Stream.empty();
        System.out.println(streamVacio);

    }
}
