package estancias.servicios;

import estancias.entidades.Estancia;
import estancias.persistencia.EstanciaDaoExt;
import estancias.servicios.excepciones.EstanciaExcepcion;
import java.sql.*;
import java.util.Scanner;

public class EstanciaServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private final EstanciaDaoExt estanciaDao;

    public EstanciaServicio() {
        this.estanciaDao = new EstanciaDaoExt();
    }

    public Estancia crearEstancia() {

        int idCliente, idCasa, anio, mes, dia;
        String nombreHuesped;
        Date fechaDesde, fechaHasta;

        try {
            System.out.println("Ingrese el id del cliente");
            idCliente = leer.nextInt();
        } catch (EstanciaExcepcion ex) {
            throw new EstanciaExcepcion("Ha ocurrido un error al ingresar el ID del cliente");
        }

        try {
            System.out.println("Ingrese el id de la casa");
            idCasa = leer.nextInt();
        } catch (EstanciaExcepcion ex) {
            throw new EstanciaExcepcion("Ha ocurrido un error al ingresar el ID de la casa");
        }

        System.out.println("Ingrese el nombre de su huesped");
        nombreHuesped = leer.next();

        if (nombreHuesped == null || nombreHuesped.trim().isEmpty()) {
            throw new EstanciaExcepcion("No ha ingresado nombre del huesped");
        }

        try {
            System.out.println("Fecha desde:");
            System.out.println("Año:");
            anio = leer.nextInt();
            System.out.println("Mes:");
            mes = leer.nextInt();
            System.out.println("Día:");
            dia = leer.nextInt();
            fechaDesde = new Date(anio - 1900, mes - 1, dia);

            System.out.println("Fecha hasta:");
            System.out.println("Año:");
            anio = leer.nextInt();
            System.out.println("Mes:");
            mes = leer.nextInt();
            System.out.println("Día:");
            dia = leer.nextInt();
            fechaHasta = new Date(anio - 1900, mes - 1, dia);
        } catch (EstanciaExcepcion ex) {
            throw new EstanciaExcepcion("Ha ocurrido un error al ingresar una fecha");
        }

        return new Estancia(idCliente, idCasa, nombreHuesped, fechaDesde, fechaHasta);

    }

    public void listarEstanciasReservadasSegunClienteAdemasDeCasa() throws SQLException, ClassNotFoundException {
        estanciaDao.consultarEstanciasSegunCasaAdemasDeCliente();
    }

    public void ingresarEstancia() throws SQLException, ClassNotFoundException {
        Estancia estancia = crearEstancia();
        estanciaDao.insertarEstancia(estancia);
    }

}
