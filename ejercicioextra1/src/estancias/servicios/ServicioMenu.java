package estancias.servicios;

import java.sql.SQLException;
import java.util.Scanner;

public class ServicioMenu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    /*    
    1. Listar aquellas familias que tienen al menos 3 hijos, y con edad máxima inferior a 10 
    años.
    2. Buscar y listar las casas disponibles para el periodo comprendido entre el 1 de 
    agosto de 2020 y el 31 de agosto de 2020 en Reino Unido.
    3. Encuentra todas aquellas familias cuya dirección de mail sea de Hotmail.
    4. Consulta la BD para que te devuelva aquellas casas disponibles a partir de una fecha 
    dada y un número de días específico.
    5. Listar los datos de todos los clientes que en algún momento realizaron una estancia 
    y la descripción de la casa donde la realizaron.
    6. Listar todas las estancias que han sido reservadas por un cliente, mostrar el nombre, 
    país y ciudad del cliente y además la información de la casa que reservó. La que 
    reemplazaría a la anterior.
    7. Debido a la devaluación de la libra esterlina con respecto al euro se desea 
    incrementar el precio por día en un 5% de todas las casas del Reino Unido. Mostar 
    los precios actualizados.
    8. Obtener el número de casas que existen para cada uno de los países diferentes.
    9. Busca y listar aquellas casas del Reino Unido de las que se ha dicho de ellas 
    (comentarios) que están ‘limpias’.
    10. Insertar nuevos datos en la tabla estancias verificando la disponibilidad de las fechas.
     */
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

        /*
        1. Familia consulta
        2. Casa cosnutla
        4. casa consulta
        5. clientes consulta
        6. estancias consutla cliente
        7. casa incrementar y mostrear
        8. casas consular
        9. casas consulta comentarios
        10. estancias insertar 
        */
        //estancia cliente casa comentarios 
        
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

    public void ejecutarOpcion1() throws ClassNotFoundException, SQLException{
        ServicioFamilia servicioFamilia = new ServicioFamilia();
        servicioFamilia.listarFamiliasConEdadMinima10AdemasDeTenerMinimo3NumeroDeHijos();
    }
    
    public void ejecutarOpcion2(){
        
    }
    
    public void ejecutarOpcion3() throws ClassNotFoundException, SQLException{
        ServicioFamilia servicioFamilia = new ServicioFamilia();
        servicioFamilia.listarFamiliasConHotmail();
    }
    
    public void ejecutarOpcion4(){
        
    }
    
    public void ejecutarOpcion5(){
        
    }
    
    public void ejecutarOpcion6(){
        
    }
    
    public void ejecutarOpcion7(){
        
    }
    
    public void ejecutarOpcion8(){
        
    }
    
    public void ejecutarOpcion9(){
        
    }
    
    public void ejecutarOpcion10(){
        
    }
    
    public void ejecutarOpcion11(){
        
    }
    
    
    
}

