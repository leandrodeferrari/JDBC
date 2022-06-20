package estancias.persistencia;

import java.sql.*;

public class FamiliaDaoExt extends Dao {

    private PreparedStatement sentenciaPreparada;

    private final String SQL_SELECT_CON_HIJOS_Y_EDAD_MAXIMA = "SELECT * FROM estancias_exterior.familias WHERE num_hijos >= ? AND edad_maxima < ?";
    private final String SQL_SELECT_CON_HOTMAIL = "SELECT * FROM estancias_exterior.familias WHERE email LIKE '%hotmail%'";

    @Override
    public void desconectarBaseDeDatos() throws SQLException {

        super.desconectarBaseDeDatos();
        try {
            if (sentenciaPreparada != null) {
                sentenciaPreparada.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void consultarFamiliasConEdadMaximaMenorQue10AdemasDeNumeroHijosMayorIgualQue3() throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            sentenciaPreparada = conexion.prepareStatement(SQL_SELECT_CON_HIJOS_Y_EDAD_MAXIMA);
            sentenciaPreparada.setInt(1, 3);
            sentenciaPreparada.setInt(2, 10);
            resultado = sentenciaPreparada.executeQuery();
            while (resultado.next()) {
                System.out.println(resultado.getInt("id_familia") + " " + resultado.getString("nombre") + " "
                        + resultado.getInt("edad_minima") + " " + resultado.getInt("edad_maxima") + " "
                        + resultado.getInt("num_hijos") + " " + resultado.getString("email") + " "
                        + resultado.getInt("id_casa_familia"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarFamiliasCuyoEmailSeaHotmail() throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            consultarBaseDeDatos(SQL_SELECT_CON_HOTMAIL);
            while (resultado.next()) {
                System.out.println(resultado.getInt("id_familia") + " " + resultado.getString("nombre") + " "
                        + resultado.getInt("edad_minima") + " " + resultado.getInt("edad_maxima") + " "
                        + resultado.getInt("num_hijos") + " " + resultado.getString("email") + " "
                        + resultado.getInt("id_casa_familia"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

}
