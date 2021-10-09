package ejerciciosPropuestos;
import java.util.Scanner;

public class ejercicio1ConScannerYFunciones {
    public static void main(String[] args){
        float numero1;
        float numero2;
        numero1, numero2 = cargoDatos();

    }


    public static float cargoDatos(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Enter number");
        float numero1 = teclado.nextFloat();
        System.out.println("Enter second number");
        float numero2 = teclado.nextFloat();
        return numero1, numero2;
    }
}
