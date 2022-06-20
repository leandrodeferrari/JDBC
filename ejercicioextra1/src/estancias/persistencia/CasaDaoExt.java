package estancias.persistencia;

import java.sql.*;

public class CasaDaoExt extends Dao{
    
    private PreparedStatement sentenciaPreparada;
    
    
    
    @Override
    public void desconectarBaseDeDatos() throws SQLException{
        
        super.desconectarBaseDeDatos();
        try {
            if(sentenciaPreparada != null){
            sentenciaPreparada.close();
        }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
}
