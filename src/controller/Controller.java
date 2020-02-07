package controller;

import java.util.List;
import java.util.Scanner;

import model.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
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
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
				    modelo = new Modelo();
				    List<Comparendo> lista = modelo.cargarDatos();
					view.printMessage("Datos cargados: " + lista.size() + "\n");
					view.printMessage("Primer dato: " + lista.get(0) + "\n");
					view.printMessage("Ultimo dato: " + lista.get(lista.size() - 1 ) + "\n");
					break;
					
				case 2:
					fin = true;
					break;

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
