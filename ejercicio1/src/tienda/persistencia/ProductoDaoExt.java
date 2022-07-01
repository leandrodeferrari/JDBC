package tienda.persistencia;

import java.sql.*;
import java.util.*;
import tienda.entidades.Producto;

public final class ProductoDaoExt extends Dao {

    public void guardarProducto(Producto producto) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            if (producto != null) {
                String sql = "INSERT INTO producto(nombre, precio, codigo_fabricante) "
                        + "VALUES('" + producto.getNombre() + "', " + producto.getPrecio() + ", "
                        + producto.getCodigoFabricante() + ")";
                insertarModificarEliminar(sql);
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void modificarProducto(Producto producto) throws ClassNotFoundException, SQLException {

        Producto productoEncontrado = buscarProductoPorCodigo(producto.getCodigo());

        if (productoEncontrado != null) {
            String sql = "UPDATE producto SET nombre = '" + producto.getNombre()
                    + "', precio = " + producto.getPrecio()
                    + ", codigo_fabricante = " + producto.getCodigoFabricante()
                    + " WHERE codigo = " + producto.getCodigo();
            insertarModificarEliminar(sql);
        } else if (productoEncontrado == null) {
            System.out.println("Lo siento, no hay producto con ese código");
        }

    }

    public Producto buscarProductoPorCodigo(int codigoProducto) throws ClassNotFoundException, SQLException {

        Producto producto = new Producto();
        String sql = "SELECT * FROM producto WHERE codigo = " + codigoProducto;

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
            }
        }

        return producto;

    }

    public List<Producto> consultarProductos() throws ClassNotFoundException, SQLException {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                productos.add(producto);
            }
        }

        return productos;

    }

    public List<Producto> consultarProductosEntrePrecios(double precio1, double precio2) throws ClassNotFoundException, SQLException {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE precio BETWEEN " + precio1 + " AND " + precio2;

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                productos.add(producto);
            }

        }

        return productos;

    }

    public List<Producto> consultarProductosPortatiles() throws ClassNotFoundException, SQLException {

        List<Producto> productos = new ArrayList<>();
        String textoPortatilSql = "'%Portátil%'";
        String sql = "SELECT * FROM tienda.producto WHERE nombre LIKE " + textoPortatilSql;

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                productos.add(producto);
            }

        }

        return productos;

    }

    public List<Producto> consultarLosProductosMasBaratos() throws ClassNotFoundException, SQLException {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE precio = (SELECT MIN(precio) FROM tienda.producto)";

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                productos.add(producto);
            }
        }

        return productos;

    }

}
