package modelo.factory;

import modelo.implementaciones.CalculadoraVacacionesEjecutiva;
import modelo.implementaciones.CalculadoraVacacionesEstandar;
import modelo.interfaces.ICalculadoraVacaciones;

/**
 * Factory para crear instancias de calculadoras de vacaciones
 * @author Estudiante
 * @version 1.0
 * @since 26/04/2023
 */
public class CalculadoraVacacionesFactory {
    
    /**
     * Tipos de calculadoras disponibles
     */
    public enum TipoCalculadora {
        ESTANDAR,
        EJECUTIVA
    }
    
    /**
     * Crea una instancia de calculadora de vacaciones según el tipo especificado
     * @param tipo Tipo de calculadora a crear
     * @return Instancia de la calculadora de vacaciones
     */
    public static ICalculadoraVacaciones crearCalculadora(TipoCalculadora tipo) {
        switch (tipo) {
            case EJECUTIVA:
                return new CalculadoraVacacionesEjecutiva();
            case ESTANDAR:
            default:
                return new CalculadoraVacacionesEstandar();
        }
    }
}
