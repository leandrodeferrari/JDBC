package estancias.servicios;

import estancias.persistencia.ClienteDaoExt;
import java.sql.SQLException;

public class ServicioCliente {
    
    private final ClienteDaoExt clienteDao;

    public ServicioCliente() {
        this.clienteDao = new ClienteDaoExt();
    }
    
    public void listarClientesQueRealizaronEstanciaAlgunaVez() throws SQLException, ClassNotFoundException{
        
        clienteDao.consultarClientesQueRealizaronEstanciaAlgunaVez();
        
    }
    
}
