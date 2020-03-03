package controller;

import java.util.Scanner;
import model.logic.Comparendo;
import model.logic.Modelo;
import model.data_structures.Queue;


import view.View;

public class Controller {

    private Modelo modelo;

    private View view;

    /**
     * Crear la vista y el modelo del proyecto
     */
    public Controller ()
    {
        view = new View();
        modelo = new Modelo();
    }

    public void run()
    {
        Scanner lector = new Scanner(System.in);
        boolean fin = false;

        while( !fin ){
            view.printMenu();

            int option = lector.nextInt();

            switch(option){
                case 1:


                    long start = System.currentTimeMillis();
                    modelo.cargarDatos();
                    long end = System.currentTimeMillis();

                    Comparendo mayorOBJID = modelo.mayorObjectID();

                    Queue<Comparendo> listaC = (Queue<Comparendo>) modelo.darDatosC();

                    view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0 + "\n");

                    view.printMessage("Total datos cargados: " + listaC.darTamano() + "\n");

                    view.printMessage("Elemento con el mayor OBJECTID: " + mayorOBJID.toString() + "\n");

                    view.printMessage("Zona Minimax: (" + modelo.menorLatitud() + "," + modelo.menorLongitud() + ") y "
                            + "("+ modelo.mayorLatitud() +","+ modelo.mayorLongitud() +")");

                    break;
                case 2:

                    break;
                case 3:

                case 4:

                    break;
                case 5:
                    Scanner opcion1B = new Scanner(System.in);
                    view.printMessage("Por favor, ingrese la infracciï¿½n que desea buscar");
                    String opcion = opcion1B.nextLine();
                    Comparendo buscado = modelo.primerComparendoPorInfraccionDada(opcion);

                    if (buscado == null)
                        view.printMessage("No existe una infracciï¿½n con ese cï¿½digo en nuestros registros");
                    else
                        view.printMessage(buscado.toString());

                    break;
                case 6:
                    Scanner opcion2B = new Scanner(System.in);
                    view.printMessage("Por favor, ingrese la infracciï¿½n por la que desea buscar");
                    String linea2B = opcion2B.nextLine();

                    Queue<Comparendo> orden = (Queue<Comparendo>) modelo.darComparendosEnOrdenCronologico(linea2B);

                    view.printMessage(""+orden.darTamano());

                    while(!orden.estaVacia())
                    {
                        view.printMessage(orden.deQueue().toString());
                    }
                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    Queue<String> localidades = modelo.localidades();

                    while(!localidades.estaVacia())
                    {
                        view.printMessage(localidades.deQueue());
                    }

                    break;
                default:
                    view.printMessage("--------- \n Opcion Invalida !! \n---------");
                    break;
            }
        }

    }
}