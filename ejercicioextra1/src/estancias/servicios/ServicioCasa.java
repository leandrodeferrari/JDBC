package estancias.servicios;

import estancias.persistencia.CasaDaoExt;
import java.sql.SQLException;
import java.util.Scanner;

public class ServicioCasa {
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    private final CasaDaoExt casaDao;
    
    public ServicioCasa(){
        this.casaDao = new CasaDaoExt();
    }
    
    public void listarCasasDeReinoUnidoDeAgosto() throws ClassNotFoundException, SQLException{
        casaDao.consultarCasasDeReinoUnidoAdemasDeSerEnAgosto();
    }
    
    public void listarCasasPorFechaTiempo() throws ClassNotFoundException, SQLException{
        
        String anio, mes, dia;
        int tiempoMinimo, tiempoMaximo;
        
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
        
        
        casaDao.consultarCasasPorFechaAdemasDeTiempoMinimoTiempoMaximo(anio, mes, dia, tiempoMinimo, tiempoMaximo);
        
    }
    
    public void incrementarPrecioHabitacionUn5PorcientoAdemasDeMostrarlo() throws ClassNotFoundException, SQLException{
        
        casaDao.incrementarPrecioDeLasCasas(1.05);
        System.out.println("Precios actualizados:");
        casaDao.consultarPrecioHabitacion();
        
    }
    
    public void obtenerNumeroDeCasasPorPais() throws ClassNotFoundException, SQLException{
        casaDao.consultarNumeroCasasPorPais();
    }
    
    public void listarCasasLimpiasDelReinoUnido() throws ClassNotFoundException, SQLException{
        casaDao.consultarCasasLimpiasDelReinoUnido();
    }
    
}
