package model.logic;

import java.io.FileReader;
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

    public static String PATH = "./data/comparendos_dei_2018_small.geojson";

    private Queue<Comparendo> datos;

    // Solución de carga de Datos implementada en la clase.

    public Queue<Comparendo> cargarDatosPila() {
        datos = new Queue<>();

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

                Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRAC, LOCALIDAD, longitud, latitud);
                datos.enQueue(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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


    // Requerimientos Parte C
    // Parte 1C: Mostrar el número de comparendos por cada código INFRACCION en una LOCALIDAD dada, para un periodo de tiempo dado.
    // Metodo para generar los comparendos que estan entre esas fechas. Ademas la cola es ordenada alfabeticamente segun infracción.

    public Queue darCola(Date pFechaInicial, Date pFechaFinal) {

        Node actual = datos.darPrimero();
        Queue<Comparendo> cola = new Queue();

        while (actual != null) {
            Comparendo A = (Comparendo) actual.darElemento();

            if ((pFechaInicial.compareTo(A.getFecha_hora()) <= 0) && A.getFecha_hora().compareTo(pFechaFinal) <= 0) {
                cola.enQueue(A);
            }

            actual = actual.darSiguiente();
        }
        ordenarAlfabeticamenteCola3A(cola);
        return cola;
    }

    public ArrayList darArrayListComparendosPorInfraccion(Queue<Comparendo> cola) {

        Node actual = cola.darPrimero();
        Comparendo A = (Comparendo) actual.darElemento();
        String infra = A.getInfraccion();

        ArrayList listaFinal = new ArrayList();
        Comparendo[] lista = null;

        while (actual != null) {

            int i = 0;

            while (infra.equals(A.getInfraccion())) {

                A = (Comparendo) actual.darElemento();
                lista[i] = A;
                i++;
                actual = actual.darSiguiente();
            }

            infra = ((Comparendo) actual.darElemento()).getInfraccion();
            listaFinal.add(lista);
            lista = null;

        }
        return listaFinal;
    }

    public ArrayList metodo1C(Queue<Comparendo> cola) {

        ArrayList lista = darArrayListComparendosPorInfraccion(cola);
        ArrayList listaFinal = new ArrayList();
        Comparendo[] comparendos = null;
        String[] listaC = null;

        for (int i = 0; i < lista.size(); i++) {
            comparendos = (Comparendo[]) lista.get(i);
            String codigoInfraccion = comparendos[0].getInfraccion();
            int cantidad = comparendos.length;
            listaC[0] = codigoInfraccion;
            listaC[1] = String.valueOf(cantidad);
            listaFinal.add(listaC);
            listaC = null;


        }

        return listaFinal;
    }


}