package tienda.servicios;

import java.sql.SQLException;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDaoExt;
import tienda.servicios.excepciones.FabricanteExcepcion;

public class FabricanteServicio {

    private final FabricanteDaoExt fabricanteDao;

    public FabricanteServicio() {
        this.fabricanteDao = new FabricanteDaoExt();
    }

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public Fabricante crearFabricante() {

        String nombre;

        System.out.println("Ingrese el nombre de su nuevo fabricante");
        nombre = leer.next();

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new FabricanteExcepcion("No ha ingresado un nombre");
        }

        return new Fabricante(nombre);

    }

    public void ingresarFabricante(Fabricante fabricante) throws NullPointerException, ClassNotFoundException, SQLException {

        if (fabricante != null) {
            fabricanteDao.guardarFabricante(fabricante);
        } else if(fabricante == null){
            throw new FabricanteExcepcion("Ha ingresado un fabricante inválido");
        }

    }

}
