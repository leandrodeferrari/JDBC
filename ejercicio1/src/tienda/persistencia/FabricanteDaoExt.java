package tienda.persistencia;

import java.sql.*;
import tienda.entidades.Fabricante;

public final class FabricanteDaoExt extends Dao {

    private PreparedStatement sentenciaPreparada;

    private final String SQL_INSERT = "INSERT INTO tienda.fabricante(nombre) VALUES(?)";
    private final String SQL_SELECT = "SELECT * FROM tienda.fabricante WHERE codigo = ?";

    @Override
    public void desconectarBaseDeDatos() throws SQLException{
        
        super.desconectarBaseDeDatos();
        try {
            if (sentenciaPreparada != null) {
                sentenciaPreparada.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    public void guardarFabricante(Fabricante fabricante) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            if (fabricante == null) {
                throw new NullPointerException("No ha indicado el fabricante");
            } else {
                conectarBaseDeDatos();
                sentenciaPreparada = conexion.prepareStatement(SQL_INSERT);
                sentenciaPreparada.setString(1, fabricante.getNombre());
                sentenciaPreparada.executeUpdate();
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
            resultado = sentenciaPreparada.executeQuery();
            if (resultado == null) {
                throw new NullPointerException("Lo siento, no se encontró ese fabricante");
            } else {
                while (resultado.next()) {
                    fabricante.setCodigo(resultado.getInt("codigo"));
                    fabricante.setNombre(resultado.getString("nombre"));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

        return fabricante;

    }

}
