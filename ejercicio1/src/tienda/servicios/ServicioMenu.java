package tienda.servicios;

import java.sql.SQLException;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;

public class ServicioMenu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void vistaMenu() {

        System.out.println("¡Bienvenido al menú de la tienda!");
        System.out.println("¿Qué desea realizar?");

        System.out.println("1. Listar el nombre de los productos");
        System.out.println("2. Listar el nombre y los precios de los productos");
        System.out.println("3. Listar los productos que tienen un precio entre 120 y 202");
        System.out.println("4. Listar los productos que son portátiles");
        System.out.println("5. Listar el producto más barato");
        System.out.println("6. Ingresar un producto");
        System.out.println("7. Modificar un producto");
        System.out.println("8. Ingresar un fabricante");
        System.out.println("9. Salir");
        System.out.println("");

    }

    public void ejecutarMenu() throws ClassNotFoundException, SQLException {

        int opcion = 0;

        while (opcion != 9) {

            vistaMenu();

            System.out.println("Ingrese su opción:");
            opcion = leer.nextInt();

            switch (opcion) {

                case 1:
                    ejecutarOpcion1();
                    break;

                case 2:
                    ejecutarOpcion2();
                    break;

                case 3:
                    ejecutarOpcion3();
                    break;

                case 4:
                    ejecutarOpcion4();
                    break;

                case 5:
                    ejecutarOpcion5();
                    break;

                case 6:
                    ejecutarOpcion6();
                    break;

                case 7:
                    ejecutarOpcion7();
                    break;

                case 8:
                    ejecutarOpcion8();
                    break;

                case 9:
                    ejecutarOpcion9();
                    break;

                default:
                    System.out.println("Error al ingresar la opción");
                    opcion = 9;
                    ejecutarOpcion9();

            }

            System.out.println("");

        }

    }

    public void ejecutarOpcion1() throws ClassNotFoundException, SQLException {

        ServicioProducto servicioProducto = new ServicioProducto();
        servicioProducto.listarNombresDeProductos();

    }

    public void ejecutarOpcion2() throws ClassNotFoundException, SQLException {

        ServicioProducto servicioProducto = new ServicioProducto();
        servicioProducto.listarNombrePrecioDeProductos();

    }

    public void ejecutarOpcion3() throws ClassNotFoundException, SQLException {

        ServicioProducto servicioProducto = new ServicioProducto();
        servicioProducto.listarProductosEntrePrecio120y202();

    }

    public void ejecutarOpcion4() throws ClassNotFoundException, SQLException {

        ServicioProducto servicioProducto = new ServicioProducto();
        servicioProducto.listarProductosPortatiles();

    }

    public void ejecutarOpcion5() throws ClassNotFoundException, SQLException {

        ServicioProducto servicioProducto = new ServicioProducto();
        servicioProducto.listarElProductoMasBarato();

    }

    public void ejecutarOpcion6() throws ClassNotFoundException, SQLException {

        ServicioProducto servicioProducto = new ServicioProducto();
        Producto producto = servicioProducto.crearProducto();
        
        servicioProducto.ingresarProducto(producto);

    }

    public void ejecutarOpcion7() throws ClassNotFoundException, SQLException {

        ServicioProducto servicioProducto = new ServicioProducto();
        servicioProducto.modificarTodosLosDatosDeUnProducto();

    }

    public void ejecutarOpcion8() throws ClassNotFoundException, SQLException {

        ServicioFabricante servicioFabricante = new ServicioFabricante();
        Fabricante fabricante = servicioFabricante.crearFabricante();

        servicioFabricante.ingresarFabricante(fabricante);

    }

    public void ejecutarOpcion9() {

        System.out.println("¡Hasta la próxima!");

    }

}
