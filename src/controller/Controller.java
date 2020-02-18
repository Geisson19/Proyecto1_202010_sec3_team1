package controller;

import java.util.Scanner;

import model.Comparendo;
import model.logic.Cola;
import model.logic.Modelo;

import view.View;

public class Controller {

	private Modelo modelo;
	
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
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
				    modelo = new Modelo();
					modelo.cargarDatos();
					

				    long start = System.currentTimeMillis();
				    Cola<Comparendo> listaC = modelo.darDatosC();
				    
				    long end = System.currentTimeMillis();
				    
					double minlon, minlat, maxlon, maxlat;
					
					minlon = modelo.menorLongitud();
					
					minlat = modelo.menorLatitud();
					
					maxlon = modelo.mayorLongitud();
					
					maxlat = modelo.mayorLatitud();
				    
				    view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0 + "\n");

				    view.printMessage("Total datos cargados: " + listaC.size() + "\n");

					view.printMessage("Comparendo con el OBJECT ID más alto: " + modelo.buscarMayorComparendPorOBID().toString() + "\n");

					view.printMessage("Rectangulo Minimax: " + "(" + minlat + "," + minlon + ")" + "(" + maxlat + "," + maxlon + ")");

					
					
					break;
				case 2:
					
					break;
				case 3:
					

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}