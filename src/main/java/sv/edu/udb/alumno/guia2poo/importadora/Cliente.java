package sv.edu.udb.alumno.guia2poo.importadora;

public class Cliente {
    private String nombre;
    private String sexo;

    public Cliente(String nombre, String sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}