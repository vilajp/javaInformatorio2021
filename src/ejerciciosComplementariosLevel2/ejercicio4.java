/*
Cargar un arrayList con 12 nombres de estudiantes (String), luego separarlos en 3 cursos (3 arrayList) e imprimir
dichos cursos.

Ayuda: ArrayList posee un método para particionar en sub-listas?

*/

package ejerciciosComplementariosLevel2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ejercicio4 {

    public static void main(String[] args) {
        System.out.println("Ingrese el nombres de 12 estudiantes");
        Scanner teclado = new Scanner(System.in);

        List<String> nombresEstudiantes = new ArrayList<>();
        List<String> curso1 = new ArrayList<>();
        List<String> curso2 = new ArrayList<>();
        List<String> curso3 = new ArrayList<>();


        for (int cadaEstudiante = 1; cadaEstudiante <= 12; cadaEstudiante++) {
            System.out.println("Ingrese Nro. para estudiante Nro " + cadaEstudiante);
            String unEstudiante = teclado.nextLine();
            nombresEstudiantes.add(unEstudiante);
        }

        int cantidadEstudiantePorCurso = nombresEstudiantes.size() / 3;
        for (String cadaEstudiante : nombresEstudiantes) {
            if (nombresEstudiantes.indexOf(cadaEstudiante)+1 <= cantidadEstudiantePorCurso) {
                curso1.add(cadaEstudiante);
            } else if (nombresEstudiantes.indexOf(cadaEstudiante)+1 <= cantidadEstudiantePorCurso * 2) {
                curso2.add(cadaEstudiante);
            } else {
                curso3.add(cadaEstudiante);
            }
        }
        System.out.println("\nAlumnos Curso 1\n");
        for (String cadaAlumno : curso1) {
            System.out.println(cadaAlumno);
        }
        System.out.println("\nAlumnos Curso 2\n");
        for (String cadaAlumno : curso2) {
            System.out.println(cadaAlumno);
        }
        System.out.println("\nAlumnos Curso 3\n");
        for (String cadaAlumno : curso3) {
            System.out.println(cadaAlumno);
        }
    }
}