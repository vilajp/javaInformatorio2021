/*Supongamos que tenemos una lista con objetos de tipo Producto, que poseen atributos nombre, tipo,
precio unitario (String, String, BigDecimal), encontrar y devolver el primer producto que su precio
unitario sea menor a 200000.00. Caso contrario mostrar mensaje de No encontrado (ver formato del mensaje
en la imagen debajo).*/

package PracticaGuiadaOptionalStreams;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Ejercicio4 {

    private static final BigDecimal PRECIO_BUSQUEDA = new BigDecimal("200000.00");

    public static void main(String[] args) {

        List<Producto> catalogoProductos = List.of(
                new Producto("iPhone 11 Pro", "Celulares", new BigDecimal("400000.00")),
                new Producto("Samsumg Galaxy S21 Ultra", "Celulares", new BigDecimal("200000.00")),
                new Producto("Motorola Edge Special Edition", "Celulares", new BigDecimal("159899.00"))
        );
        Optional<Producto> productoBarato = catalogoProductos.stream()
                .filter(producto -> PRECIO_BUSQUEDA.compareTo(producto.getPrecioUnitario())>0)
                .findFirst();

        if(productoBarato.isPresent()){
            System.out.println("El primer producto menor que "+ PRECIO_BUSQUEDA + " es "+ productoBarato.get().getNombre());
        } else {
            System.out.println("No se encuentra producto menor que "+PRECIO_BUSQUEDA);
        }

    }
}
