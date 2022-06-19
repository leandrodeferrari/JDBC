package tienda.persistencia;

import java.sql.*;
import tienda.entidades.Producto;

public final class ProductoDaoExt extends Dao {

    private final String SQL_INSERT = "INSERT INTO tienda.producto(nombre, precio, codigo_fabricante) VALUES(?, ?, ?)";
    private final String SQL_SELECT = "SELECT * FROM tienda.producto WHERE codigo = ?";
    private final String SQL_SELECT_NOMBRE_LIKE = "SELECT * FROM tienda.producto WHERE nombre LIKE '%Portátil%'";
    private final String SQL_SELECT_NOMBRES = "SELECT nombre FROM tienda.producto";
    private final String SQL_SELECT_NOMBRES_PRECIO = "SELECT nombre, precio FROM tienda.producto";
    private final String SQL_SELECT_ENTRE_PRECIO = "SELECT * FROM tienda.producto WHERE precio BETWEEN ? AND ?";
    private final String SQL_SELECT_MAS_BARATO = "SELECT nombre, precio FROM tienda.producto WHERE precio = (SELECT MIN(precio) FROM tienda.producto)";
    private final String SQL_UPDATE = "UPDATE tienda.producto SET nombre = ?, precio = ?, codigo_fabricante = ? WHERE codigo = ?";

    private PreparedStatement sentenciaPreparada;

    public void guardarProducto(Producto producto) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            if (producto == null) {
                throw new NullPointerException("No ha indicado un producto");
            } else {
                conectarBaseDeDatos();
                sentenciaPreparada = conexion.prepareStatement(SQL_INSERT);
                sentenciaPreparada.setString(1, producto.getNombre());
                sentenciaPreparada.setDouble(2, producto.getPrecio());
                sentenciaPreparada.setInt(3, producto.getCodigoFabricante());
                sentenciaPreparada.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public Producto buscarProductoPorCodigo(int codigoProducto) throws ClassNotFoundException, SQLException {

        Producto producto = new Producto();

        try {
            conectarBaseDeDatos();
            sentenciaPreparada = conexion.prepareStatement(SQL_SELECT);
            sentenciaPreparada.setInt(1, codigoProducto);
            resultado = sentenciaPreparada.executeQuery();
            if (resultado == null) {
                throw new NullPointerException("Lo siento, no se encontró ese producto");
            } else {
                while (resultado.next()) {
                    producto.setCodigo(resultado.getInt("codigo"));
                    producto.setNombre(resultado.getString("nombre"));
                    producto.setPrecio(resultado.getDouble("precio"));
                    producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

        return producto;

    }

    public void modificarProducto(int codigoProductoParaBuscar, Producto producto) throws ClassNotFoundException, SQLException {

        Producto productoEncontrado = buscarProductoPorCodigo(codigoProductoParaBuscar);

        if (productoEncontrado.getCodigo() != codigoProductoParaBuscar) {
            System.out.println("Lo siento, no hay producto con ese código");
        } else {

            try {
                conectarBaseDeDatos();
                sentenciaPreparada = conexion.prepareStatement(SQL_UPDATE);
                sentenciaPreparada.setString(1, producto.getNombre());
                sentenciaPreparada.setDouble(2, producto.getPrecio());
                sentenciaPreparada.setInt(3, producto.getCodigoFabricante());
                sentenciaPreparada.setInt(4, codigoProductoParaBuscar);
                sentenciaPreparada.executeUpdate();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace(System.out);
            } finally {
                desconectarBaseDeDatos();
            }

        }

    }

    public void consultarNombreDeProductos() throws SQLException {

        try {
            conectarBaseDeDatos();
            consultarBaseDeDatos(SQL_SELECT_NOMBRES);
            while (resultado.next()) {
                System.out.println(resultado.getString("nombre"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarNombrePrecioDeProductos() throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            consultarBaseDeDatos(SQL_SELECT_NOMBRES_PRECIO);
            while (resultado.next()) {
                System.out.println(resultado.getString("nombre") + " " + resultado.getDouble("precio"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarProductosEntrePrecios(double precio1, double precio2) throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            sentenciaPreparada = conexion.prepareStatement(SQL_SELECT_ENTRE_PRECIO);
            sentenciaPreparada.setDouble(1, precio1);
            sentenciaPreparada.setDouble(2, precio2);
            resultado = sentenciaPreparada.executeQuery();
            while (resultado.next()) {
                System.out.println(resultado.getInt("codigo") + " " + resultado.getString("nombre") + " " + resultado.getDouble("precio") + " " + resultado.getInt("codigo_fabricante"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarProductosPortatiles() throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            consultarBaseDeDatos(SQL_SELECT_NOMBRE_LIKE);
            while (resultado.next()) {
                System.out.println(resultado.getInt("codigo") + " " + resultado.getString("nombre") + " " + resultado.getDouble("precio") + " " + resultado.getInt("codigo_fabricante"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarNombrePrecioDelProductoMasBarato() throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            consultarBaseDeDatos(SQL_SELECT_MAS_BARATO);
            while (resultado.next()) {
                System.out.println(resultado.getString("nombre") + " " + resultado.getDouble("precio"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

}
