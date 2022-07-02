package estancias.persistencia;

import estancias.entidades.Casa;
import estancias.persistencia.excepciones.CasaDaoExcepcion;
import java.sql.*;
import java.util.*;

public class CasaDaoExt extends Dao {

    private final String SQL_SELECT_TIEMPO_FECHA = "SELECT * FROM estancias_exterior.casas "
            + "WHERE YEAR(fecha_desde) = ? "
            + "AND MONTH(fecha_desde) = ? "
            + "AND DAY(fecha_desde) = ? "
            + "AND tiempo_minimo = ? "
            + "AND tiempo_maximo = ?";

    public List<Casa> consultarCasasDeReinoUnidoAdemasDeSerEnAgosto() throws ClassNotFoundException, SQLException {

        List<Casa> casas = new ArrayList<>();
        String sql = "SELECT * FROM estancias_exterior.casas WHERE pais = 'Reino unido' "
                + "AND DATEDIFF(fecha_hasta, fecha_desde) >= 30 "
                + "AND MONTH(fecha_hasta) = 06 "
                + "AND MONTH(fecha_desde) = 06";

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(resultado.getInt("id_casa"));
                casa.setCalle(resultado.getString("calle"));
                casa.setNumero(resultado.getInt("numero"));
                casa.setCodigoPostal(resultado.getString("codigo_postal"));
                casa.setCiudad(resultado.getString("ciudad"));
                casa.setPais(resultado.getString("pais"));
                casa.setFechaDesde(resultado.getDate("fecha_desde"));
                casa.setFechaHasta(resultado.getDate("fecha_hasta"));
                casa.setTiempoMinimo(resultado.getInt("tiempo_minimo"));
                casa.setTiempoMaximo(resultado.getInt("tiempo_maximo"));
                casa.setPrecioHabitacion(resultado.getDouble("precio_habitacion"));
                casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                casas.add(casa);
            }
        }

        desconectarBaseDeDatos();

        return casas;

    }

    public List<Casa> consultarCasasPorFechaAdemasDeTiempoMinimoTiempoMaximo(String anio, String mes, String dia, int tiempoMinimo, int tiempoMaximo) throws ClassNotFoundException, SQLException {

        List<Casa> casas = new ArrayList<>();

        try {
            conectarBaseDeDatos();

            this.sentenciaPreparada = conexion.prepareStatement(SQL_SELECT_TIEMPO_FECHA);
            sentenciaPreparada.setString(1, anio);
            sentenciaPreparada.setString(2, mes);
            sentenciaPreparada.setString(3, dia);
            sentenciaPreparada.setInt(4, tiempoMinimo);
            sentenciaPreparada.setInt(5, tiempoMaximo);

            this.resultado = sentenciaPreparada.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    Casa casa = new Casa();
                    casa.setIdCasa(resultado.getInt("id_casa"));
                    casa.setCalle(resultado.getString("calle"));
                    casa.setNumero(resultado.getInt("numero"));
                    casa.setCodigoPostal(resultado.getString("codigo_postal"));
                    casa.setCiudad(resultado.getString("ciudad"));
                    casa.setPais(resultado.getString("pais"));
                    casa.setFechaDesde(resultado.getDate("fecha_desde"));
                    casa.setFechaHasta(resultado.getDate("fecha_hasta"));
                    casa.setTiempoMinimo(resultado.getInt("tiempo_minimo"));
                    casa.setTiempoMaximo(resultado.getInt("tiempo_maximo"));
                    casa.setPrecioHabitacion(resultado.getDouble("precio_habitacion"));
                    casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                    casas.add(casa);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            desconectarBaseDeDatos();
        }

        return casas;

    }

    public void incrementarPrecioDeLasCasas(Double numero) throws ClassNotFoundException, SQLException {

        String sql = "UPDATE estancias_exterior.casas "
                + "SET casas.precio_habitacion = casas.precio_habitacion * " + numero + " WHERE casas.id_casa";

        if (numero != null) {
            insertarModificarEliminar(sql);
        } else {
            throw new CasaDaoExcepcion("No se pudo incrementar el precio de las casas");
        }

    }

    public List<Casa> consultarCasas() throws ClassNotFoundException, SQLException {

        List<Casa> casas = new ArrayList<>();
        String sql = "SELECT * FROM estancias_exterior.casas";

        this.resultado = consultarBaseDeDatos(sql);

        if (resultado != null) {
            while (resultado.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(resultado.getInt("id_casa"));
                casa.setCalle(resultado.getString("calle"));
                casa.setNumero(resultado.getInt("numero"));
                casa.setCodigoPostal(resultado.getString("codigo_postal"));
                casa.setCiudad(resultado.getString("ciudad"));
                casa.setPais(resultado.getString("pais"));
                casa.setFechaDesde(resultado.getDate("fecha_desde"));
                casa.setFechaHasta(resultado.getDate("fecha_hasta"));
                casa.setTiempoMinimo(resultado.getInt("tiempo_minimo"));
                casa.setTiempoMaximo(resultado.getInt("tiempo_maximo"));
                casa.setPrecioHabitacion(resultado.getDouble("precio_habitacion"));
                casa.setTipoVivienda(resultado.getString("tipo_vivienda"));
                casas.add(casa);
            }
        }

        desconectarBaseDeDatos();

        return casas;

    }

    public void consultarNumeroDeCasasPorPais() throws ClassNotFoundException, SQLException {

        String sql = "SELECT COUNT(id_casa) AS numero_casas, pais FROM estancias_exterior.casas GROUP BY pais";

        try {
            this.resultado = consultarBaseDeDatos(sql);
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

        String sql = "SELECT * FROM estancias_exterior.casas "
                + "INNER JOIN estancias_exterior.comentarios ON casas.id_casa = comentarios.id_casa "
                + "WHERE comentario LIKE '%limpia%'";

        try {
            this.resultado = consultarBaseDeDatos(sql);
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
