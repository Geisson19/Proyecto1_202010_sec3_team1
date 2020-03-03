package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Comparendo;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	private Cola<Comparendo> datosC;

	public static String PATH = "./data/comparendos_dei_2018_small.geojson";

	public void cargarDatos() {

		datosC = new Cola<Comparendo>();

		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);
				datosC.enqueue(c);
			}

		}
		catch (FileNotFoundException | ParseException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public Cola<Comparendo> copiarDatos()
	{
		Cola<Comparendo> copia = new Cola<Comparendo>();
		for(int i = 0; i<datosC.size(); i++)
		{
			copia.enqueue(datosC.getIndex(i).darElemento());
		}
		return copia;
	}
	
	public Cola<Comparendo> darDatosC()
	{
		return datosC;
	}
	
	public Comparendo buscarMayorComparendPorOBID()
	{
		Cola<Comparendo> persistencia = copiarDatos();

		Comparendo comparendoActual = null;

		int mayor = 0;
		
		int actual = 0;
		
		Comparendo mayorComp = null;
		
		while(!persistencia.estaVacia())
		{
			
			comparendoActual = persistencia.dequeue();
			
			actual = comparendoActual.getObjectId();
			
			if(actual > mayor)
			{
				
				mayor = actual;
				mayorComp = comparendoActual;
				
			}
		
		}
		
		return mayorComp;
		
	}
	
	public double mayorLatitud()
	{
		Cola<Comparendo> persistencia = copiarDatos();

		Comparendo comparendoActual = null;

		double mayor = 0;
		
		double actual = 0;
		
		while(!persistencia.estaVacia())
		{
			
			comparendoActual = persistencia.dequeue();
			
			actual = comparendoActual.getLatitud();
			
			if(actual > mayor)
			{
				
				mayor = actual;
				
			}
		
		}
		return mayor;
		
	}
	
	public double menorLatitud()
	{
		Cola<Comparendo> persistencia = copiarDatos();

		Comparendo comparendoActual = null;

		double menor = 0;
		
		double actual = 0;
		
		while(!persistencia.estaVacia())
		{
			
			comparendoActual = persistencia.dequeue();
			
			actual = comparendoActual.getLatitud();
			
			if(actual < menor)
			{
				
				menor = actual;
				
			}
		
		}
		return menor;
		
	}
	
	public double mayorLongitud()
	{
		Cola<Comparendo> persistencia = copiarDatos();

		Comparendo comparendoActual = null;

		double mayor = 0;
		
		double actual = 0;
		
		while(!persistencia.estaVacia())
		{
			
			comparendoActual = persistencia.dequeue();
			
			actual = comparendoActual.getLongitud();
			
			if(actual > mayor)
			{
				
				mayor = actual;
				
			}
		
		}
		return mayor;
		
	}
	
	public double menorLongitud()
	{
		Cola<Comparendo> persistencia = copiarDatos();

		Comparendo comparendoActual = null;

		double menor = 0;
		
		double actual = 0;
		
		while(!persistencia.estaVacia())
		{
			
			comparendoActual = persistencia.dequeue();
			
			actual = comparendoActual.getLongitud();
			
			if(actual < menor)
			{
				
				menor = actual;
				
			}
		
		}
		return menor;
		
	}


	//Requerimientos PARTE B
	
	//1B
	public Comparendo primerComparendoPorInfraccionDada(String pInfraccion)
	{
		Cola<Comparendo> persistencia = copiarDatos();

		Comparendo comparendoActual = null;
		
		boolean encontro = false;
		
		while(!persistencia.estaVacia() && !encontro)
		{
			
			comparendoActual = persistencia.dequeue();
			
			if(comparendoActual.getInfraccion().equalsIgnoreCase(pInfraccion))
				encontro = true;
		
		}
		return encontro ? comparendoActual : null;
	}
	
	public Cola<Comparendo> consultarComparendosPorInfraccion(String pFechaHora)
	{
		return null;
	}

	public Cola<Comparendo> compararComparendoPorInfraccion()
	{
		return null;
	}

	public Cola<Comparendo> mostrarNumeroDeInfraccionParaUnTiempo(String pFechainicial, String pFechaFinal)
	{
		return null;
	}

	public Cola<Comparendo> informacionDeNComparendosPorInfraccionesEnOrden(String pFechaInicial, String pFechaFinal)
	{
		return null;
	}

	public Cola<Comparendo> ordenarHistograma()
	{
		return null;
	}

}

