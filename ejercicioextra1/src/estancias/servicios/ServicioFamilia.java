package estancias.servicios;

import estancias.persistencia.FamiliaDaoExt;
import java.sql.SQLException;

public class ServicioFamilia {
    
    private final FamiliaDaoExt familiaDao;

    public ServicioFamilia() {
        this.familiaDao = new FamiliaDaoExt();
    }
    
    public void listarFamiliasConEdadMinima10AdemasDeTenerMinimo3NumeroDeHijos() throws ClassNotFoundException, SQLException{
        familiaDao.consultarFamiliasConEdadMaximaMenorQue10AdemasDeNumeroHijosMayorIgualQue3();
    }
    
    public void listarFamiliasConHotmail() throws ClassNotFoundException, SQLException{
        familiaDao.consultarFamiliasCuyoEmailSeaHotmail();
    }
    
}
