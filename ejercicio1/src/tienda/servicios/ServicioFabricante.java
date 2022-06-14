
package tienda.servicios;

import java.sql.SQLException;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDaoExt;

public class ServicioFabricante {
    
    private final FabricanteDaoExt fabricanteDao;

    public ServicioFabricante() {
        this.fabricanteDao = new FabricanteDaoExt();
    }
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public Fabricante crearFabricante() throws NullPointerException, ClassNotFoundException, SQLException{
        
        int codigo;
        String nombre;
        
        System.out.println("Ingrese el código de su nuevo fabricante");
        codigo = leer.nextInt();
        System.out.println("Ingrese el nombre de su nuevo fabricante");
        nombre = leer.next();
        Fabricante fabricante = new Fabricante(codigo,nombre);
        if(nombre == null || nombre.trim().isEmpty()){
            throw new NullPointerException("No ha ingresado un nombre");
        }
        if(fabricanteDao.buscarFabricantePorCodigo(fabricante.getCodigo()) != null){
            fabricante = new Fabricante();
            throw new NullPointerException("Lo siento, ya existe un fabricante con ese código");
        }
        return fabricante;
        // Debo crear una clase excepción para fabricante
    }
    
    public void ingresarFabricante() throws NullPointerException, ClassNotFoundException, SQLException{
        
        Fabricante nuevoFabricante = crearFabricante();
        fabricanteDao.guardarFabricante(nuevoFabricante);
        
    }
    
}
