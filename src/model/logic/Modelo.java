package model.logic;

import
        java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.Node;
import model.data_structures.Queue;


/**
 * Definicion del modelo del mundo
 */
public class Modelo {

    private Queue<Comparendo> datos;

    public static String PATH = "./data/comparendos_dei_2018.geojson";

    public void cargarDatos() {

        datos = new Queue<Comparendo>();

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(PATH));
            JsonElement elem = JsonParser.parseReader(reader);
            JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


            SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");

            for (JsonElement e : e2) {
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
                datos.enQueue(c);
            }

        } catch (FileNotFoundException | ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public Queue<Comparendo> copiarDatos() {
        Queue<Comparendo> copia = new Queue<>();
        for (int i = 0; i < datos.darTamano(); i++) {
            copia.enQueue(datos.getIndex(i).darElemento());
        }
        return copia;
    }

    public Queue<Comparendo> darDatosC() {
        return datos;
    }


    // Metodos al cargar datos
    public Comparendo mayorObjectID() {

        Node actual = datos.darPrimero();
        Comparendo mayor = (Comparendo) actual.darElemento();

        while (actual != null) {
            Comparendo A = (Comparendo) actual.darElemento();

            if (mayor.getObjectId() < A.getObjectId()) {
                mayor = A;
            }

            actual = actual.darSiguiente();
        }
        return mayor;
    }

    public double mayorLatitud() {
        Node actual = datos.darPrimero();
        Comparendo mayor = (Comparendo) actual.darElemento();

        while (actual != null) {
            if (mayor.getLatitud() < ((Comparendo) actual.darElemento()).getLatitud()) {
                mayor = (Comparendo) actual.darElemento();
            }

            actual = actual.darSiguiente();
        }
        return mayor.getLatitud();
    }

    public double menorLatitud() {
        Node actual = datos.darPrimero();
        Comparendo mayor = (Comparendo) actual.darElemento();

        while (actual != null) {
            if (mayor.getLatitud() > ((Comparendo) actual.darElemento()).getLatitud()) {
                mayor = (Comparendo) actual.darElemento();
            }

            actual = actual.darSiguiente();
        }
        return mayor.getLatitud();
    }

    public double mayorLongitud() {
        Node actual = datos.darPrimero();
        Comparendo mayor = (Comparendo) actual.darElemento();

        while (actual != null) {
            if (mayor.getLongitud() < ((Comparendo) actual.darElemento()).getLongitud()) {
                mayor = (Comparendo) actual.darElemento();
            }

            actual = actual.darSiguiente();
        }
        return mayor.getLongitud();
    }

    public double menorLongitud() {
        Node actual = datos.darPrimero();
        Comparendo mayor = (Comparendo) actual.darElemento();

        while (actual != null) {
            if (mayor.getLongitud() > ((Comparendo) actual.darElemento()).getLongitud()) {
                mayor = (Comparendo) actual.darElemento();
            }

            actual = actual.darSiguiente();
        }
        return mayor.getLongitud();
    }

// Requerimientos Parte A

    // Parte 1A: Consultar el primer comparendo que aparezca en el archivo que tiene una LOCALIDAD dada.

    public Comparendo buscarPorLocalidad(String pLocalidad) throws Exception {
        Node actual = datos.darPrimero();
        Comparendo buscado = null;

        while (actual != null) {
            Comparendo A = (Comparendo) actual.darElemento();
            if (A.getLocalidad().equals(pLocalidad)) {
                buscado = A;
            }
            actual = actual.darSiguiente();
        }
        if (buscado == null) {
            throw new Exception("No se encontro ningun comparendo con la localidad dada.");
        } else {
            return buscado;
        }

    }

    //Parte 2A: Consultar los comparendos registrados en el archivo dada una FECHA_HORA.

    public Queue consultarPorFecha(Date pFecha) {
        Node actual = datos.darPrimero();
        Queue<Comparendo> cola = new Queue<>();

        while (actual != null) {
            Comparendo A = (Comparendo) actual.darElemento();
            if (A.getFecha_hora().compareTo(pFecha) == 0) {
                cola.enQueue(A);
            }
            actual = actual.darSiguiente();
        }
        Node ac = cola.darPrimero();
        while (ac != null) {
            Comparendo A = (Comparendo) ac.darElemento();
            Comparendo ASig = (Comparendo) ac.darSiguiente().darElemento();

            if (A.getInfraccion().compareTo(ASig.getInfraccion()) < 0) {
                Comparendo temp = (Comparendo) ac.darElemento();
                ac.cambiarElemento(ASig);
                ac.darSiguiente().cambiarElemento(temp);
            }
            ac = ac.darSiguiente();
        }
        return cola;
    }

    // Parte 3A: Comparar los comparendos, por cada código INFRACCION, en dos FECHA_HORA dadas.
    // Generar cola con los comparendos que tienen registros en alguna fecha que entra por parametro.

    public Queue darColaInfraccion(Date pFecha1, Date pFecha2) {

        Node actual = datos.darPrimero();
        Queue<Comparendo> cola = new Queue();

        while (actual != null) {
            Comparendo A = (Comparendo) actual.darElemento();

            if (A.getFecha_hora().equals(pFecha1) || A.getFecha_hora().equals(pFecha2)) {
                cola.enQueue(A);
            }

            actual = actual.darSiguiente();
        }

        return cola;
    }

    public void ordenarAlfabeticamenteCola3A(Queue cola) {

        Node actual = cola.darPrimero();

        while (actual != null) {
            Comparendo A = (Comparendo) actual.darElemento();
            Comparendo ASig = (Comparendo) actual.darSiguiente().darElemento();

            if (A.getInfraccion().compareToIgnoreCase(ASig.getInfraccion()) < 0) {
                Comparendo temp = (Comparendo) actual.darElemento();
                actual.cambiarElemento(ASig);
                actual.darSiguiente().cambiarElemento(temp);
            }


            actual = actual.darSiguiente();
        }

    }

    public ArrayList metodo3A(Queue colaOrdenada, Date pFecha1, Date pFecha2) {

        Node actual = colaOrdenada.darPrimero();
        Comparendo infra = (Comparendo) actual.darElemento();
        String infraccion = infra.getInfraccion();
        int numeroFecha1 = 0;
        int numeroFecha2 = 0;
        ArrayList listaFinal = new ArrayList();


        while (actual != null) {
            Comparendo A = (Comparendo) actual.darElemento();
            if (infraccion.equals(A.getInfraccion())) {

                String[] lista = null;

                if (A.getFecha_hora().equals(pFecha1)) {
                    numeroFecha1++;
                }
                if (A.getFecha_hora().equals(pFecha2)) {
                    numeroFecha2++;
                }

                lista[0] = A.getInfraccion();
                lista[1] = String.valueOf(numeroFecha1);
                lista[2] = String.valueOf(numeroFecha2);

                listaFinal.add(lista);

            } else {
                infraccion = A.getInfraccion();
                numeroFecha1 = 0;
                numeroFecha2 = 0;

                String[] lista = null;

                if (A.getFecha_hora().equals(pFecha1)) {
                    numeroFecha1++;
                }
                if (A.getFecha_hora().equals(pFecha2)) {
                    numeroFecha2++;
                }

                lista[0] = A.getInfraccion();
                lista[1] = String.valueOf(numeroFecha1);
                lista[2] = String.valueOf(numeroFecha2);

                listaFinal.add(lista);

            }

            actual = actual.darSiguiente();
        }
        return listaFinal;
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

            comparendoActual = persistencia.deQueue();

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
            actual = copia.deQueue();
            if(actual.getInfraccion().equalsIgnoreCase(infraccion))
            {
                toReturn.enQueue(actual);
            }
        }
//		qicksort(toReturn);
        return toReturn;
    }

    public Queue<String> localidades()
    {
        Queue<Comparendo> towork = copiarDatos();
        Queue<String> toReturn = new Queue<String>();

        Comparendo actual = null;

        if(!towork.estaVacia()){
            actual = towork.deQueue();
            toReturn.enQueue(actual.getLocalidad());
        }
        while(!towork.estaVacia())
        {
            actual = towork.deQueue();
            if(!estaEnCola(actual.getLocalidad(), toReturn))
            {
                toReturn.enQueue(actual.getLocalidad());
            }
        }
        return toReturn;
    }
    public boolean estaEnCola(String localidad, Queue<String> colaa)
    {
        while(!colaa.estaVacia())
        {
            if(colaa.deQueue().equals(localidad))
                return true;
        }
        return false;
    }


    public void qicksort(Queue<Comparendo> S)
    {
        int n = S.darTamano();

        if(n<2) return;

        //divide

        Comparendo pivot = (Comparendo) S.darPrimero().darElemento();

        Queue<Comparendo> L = new Queue<Comparendo>();
        Queue<Comparendo> E = new Queue<Comparendo>();
        Queue<Comparendo> G = new Queue<Comparendo>();


        while(!S.estaVacia())
        {
            Comparendo elemental = S.deQueue();
            int c = elemental.getFecha_hora().compareTo(pivot.getFecha_hora());
            if(c<0)
                L.enQueue(elemental);
            else if (c == 0)
            {
                E.enQueue(elemental);
            }
            else
            {
                G.enQueue(elemental);
            }
        }

        qicksort(L);
        qicksort(G);

        while(!L.estaVacia())
            S.enQueue(L.deQueue());
        while(!E.estaVacia())
            S.enQueue(E.deQueue());
        while(!G.estaVacia())
            S.enQueue(G.deQueue());
    }

}