package modelo.implementaciones;

import java.util.Calendar;
import java.util.Date;
import modelo.interfaces.ICalculadoraVacaciones;

/**
 * Implementación de calculadora de vacaciones para ejecutivos
 * con una política más generosa de vacaciones
 * @author Estudiante
 * @version 1.0
 * @since 26/04/2023
 */
public class CalculadoraVacacionesEjecutiva implements ICalculadoraVacaciones {

    /**
     * Calcula los días de vacaciones según la política ejecutiva:
     * - Entre 1-2 años: 15 días
     * - Entre 2-4 años: 20 días
     * - Mayor a 4 años: 30 días
     * 
     * @param fechaIngreso Fecha de ingreso del empleado
     * @return Número de días de vacaciones que corresponden
     */
    @Override
    public int calcularDiasVacaciones(Date fechaIngreso) {
        int aniosServicio = calcularAniosServicio(fechaIngreso);
        
        if (aniosServicio >= 1 && aniosServicio <= 2) {
            return 15;
        } else if (aniosServicio > 2 && aniosServicio <= 4) {
            return 20;
        } else if (aniosServicio > 4) {
            return 30;
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
