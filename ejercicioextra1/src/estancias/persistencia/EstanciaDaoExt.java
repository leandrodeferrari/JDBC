package estancias.persistencia;

import java.sql.*;

public class EstanciaDaoExt extends Dao {

    private PreparedStatement sentenciaPreparada;
    
    private final String SQL_SELECT_ESTANCIAS_RESERVADAS = "SELECT estancias.*, clientes.nombre, "
            + "clientes.pais, clientes.ciudad, casas.* FROM estancias_exterior.clientes "
            + "INNER JOIN estancias_exterior.estancias ON clientes.id_cliente = estancias.id_cliente "
            + "INNER JOIN estancias_exterior.casas ON estancias.id_casa = casas.id_casa";
    
    // 10. Insertar nuevos datos en la tabla estancias verificando la disponibilidad de las fechas.
    
    private final String SQL_INSERT = "INSER INTO "; 
    
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

    public void consultarEstanciasSegunCasaAdemasDeCliente() throws SQLException, ClassNotFoundException{
        
        try {
            consultarBaseDeDatos(SQL_SELECT_ESTANCIAS_RESERVADAS);
            while(resultado.next()){
                System.out.println("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }
        
    }
    
    public void insertarEstanciaVerificandoFechas(){
        
    }
    
}
