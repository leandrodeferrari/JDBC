package estancias.servicios;

import estancias.entidades.Casa;
import estancias.persistencia.CasaDaoExt;
import estancias.servicios.excepciones.CasaExcepcion;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CasaServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private final CasaDaoExt casaDao;

    public CasaServicio() {
        this.casaDao = new CasaDaoExt();
    }

    public void listarCasasDeReinoUnidoDeAgosto() throws ClassNotFoundException, SQLException {
        List<Casa> casas = casaDao.consultarCasasDeReinoUnidoAdemasDeSerEnAgosto();

        if (!casas.isEmpty()) {
            casas.forEach((casa) -> {
                System.out.println(casa.toString());
            });
        }
    }

    public void listarCasasPorFechaTiempo() throws ClassNotFoundException, SQLException {

        String anio, mes, dia;
        int tiempoMinimo, tiempoMaximo;

        try {
            System.out.println("Ingrese el año que desea buscar casa (YYYY)");
            anio = leer.next();
            System.out.println("Ingrese el mes que desea buscar casa (MM)");
            mes = leer.next();
            System.out.println("Ingrese el día que desea buscar casa (DD)");
            dia = leer.next();
            System.out.println("Ingrese el tiempo mínimo de la casa");
            tiempoMinimo = leer.nextInt();
            System.out.println("Ingrese el tiempo máximo de la casa");
            tiempoMaximo = leer.nextInt();
        } catch (CasaExcepcion ex) {
            throw new CasaExcepcion("Error al ingresar los datos de la casa, para listarlas por fecha de tiempo");
        }

        List<Casa> casas = casaDao.consultarCasasPorFechaAdemasDeTiempoMinimoTiempoMaximo(anio, mes, dia, tiempoMinimo, tiempoMaximo);

        if (!casas.isEmpty()) {
            casas.forEach((casa) -> {
                System.out.println(casa.toString());
            });
        } else {
            System.out.println("No hay casas con esos requerimientos");
        }

    }

    public void incrementarPrecioHabitacionUn5PorCientoAdemasDeMostrarlo() throws ClassNotFoundException, SQLException {

        casaDao.incrementarPrecioDeLasCasas(1.05);

        System.out.println("Precios actualizados:");

        List<Casa> casas = casaDao.consultarCasas();

        casas.forEach((casa) -> {
            System.out.println(casa.toString());
        });

    }

    public void obtenerNumeroDeCasasPorPais() throws ClassNotFoundException, SQLException {
        casaDao.consultarNumeroDeCasasPorPais();
    }

    public void listarCasasLimpiasDelReinoUnido() throws ClassNotFoundException, SQLException {
        casaDao.consultarCasasLimpiasDelReinoUnido();
    }

}
