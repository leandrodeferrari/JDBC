package estancias.servicios;

import estancias.entidades.Familia;
import estancias.persistencia.FamiliaDaoExt;
import java.sql.SQLException;
import java.util.List;

public class FamiliaServicio {
    
    private final FamiliaDaoExt familiaDao;

    public FamiliaServicio() {
        this.familiaDao = new FamiliaDaoExt();
    }
    
    public void listarFamiliasConEdadMinima10AdemasDeTenerMinimo3NumeroDeHijos() throws ClassNotFoundException, SQLException{
        List<Familia> familias = familiaDao.consultarFamiliasConEdadMaximaMenorQue10AdemasDeNumeroHijosMayorIgualQue3();
        
        if(!familias.isEmpty()){
            familias.forEach((familia) -> {
                System.out.println(familia.toString());
            });
        } else {
            System.out.println("No hay familias con esos requerimientos");
        }
    }
    
    public void listarFamiliasConHotmail() throws ClassNotFoundException, SQLException{
        List<Familia> familias = familiaDao.consultarFamiliasCuyoEmailSeaHotmail();
        
        if(!familias.isEmpty()){
            familias.forEach((familia) -> {
                System.out.println(familia.toString());
            });
        } else {
            System.out.println("No hay familias con hotmail");
        }
    }
    
}
