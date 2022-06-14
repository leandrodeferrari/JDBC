
package tienda.servicios;

import java.sql.SQLException;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDaoExt;

public class ServicioProducto {
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    private final ProductoDaoExt productoDao;

    public ServicioProducto() {
        this.productoDao = new ProductoDaoExt();
    }
    
    public Producto crearProducto() throws ClassNotFoundException, SQLException{
        
        int codigo, codigoFabricante;
        String nombre;
        double precio;
        
        System.out.println("Ingrese el código del producto");
        codigo = leer.nextInt();
        
        System.out.println("Ingrese el nombre del producto");
        nombre = leer.next();
        
        System.out.println("Ingrese el precio del producto");
        precio = leer.nextDouble();
        
        System.out.println("Ingrese el código del fabricante, del producto");
        codigoFabricante = leer.nextInt();
        
        Producto producto = new Producto(codigo, nombre, precio, codigoFabricante);
        
        if(productoDao.buscarProductoPorCodigo(producto.getCodigo()) != null){
            throw new NullPointerException("Lo siento, ya existe un producto con ese código");
        }
        
        return producto;
        // Tengo que crear una clase expeción para Producto
        // Me falta validar los atributos del producto
    }
    
    public void listarNombresDeProductos() throws ClassNotFoundException, SQLException{
        productoDao.consultarProductos("nombre");
    }
    
    public void listarProductos() throws ClassNotFoundException, SQLException{
        productoDao.consultarProductos("*");
    }
    
    public void listarProductosEntrePrecio120y202() throws ClassNotFoundException, SQLException{
        productoDao.consultarProductosSegunCondicion("*", "precio BETWEEN 102 AND 202");
    }
    
    public void listarProductosPortatiles() throws ClassNotFoundException, SQLException{
        productoDao.consultarProductosSegunCondicion("*", "nombre LIKE ´%Portátil%´");
    }
    
    public void listarElProductoMasBarato() throws ClassNotFoundException, SQLException{
        productoDao.consultarProductosSegunCondicion("nombre, precio", "precio = "
                + "(SELECT MIN(precio) FROM tienda.producto)");
    }
    
}
