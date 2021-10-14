/*
Dados 2 ArrayList que contienen horas-trabajadas (array1) y valor-por-hora(array2) del resumen de carga de horas
semanal de un empleado. Se debe generar otra lista que contenga los totales y luego imprimir el total final a
cobrar
Input (Entrada):
[6, 7, 8, 4, 5]
[350, 345, 550, 600, 320]

## Los arrays son iguales y corresponden a los días trabajados de una semana Lun-Vie.

Output (Salida):
[2100, 2415, 4400, 2400, 1600]
Total Final: $ 12915


*/

package ejerciciosComplementariosLevel2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ejercicio5 {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String stringHorasTrabajadas = teclado.nextLine();
        String stringValorPorHora = teclado.nextLine();

        List<Integer> totalesSueldo = new ArrayList<>();

        String[] horasTrabajadas = stringHorasTrabajadas.substring(1,stringHorasTrabajadas.length()-1).split(",");
        String[] valorPorHora = stringValorPorHora.substring(1,stringValorPorHora.length()-1).split(",");

        int totalSueldoFinal = 0;

        for(int cadaIndice= 0; cadaIndice<horasTrabajadas.length; cadaIndice++){
            int valorHora = Integer.parseInt(horasTrabajadas[cadaIndice].strip());
            int horaTrabajada = Integer.parseInt(valorPorHora[cadaIndice].strip());
            int totalSueldo = valorHora*horaTrabajada;
            totalesSueldo.add(totalSueldo);
            totalSueldoFinal+=totalSueldo;
        }
        System.out.println(totalesSueldo);
        System.out.println("Total Final: $ "+totalSueldoFinal);
    }
}
