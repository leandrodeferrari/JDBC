package estancias.persistencia.excepciones;

public class DaoExcepcion extends RuntimeException{

    public DaoExcepcion(String mensaje) {
        super(mensaje);
    }
    
}
