/*
Crear una función que dado 2 argumentos (int, siendo el primero menor al segundo), nos devuelva un array
 de Strings. Con la secuencia de números enteros de principio a final. Pero si el número es multiplo de 2 colocara
 el valor “Fizz”, si es múltiplo de 3 “Buzz” y si es a la vez múltiplo de ambos colocara “FizzBuzz”.

        Observacion: Los 2 argumentos indican con que valor se arranca a calcular y el segundo con qué valor debe
         frenar (no se incluye en el cálculo)
        Ejemplo: (1, 5) ----> calculará valores de 1, 2, 3, 4
        Input (Entrada):
        fizzBuzzFuncion(1,6)    // 6 si marca el límite y no se lo incluye
        fizzBuzzFuncion(1,8)

        Output (Salida):
        [“1”, “Fizz”, “Buzz”, “Fizz”, 5]
        [“1”, “Fizz”, “Buzz”, “Fizz”, “5”, “FizzBuzz”, “7”]
*/


package ejerciciosComplementariosLevel2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ejercicio7 {
    public static void main(String[] args) {
        List<String> fizzBuzz = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        int sigo = 1;
        String mensaje = "";
        int valorInicial = 0;
        int valorFinal = 0;
        while(sigo == 1) {
            System.out.println(mensaje);
            System.out.println("Ingrese valor inicial de la funcion");
            valorInicial = teclado.nextInt();
            System.out.println("Ingrese valor final de la funcion");
            valorFinal = teclado.nextInt();
            if(valorFinal<=valorInicial) {
                mensaje = "El valor inicial debe ser menor que el valor final, intente nuevamente...";
            }else{
                sigo = 0;
            }
        }
        fizzBuzz = fizzBuzzfuncion(valorInicial,valorFinal);
        System.out.println(fizzBuzz);
    }
    public static List fizzBuzzfuncion(int valorInicial, int valorFinal){
        List<String> resultado = new ArrayList<>();
        for (int incremento=valorInicial; incremento<valorFinal; incremento++){
            if (incremento%2==0&incremento%3==0){
                resultado.add("FizzBuzz");
            }else if(incremento%2==0){
                resultado.add("Fizz");
            }else if(incremento%3==0){
                resultado.add("Buzz");
            }else {
                resultado.add(String.valueOf(incremento));
            }
        }
        return resultado;
    }
}
