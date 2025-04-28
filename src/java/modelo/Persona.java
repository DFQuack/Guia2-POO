package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import modelo.factory.CalculadoraVacacionesFactory;
import modelo.factory.CalculadoraVacacionesFactory.TipoCalculadora;
import modelo.interfaces.ICalculadoraVacaciones;

/**
 * Clase que representa a una persona con sus datos personales y laborales
 * @author Estudiante
 * @version 1.0
 * @since 26/04/2023
 */
public class Persona {
    private String nombres;
    private String apellidos;
    private double salario;
    private Date fechaIngreso;
    private int diasVacaciones;
    private int aniosServicio;
    private ICalculadoraVacaciones calculadora;
    
    /**
     * Constructor vacío de la clase Persona
     */
    public Persona() {
        // Por defecto, usamos la calculadora estándar
        this.calculadora = CalculadoraVacacionesFactory.crearCalculadora(TipoCalculadora.ESTANDAR);
    }
    
    /**
     * Constructor con parámetros de la clase Persona
     * @param nombres Nombres de la persona
     * @param apellidos Apellidos de la persona
     * @param salario Salario de la persona
     * @param fechaIngreso Fecha de ingreso a la empresa
     */
    public Persona(String nombres, String apellidos, double salario, Date fechaIngreso) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        // Por defecto, usamos la calculadora estándar
        this.calculadora = CalculadoraVacacionesFactory.crearCalculadora(TipoCalculadora.ESTANDAR);
        calcularVacaciones();
    }
    
    /**
     * Constructor con parámetros de la clase Persona que incluye el tipo de calculadora
     * @param nombres Nombres de la persona
     * @param apellidos Apellidos de la persona
     * @param salario Salario de la persona
     * @param fechaIngreso Fecha de ingreso a la empresa
     * @param tipoCalculadora Tipo de calculadora a utilizar
     */
    public Persona(String nombres, String apellidos, double salario, Date fechaIngreso, TipoCalculadora tipoCalculadora) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.calculadora = CalculadoraVacacionesFactory.crearCalculadora(tipoCalculadora);
        calcularVacaciones();
    }
    
    /**
     * Método que calcula los años de servicio y días de vacaciones usando la calculadora
     */
    private void calcularVacaciones() {
        if (fechaIngreso != null && calculadora != null) {
            this.aniosServicio = calculadora.calcularAniosServicio(fechaIngreso);
            this.diasVacaciones = calculadora.calcularDiasVacaciones(fechaIngreso);
        }
    }
    
    /**
     * Método que permite cambiar la calculadora de vacaciones
     * @param tipoCalculadora Tipo de calculadora a utilizar
     */
    public void cambiarCalculadora(TipoCalculadora tipoCalculadora) {
        this.calculadora = CalculadoraVacacionesFactory.crearCalculadora(tipoCalculadora);
        calcularVacaciones();
    }
    
    /**
     * Método que devuelve la fecha de ingreso formateada como dd/MM/yyyy
     * @return Fecha de ingreso formateada
     */
    public String getFechaIngresoFormateada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaIngreso);
    }
    
    /**
     * Método estático para validar y convertir una fecha en formato String a Date
     * @param fechaStr Fecha en formato String (dd/MM/yyyy)
     * @return Fecha convertida a objeto Date
     * @throws ParseException Si el formato de la fecha es incorrecto
     * @throws IllegalArgumentException Si la fecha es futura o inválida
     */
    public static Date validarFecha(String fechaStr) throws ParseException, IllegalArgumentException {
        // Validar el formato con expresión regular antes de parsear
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }
        
        // Expresión regular para validar el formato dd/MM/yyyy
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d\\d$";
        
        if (!fechaStr.matches(regex)) {
            throw new IllegalArgumentException("El formato de fecha debe ser dd/MM/yyyy (ejemplo: 15/04/2023)");
        }
        
        // Validar que la fecha sea válida (por ejemplo, 30/02/2023 no es válido)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // No permite fechas inválidas
        
        try {
            Date fecha = sdf.parse(fechaStr);
            
            // Validar que la fecha no sea futura
            if (fecha.after(new Date())) {
                throw new IllegalArgumentException("La fecha de ingreso no puede ser una fecha futura");
            }
            
            // Validar que la fecha no sea anterior a 1900
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            if (cal.get(Calendar.YEAR) < 1900) {
                throw new IllegalArgumentException("La fecha de ingreso no puede ser anterior al año 1900");
            }
            
            return fecha;
        } catch (ParseException e) {
            // Esta excepción se lanzará si la fecha no es válida (como 31/04/2023)
            throw new IllegalArgumentException("La fecha ingresada no es válida (verifique días del mes)");
        }
    }
    
    /**
     * Método estático para validar que el salario sea un valor positivo
     * @param salario Salario a validar
     * @throws IllegalArgumentException Si el salario es negativo o cero
     */
    public static void validarSalario(double salario) throws IllegalArgumentException {
        if (salario <= 0) {
            throw new IllegalArgumentException("El salario debe ser un valor positivo");
        }
    }
    
    /**
     * Método estático para validar que los nombres y apellidos no estén vacíos
     * @param texto Texto a validar
     * @param campo Nombre del campo (para el mensaje de error)
     * @throws IllegalArgumentException Si el texto está vacío
     */
    public static void validarTexto(String texto, String campo) throws IllegalArgumentException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " no puede estar vacío");
        }
        
        if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            throw new IllegalArgumentException("El campo " + campo + " solo debe contener letras");
        }
    }

    // Getters y Setters
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        calcularVacaciones();
    }

    public int getDiasVacaciones() {
        return diasVacaciones;
    }

    public int getAniosServicio() {
        return aniosServicio;
    }
}
