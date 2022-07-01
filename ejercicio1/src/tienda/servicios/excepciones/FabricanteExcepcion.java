package tienda.servicios.excepciones;

public class FabricanteExcepcion extends RuntimeException{

    public FabricanteExcepcion(String mensaje) {
        super(mensaje);
    }
    
}
