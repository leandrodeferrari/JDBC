package tienda.servicios.excepciones;

public class ProductoExcepcion extends RuntimeException{

    public ProductoExcepcion(String mensaje) {
        super(mensaje);
    }
    
}
