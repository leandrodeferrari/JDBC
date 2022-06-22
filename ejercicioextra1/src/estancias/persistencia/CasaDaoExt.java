package estancias.persistencia;

import java.sql.*;

public class CasaDaoExt extends Dao {

    private PreparedStatement sentenciaPreparada;

    private final String SQL_SELECT_MOSTRAR_CASAS_LIMPIAS = "SELECT * FROM estancias_exterior.casas INNER JOIN estancias_exterior.comentarios ON casas.id_casa = comentarios.id_casa WHERE comentario LIKE '%limpia%'";

    private final String SQL_SELECT_NUMEROCASAS_PORPAIS = "SELECT COUNT(id_casa) AS numero_casas, pais FROM estancias_exterior.casas GROUP BY pais";

    private final String SQL_UPDATE_INCREMENTAR_PRECIO = "UPDATE estancias_exterior.casas SET casas.precio_habitacion = casas.precio_habitacion * ? WHERE casas.id_casa";

    private final String SQL_SELECT_PRECIO = "SELECT precio_habitacion FROM estancias_exterior.casas";

    private final String SQL_SELECT_TIEMPO_FECHA = "SELECT * FROM estancias_exterior.casas "
            + "WHERE YEAR(fecha_desde) = ? "
            + "AND MONTH(fecha_desde) = ? "
            + "AND DAY(fecha_desde) = ? "
            + "AND tiempo_minimo = ? "
            + "AND tiempo_maximo = ?";

    private final String SQL_SELECT_AGOSTO_REINOUNIDO = "SELECT * FROM estancias_exterior.casas WHERE pais = 'Reino unido' "
            + "AND DATEDIFF(fecha_hasta, fecha_desde) >= 30 "
            + "AND MONTH(fecha_hasta) = 06 AND "
            + "MONTH(fecha_desde) = 06;";

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

    public void consultarCasasDeReinoUnidoAdemasDeSerEnAgosto() throws ClassNotFoundException, SQLException {

        try {
            consultarBaseDeDatos(SQL_SELECT_AGOSTO_REINOUNIDO);
            while (resultado.next()) {
                System.out.println(resultado.getInt("id_casa") + " " + resultado.getString("calle") + " "
                        + resultado.getInt("numero") + " " + resultado.getString("codigo_postal") + " "
                        + resultado.getString("ciudad") + " " + resultado.getString("pais") + " "
                        + resultado.getDate("fecha_desde") + " " + resultado.getDate("fecha_hasta") + " "
                        + resultado.getInt("tiempo_minimo") + " " + resultado.getInt("tiempo_maximo") + " "
                        + resultado.getDouble("precio_habitacion") + " " + resultado.getString("tipo_vivienda"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarCasasPorFechaAdemasDeTiempoMinimoTiempoMaximo(String anio, String mes, String dia, int tiempoMinmo, int tiempoMaximo) throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            sentenciaPreparada = conexion.prepareStatement(SQL_SELECT_TIEMPO_FECHA);
            sentenciaPreparada.setString(1, anio);
            sentenciaPreparada.setString(2, mes);
            sentenciaPreparada.setString(3, dia);
            sentenciaPreparada.setInt(4, tiempoMinmo);
            sentenciaPreparada.setInt(5, tiempoMaximo);
            resultado = sentenciaPreparada.executeQuery();
            while (resultado.next()) {
                System.out.println(resultado.getInt("id_casa") + " " + resultado.getString("calle") + " "
                        + resultado.getInt("numero") + " " + resultado.getString("codigo_postal") + " "
                        + resultado.getString("ciudad") + " " + resultado.getString("pais") + " "
                        + resultado.getDate("fecha_desde") + " " + resultado.getDate("fecha_hasta") + " "
                        + resultado.getInt("tiempo_minimo") + " " + resultado.getInt("tiempo_maximo") + " "
                        + resultado.getDouble("precio_habitacion") + " " + resultado.getString("tipo_vivienda"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void incrementarPrecioDeLasCasas(Double numero) throws ClassNotFoundException, SQLException {

        try {
            conectarBaseDeDatos();
            sentenciaPreparada = conexion.prepareStatement(SQL_UPDATE_INCREMENTAR_PRECIO);
            sentenciaPreparada.setDouble(1, numero);
            sentenciaPreparada.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarPrecioHabitacion() throws ClassNotFoundException, SQLException {

        try {
            consultarBaseDeDatos(SQL_SELECT_PRECIO);
            while (resultado.next()) {
                System.out.println(resultado.getDouble("precio_habitacion"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarNumeroCasasPorPais() throws ClassNotFoundException, SQLException {

        try {
            consultarBaseDeDatos(SQL_SELECT_NUMEROCASAS_PORPAIS);
            while (resultado.next()) {
                System.out.println(resultado.getInt("numero_casas") + " " + resultado.getString("pais"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

    public void consultarCasasLimpiasDelReinoUnido() throws ClassNotFoundException, SQLException {

        try {
            consultarBaseDeDatos(SQL_SELECT_MOSTRAR_CASAS_LIMPIAS);
            while (resultado.next()) {
                System.out.println(resultado.getInt("id_casa") + " " + resultado.getString("calle") + " "
                        + resultado.getInt("numero") + " " + resultado.getString("codigo_postal") + " "
                        + resultado.getString("ciudad") + " " + resultado.getString("pais") + " "
                        + resultado.getDate("fecha_desde") + " " + resultado.getDate("fecha_hasta") + " "
                        + resultado.getInt("tiempo_minimo") + " " + resultado.getInt("tiempo_maximo") + " "
                        + resultado.getDouble("precio_habitacion") + " " + resultado.getString("tipo_vivienda") 
                        + " " + resultado.getString("comentario"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

    }

}
