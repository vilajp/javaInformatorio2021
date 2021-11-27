/*Se posee una Lista con objetos de clase Alumno con los atributos: apellido, nombre y fechaDeNacimiento
 (con tipos: String, String y LocalDate). Se desea generar un Map<String, Integer> donde la clave de Map
 será el apellido concatenado con el nombre (con separador de espacio en blanco) y el value la edad del alumno.
La lista de entrada debe estar cargada con varios alumnos (al menos 5) para subir el ejemplo y demostrar
su funcionamiento.
En ejemplo se muestra solo con 1 Alumno a modo de abreviar
En el ejemplo también se usa LocalDate.now().minusYears(30), en el ejercicio a presentar se deberá usar otra
tecnica de construcción para la fecha (no usar .now(). Pueden ver métodos .parse(), etc)
Input (Entrada)
List<Alumno> alumnos = List.of(new Alumno("Homero", "Simpson", LocalDate.now().minusYears(30)));
LocalDate localdate
            = LocalDate.of(2020, 5, 13);

Output (Salida):
{"Simpson Homero"=30}

*/
package EjerciciosComplementariosLevel3;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio5 {
    public static void main(String[] args) {

        List<Alumno> alumnos = List.of(
                new Alumno("Homero", "Simpson", LocalDate.of(1975,1,20)),
                new Alumno("Bart", "Simpson", LocalDate.of(2019,12,8)),
                new Alumno("Lisa", "Simpson", LocalDate.of(2004,2,18)),
                new Alumno("Marge", "Simpson", LocalDate.of(1982,7,16)),
                new Alumno("SantasHelper", "Simpson", LocalDate.of(2005,7,19))
        );

        Map<String, Integer> nombresYEdadesStream = alumnos.stream()
                .collect(Collectors.toMap(nombreAlumno -> nombreAlumno.getApellido()+" "+nombreAlumno.getNombre(),
                        edadEmpleado ->  calculoEdad(edadEmpleado.getFechaDeNacimiento())));
        System.out.println(nombresYEdadesStream);
    }

    private static Integer calculoEdad(LocalDate fechaNacimientoEmpleado){
        Stream<LocalDate> edadEmpleado = fechaNacimientoEmpleado.datesUntil(LocalDate.now());
        List<LocalDate> listaFechas = edadEmpleado
                .collect(Collectors.toList());
        Integer diasEmpleado = listaFechas.size();
        return diasEmpleado/365;

    }
}
