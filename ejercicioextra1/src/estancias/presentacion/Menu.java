package estancias.presentacion;

import estancias.servicios.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void vistaMenu() {

        System.out.println("¡Bienvenido a Estancias!");
        System.out.println("Elija una opción:");
        System.out.println("1. Listar familias que tienen al menos 3 hijos, y con edad máxima inferior a 10 años.");
        System.out.println("2. Listar las casas disponibles para el periodo comprendido entre el 1 de agosto de 2020 y el 31 de agosto de 2020 en Reino Unido.");
        System.out.println("3. Listar aquellas familias, cuya dirección de mail sea de Hotmail.");
        System.out.println("4. Listar las casas disponibles a partir de una fecha dada y un número de días específico.");
        System.out.println("5. Listar los clientes que en algún momento realizaron una estancia (y la descripción de la casa donde la realizaron).");
        System.out.println("6. Listar las estancias que han sido reservadas por un cliente, mostrar el nombre, país y ciudad del cliente y además la información de la casa que reservó. La que reemplazaría a la anterior.");
        System.out.println("7. Incrementar el precio por día en un 5% de todas las casas del Reino Unido. Mostar los precios actualizados.");
        System.out.println("8. Obtener el número de casas que existen para cada uno de los países diferentes.");
        System.out.println("9. Listar aquellas casas, del Reino Unido, de las que se ha dicho de ellas (comentarios) que están limpias.");
        System.out.println("10. Insertar nuevos datos en la tabla estancias verificando la disponibilidad de las fechas.");
        System.out.println("11. Salir");

    }

    public void ejecutarMenu() throws ClassNotFoundException, SQLException {

        int opcion = 0;

        while (opcion != 11) {

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

                case 10:
                    ejecutarOpcion10();
                    break;

                case 11:
                    ejecutarOpcion11();
                    break;

                default:
                    System.out.println("Error al ingresar la opción");
                    opcion = 11;
                    ejecutarOpcion11();

            }

            System.out.println("");

        }

    }

    public void ejecutarOpcion1() throws ClassNotFoundException, SQLException {
        ServicioFamilia servicioFamilia = new ServicioFamilia();
        servicioFamilia.listarFamiliasConEdadMinima10AdemasDeTenerMinimo3NumeroDeHijos();
    }

    public void ejecutarOpcion2() throws ClassNotFoundException, SQLException {
        ServicioCasa servicioCasa = new ServicioCasa();
        servicioCasa.listarCasasDeReinoUnidoDeAgosto();
    }

    public void ejecutarOpcion3() throws ClassNotFoundException, SQLException {
        ServicioFamilia servicioFamilia = new ServicioFamilia();
        servicioFamilia.listarFamiliasConHotmail();
    }

    public void ejecutarOpcion4() throws ClassNotFoundException, SQLException {
        ServicioCasa servicioCasa = new ServicioCasa();
        servicioCasa.listarCasasPorFechaTiempo();
    }

    public void ejecutarOpcion5() throws SQLException, ClassNotFoundException {
        ServicioCliente servicioCliente = new ServicioCliente();
        servicioCliente.listarClientesQueRealizaronEstanciaAlgunaVez();
    }

    public void ejecutarOpcion6() throws SQLException, ClassNotFoundException {
        ServicioEstancia servicioEstancia = new ServicioEstancia();
        servicioEstancia.listarEstanciasReservadasSegunClienteAdemasDeCasa();
    }

    public void ejecutarOpcion7() throws ClassNotFoundException, SQLException {
        ServicioCasa servicioCasa = new ServicioCasa();
        servicioCasa.incrementarPrecioHabitacionUn5PorcientoAdemasDeMostrarlo();
    }

    public void ejecutarOpcion8() throws ClassNotFoundException, SQLException {
        ServicioCasa servicioCasa = new ServicioCasa();
        servicioCasa.obtenerNumeroDeCasasPorPais();
    }

    public void ejecutarOpcion9() throws ClassNotFoundException, SQLException {
        ServicioCasa servicioCasa = new ServicioCasa();
        servicioCasa.listarCasasLimpiasDelReinoUnido();
    }

    public void ejecutarOpcion10() throws SQLException, ClassNotFoundException {
        ServicioEstancia servicioEstancia = new ServicioEstancia();
        servicioEstancia.ingresarEstancia();
    }

    public void ejecutarOpcion11() {
        System.out.println("¡Hasta la próxima!");
    }

}
