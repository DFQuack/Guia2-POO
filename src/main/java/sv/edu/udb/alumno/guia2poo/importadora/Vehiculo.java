package sv.edu.udb.alumno.guia2poo.importadora;

public class Vehiculo {
    private String marca;
    private int anio;
    private double precio;

    public Vehiculo(String marca, int anio, double precio) {
        this.marca = marca;
        this.anio = anio;
        this.precio = precio;
    }

    // Getters y setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnio() {
        return anio;
    }

    public void setAño(int año) {
        this.anio = año;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
