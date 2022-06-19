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

    public Fabricante crearFabricante() throws ClassNotFoundException, SQLException {

        String nombre;

        System.out.println("Ingrese el nombre de su nuevo fabricante");
        nombre = leer.next();
        
        Fabricante fabricante = new Fabricante(nombre);
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("No ha ingresado un nombre");
            // Debería de crear una clase excepcion para esto.
        }

        return fabricante;

    }

    public void ingresarFabricante(Fabricante fabricante) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            fabricanteDao.guardarFabricante(fabricante);
        } catch (NullPointerException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

}
