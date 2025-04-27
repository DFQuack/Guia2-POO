package sv.edu.udb.alumno.guia2poo.carclean;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Cliente implements ITotalPago {
    private String nombre, apellidos;
    private boolean vip;

    public Cliente() {}

    @Override
    public double totalPago(String tipo, boolean vip) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        double precio = switch (tipo) {
            case "Motocicleta" -> 2.75;
            case "Sedán" -> 3.50;
            case "Camioneta" -> 4;
            case "Microbús" -> 5;
            case "Autobús" -> 7;
            default -> 1;
        };
        if (vip) {
            precio -= precio * 0.15;
        }
        String precioStr = formatter.format(precio);
        precio = Double.parseDouble(precioStr);
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
