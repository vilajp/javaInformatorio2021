/*
Se dispone de una lista de Empleados, de cada empleado se conoce:
        Nombre y Apellido
        DNI
        horasTrabajadas
        valorPorHora
        Todos los empleados están cargados en un Set (HashSet), se desea calcular el sueldo (horasTrabajadas x
        valorPorHora) de toda esa lista para luego almacenar en un Map (o Diccionario) donde la clave (key) es el
        dni y el valor (value) es el sueldo calculado.

*/

package ejerciciosComplementariosLevel2;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.Method;


public class ejercicio6 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map<Integer, Integer> listadoSueldos = new HashMap<Integer, Integer>();
        Set<Object> listaEmpleados = new HashSet<>();


        Empleado empleado1 = new Empleado("Juan Pablo Vila",23999999,600,8);
        Empleado empleado2 = new Empleado("Andrea Miron",29999999, 400,9);
        Empleado empleado3 = new Empleado("Dulcinea Vila",45000000, 300,10);

        listaEmpleados.add(empleado1);
        listaEmpleados.add(empleado2);
        listaEmpleados.add(empleado3);



        for(Object cadaEmpleado:listaEmpleados) {
            Method methodDni = cadaEmpleado.getClass().getMethod("getDni");
            Integer dniEmpleado = (Integer) methodDni.invoke(cadaEmpleado);
            Method methodCalculo = cadaEmpleado.getClass().getMethod("calcularSueldo");
            Integer sueldoTotalEmpleado = (Integer) methodCalculo.invoke(cadaEmpleado);
            listadoSueldos.put(dniEmpleado,sueldoTotalEmpleado);
            }

        System.out.println(listadoSueldos);
        }
    }


