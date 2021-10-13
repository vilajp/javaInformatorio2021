/*
Crear una lista que contenga como elementos la numeración de cartas de una baraja francesa (solo los valores, no
figuras). Se deberá cargar el ArrayList (en orden), imprimir, imprimir en orden inverso (reverse),
desordenar (mezclar) el arrayList y volver a imprimir.

Ayuda: ArrayList implementa la interface Collection, existe algún método que me permita hacer la operación
sort (mezclar) aleatoriamente? Idem para el reverso
*/


package ejerciciosComplementariosLevel2;
import java.util.ArrayList;
import java.util.List;

public class ejercicio3 {
    public static void main(String[] args) {
        List<Integer> valoresBaraja = new ArrayList<>();

        for(int cadaValorBaraja=2; cadaValorBaraja<=10; cadaValorBaraja++){
            valoresBaraja.add(cadaValorBaraja);
        }
        System.out.println("\nValores Ordenados\n");
        for(int cadaValorBaraja:valoresBaraja){
            System.out.println(cadaValorBaraja);
        }
        System.out.println("\nValores Orden inverso\n");
        for(int indiceValores=valoresBaraja.size()-1; indiceValores>=0; indiceValores--) {
            System.out.println(valoresBaraja.get(indiceValores));
        }
        System.out.println("\nValores mezclados\n");
        int lado = 1;
        int inicio = 0;
        int ultimo = valoresBaraja.size()-1;
        for(int indiceValores=valoresBaraja.size()-1; indiceValores>=0; indiceValores--) {
            if (lado >0){
                System.out.println(valoresBaraja.get(inicio));
                inicio +=1;

            }
            if (lado <0){
                System.out.println(valoresBaraja.get(ultimo));
                ultimo -=1;
            }
            lado*=-1;


        }

    }
}
