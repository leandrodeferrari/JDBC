package estancias.servicios;

import estancias.persistencia.EstanciaDaoExt;
import java.sql.SQLException;

public class ServicioEstancia {
    
    private final EstanciaDaoExt estanciaDao;

    public ServicioEstancia() {
        this.estanciaDao = new EstanciaDaoExt();
    }
    
    public void listarEstanciasReservadasSegunClienteAdemasDeCasa() throws SQLException, ClassNotFoundException{
        estanciaDao.consultarEstanciasSegunCasaAdemasDeCliente();
    }
    
    public void ingresarEstanciaVerificandoLasFechas(){
        estanciaDao.insertarEstanciaVerificandoFechas();
    }
    
}
