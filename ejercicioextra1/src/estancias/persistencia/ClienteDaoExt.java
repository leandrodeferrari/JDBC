package estancias.persistencia;

import estancias.entidades.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoExt extends Dao {

    public List<Cliente> consultarClientesQueRealizaronEstanciaAlgunaVez() throws ClassNotFoundException, SQLException {

        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT clientes.*, estancias.id_casa FROM estancias_exterior.clientes "
                + "INNER JOIN estancias_exterior.estancias ON clientes.id_cliente = estancias.id_cliente";

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultado.getInt("id_cliente"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setCalle(resultado.getString("calle"));
                cliente.setNumero(resultado.getInt("numero"));
                cliente.setCodigoPostal(resultado.getString("codigo_postal"));
                cliente.setCiudad(resultado.getString("ciudad"));
                cliente.setPais(resultado.getString("pais"));
                cliente.setEmail(resultado.getString("email"));
                clientes.add(cliente);
            }
        }

        desconectarBaseDeDatos();

        return clientes;

    }

}
