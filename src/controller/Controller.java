package controller;

import java.util.Scanner;

<<<<<<< HEAD
import model.logic.Comparendo;
=======
import model.Comparendo;
>>>>>>> nueva
import model.data_structures.Queue;
import model.logic.Modelo;

import view.View;

public class Controller {

<<<<<<< HEAD
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
=======
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
		
	@SuppressWarnings("resource")
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
				    
				    Comparendo mayorOBJID = modelo.buscarMayorComparendPorOBID();
				    
				    Queue<Comparendo> listaC = modelo.darDatosC();

				    view.printMessage("Tiempo de carga (seg): " + (end-start)/1000 + "\n");

				    view.printMessage("Total datos cargados: " + listaC.size() + "\n");

				    view.printMessage("Elemento con el mayor OBJECTID: " + mayorOBJID.toString() + "\n");
				    
				    view.printMessage("Zona Minimax: (" + modelo.menorLatitud() + "," + modelo.menorLongitud() + ") y "
				    		+ "("+ modelo.mayorLatitud() +","+ modelo.mayorLongitud() +")");
				    
					break;
				case 2:// caso 1B
					
					Scanner opcion1B = new Scanner(System.in);
					view.printMessage("Por favor, ingrese la infracci�n que desea buscar");
					String opcion = opcion1B.nextLine();
					Comparendo buscado = modelo.primerComparendoPorInfraccionDada(opcion);
					
					if (buscado == null)
						view.printMessage("No existe una infracci�n con ese c�digo en nuestros registros");
					else
						view.printMessage(buscado.toString());

					break;
				case 3:// caso 2B
					Scanner opcion2B = new Scanner(System.in);
					view.printMessage("Por favor, ingrese la infracci�n por la que desea buscar");
					String linea2B = opcion2B.nextLine();
					
					Queue<Comparendo> orden = modelo.darComparendosEnOrdenCronologico(linea2B);
					
					view.printMessage(""+orden.size());
					
					while(!orden.estaVacia())
					{
						view.printMessage(orden.dequeue().toString());
					}

					break;
				case 4:// caso 3B
					
					break;
				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
>>>>>>> nueva
}