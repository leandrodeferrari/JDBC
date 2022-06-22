package estancias.servicios;

import estancias.entidades.Estancia;
import estancias.persistencia.EstanciaDaoExt;
import java.sql.*;
import java.util.Scanner;

public class ServicioEstancia {
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    private final EstanciaDaoExt estanciaDao;

    public ServicioEstancia() {
        this.estanciaDao = new EstanciaDaoExt();
    }
    
    public Estancia crearEstancia(){
        
        int idCliente, idCasa, anio, mes, dia;
        String nombreHuesped;
        Date fechaDesde, fechaHasta;
        
        System.out.println("Ingrese el id del cliente");
        idCliente = leer.nextInt();
        
        System.out.println("Ingrese el id de la casa");
        idCasa = leer.nextInt();
        
        System.out.println("Ingrese el nombre de su huesped");
        nombreHuesped = leer.next();
        
        System.out.println("Fecha desde:");
        System.out.println("Año:");
        anio = leer.nextInt();
        System.out.println("Mes:");
        mes = leer.nextInt();
        System.out.println("Día:");
        dia = leer.nextInt();
        fechaDesde = new Date(anio-1900, mes-1, dia);
        
        System.out.println("Fecha hasta:");
        System.out.println("Año:");
        anio = leer.nextInt();
        System.out.println("Mes:");
        mes = leer.nextInt();
        System.out.println("Día:");
        dia = leer.nextInt();        
        fechaHasta = new Date(anio-1900, mes-1, dia);
        
        Estancia estancia = new Estancia(idCliente, idCasa, nombreHuesped, fechaDesde, fechaHasta);
        
        return estancia;
    }
    
    
    public void listarEstanciasReservadasSegunClienteAdemasDeCasa() throws SQLException, ClassNotFoundException{
        estanciaDao.consultarEstanciasSegunCasaAdemasDeCliente();
    }
    
    public void ingresarEstancia() throws SQLException, ClassNotFoundException{
        Estancia estancia = crearEstancia();
        estanciaDao.insertarEstancia(estancia);
    }
    
}
