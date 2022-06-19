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

    public Producto crearProducto() throws RuntimeException, ClassNotFoundException, SQLException {

        int codigoFabricante;
        String nombre;
        double precio;

        System.out.println("Ingrese el nombre del producto");
        nombre = leer.next();

        System.out.println("Ingrese el precio del producto");
        precio = leer.nextDouble();

        System.out.println("Ingrese el código del fabricante, del producto");
        codigoFabricante = leer.nextInt();

        Producto producto = new Producto(nombre, precio, codigoFabricante);

        if (nombre == null || nombre.trim().isEmpty()){
            throw new RuntimeException("No ha ingresado un nombre");
        }

        return producto;
        // Tengo que crear una clase expeción para Producto
        // Me falta validar los atributos del producto
    }

    public void ingresarProducto(Producto producto) throws ClassNotFoundException, SQLException {

        try {
            productoDao.guardarProducto(producto);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void modificarTodosLosDatosDeUnProducto() throws ClassNotFoundException, SQLException {

        int codigoParaBuscar, codigoFabricante;
        String nombre;
        double precio;

        System.out.println("Ingrese el código del producto que desea modificar");
        codigoParaBuscar = leer.nextInt();

        System.out.println("Ingrese el nuevo nombre de su producto");
        nombre = leer.next();

        System.out.println("ingrese el nuevo precio de su producto");
        precio = leer.nextDouble();

        System.out.println("Ingrese el nuevo codigo de fabricante, de su producto");
        codigoFabricante = leer.nextInt();

        Producto producto = new Producto(nombre, precio, codigoFabricante);

        productoDao.modificarProducto(codigoParaBuscar, producto);

    }

    public void listarNombresDeProductos() throws ClassNotFoundException, SQLException {
        productoDao.consultarNombreDeProductos();
    }

    public void listarNombrePrecioDeProductos() throws ClassNotFoundException, SQLException {
        productoDao.consultarNombrePrecioDeProductos();
    }

    public void listarProductosEntrePrecio120y202() throws ClassNotFoundException, SQLException {
        productoDao.consultarProductosEntrePrecios(102,202);
    }

    public void listarProductosPortatiles() throws ClassNotFoundException, SQLException {
        productoDao.consultarProductosPortatiles();
    }

    public void listarElProductoMasBarato() throws ClassNotFoundException, SQLException {
        productoDao.consultarNombrePrecioDelProductoMasBarato();
    }

}
