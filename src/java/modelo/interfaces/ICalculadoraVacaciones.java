package modelo.interfaces;

import java.util.Date;

/**
 * Interfaz que define el contrato para calcular días de vacaciones
 * @author Estudiante
 * @version 1.0
 * @since 26/04/2023
 */
public interface ICalculadoraVacaciones {
    
    /**
     * Calcula los días de vacaciones basado en la fecha de ingreso
     * @param fechaIngreso Fecha de ingreso del empleado
     * @return Número de días de vacaciones que corresponden
     */
    int calcularDiasVacaciones(Date fechaIngreso);
    
    /**
     * Calcula los años de servicio basado en la fecha de ingreso
     * @param fechaIngreso Fecha de ingreso del empleado
     * @return Número de años de servicio
     */
    int calcularAniosServicio(Date fechaIngreso);
}
