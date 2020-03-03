package view;


public class View 
{
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Datos");
			System.out.println("2. 1B : Consultar el primer comparendo que tiene una infracci�n dada");
			System.out.println("3. 2B : Consultar los comparendos registrados en el archivo dado el codigo de la infracci�n");
			System.out.println("4. 3B : Comparaci�n de comparendos por Infracci�n en servicio Particular y servicio P�blico");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}
