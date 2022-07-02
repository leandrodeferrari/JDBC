package estancias.persistencia;

import estancias.entidades.Familia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamiliaDaoExt extends Dao {

    private final String SQL_SELECT_CON_HIJOS_Y_EDAD_MAXIMA = "SELECT * FROM estancias_exterior.familias "
            + "WHERE num_hijos >= ? AND edad_maxima < ?";

    public List<Familia> consultarFamiliasConEdadMaximaMenorQue10AdemasDeNumeroHijosMayorIgualQue3() throws ClassNotFoundException, SQLException {

        List<Familia> familias = new ArrayList<>();

        try {
            conectarBaseDeDatos();

            this.sentenciaPreparada = conexion.prepareStatement(SQL_SELECT_CON_HIJOS_Y_EDAD_MAXIMA);
            sentenciaPreparada.setInt(1, 3);
            sentenciaPreparada.setInt(2, 10);

            this.resultado = sentenciaPreparada.executeQuery();

            while (resultado.next()) {
                Familia familia = new Familia();
                familia.setIdFamilia(resultado.getInt("id_familia"));
                familia.setNombre(resultado.getString("nombre"));
                familia.setEdadMinima(resultado.getInt("edad_minima"));
                familia.setEdadMaxima(resultado.getInt("edad_maxima"));
                familia.setCantidadDeHijos(resultado.getInt("num_hijos"));
                familia.setEmail(resultado.getString("email"));
                familia.setIdFamilia(resultado.getInt("id_casa_familia"));
                familias.add(familia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

        return familias;

    }

    public List<Familia> consultarFamiliasCuyoEmailSeaHotmail() throws ClassNotFoundException, SQLException {

        List<Familia> familias = new ArrayList<>();
        String sql = "SELECT * FROM estancias_exterior.familias WHERE email LIKE '%hotmail%'";

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                Familia familia = new Familia();
                familia.setIdFamilia(resultado.getInt("id_familia"));
                familia.setNombre(resultado.getString("nombre"));
                familia.setEdadMinima(resultado.getInt("edad_minima"));
                familia.setEdadMaxima(resultado.getInt("edad_maxima"));
                familia.setCantidadDeHijos(resultado.getInt("num_hijos"));
                familia.setEmail(resultado.getString("email"));
                familia.setIdFamilia(resultado.getInt("id_casa_familia"));
                familias.add(familia);
            }
        }

        desconectarBaseDeDatos();

        return familias;

    }

}
