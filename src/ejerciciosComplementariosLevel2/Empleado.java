package ejerciciosComplementariosLevel2;

public class Empleado {
    String nombreYApellido;
    int dni;
    int horasTrabajadas;
    int valorXHora;

    public Empleado(String nombreYApellido, int dni, int horasTrabajadas, int valorXHora) {
        this.nombreYApellido = nombreYApellido;
        this.dni = dni;
        this.horasTrabajadas = horasTrabajadas;
        this.valorXHora = valorXHora;
    }
        public int getHorasTrabajadas() {
            return this.horasTrabajadas;
        }
        public int getDni(){
            return this.dni;
        }

        public int calcularSueldo () {
            return this.horasTrabajadas * this.valorXHora;
        }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombreYApellido='" + nombreYApellido + '\'' +
                '}';
    }
}


