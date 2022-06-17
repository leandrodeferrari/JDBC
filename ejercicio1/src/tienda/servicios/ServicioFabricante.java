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

        int codigo;
        String nombre;

        System.out.println("Ingrese el código de su nuevo fabricante");
        codigo = leer.nextInt();
        System.out.println("Ingrese el nombre de su nuevo fabricante");
        nombre = leer.next();
        
        Fabricante fabricante = new Fabricante();
//        if (nombre == null || nombre.trim().isEmpty()) {
//            throw new RuntimeException("No ha ingresado un nombre");
//        }
        if (fabricanteDao.buscarFabricantePorCodigo(codigo).getCodigo() != codigo) {
            fabricante.setCodigo(codigo);
            fabricante.setNombre(nombre);
        } else {
            System.out.println("Lo siento, ya existe un fabricante con ese código");
        }
        return fabricante;
        // Debo crear una clase excepción para fabricante
        // Debo ver lo de las validaciones
    }

    public void ingresarFabricante(Fabricante fabricante) throws NullPointerException, ClassNotFoundException, SQLException {

        try {
            fabricanteDao.guardarFabricante(fabricante);
        } catch (NullPointerException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }

    }

}
