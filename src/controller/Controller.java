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
					
				    Long start = System.currentTimeMillis();
				    Queue<Comparendo> listaC = modelo.darDatosC();
				    
				    Long end = System.currentTimeMillis();
				    
				    view.printMessage("Tiempo de carga (seg): " + (end-start)/1000);

				    view.printMessage("Total datos cargados: " + listaC.darTamano() + "\n");

					view.printMessage("Primer dato: " + listaC.darPrimero().darElemento().toString() + "\n");
				    
				    
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