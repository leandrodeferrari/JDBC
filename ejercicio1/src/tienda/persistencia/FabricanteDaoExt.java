package tienda.persistencia;

import java.sql.*;
import tienda.entidades.Fabricante;

public final class FabricanteDaoExt extends Dao {

    public void guardarFabricante(Fabricante fabricante) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            if (fabricante == null) {
                throw new NullPointerException("No ha indicado el fabricante");
            } else {
                conectarBaseDeDatos();
                String sql = "INSERT INTO " + "tienda.fabricante" + " (codigo, nombre) VALUES ('" + fabricante.getCodigo() + "', '" + fabricante.getNombre() + "')";
                insertarModificarEliminar(sql);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            desconectarBaseDeDatos();
        }
        // ver lo del codigo de fabricante.
    }

    public Fabricante buscarFabricantePorCodigo(int codigoFabricante) throws ClassNotFoundException, SQLException {

        try {
            String sql = "SELECT * FROM tienda.fabricante WHERE codigo = " + codigoFabricante;
            consultarBaseDeDatos(sql);
            Fabricante fabricante = null;
            while(resultado.next()){
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt("codigo"));
                fabricante.setNombre(resultado.getString("nombre"));
            }
            return fabricante;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            desconectarBaseDeDatos();
            // No estoy seguro si es necesario desconectar la base de datos
        }
    }

}
