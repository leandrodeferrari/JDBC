package estancias.persistencia.excepciones;

public class EstanciaDaoExcepcion extends RuntimeException{

    public EstanciaDaoExcepcion(String mensaje) {
        super(mensaje);
    }
    
}
