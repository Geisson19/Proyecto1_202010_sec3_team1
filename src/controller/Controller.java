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
				    
				    Comparendo mayorOBJID = modelo.buscarMayorComparendPorOBID();
				    
				    Cola<Comparendo> listaC = modelo.darDatosC();

				    view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0 + "\n");

				    view.printMessage("Total datos cargados: " + listaC.size() + "\n");
				    
				    view.printMessage("Elemento con el mayor OBJECTID: " + mayorOBJID.toString() + "\n");
				    
				    view.printMessage("Zona Minimax: (" + modelo.menorLatitud() + "," + modelo.menorLongitud() + ") y "
				    		+ ",("+ modelo.mayorLatitud() +","+ modelo.mayorLongitud() +")");
				    
					break;
				case 2:// caso 1B
					
					Scanner opcion1B = new Scanner(System.in);
					view.printMessage("Por favor, ingrese la infracción que desea buscar");
					String opcion = opcion1B.nextLine();
					Comparendo buscado = modelo.primerComparendoPorInfraccionDada(opcion);
					
					if (buscado == null)
						view.printMessage("No existe una infracción con ese código en nuestros registros");
					else
						view.printMessage(buscado.toString());

					break;
				case 3:// caso 2B
					
					break;
				case 4:// caso 3B
					
					break;
				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}