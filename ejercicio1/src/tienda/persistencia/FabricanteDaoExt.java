package tienda.persistencia;

import java.sql.*;
import tienda.entidades.Fabricante;

public final class FabricanteDaoExt extends Dao {
    
    private PreparedStatement sentenciaPreparada;

    private final String SQL_INSERT = "INSERT INTO tienda.fabricante (codigo, nombre) VALUES (codigo = ?, nombre = ?)";
    private final String SQL_SELECT = "SELECT * FROM tienda.fabricante WHERE codigo = ?";
    
    public void guardarFabricante(Fabricante fabricante) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            if (fabricante == null) {
                throw new NullPointerException("No ha indicado el fabricante");
            } else {
                conectarBaseDeDatos();
                sentenciaPreparada = conexion.prepareStatement(SQL_INSERT);
                sentenciaPreparada.setInt(1, fabricante.getCodigo());
                sentenciaPreparada.setString(2, fabricante.getNombre());
                insertarModificarEliminar(SQL_INSERT);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }
    }

    public Fabricante buscarFabricantePorCodigo(int codigoFabricante) throws ClassNotFoundException, SQLException {

        Fabricante fabricante = new Fabricante();
        
        try {
            conectarBaseDeDatos();
            sentenciaPreparada = conexion.prepareStatement(SQL_SELECT);
            sentenciaPreparada.setInt(1, codigoFabricante);
            consultarBaseDeDatos(SQL_SELECT);
            while (resultado.next()) {
                fabricante.setCodigo(resultado.getInt("codigo"));
                fabricante.setNombre(resultado.getString("nombre"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }
        
        return fabricante;
        
    }

}
