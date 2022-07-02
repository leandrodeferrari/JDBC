package estancias.persistencia.excepciones;

public class ComercioDaoExcepcion extends RuntimeException{

    public ComercioDaoExcepcion(String mensaje) {
        super(mensaje);
    }
    
}
