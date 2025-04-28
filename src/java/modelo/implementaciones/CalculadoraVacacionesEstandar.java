package modelo.implementaciones;

import java.util.Calendar;
import java.util.Date;
import modelo.interfaces.ICalculadoraVacaciones;

/**
 * Implementación estándar de la calculadora de vacaciones
 * @author Estudiante
 * @version 1.0
 * @since 26/04/2023
 */
public class CalculadoraVacacionesEstandar implements ICalculadoraVacaciones {

    /**
     * Calcula los días de vacaciones según la política estándar:
     * - Entre 1-3 años: 10 días
     * - Entre 3-5 años: 15 días
     * - Mayor a 5 años: 21 días
     * 
     * @param fechaIngreso Fecha de ingreso del empleado
     * @return Número de días de vacaciones que corresponden
     */
    @Override
    public int calcularDiasVacaciones(Date fechaIngreso) {
        int aniosServicio = calcularAniosServicio(fechaIngreso);
        
        if (aniosServicio >= 1 && aniosServicio <= 3) {
            return 10;
        } else if (aniosServicio > 3 && aniosServicio <= 5) {
            return 15;
        } else if (aniosServicio > 5) {
            return 21;
        } else {
            return 0; // Menos de un año de servicio
        }
    }

    /**
     * Calcula los años de servicio basado en la fecha de ingreso
     * @param fechaIngreso Fecha de ingreso del empleado
     * @return Número de años de servicio
     */
    @Override
    public int calcularAniosServicio(Date fechaIngreso) {
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaIng = Calendar.getInstance();
        fechaIng.setTime(fechaIngreso);
        
        int aniosServicio = fechaActual.get(Calendar.YEAR) - fechaIng.get(Calendar.YEAR);
        
        // Ajuste si aún no ha cumplido el aniversario en este año
        if (fechaActual.get(Calendar.MONTH) < fechaIng.get(Calendar.MONTH) || 
            (fechaActual.get(Calendar.MONTH) == fechaIng.get(Calendar.MONTH) && 
             fechaActual.get(Calendar.DAY_OF_MONTH) < fechaIng.get(Calendar.DAY_OF_MONTH))) {
            aniosServicio--;
        }
        
        // Si los años de servicio son negativos (fecha futura), se establece en 0
        if (aniosServicio < 0) {
            aniosServicio = 0;
        }
        
        return aniosServicio;
    }
}
