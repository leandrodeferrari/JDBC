package tienda.servicios;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDaoExt;
import tienda.servicios.excepciones.ProductoExcepcion;

public class ProductoServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private final ProductoDaoExt productoDao;

    public ProductoServicio() {
        this.productoDao = new ProductoDaoExt();
    }

    public Producto crearProducto() {

        int codigoFabricante;
        String nombre;
        double precio;

        System.out.println("Ingrese el nombre del producto:");
        nombre = leer.next();

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ProductoExcepcion("No ha ingresado un nombre");
        }

        try {
            System.out.println("Ingrese el precio del producto:");
            precio = leer.nextDouble();
        } catch (ProductoExcepcion ex) {
            throw new ProductoExcepcion("No ha ingresado el precio");
        }

        try {
            System.out.println("Ingrese el código del fabricante, del producto:");
            codigoFabricante = leer.nextInt();
        } catch (ProductoExcepcion ex) {
            throw new ProductoExcepcion("No ha ingresado el código del fabricante, del producto, o no existe un fabricante, con ese código");
        }

        return new Producto(nombre, precio, codigoFabricante);

    }

    public void ingresarProducto(Producto producto) throws NullPointerException, ClassNotFoundException, SQLException {

        if (producto != null) {
            productoDao.guardarProducto(producto);
        } else if (producto == null) {
            throw new ProductoExcepcion("Ha ingresado un producto inválido");
        }

    }

    public void modificarTodosLosDatosDeUnProducto() throws ClassNotFoundException, SQLException {

        int codigo, codigoFabricante;
        String nombre;
        double precio;

        try {
            System.out.println("Ingrese el código del producto que desea modificar:");
            codigo = leer.nextInt();
        } catch (ProductoExcepcion ex) {
            throw new ProductoExcepcion("No ha ingresado el código del producto");
        }

        System.out.println("Ingrese el nuevo nombre de su producto:");
        nombre = leer.next();

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ProductoExcepcion("No ha ingresado un nombre");
        }

        try {
            System.out.println("ingrese el nuevo precio de su producto:");
            precio = leer.nextDouble();
        } catch (ProductoExcepcion ex) {
            throw new ProductoExcepcion("No ha ingresado el nuevo precio del producto");
        }

        try {
            System.out.println("Ingrese el nuevo codigo de fabricante, de su producto:");
            codigoFabricante = leer.nextInt();
        } catch (ProductoExcepcion ex) {
            throw new ProductoExcepcion("No ha ingresado el nuevo código del fabricante, del producto");
        }

        Producto producto = new Producto(codigo, nombre, precio, codigoFabricante);

        productoDao.modificarProducto(producto);

    }

    public void listarNombresDeProductos() throws ClassNotFoundException, SQLException {

        List<Producto> productos = productoDao.consultarProductos();
        if (!productos.isEmpty()) {
            productos.forEach((producto) -> {
                System.out.println(producto.getNombre());
            });
        } else {
            System.out.println("No hay productos para listar");
        }

    }

    public void listarNombrePrecioDeProductos() throws ClassNotFoundException, SQLException {

        List<Producto> productos = productoDao.consultarProductos();
        if (!productos.isEmpty()) {
            productos.forEach((producto) -> {
                System.out.println(producto.getNombre() + " " + producto.getPrecio());
            });
        } else {
            System.out.println("No hay productos para listar");
        }

    }

    public void listarProductosEntrePrecio120y202() throws ClassNotFoundException, SQLException {

        List<Producto> productos = productoDao.consultarProductosEntrePrecios(102, 202);

        if (!productos.isEmpty()) {
            productos.forEach((producto) -> {
                System.out.println(producto.toString());
            });
        } else {
            System.out.println("No hay productos entre 120 y 202 pesos");
        }

    }

    public void listarProductosPortatiles() throws ClassNotFoundException, SQLException {

        List<Producto> productos = productoDao.consultarProductosPortatiles();

        if (!productos.isEmpty()) {
            productos.forEach((producto) -> {
                System.out.println(producto.toString());
            });
        } else {
            System.out.println("No hay productos portátiles");
        }

    }

    public void listarProductosMasBaratos() throws ClassNotFoundException, SQLException {

        List<Producto> productos = productoDao.consultarLosProductosMasBaratos();

        if (!productos.isEmpty()) {
            productos.forEach((producto) -> {
                System.out.println(producto.toString());
            });

        } else {
            System.out.println("No es posible encontrar el/los productos más baratos");
        }

    }

}
