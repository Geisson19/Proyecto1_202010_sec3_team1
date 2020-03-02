package view;

import model.logic.Modelo;

public class View 
{
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Datos.");
			System.out.println("2. Consultar el primer comparendo que aparezca en el archivo que tiene una LOCALIDAD dada.");

		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}
