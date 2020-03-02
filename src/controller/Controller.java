package controller;

import java.util.Scanner;

import model.logic.Comparendo;
import model.data_structures.Queue;
import model.logic.Modelo;

import view.View;

public class Controller {

    private Modelo modelo;

    private View view;

    /**
     * Crear la vista y el modelo del proyecto
     */
    public Controller() {
        view = new View();
        modelo = new Modelo();
    }

    @SuppressWarnings("resource")
    public void run() {
        Scanner lector = new Scanner(System.in);
        boolean fin = false;

        while (!fin) {
            view.printMenu();

            int option = lector.nextInt();

            switch (option) {
                case 1:

                    try {

                        long start = System.currentTimeMillis();
                        Queue<Comparendo> cola = modelo.cargarDatosPila();

                        long end = System.currentTimeMillis();

                        // Tiempo de carga
                        view.printMessage("Tiempo de carga (seg): " + (end - start) / 1000.0);
                        // Total datos cargados
                        view.printMessage("Total datos cargados: " + cola.darTamano());
                        // Mayor objectID
                        view.printMessage("Comparendo con el mayor Object Id: " + modelo.mayorObjectID());
                        // Zona Minimax
                        view.printMessage("Zona Minimax: " + " Menor Latitud= " + modelo.menorLatitud() +
                                " Menor Longitud= " + modelo.menorLongitud() + " Mayor Latitud= " + modelo.mayorLatitud() + " Mayor Longitud= " + modelo.mayorLongitud() + "\n");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    view.printMessage(" Ingrese la localidad en la que desea buscar.");
                    String loc = lector.next();
                    try {
                        view.printMessage("x "+modelo.buscarPorLocalidad(loc));
                    } catch (Exception e) {
                        e.getMessage();
                    }

                    break;
                case 3:


                default:
                    view.printMessage("--------- \n Opcion Invalida !! \n---------");
                    break;
            }
        }

    }
}