package tienda.persistencia;

import java.sql.*;
import tienda.entidades.Fabricante;

public final class FabricanteDaoExt extends Dao {
    
    public void guardarFabricante(Fabricante fabricante) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            if (fabricante != null) {
                String sql = "INSERT INTO fabricante(nombre) VALUES('" + fabricante.getNombre() + "')";
                insertarModificarEliminar(sql);
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public Fabricante buscarFabricantePorCodigo(int codigoFabricante) throws ClassNotFoundException, SQLException {

        Fabricante fabricante = new Fabricante();
        String sql = "SELECT * FROM fabricante WHERE codigo = " + codigoFabricante;

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                fabricante.setCodigo(resultado.getInt("codigo"));
                fabricante.setNombre(resultado.getString("nombre"));
            }
        }

        return fabricante;

    }

}
