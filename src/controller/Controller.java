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
				
				    long start = System.currentTimeMillis();
					modelo.cargarDatos();
				    long end = System.currentTimeMillis();
				    
				    Cola<Comparendo> listaC = modelo.darDatosC();

				    view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0 + "\n");

				    view.printMessage("Total datos cargados: " + listaC.size() + "\n");
				    
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