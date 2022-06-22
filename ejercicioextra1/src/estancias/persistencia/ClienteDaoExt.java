package estancias.persistencia;

import java.sql.*;

public class ClienteDaoExt extends Dao {

    private PreparedStatement sentenciaPreparada;

    // Listar los clientes que en algún momento realizaron una estancia 
    // (y la descripción de la casa donde la realizaron).
    
    private final String SQL_SELECT_CLIENTES_CONESTANCIA = "SELECT clientes.*, estancias.id_casa FROM estancias_exterior.clientes INNER JOIN estancias_exterior.estancias ON clientes.id_cliente = estancias.id_cliente";
    
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

    public void consultarClientesQueRealizaronEstanciaAlgunaVez() throws SQLException, ClassNotFoundException{
        
        try {
            consultarBaseDeDatos(SQL_SELECT_CLIENTES_CONESTANCIA);
            while(resultado.next()){
                System.out.println(resultado.getInt("id_cliente") + " " + resultado.getString("nombre") 
                        + " " + resultado.getString("calle") + " " + resultado.getInt("numero") 
                        + " " + resultado.getString("codigo_postal") + " " + resultado.getString("ciudad") 
                        + " " + resultado.getString("pais") + " " + resultado.getString("email"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }
        
    }
    
}
