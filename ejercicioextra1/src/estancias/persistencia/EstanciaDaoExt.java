package estancias.persistencia;

import estancias.entidades.Estancia;
import estancias.persistencia.excepciones.EstanciaDaoExcepcion;
import java.sql.*;

public class EstanciaDaoExt extends Dao {

    private final String SQL_INSERT = "INSERT INTO estancias_exterior.estancias"
            + "(id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta) VALUES(?, ?, ?, ?, ?)";

    public void consultarEstanciasSegunCasaAdemasDeCliente() throws SQLException, ClassNotFoundException {

        String sql = "SELECT estancias.*, clientes.nombre, "
                + "clientes.pais, clientes.ciudad, casas.* FROM estancias_exterior.clientes "
                + "INNER JOIN estancias_exterior.estancias ON clientes.id_cliente = estancias.id_cliente "
                + "INNER JOIN estancias_exterior.casas ON estancias.id_casa = casas.id_casa";

        try {
            this.resultado = consultarBaseDeDatos(sql);

            if (resultado == null) {
                System.out.println("No hay estancias con esos requerimientos");
            } else {
                while (resultado.next()) {
                    System.out.println(resultado.getInt("id_estancia") + " " + resultado.getInt("id_cliente")
                            + " " + resultado.getInt("estancias.id_casa") + " " + resultado.getString("nombre_huesped")
                            + " " + resultado.getDate("estancias.fecha_desde") + " " + resultado.getDate("estancias.fecha_hasta")
                            + " " + resultado.getString("clientes.nombre") + " " + resultado.getString("clientes.pais")
                            + " " + resultado.getString("clientes.ciudad") + " " + resultado.getInt("casas.id_casa")
                            + " " + resultado.getString("calle") + " " + resultado.getInt("numero")
                            + " " + resultado.getString("codigo_postal") + " " + resultado.getString("casas.ciudad")
                            + " " + resultado.getString("casas.pais") + " " + resultado.getDate("casas.fecha_desde")
                            + " " + resultado.getDate("casas.fecha_hasta") + " " + resultado.getInt("tiempo_minimo")
                            + " " + resultado.getInt("tiempo_maximo") + " " + resultado.getDouble("precio_habitacion")
                            + " " + resultado.getString("tipo_vivienda") + " ");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void insertarEstancia(Estancia estancia) throws NullPointerException, ClassNotFoundException, SQLException {

        if (estancia == null) {
            throw new EstanciaDaoExcepcion("No ha indicado estancia");
        }

        try {
            conectarBaseDeDatos();

            this.sentenciaPreparada = conexion.prepareStatement(SQL_INSERT);
            sentenciaPreparada.setInt(1, estancia.getIdCliente());
            sentenciaPreparada.setInt(2, estancia.getIdCasa());
            sentenciaPreparada.setString(3, estancia.getNombreHuesped());
            sentenciaPreparada.setDate(4, estancia.getFechaDesde());
            sentenciaPreparada.setDate(5, estancia.getFechaHasta());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

}
