package estancias.persistencia;

import java.sql.*;

public class Dao {

    protected Connection conexion;
    protected Statement sentencia;
    protected ResultSet resultado;

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
            // conexion.rollback(); AVERIGUAR COMO USAR ESTO
            // SET autocommit = 1;
            // COMMIT;
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    protected void consultarBaseDeDatos(String sql) throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

}