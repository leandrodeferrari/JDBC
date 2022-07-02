package estancias.persistencia.excepciones;

public class CasaDaoExcepcion extends RuntimeException{

    public CasaDaoExcepcion(String mensaje) {
        super(mensaje);
    }
    
}
