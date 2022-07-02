package estancias.servicios;

import estancias.entidades.Cliente;
import estancias.persistencia.ClienteDaoExt;
import java.sql.SQLException;
import java.util.List;

public class ClienteServicio {
    
    private final ClienteDaoExt clienteDao;

    public ClienteServicio() {
        this.clienteDao = new ClienteDaoExt();
    }
    
    public void listarClientesQueRealizaronEstanciaAlgunaVez() throws SQLException, ClassNotFoundException{
        
        List<Cliente> clientes = clienteDao.consultarClientesQueRealizaronEstanciaAlgunaVez();
        
        if(!clientes.isEmpty()){
            clientes.forEach((cliente) -> {
                System.out.println(cliente.toString());
            });
        } else {
            System.out.println("No hay clientes con estancia");
        }
        
    }
    
}
