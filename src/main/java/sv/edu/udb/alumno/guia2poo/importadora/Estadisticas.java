package sv.edu.udb.alumno.guia2poo.importadora;

import java.util.List;

public class Estadisticas {

    public static int contarPorMarca(List<Vehiculo> vehiculos, String marca) {
        int count = 0;
        for (Vehiculo v : vehiculos) {
            if (v.getMarca().equalsIgnoreCase(marca)) {
                count++;
            }
        }
        return count;
    }

    public static double sumarPorMarca(List<Vehiculo> vehiculos, String marca) {
        double suma = 0;
        for (Vehiculo v : vehiculos) {
            if (v.getMarca().equalsIgnoreCase(marca)) {
                suma += v.getPrecio();
            }
        }
        return suma;
    }

    public static int contarPorRango(List<Vehiculo> vehiculos, int inicio, int fin) {
        int count = 0;
        for (Vehiculo v : vehiculos) {
            if (v.getAnio() >= inicio && v.getAnio() <= fin) {
                count++;
            }
        }
        return count;
    }
}