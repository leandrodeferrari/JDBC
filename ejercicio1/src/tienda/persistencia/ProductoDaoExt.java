package tienda.persistencia;

import java.sql.*;
import tienda.entidades.Producto;

public final class ProductoDaoExt extends Dao {

    private final String SQL_INSERT = "INSERT INTO tienda.producto (codigo, nombre, precio, codigo_fabricante) VALUES (codigo = ?, nombre = ?, precio = ?, codigo_fabricante = ?)";
    private final String SQL_SELECT = "SELECT * FROM tienda.producto WHERE codigo = ?";
    private final String SQL_UPDATE = "UPDATE tienda.producto SET nombre = ?, precio = ?, codigo_fabricante = ? WHERE codigo = ?";

    private PreparedStatement sentenciaPreparada;

    public void guardarProducto(Producto producto) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            if (producto == null) {
                throw new NullPointerException("No ha indicado un producto");
            } else {
                conectarBaseDeDatos();
                sentenciaPreparada = conexion.prepareStatement(SQL_INSERT);
                sentenciaPreparada.setInt(1, producto.getCodigo());
                sentenciaPreparada.setString(2, producto.getNombre());
                sentenciaPreparada.setDouble(3, producto.getPrecio());
                sentenciaPreparada.setInt(4, producto.getCodigoFabricante());
                insertarModificarEliminar(SQL_INSERT);
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
            consultarBaseDeDatos(SQL_SELECT);
            while (resultado.next()) {
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
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
                insertarModificarEliminar(SQL_UPDATE);
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace(System.out);
            } finally {
                desconectarBaseDeDatos();
            }

        }

    }

    
//    public void consultarProductos(String camposSql) throws ClassNotFoundException, SQLException {
//
//        try {
//            conectarBaseDeDatos();
//            String sql = "SELECT " + camposSql + " FROM " + "tienda.producto";
//            consultarBaseDeDatos(sql);
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw ex;
//        } 
////        finally {
////            desconectarBaseDeDatos();
////        }
//
//    }
//    public void consultarProductosSegunCondicion(String camposSql, String condicion) throws ClassNotFoundException, SQLException {
//
//        try {
//            conectarBaseDeDatos();
//            String sql = "SELECT " + camposSql + " FROM tienda.producto WHERE " + condicion;
//            consultarBaseDeDatos(sql);
//        } catch (ClassNotFoundException | SQLException e) {
//        } 
////        finally {
////            desconectarBaseDeDatos();
////        }
//
//    }
}
