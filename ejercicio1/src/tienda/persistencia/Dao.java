package tienda.persistencia;

import java.sql.*;

public abstract class Dao {

    protected Connection conexion = null;
    protected Statement sentencia = null;
    protected ResultSet resultado = null;

    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    protected void conectarBaseDeDatos() throws ClassNotFoundException, SQLException {

        try {
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + DATABASE + "?zeroDateTimeBehavior=convertToNull [root on Default schema]";
            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }

    }

    protected void desconectarBaseDeDatos() throws SQLException{
        
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
            throw ex;
        }
        
    }    
    
    protected void insertarModificarEliminar(String sql) throws ClassNotFoundException, SQLException{
        
        try {
            conectarBaseDeDatos();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            // conexion.rollback(); AVERIGUAR COMO USAR ESTO
            // SET autocommit = 1;
            // COMMIT;
            throw ex;
        } finally {
            desconectarBaseDeDatos();
        }
        
    }
 
    protected void consultarBaseDeDatos(String sql) throws ClassNotFoundException, SQLException{
        
        try {
            conectarBaseDeDatos();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException ex) {
        } finally {
            desconectarBaseDeDatos();
        }
        
    }
    
}
