package estancias.persistencia;

import java.sql.*;

public class Dao {

    protected Connection conexion;
    protected Statement sentencia;
    protected ResultSet resultado;
    protected PreparedStatement sentenciaPreparada;

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE = "estancias_exterior";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    protected void conectarBaseDeDatos() throws ClassNotFoundException, SQLException {

        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false&useTimezone&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            conexion = DriverManager.getConnection(urlBaseDeDatos, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

    protected void desconectarBaseDeDatos() throws SQLException {

        try {
            if (resultado != null) {
                resultado.close();
            }

            if(sentenciaPreparada != null){
                sentenciaPreparada.close();
            }
            
            if (sentencia != null) {
                sentencia.close();
            }

            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

    protected void insertarModificarEliminar(String sql) throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    protected ResultSet consultarBaseDeDatos(String sql) throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return resultado;
        
    }

}
