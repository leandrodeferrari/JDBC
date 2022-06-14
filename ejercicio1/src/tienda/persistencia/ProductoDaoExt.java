package tienda.persistencia;

import java.sql.SQLException;
import java.util.Scanner;
import tienda.entidades.Producto;

public final class ProductoDaoExt extends Dao {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void guardarProducto(Producto producto) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            if (producto == null) {
                throw new NullPointerException("No ha indicado un producto");
            } else {
                conectarBaseDeDatos();
                String sql = "INSER INTO " + "tienda.producto" + " (codigo, nombre, precio, codigo_fabricante) "
                        + "VALUES ('" + producto.getCodigo() + "', '" + producto.getNombre() + "', '"
                        + producto.getPrecio() + "', '" + producto.getCodigoFabricante() + "')";
                insertarModificarEliminar(sql);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            desconectarBaseDeDatos();
        }
        // Ver como hacer lo del codigo producto y codigo Fabricante
    }

    public Producto buscarProductoPorCodigo(int codigoProducto) throws ClassNotFoundException, SQLException {

        try {
            String sql = "SELECT * FROM tienda.producto WHERE codigo = " + codigoProducto;
            consultarBaseDeDatos(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
            }
            return producto;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            desconectarBaseDeDatos();
            // No estoy seguro si tengo que volver a desconectar la base de datos
        }

    }

    public void modificarProducto(int codigoProducto) throws ClassNotFoundException, SQLException {

        int codigoFabricante;
        double precio;
        String nombre;

        System.out.println("Ingrese el nuevo nombre, del producto que desea modificar");
        nombre = leer.next();

        System.out.println("Ingrese el nuevo precio, del producto que desea modificar");
        precio = leer.nextDouble();

        System.out.println("Ingrese el nuevo código de fabricante, del producto que desea modificar");
        codigoFabricante = leer.nextInt();

        try {
            conectarBaseDeDatos();
            String sql = "UPDATE " + "tienda.producto" + " SET " + "nombre = '" + nombre
                    + "', precio = '" + precio + "', codigo_fabricante = '" + codigoFabricante
                    + "' WHERE codigo = " + codigoProducto;
            insertarModificarEliminar(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            desconectarBaseDeDatos();
        }
        // Ver lo del codigo producto (en caso de no existir producto con ese codigo).
    }

    public void consultarProductos(String camposSql) throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            String sql = "SELECT " + camposSql + " FROM " + "tienda.producto";
            consultarBaseDeDatos(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarProductosSegunCondicion(String camposSql, String condicion) throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            String sql = "SELECT " + camposSql + " FROM tienda.producto WHERE " + condicion;
            consultarBaseDeDatos(sql);
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            desconectarBaseDeDatos();
        }

    }

}
