package view;


public class View 
{
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Datos");
			System.out.println("1B. Consultar el primer comparendo que tiene una infracción dada");
			System.out.println("2B. Consultar los comparendos registrados en el archivo dado el codigo de la infracción");
			System.out.println("3B– Comparación de comparendos por Infracción en servicio Particular y servicio Público");	
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}
