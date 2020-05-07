package model.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Arco;
import model.InformacionArco;
import model.InformacionVertice;
import model.data_structures.Edge;
import model.data_structures.SeparteChainingHashST;
import model.data_structures.UnGraph;
import model.data_structures.Vertex;

public class MallaVialBogota 
{
	// Atributos

	/**
	 * Atributo para crear un grafo no dirigido
	 */
	private UnGraph<String, InformacionVertice, InformacionArco> UnDiGraph;
	
	/**
	 * Atributo para crear un grafo no dirigido para el JSON
	 */
	private UnGraph<String, InformacionVertice, InformacionArco> UnDiGraph1;

	/**
	 * Tabla de hash donde se guardaran los vertices con su informacion
	 */
	@SuppressWarnings("unused")
	private SeparteChainingHashST<Integer, Vertex<String, InformacionVertice, InformacionArco>> vertices;

	/**
	 * Tabla de hash donde se guardaran los arcos con su informacion de costo
	 */
	@SuppressWarnings("unused")
	private SeparteChainingHashST<String, Edge<String, InformacionArco>> arcos;

	// Metodo Constructor

	/**
	 * Inicializa el grafo, la tablas de hash de vertices y arcos
	 */
	public MallaVialBogota()
	{
		UnDiGraph = new UnGraph<String, InformacionVertice, InformacionArco>();
		UnDiGraph1 = new UnGraph<String, InformacionVertice, InformacionArco>();
		vertices = new SeparteChainingHashST<Integer, Vertex<String, InformacionVertice, InformacionArco>>();
		arcos = new SeparteChainingHashST<String, Edge<String, InformacionArco>>();
	}

	// Metodos

	/**
	 * Obtener la distancia haversiana de acuerdo con la latitudes y longitudes que entran como parametro
	 * @param startLat Latitud de inicio
	 * @param startLong Longitud de inicio
	 * @param endLat Latitud de final
	 * @param endLong Longitud de final
	 * @return La distancia entre los dos puntos establecidos
	 */
	public double getDistanceHaversian(double startLat, double startLong, double endLat, double endLong) 
	{

		final int EARTH_RADIUS = 6371;
		double dLat  = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}
	
	/**
	 * Haversin funcion matematica
	 * @param val Valor de entrada
	 * @return El valor haversiano de la distancias
	 */
	public static double haversin(double val)
	{
        return Math.pow(Math.sin(val / 2), 2);
    }
	
	/**
	 * Metodo para cargar grafo 
	 */
	public void cargarGrafo()
	{
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<String> arcos =new ArrayList<String>();
		try
		{
			BufferedReader bf = new BufferedReader(new FileReader("./data/bogota_vertices.txt"));
			String linea;
			while((linea = bf.readLine()) != null)
			{
				vertices.add(linea);
			}
			bf.close();
	
			UnDiGraph = new UnGraph<String, InformacionVertice, InformacionArco>();
			int numeroVertices = vertices.size();
			
			// Clico que crea los vertices del grafo no dirigido
			for(int i = 0; vertices != null && i < numeroVertices; i++)
			{
				String lineaActual = vertices.get(i);
				String[] valores = lineaActual.split(",");
				String id = valores[0];
				double longitud = Double.parseDouble(valores[1]); 
				double latitud = Double.parseDouble(valores[2]); 
				UnDiGraph.addVertex(id, new InformacionVertice(longitud, latitud));
			}
			
			// Carga de arcos en el la grafo no dirigido
			BufferedReader bf1 = new BufferedReader(new FileReader("./data/bogota_arcos.txt"));
			String linea1;
			while((linea1 = bf1.readLine()) != null)
			{
				arcos.add(linea1);
			}
			bf1.close();
			
			int numeroArcos = arcos.size();
			for(int i = 0; arcos != null && i < numeroArcos; i++)
			{
				String lineaActual = arcos.get(i);
				String[] valores = lineaActual.split(" ");
				String id = valores[0];
				for(int j = 1; j < valores.length; j++)
				{
					double pLonIn = UnDiGraph.getInfoVertex(id).getLongitud();
					double pLatIn = UnDiGraph.getInfoVertex(id).getLatitud();
					double pLonFi = UnDiGraph.getInfoVertex(valores[j]).getLongitud();
					double pLatFi = UnDiGraph.getInfoVertex(valores[j]).getLatitud();

					double pCosto = getDistanceHaversian(pLatIn, pLonIn, pLatFi, pLonFi);
					UnDiGraph.addEdge(id, valores[j], new InformacionArco(pCosto));
				}
			}
			System.out.println("Numero de vertices: " + UnDiGraph.V());
			System.out.println("Numero de arcos: " + UnDiGraph.E());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que genera el archivo .json con los arcos y vertices del grafo
	 * @param filePath Direccion en donde se va a creear el archivo
	 * @param fileName Nombre del Archivo
	 */
	@SuppressWarnings("unchecked")
	public void generarJSON(String filePath, String fileName)
	{	
		cargarGrafo();
		JSONObject obj = new JSONObject();
		obj.put("type", "Collection Graph");
		obj.put("name", "UnDiGraph - Malla Vial Bogota");
		
		JSONArray list = new JSONArray();
		obj.put("Vertices", list);
		for(int i = 0; i < UnDiGraph.V(); i++)
		{
			JSONObject obj1 = new JSONObject();
			String id = i + "";
			obj1.put("Vertice", id);
			obj1.put("Longitud", UnDiGraph.getInfoVertex(id).getLongitud());
			obj1.put("Latitud", UnDiGraph.getInfoVertex(id).getLatitud());
			
			list.add(obj1);
		}
		
		ArrayList<String> arcos = new ArrayList<String>();
		ArrayList<Arco> arcos2 = new ArrayList<Arco>();
		BufferedReader bf1;
		try 
		{
			bf1 = new BufferedReader(new FileReader("./data/bogota_arcos.txt"));
			String linea1;
			while((linea1 = bf1.readLine()) != null)
			{
				arcos.add(linea1);
			}
			bf1.close();
			
			int numeroArcos = arcos.size();
			for(int i = 0; arcos != null && i < numeroArcos; i++)
			{
				String lineaActual = arcos.get(i);
				String[] valores = lineaActual.split(" ");
				String id = valores[0];
				for(int j = 1; j < valores.length; j++)
				{
					double pLonIn = UnDiGraph.getInfoVertex(id).getLongitud();
					double pLatIn = UnDiGraph.getInfoVertex(id).getLatitud();
					double pLonFi = UnDiGraph.getInfoVertex(valores[j]).getLongitud();
					double pLatFi = UnDiGraph.getInfoVertex(valores[j]).getLatitud();
					double pCosto = getDistanceHaversian(pLatIn, pLonIn, pLatFi, pLonFi);
					arcos2.add(new Arco(id, valores[j], pCosto));
				}
			}
			
			JSONArray list2 = new JSONArray();
			obj.put("Arcos", list2);
			for(int i = 0; i < arcos2.size(); i++)
			{
				JSONObject obj2 = new JSONObject();
				Arco actual = arcos2.get(i);
				obj2.put("Arco Salida", actual.getArcoSalida());
				obj2.put("Arco Entrada", actual.getArcoEntrada());
				obj2.put("Distancia Haversiana", actual.getCosto());
				
				list2.add(obj2);
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			File file = new File(filePath, fileName);
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(obj.toJSONString());
			bw.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error: "+ ex.toString());
		}
	}
	
	/**
	 * Ruta del archivo JSON creado en el punto anterior
	 */
//	public static String PATH = "./data/UnDiGraph_small.json";
	public static String PATH = "./data/UnDiGraph_Complete.json";
	
	/**
	 * Carga JSON del archivo generado en el requerimiento anterior
	 */
	public void cargarGrafoJSON()
	{
		UnDiGraph1 = new UnGraph<String,InformacionVertice,InformacionArco>();

		JsonReader reader;
		try 
		{
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("Vertices").getAsJsonArray();

			for(JsonElement e: e2) 
			{
				String id = e.getAsJsonObject().get("Vertice").getAsString();
				double longitud = e.getAsJsonObject().get("Longitud").getAsDouble();
				double latitud = e.getAsJsonObject().get("Latitud").getAsDouble();
				UnDiGraph1.addVertex(id, new InformacionVertice(longitud, latitud));
			}
			
			JsonArray e3 = elem.getAsJsonObject().get("Arcos").getAsJsonArray();
			
			for(JsonElement e: e3) 
			{
				String verticeSale = e.getAsJsonObject().get("Arco Salida").getAsString();
				String verticeEntra = e.getAsJsonObject().get("Arco Entrada").getAsString();
				double harvesiana = e.getAsJsonObject().get("Distancia Haversiana").getAsDouble();
				UnDiGraph1.addEdge(verticeSale, verticeEntra, new InformacionArco(harvesiana));
			}
		} 
		catch (FileNotFoundException  e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Numero de vertices: " + UnDiGraph1.V());
		System.out.println("Numero de arcos: " + UnDiGraph1.E());
	}
}
