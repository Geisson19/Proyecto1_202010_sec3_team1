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
import model.data_structures.Queue;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	private Queue<Comparendo> datosC;

	public static String PATH = "./data/comparendos_dei_2018.geojson";

	public void cargarDatos() {

		datosC = new Queue<Comparendo>();

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


	public Queue<Comparendo> copiarDatos()
	{
		Queue<Comparendo> copia = new Queue<Comparendo>();
		for(int i = 0; i<datosC.size(); i++)
		{
			copia.enqueue(datosC.getIndex(i).darElemento());
		}
		return copia;
	}

	public Queue<Comparendo> darDatosC()
	{
		return datosC;
	}

	public Comparendo buscarMayorComparendPorOBID()
	{
		Queue<Comparendo> persistencia = copiarDatos();

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
		Queue<Comparendo> persistencia = copiarDatos();

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
		Queue<Comparendo> persistencia = copiarDatos();

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
		Queue<Comparendo> persistencia = copiarDatos();

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
		Queue<Comparendo> persistencia = copiarDatos();

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
		Queue<Comparendo> persistencia = copiarDatos();

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


	//2B
	public Queue<Comparendo> darComparendosEnOrdenCronologico(String infraccion)
	{
		Comparendo actual = null;
		Queue<Comparendo> copia = copiarDatos();
		Queue<Comparendo> toReturn = new Queue<Comparendo>();
		while(!copia.estaVacia())
		{
			actual = copia.dequeue();
			if(actual.getInfraccion().equalsIgnoreCase(infraccion))
			{
				toReturn.enqueue(actual);
			}
		}
//		qicksort(toReturn);
		return toReturn;
	}


	public void qicksort(Queue<Comparendo> S)
	{
		int n = S.size();

		if(n<2) return;

		//divide

		Comparendo pivot = S.primeroNodo();

		Queue<Comparendo> L = new Queue<Comparendo>();
		Queue<Comparendo> E = new Queue<Comparendo>();
		Queue<Comparendo> G = new Queue<Comparendo>();


		while(!S.estaVacia())
		{
			Comparendo elemental = S.dequeue();
			int c = elemental.getFecha_hora().compareTo(pivot.getFecha_hora());
			if(c<0)
				L.enqueue(elemental);
			else if (c == 0)
			{
				E.enqueue(elemental);
			}
			else
			{
				G.enqueue(elemental);
			}
		}

		qicksort(L);
		qicksort(G);

		while(!L.estaVacia())
			S.enqueue(L.dequeue());
		while(!E.estaVacia())
			S.enqueue(E.dequeue());
		while(!G.estaVacia())
			S.enqueue(G.dequeue());
	}

}
