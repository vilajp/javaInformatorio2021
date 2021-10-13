/*Crear un ArrayList y cargarlo con tus ciudades favoritas de Argentina, luego imprimir por pantalla
el ranking
        Input (Entrada):
        Bariloche
        Córdoba
        Resistencia

        Output (Salida):
        #1 - Bariloche
        #2 - Córdoba
        #3 - Resistencia*/


package ejerciciosComplementariosLevel2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ejercicio1 {
    public static void main(String[] args) {
        System.out.println("Ingrese el nombre de sus tres ciudades favoritas");
        Scanner teclado = new Scanner(System.in);

        List<String> ciudades = new ArrayList<>();


        for (int vueltas = 1; vueltas <= 3; vueltas++){
            System.out.println("Ingrese Ciudad Nro."+vueltas);
            String nombreCiudad = teclado.nextLine();
            ciudades.add(nombreCiudad);
        }

        for (String cadaCiudad:ciudades){
            System.out.println("#" + (ciudades.indexOf(cadaCiudad)+1)+" - "+cadaCiudad);


        }

    }
}
