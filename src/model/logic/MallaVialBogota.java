package model.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Arco;
import model.InformacionArco;
import model.InformacionVertice;
import model.data_structures.ComponentConnected;
import model.data_structures.DijkstraSP;
import model.data_structures.Edge;
import model.data_structures.LinkedQueue;
import model.data_structures.PrimMST;
import model.data_structures.PrimMST2;
import model.data_structures.SeparteChainingHashST;
import model.data_structures.UnGraph;
import model.data_structures.Vertex;
import model.Comparendo;
import model.EstacionPolicia;

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
	private SeparteChainingHashST<Integer, Vertex<String, InformacionVertice, InformacionArco>> vertices1;

	/**
	 * Tabla de hash donde se guardaran los arcos con su informacion de costo
	 */
	@SuppressWarnings("unused")
	private SeparteChainingHashST<String, Edge<String, InformacionArco>> arcos1;

	/**
	 * Tabla de hash donde se guardan los comparendos segun el vertice mas cercano
	 */
	private SeparteChainingHashST<String, ArrayList<Comparendo>> comparendosVertice;

	/**
	 * Tabla de hash donde se guardan los estaciones segun el vertic mas cercano
	 */
	private SeparteChainingHashST<String, EstacionPolicia> estacionesVertice;

	/**
	 * Tabla de hash donde se guardan el id del arco con el numero de comparendos
	 */
	private SeparteChainingHashST<String, Integer> comparendosArcos;

	/**
	 * Comparendos en una cola
	 */
	private LinkedQueue<Comparendo> comparendos;

	/**
	 * Instancia del Modelo
	 */
	private Modelo modelo;

	// Metodo Constructor

	/**
	 * Inicializa el grafo, la tablas de hash de vertices y arcos
	 */
	public MallaVialBogota()
	{
		UnDiGraph = cargarGrafo();
		UnDiGraph1 = cargarGrafo2();
		vertices1 = new SeparteChainingHashST<Integer, Vertex<String, InformacionVertice, InformacionArco>>();
		arcos1 = new SeparteChainingHashST<String, Edge<String, InformacionArco>>();
		comparendosVertice = new SeparteChainingHashST<String, ArrayList<Comparendo>>();
		estacionesVertice = new SeparteChainingHashST<String, EstacionPolicia>();
		comparendosArcos = new SeparteChainingHashST<String, Integer>();
		modelo = new Modelo();
		//		adicionarComparendosAVertices();
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
	 * Metodo para cargar grafo costo del arco distancia
	 */
	public UnGraph<String, InformacionVertice, InformacionArco> cargarGrafo()
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
			//			System.out.println("Numero de vertices: " + UnDiGraph.V());
			//			System.out.println("Numero de arcos: " + UnDiGraph.E());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return UnDiGraph;
	}

	/**
	 * Metodo para cargar grafo con costo de comparendos
	 */
	public UnGraph<String, InformacionVertice, InformacionArco> cargarGrafo2()
	{
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<String> arcos =new ArrayList<String>();
		ArrayList<String> comparendosArcos = new ArrayList<String>();

		try
		{
			BufferedReader bf = new BufferedReader(new FileReader("./data/ComparendosArcos.txt"));
			String linea;
			while((linea = bf.readLine()) != null)
			{
				comparendosArcos.add(linea);
			}
			bf.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			BufferedReader bf = new BufferedReader(new FileReader("./data/bogota_vertices.txt"));
			String linea;
			while((linea = bf.readLine()) != null)
			{
				vertices.add(linea);
			}
			bf.close();

			UnDiGraph1 = new UnGraph<String, InformacionVertice, InformacionArco>();
			int numeroVertices = vertices.size();

			// Clico que crea los vertices del grafo no dirigido
			for(int i = 0; vertices != null && i < numeroVertices; i++)
			{
				String lineaActual = vertices.get(i);
				String[] valores = lineaActual.split(",");
				String id = valores[0];
				double longitud = Double.parseDouble(valores[1]); 
				double latitud = Double.parseDouble(valores[2]); 
				UnDiGraph1.addVertex(id, new InformacionVertice(longitud, latitud));
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
			int arcosCom = 0;
			for(int i = 0; arcos != null && i < numeroArcos; i++)
			{
				String lineaActual = arcos.get(i);
				String[] valores = lineaActual.split(" ");
				String id = valores[0];
				for(int j = 1; j < valores.length;)
				{
					String valor = comparendosArcos.get(arcosCom);
					String[] costo = valor.split(",");
					double pCosto = Double.parseDouble(costo[1]);
					UnDiGraph1.addEdge(id, valores[j], new InformacionArco(pCosto));
					j++;
					arcosCom++;
				}
			}
//			System.out.println("Numero de vertices: " + UnDiGraph1.V());
//			System.out.println("Numero de arcos: " + UnDiGraph1.E());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return UnDiGraph1;
	}

	/**
	 * Adicionar comparendos a los vertices
	 */
	public void adicionarComparendosAVertices()
	{
		comparendos = modelo.cargarDatos3();
		FileWriter fichero = null;
		PrintWriter pw = null;

		try
		{
			fichero = new FileWriter("./data/comparendos.txt");
			pw = new PrintWriter(fichero);

			for(int i = 0; i < UnDiGraph.V(); i++)
			{
				Vertex<String,InformacionVertice,InformacionArco> actualVertice = UnDiGraph.getInfoVertexV(i+"");

				Iterator<Comparendo> comp = comparendos.iterator();
				while(comp.hasNext())
				{
					Comparendo actual = comp.next();
					double distancia1 = getDistanceHaversian(actualVertice.getValorVertice().getLatitud(), actualVertice.getValorVertice().getLongitud(), actual.getLatitud(), actual.getLongitud());				
					if(distancia1 < 0.025 )
					{	
						actualVertice.adicionarComparendo(actual);
					}
				}
				pw.println(i + "," + actualVertice.tamañoComparendos()); 
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error: "+ ex.toString());
		}
		finally
		{
			try
			{
				if(null != fichero)
				{
					fichero.close();
				}
			}
			catch( Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}

	/**
	 * Adicionar los comparendos al grafo en los arcos
	 */
	public void adicionarComparendosArcos()
	{
		ArrayList<String> comparendosVer = new ArrayList<String>();

		try
		{
			BufferedReader bf = new BufferedReader(new FileReader("./data/ComparendosVertices.txt"));
			String linea;
			while((linea = bf.readLine()) != null)
			{
				comparendosVer.add(linea);
			}
			bf.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try
		{
			fichero = new FileWriter("./data/ComparendosArcos.txt");
			pw = new PrintWriter(fichero);
			
			Iterator<Edge<String,InformacionArco>> it = UnDiGraph.getArcosGrafo().Vals().iterator();
			int i = 0;
			while(it.hasNext())
			{
				Edge<String,InformacionArco> actualArco = it.next();
				String inicio = actualArco.getIdVerticeInicio();
				String finalv = actualArco.getIdVerticeFinal();
				int cont = 0;
				for(int j =0; j < comparendosVer.size();j++)
				{
					String actual = comparendosVer.get(j);
					String[] compa = actual.split(",");
					if(compa[0].equals(inicio))
					{
						cont += Integer.parseInt(compa[1]);
					}
					
					if(compa[0].equals(finalv))
					{
						cont += Integer.parseInt(compa[1]);
					}
				}
				pw.println(i + "," + cont);
				i++;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error: "+ ex.toString());
		}
		finally
		{
			try
			{
				if(null != fichero)
				{
					fichero.close();
				}
			}
			catch( Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}	

	/**
	 * Adicionar los comparendos al grafo en los arcos
	 */
	public ArrayList<Integer> adiciona()
	{
		ArrayList<Integer> tam =new ArrayList<Integer>();
		int num = 0;

		Iterator<Edge<String,InformacionArco>> it = UnDiGraph.getArcosGrafo().Vals().iterator();
		while(it.hasNext())
		{
			Edge<String,InformacionArco> actualArco = it.next();
			Vertex<String,InformacionVertice,InformacionArco> vertice1 = UnDiGraph.getInfoVertexV(actualArco.getIdVerticeInicio());
			Vertex<String,InformacionVertice,InformacionArco> vertice2 = UnDiGraph.getInfoVertexV(actualArco.getIdVerticeFinal());
			num = vertice1.tamañoComparendos() + vertice2.tamañoComparendos();
			actualArco.setCostoCom(num);
			tam.add(num);
		}
		return tam;
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
	public UnGraph<String,InformacionVertice,InformacionArco> cargarGrafoJSON()
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

		return UnDiGraph1;
	}

	/**
	 * Muestra la informacion con el mayor ID del vertice
	 * @return La informacion del veritice con el mayor ID
	 */
	public String darIdMayorVertice()
	{
		String mensaje = " ";
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		Vertex<String,InformacionVertice,InformacionArco> actual = grafo.getInfoVertexV("0");

		for(int i = 0; i < grafo.V() ; i++)
		{
			String key = i + "";
			Vertex<String,InformacionVertice,InformacionArco> elemento = grafo.getInfoVertexV(key);
			int el1 = Integer.parseInt(elemento.getIdVertice());
			int el2 = Integer.parseInt(actual.getIdVertice());
			if(el1 > el2)
			{
				actual = elemento;
			}
		}

		String vertice = actual.getIdVertice();
		mensaje = vertice + ", " + grafo.getInfoVertex(vertice).getLatitud() + ", " + grafo.getInfoVertex(vertice).getLongitud();

		return mensaje;
	}

	/**
	 * Muestra la informacion con el mayor ID del arco
	 * @return La informacion del veritice con el mayor ID
	 */
	public String darIdMayorArco()
	{
		String mensaje = " ";
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		SeparteChainingHashST<Integer, Edge<String, InformacionArco>> actual = cargarGrafo().getArcosGrafo();

		Iterator<Edge<String, InformacionArco>> it = actual.Vals().iterator();
		while(it.hasNext())
		{
			Edge<String, InformacionArco> elemento = it.next();
			mensaje = grafo.E() + ", " + elemento.getIdVerticeInicio() + ", " + elemento.getIdVerticeFinal();
		}

		return mensaje;
	}

	/**
	 * Dada una localizacion geografica con latitud y longitud, se busca el id del vertice de la malla vial mas cercano por distancia haversina
	 * @return Id del vertice mas cercano
	 */
	public String darIdLocalizacion(double pLatitud, double pLongitud)
	{
		String mensaje = " ";
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		Vertex<String,InformacionVertice,InformacionArco> actual = grafo.getInfoVertexV("0");

		for(int i = 0; i < grafo.V() ; i++)
		{
			String key = i + "";
			Vertex<String,InformacionVertice,InformacionArco> elemento = grafo.getInfoVertexV(key);

			double distancia1 = getDistanceHaversian(actual.getValorVertice().getLatitud(), actual.getValorVertice().getLongitud(), pLatitud, pLongitud);
			double distancia2 = getDistanceHaversian(elemento.getValorVertice().getLatitud(), elemento.getValorVertice().getLongitud(), pLatitud, pLongitud);
			if(distancia2 < distancia1)
			{
				actual = elemento;
			}
		}

		String vertice = actual.getIdVertice();
		mensaje = vertice;

		return mensaje;
	}

	/**
	 * Asociar comparendos al vertice mas cercano de donde fueron tomados los comparendos
	 */
	public void asociarComparendosVertice()
	{
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		LinkedQueue<Comparendo> comparendos = modelo.cargarDatos2();
		int NumComparendos = comparendos.getSize();
		int j = 0;

		for(int i = 0; i < grafo.V(); i++)
		{
			String key = i + "";
			Vertex<String,InformacionVertice,InformacionArco> actualVertice = grafo.getInfoVertexV(key);
			ArrayList<Comparendo> listaComparendos = new ArrayList<Comparendo>();

			Iterator<Comparendo> it = comparendos.iterator();
			while(it.hasNext())
			{
				Comparendo actualComparendo = it.next();
				double distancia1 = getDistanceHaversian(actualVertice.getValorVertice().getLatitud(), actualVertice.getValorVertice().getLongitud(), actualComparendo.getLatitud(), actualComparendo.getLongitud());				
				if(distancia1 < 0.025 )
				{
					listaComparendos.add(actualComparendo);
					j++;

					if(j == NumComparendos)
					{
						break;
					}
				}
			}
			if(comparendosVertice.contains(key) == false)
			{
				comparendosVertice.put(key, listaComparendos);
			}		

			if(j == NumComparendos)
			{
				break;
			}
		}
	}

	/**
	 * Asociar comparendos al vertice mas cercano de donde fueron tomados los comparendos
	 * @return Tabla de hash con los comparendos asociados a los vertices
	 */
	public SeparteChainingHashST<String, ArrayList<Comparendo>> asociarComparendosVertice1()
	{
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		LinkedQueue<Comparendo> comparendos = modelo.cargarDatos2();
		SeparteChainingHashST<String, ArrayList<Comparendo>> nueva = comparendosVertice;
		int NumComparendos = comparendos.getSize();
		int j = 0;

		for(int i = 0; i < grafo.V(); i++)
		{
			String key = i + "";
			Vertex<String,InformacionVertice,InformacionArco> actualVertice = grafo.getInfoVertexV(key);
			ArrayList<Comparendo> listaComparendos = new ArrayList<Comparendo>();

			Iterator<Comparendo> it = comparendos.iterator();
			while(it.hasNext())
			{
				Comparendo actualComparendo = it.next();
				double distancia1 = getDistanceHaversian(actualVertice.getValorVertice().getLatitud(), actualVertice.getValorVertice().getLongitud(), actualComparendo.getLatitud(), actualComparendo.getLongitud());				
				if(distancia1 < 0.025 )
				{
					listaComparendos.add(actualComparendo);
					j++;

					if(j == NumComparendos)
					{
						break;
					}
				}
			}
			if(nueva.contains(key) == false)
			{
				nueva.put(key, listaComparendos);
			}		

			if(j == NumComparendos)
			{
				break;
			}
		}
		return nueva;
	}

	/**
	 * Asociar las estaciones de policia al vertice mas cercano
	 */
	public void asociarEstacionesVertice()
	{
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		LinkedQueue<EstacionPolicia> estaciones = modelo.cargarDatosEstacionesPolicia2();

		for(int i = 0; i < grafo.V() ; i++)
		{
			String key = i + "";
			Vertex<String,InformacionVertice,InformacionArco> actualVertice = grafo.getInfoVertexV(key);

			Iterator<EstacionPolicia> it = estaciones.iterator();
			while(it.hasNext())
			{
				EstacionPolicia actualEstacion = it.next();
				double distancia1 = getDistanceHaversian(actualVertice.getValorVertice().getLatitud(), actualVertice.getValorVertice().getLongitud(), actualEstacion.getLatitud(), actualEstacion.getLongitud());				
				if(distancia1 < 0.05 )
				{	
					if(estacionesVertice.contains(key) == false)
					{
						estacionesVertice.put(key, actualEstacion);
					}
				}
			}
		}
	}

	/**
	 * Asociar las estaciones de policia al vertice mas cercano
	 * @return Tabla de hash que asigna a cada vertice la estacion mas cercana
	 */
	public SeparteChainingHashST<String, EstacionPolicia> asociarEstacionesVertice1()
	{
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		LinkedQueue<EstacionPolicia> estaciones = modelo.cargarDatosEstacionesPolicia2();
		SeparteChainingHashST<String, EstacionPolicia> nueva = estacionesVertice;

		for(int i = 0; i < grafo.V() ; i++)
		{
			String key = i + "";
			Vertex<String,InformacionVertice,InformacionArco> actualVertice = grafo.getInfoVertexV(key);

			Iterator<EstacionPolicia> it = estaciones.iterator();
			while(it.hasNext())
			{
				EstacionPolicia actualEstacion = it.next();
				double distancia1 = getDistanceHaversian(actualVertice.getValorVertice().getLatitud(), actualVertice.getValorVertice().getLongitud(), actualEstacion.getLatitud(), actualEstacion.getLongitud());				
				if(distancia1 < 0.05 )
				{	
					if(nueva.contains(key) == false)
					{
						nueva.put(key, actualEstacion);
					}
				}
			}
		}

		return nueva;
	}

	/**
	 * Asociar el total de comparendos entre los vértices que lo conenctan teniendo en cuenta la latitud y longitud de los vertices
	 */
	public SeparteChainingHashST<String, Integer> asociarComparendosArco1()
	{
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		SeparteChainingHashST<Integer, Edge<String, InformacionArco>> actual = grafo.getArcosGrafo();
		SeparteChainingHashST<String, Integer> nuevo = comparendosArcos;
		SeparteChainingHashST<String, ArrayList<Comparendo>> nueva = asociarComparendosVertice1();

		int i = 0;
		Iterator<Edge<String, InformacionArco>> it = actual.Vals().iterator();
		while(it.hasNext() && i < grafo.E())
		{
			Edge<String, InformacionArco> elemento = it.next();
			Vertex<String,InformacionVertice,InformacionArco> inicioVertice = grafo.getInfoVertexV(elemento.getIdVerticeInicio());
			Vertex<String,InformacionVertice,InformacionArco> finalVertice = grafo.getInfoVertexV(elemento.getIdVerticeFinal());

			int numeroComparendosArco = 0;
			if(nueva.get(finalVertice.getIdVertice()) != null)
			{
				int valorVertexIn = nueva.get(inicioVertice.getIdVertice()).size();

				int valorVertexFn = nueva.get(finalVertice.getIdVertice()).size();
				numeroComparendosArco = valorVertexIn + valorVertexFn;
			}

			i++;
			String key = i + "";
			nuevo.put(key, numeroComparendosArco);
		}
		return nuevo;
	}

	/**
	 * Asociar el total de comparendos entre los vértices que lo conenctan teniendo en cuenta la latitud y longitud de los vertices
	 */
	public void asociarComparendosArcos()
	{
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		SeparteChainingHashST<Integer, Edge<String, InformacionArco>> actual = grafo.getArcosGrafo();
		SeparteChainingHashST<String, ArrayList<Comparendo>> nueva = asociarComparendosVertice1();

		int i = 0;
		Iterator<Edge<String, InformacionArco>> it = actual.Vals().iterator();
		while(it.hasNext() && i < grafo.E())
		{
			Edge<String, InformacionArco> elemento = it.next();
			Vertex<String,InformacionVertice,InformacionArco> inicioVertice = grafo.getInfoVertexV(elemento.getIdVerticeInicio());
			Vertex<String,InformacionVertice,InformacionArco> finalVertice = grafo.getInfoVertexV(elemento.getIdVerticeFinal());

			int numeroComparendosArco = 0;
			if(nueva.get(finalVertice.getIdVertice()) != null)
			{
				int valorVertexIn = nueva.get(inicioVertice.getIdVertice()).size();

				int valorVertexFn = nueva.get(finalVertice.getIdVertice()).size();
				numeroComparendosArco = valorVertexIn + valorVertexFn;
			}

			i++;
			String key = i + "";
			comparendosArcos.put(key, numeroComparendosArco);
		}
	}

	/**
	 * Inicializar el grafo malla vial
	 */
	public void inicializarGrafo()
	{
		UnGraph<String, InformacionVertice, InformacionArco> grafo = cargarGrafo();
		UnDiGraph = grafo;
	}

	/**
	 * Aproxima la coordenada a un verice del grafo correspondiente
	 * @param pCoordenada Coordenadas ingresada por el usuario
	 * @return Una nueva coordenada que se encuentra en el grafo
	 */
	public String aproximarCordenadasVerticesGrafo(String pCoordenada)
	{
		inicializarGrafo();
		String aproxCoordenada = "";

		String[] coordenadas = pCoordenada.split(",");
		double latitud = Double.parseDouble(coordenadas[0]);
		double longitud = Double.parseDouble(coordenadas[1]);

		SeparteChainingHashST<String,Vertex<String,InformacionVertice,InformacionArco>> veticesGrafo = UnDiGraph.getVerticesGrafo();
		double temporalDistancia = Double.MAX_VALUE;

		Iterator<Vertex<String,InformacionVertice,InformacionArco>> it = veticesGrafo.Vals().iterator();
		while(it.hasNext())
		{
			Vertex<String,InformacionVertice,InformacionArco> actualVertice = it.next();
			double distanciaHaversiana = getDistanceHaversian(actualVertice.getValorVertice().getLatitud(), actualVertice.getValorVertice().getLongitud(), latitud, longitud);

			if(distanciaHaversiana <= temporalDistancia)
			{
				temporalDistancia = distanciaHaversiana;
				aproxCoordenada = actualVertice.getValorVertice().getLatitud() + "," + actualVertice.getValorVertice().getLongitud();
			}
		}

		return aproxCoordenada;
	}

	/**
	 * Aproxima la coordenada a un verice del grafo correspondiente
	 * @param pCoordenada Coordenadas ingresada por el usuario
	 * @return Una nueva coordenada que se encuentra en el grafo
	 */
	public Vertex<String,InformacionVertice,InformacionArco> aproximarCordenadasVerticesGrafoVer(String pCoordenada)
	{
		inicializarGrafo();
		Vertex<String,InformacionVertice,InformacionArco> vertice = null;

		String[] coordenadas = pCoordenada.split(",");
		double latitud = Double.parseDouble(coordenadas[0]);
		double longitud = Double.parseDouble(coordenadas[1]);

		SeparteChainingHashST<String,Vertex<String,InformacionVertice,InformacionArco>> veticesGrafo = UnDiGraph.getVerticesGrafo();
		double temporalDistancia = Double.MAX_VALUE;

		Iterator<Vertex<String,InformacionVertice,InformacionArco>> it = veticesGrafo.Vals().iterator();
		while(it.hasNext())
		{
			Vertex<String,InformacionVertice,InformacionArco> actualVertice = it.next();
			double distanciaHaversiana = getDistanceHaversian(actualVertice.getValorVertice().getLatitud(), actualVertice.getValorVertice().getLongitud(), latitud, longitud);

			if(distanciaHaversiana <= temporalDistancia)
			{
				temporalDistancia = distanciaHaversiana;
				vertice = actualVertice;
			}
		}

		return vertice;
	}

	/**
	 * Obtener el camino de costo minimo entre dos ubicaciones geograficas por distancia
	 * @param pOrigen Punto de origen ingresado por el usuario
	 * @param pDestino Punto de llegada ingresado por el usuario
	 */
	public void grafoMenorDistancia(Vertex<String,InformacionVertice,InformacionArco> pOrigen, Vertex<String,InformacionVertice,InformacionArco> pDestino)
	{
		String numVerticeInicio = pOrigen.getIdVertice();
		String numVerticeFinal = pDestino.getIdVertice();

		int numeroVertices = 0;
		int numeroArcos = 0;
		double menorDistancia = Double.MAX_VALUE;
		double promedioDistancia = 0;
		ArrayList<Vertex<String,InformacionVertice,InformacionArco>> verticesRecorridos = new ArrayList<Vertex<String,InformacionVertice,InformacionArco>>();


		DijkstraSP<String> dijkstra = new DijkstraSP<String>(UnDiGraph, numVerticeInicio);

		Iterable<Edge<String, InformacionArco>> recorrerRuta = dijkstra.pathTo(UnDiGraph.getInfoVertexInfo(numVerticeFinal), UnDiGraph);
		if(recorrerRuta != null)
		{
			Iterator<Edge<String, InformacionArco>> it = recorrerRuta.iterator();
			while(it.hasNext())
			{
				Edge<String, InformacionArco> actualArco = it.next();
				Vertex<String,InformacionVertice, InformacionArco> vertice1 = UnDiGraph.getInfoVertexV(actualArco.getIdVerticeInicio());
				Vertex<String,InformacionVertice, InformacionArco> vertice2 = UnDiGraph.getInfoVertexV(actualArco.getIdVerticeFinal());
				double costo = actualArco.getCostArc().getCosto();

				if(!verticesRecorridos.contains(vertice1))
				{
					verticesRecorridos.add(vertice1);
					numeroVertices++;
				}
				if(!verticesRecorridos.contains(vertice2))
				{
					verticesRecorridos.add(vertice2);
					numeroVertices++;
				}

				if(costo <= menorDistancia)
				{
					menorDistancia = costo;
				}

				numeroArcos++;
				promedioDistancia += costo;
			}
		}

		promedioDistancia = promedioDistancia / numeroArcos;

		System.out.println("El numero de vertices entre los dos puntos ingresados es: " + numeroVertices);
		System.out.println("Los vertices recorridos entre los puntos son:");
		for(int i = 0; i < verticesRecorridos.size(); i++)
		{
			Vertex<String,InformacionVertice,InformacionArco> actual = verticesRecorridos.get(i);
			System.out.println(actual.getIdVertice() + ", " + actual.getValorVertice().getLatitud() + ", " + actual.getValorVertice().getLongitud());
		}
		System.out.println("La distancia con el menor costo mínimo es de: " + menorDistancia);
		System.out.println("La distancia estimana es de: " + promedioDistancia);
	}

	/**
	 * Crear grafo para poder ser pintado en el mapa correspodientemente
	 * @param pOrigen Origen establecido por el usuario
	 * @param pDestino Destino establecido por el usuario
	 * @return Grafo para ser pintado en el mapa
	 */
	public UnGraph<String,InformacionVertice,InformacionArco> grafoMenorDistanciaPintar(Vertex<String,InformacionVertice,InformacionArco> pOrigen, Vertex<String,InformacionVertice,InformacionArco> pDestino)
	{
		String numVerticeInicio = pOrigen.getIdVertice();
		String numVerticeFinal = pDestino.getIdVertice();

		UnGraph<String,InformacionVertice,InformacionArco> grafoDistancia = new UnGraph<String,InformacionVertice,InformacionArco>();		
		DijkstraSP<String> dijkstra = new DijkstraSP<String>(UnDiGraph, numVerticeInicio);

		Iterable<Edge<String, InformacionArco>> recorrerRuta = dijkstra.pathTo(UnDiGraph.getInfoVertexInfo(numVerticeFinal), UnDiGraph);
		if(recorrerRuta != null)
		{
			Iterator<Edge<String, InformacionArco>> it = recorrerRuta.iterator();
			while(it.hasNext())
			{
				Edge<String, InformacionArco> actualArco = it.next();
				Vertex<String,InformacionVertice, InformacionArco> vertice1 = UnDiGraph.getInfoVertexV(actualArco.getIdVerticeInicio());
				Vertex<String,InformacionVertice, InformacionArco> vertice2 = UnDiGraph.getInfoVertexV(actualArco.getIdVerticeFinal());

				if(!grafoDistancia.getCointainsVertex(vertice1.getIdVertice()))
				{
					grafoDistancia.addVertex(vertice1.getIdVertice(), new InformacionVertice(vertice1.getValorVertice().getLongitud(), vertice1.getValorVertice().getLatitud()));
				}
				if(!grafoDistancia.getCointainsVertex(vertice2.getIdVertice()))
				{
					grafoDistancia.addVertex(vertice2.getIdVertice(), new InformacionVertice(vertice2.getValorVertice().getLongitud(), vertice2.getValorVertice().getLatitud()));
				}

				grafoDistancia.addEdge(actualArco.getIdVerticeInicio(), actualArco.getIdVerticeFinal(), actualArco.getCostArc());

			}
		}

		return grafoDistancia;
	}

	/**
	 * Proporciona los limetes de lat y longitu en Bogota
	 * @return latMin,latMax,longMin,longMax
	 */
	public String darCoordenadasMaxYMinBogota()
	{
		double latMin = Double.MAX_VALUE;
		double latMax = Double.MIN_VALUE;
		double longMax = Double.MIN_VALUE;
		double longMin = Double.MAX_VALUE;

		Iterator<Vertex<String,InformacionVertice,InformacionArco>> it = UnDiGraph.getVerticesGrafo().Vals().iterator();
		while(it.hasNext())
		{
			Vertex<String,InformacionVertice,InformacionArco> actual = it.next();
			double longitudVertice = actual.getValorVertice().getLongitud();
			double latitudVertice = actual.getValorVertice().getLatitud();
			double i = Math.abs(longitudVertice);

			if(latitudVertice <= latMin)
			{
				latMin = latitudVertice;
			}
			if(latitudVertice >= latMax)
			{
				latMax = latitudVertice;
			}

			if(i <= longMin)
			{
				longMin = Math.abs(longitudVertice);
			}
			if(i >= longMax)
			{
				longMax = Math.abs(longitudVertice);
			}

		}

		String coordenadas = latMin + "," + latMax + ",-" + longMin + ",-" + longMax;
		return coordenadas;
	}
	
	/**
	 * Obtener el camino de costo minimo entre dos ubicaciones geograficas por cantidad de comparendos
	 * @param pOrigen Punto de origen ingresado por el usuario
	 * @param pDestino Punto de llegada ingresado por el usuario
	 */
	public void grafoMenorComparendos(Vertex<String,InformacionVertice,InformacionArco> pOrigen, Vertex<String,InformacionVertice,InformacionArco> pDestino)
	{
		String numVerticeInicio = pOrigen.getIdVertice();
		String numVerticeFinal = pDestino.getIdVertice();

		int numeroVertices = 0;
		int numeroArcos = 0;
		double menorComparendos = Double.MAX_VALUE;
		double promedioDistancia = 0;
		int cantidadComparendos = 0;
		ArrayList<Vertex<String,InformacionVertice,InformacionArco>> verticesRecorridos = new ArrayList<Vertex<String,InformacionVertice,InformacionArco>>();


		DijkstraSP<String> dijkstra = new DijkstraSP<String>(UnDiGraph1, numVerticeInicio);
		DijkstraSP<String> dijkstra1 = new DijkstraSP<String>(UnDiGraph, numVerticeInicio);
		
		Iterable<Edge<String, InformacionArco>> recorrerRuta = dijkstra.pathTo(UnDiGraph1.getInfoVertexInfo(numVerticeFinal), UnDiGraph1);
		Iterable<Edge<String, InformacionArco>> recorrerRuta1 = dijkstra1.pathTo(UnDiGraph.getInfoVertexInfo(numVerticeFinal), UnDiGraph);

		if(recorrerRuta != null)
		{
			Iterator<Edge<String, InformacionArco>> it = recorrerRuta.iterator();
			Iterator<Edge<String, InformacionArco>> it1 = recorrerRuta1.iterator();
			while(it.hasNext() && it1.hasNext())
			{
				Edge<String, InformacionArco> actualArco = it.next();
				Edge<String, InformacionArco> actualArco1 = it1.next();
				Vertex<String,InformacionVertice, InformacionArco> vertice1 = UnDiGraph1.getInfoVertexV(actualArco.getIdVerticeInicio());
				Vertex<String,InformacionVertice, InformacionArco> vertice2 = UnDiGraph1.getInfoVertexV(actualArco.getIdVerticeFinal());
				double costo = actualArco.getCostArc().getCosto();
				double costo1 = actualArco1.getCostArc().getCosto();

				if(!verticesRecorridos.contains(vertice1))
				{
					verticesRecorridos.add(vertice1);
					numeroVertices++;
				}
				if(!verticesRecorridos.contains(vertice2))
				{
					verticesRecorridos.add(vertice2);
					numeroVertices++;
				}

				if(costo <= menorComparendos)
				{
					menorComparendos = (int) costo;
					cantidadComparendos += (int) costo;
				}

				numeroArcos++;
				promedioDistancia += costo1;
			}
		}

		promedioDistancia = promedioDistancia / numeroArcos;

		System.out.println("El numero de vertices entre los dos puntos ingresados es: " + numeroVertices);
		System.out.println("Los vertices recorridos entre los puntos son:");
		for(int i = 0; i < verticesRecorridos.size(); i++)
		{
			Vertex<String,InformacionVertice,InformacionArco> actual = verticesRecorridos.get(i);
			System.out.println(actual.getIdVertice() + ", " + actual.getValorVertice().getLatitud() + ", " + actual.getValorVertice().getLongitud());
		}
		System.out.println("El camino con menos comparendos es: " + menorComparendos);
		System.out.println("La menor contidad de comparendos en la ruta es de: " + cantidadComparendos);
		System.out.println("La distancia estimana es de: " + promedioDistancia);
	}
	
	/**
	 * Grafo con las conexiones utilizando el algoritmo de prim
	 * @return Grafo con todas las conexiones en las cuales se deberian instalar camaras de seguridad
	 */
	public UnGraph<String,InformacionVertice,InformacionArco> redComunicacionesInstalacionCamerasBogota()
	{
		PrimMST<String> prim = new PrimMST<String>(UnDiGraph);
		UnGraph<String,InformacionVertice,InformacionArco> grafo = prim.arbolMST(UnDiGraph);
		return grafo;
	}
	
	/**
	 * Grafo teniendo en cuenta el numero de comparendos de mayor grado
	 * @param pSitios Sitios en donde se presentan estos comparendos
	 * @return
	 */
	public UnGraph<String,InformacionVertice,InformacionArco> redComunicacionesInstalacionCamaras(int pSitios)
	{	
		PrimMST<String> prim = new PrimMST<String>(UnDiGraph);
		UnGraph<String,InformacionVertice,InformacionArco> grafo = prim.arbolVerticesMST(UnDiGraph, pSitios);
		return grafo;
	}
	
	/**
	 * Informacion de la red de comunicaciones de la instalacion de camaras
	 * @param pComparendos Comparendos de mayor gravedad asignados a los vertices 
	 */
	public void informacionRedComunicacionesInstalacioneCamera(int pComparendos)
	{
		UnGraph<String,InformacionVertice,InformacionArco> grafo =redComunicacionesInstalacionCamaras(pComparendos);
		ArrayList<Comparendo> comparendosMayorGravedad = new ArrayList<Comparendo>();
		
		Comparendo[] comparendos = modelo.organizarComparendosMayorGravedad();
		for(int i = 0; i < comparendos.length; i++)
		{
			Comparendo actual = comparendos[i];
			comparendosMayorGravedad.add(actual);
		}
		
		String cadenaVertices1 = "";
		String cadenaVertices2 = "";
		
		System.out.println("Identificadores de los vertices que hacen parte de la red de camaras: ");
		for(int i = 0; i < grafo.getVerticesGrafoArreglo().size(); i++)
		{
			Vertex<String,InformacionVertice,InformacionArco> verticeActual = grafo.getVerticesGrafoArreglo().get(i);
			cadenaVertices1 += verticeActual.getIdVertice() + ", ";
		}
		
		ordenVertices(grafo.getVerticesGrafoArreglo());
		for(int i = 0; i < grafo.getVerticesGrafoArreglo().size(); i++)
		{
			Vertex<String,InformacionVertice,InformacionArco> verticeActual = grafo.getVerticesGrafoArreglo().get(i);
			cadenaVertices2 += verticeActual.getIdVertice() + ", ";
		}
		
		System.out.println("- Vertices desorganizados: ");
		System.out.println(cadenaVertices2);
		System.out.println("- Vertices organizados: ");
		System.out.println(cadenaVertices1);
		System.out.println(" ");
		
		String cadenaArcos = "";
		double costoAcomulado = 0;
		
		System.out.println("Arcos que comprenden la red de comunicaciones de la instalacion de camaras de video: ");
		for(int i = 0; i < grafo.getArcosGrafoArreglo().size(); i++)
		{
			Edge<String,InformacionArco> arcoActual = grafo.getArcosGrafoArreglo().get(i);
			cadenaArcos += arcoActual.getIdVerticeInicio() + "-" + arcoActual.getIdVerticeFinal() + ", ";
			costoAcomulado += arcoActual.getCostArc().getCosto();
		}
		
		System.out.println(cadenaArcos);
		System.out.println(" ");
		
		double precioKilometro = costoAcomulado * 10000;
		System.out.println("Costo monetario total de la instalacion de la red es de: " + precioKilometro + " dolares.");	
	}
	
	/**
	 * Teniendo los vertices los desordena de la lista
	 * @param list Lista de v
	 */
	public void ordenVertices(ArrayList<Vertex<String,InformacionVertice,InformacionArco>> list) 
	{
		Random random = new Random(); 
		int count = list.size() - 1;
		for (int i = count; i > 1; i--) 
		{
			Collections.swap(list, i, random.nextInt(i));
		} 
	}
	
	/**
	 * Grafo teniendo en cuenta el mayor numero de comparendos con tener en cuenta la malla vial
	 * @param pSitios Sitios en donde se presentan mas comparendos
	 * @return Grafo con el mayor numero de comparendos
	 */
	public UnGraph<String,InformacionVertice,InformacionArco> redComunicacionesInstalacionCamarasMayorComparendos(int pSitios)
	{	
		PrimMST2<String> prim = new PrimMST2<String>(UnDiGraph1);
		UnGraph<String,InformacionVertice,InformacionArco> total = prim.arbolVerticesMST(UnDiGraph1, pSitios);
		return total;
	}
	
	/**
	 * Grafo teniendo en cuenta el mayor numero de comparendos sin tener en cuenta la malla vial
	 * @param pSitios Sitios en donde se presentan mas comparendos
	 * @return Grafo con el mayor numero de comparendos
	 */
	public UnGraph<String,InformacionVertice,InformacionArco> redComunicacionesInstalacionCamarasMayorComparendosSinMallaVial(int pSitios)
	{	
		PrimMST2<String> prim = new PrimMST2<String>(UnDiGraph1);
		UnGraph<String,InformacionVertice,InformacionArco> total = prim.arbolVerticesMST(UnDiGraph1, pSitios);
		UnGraph<String,InformacionVertice,InformacionArco> grafoMayorComparendos =  new UnGraph<String,InformacionVertice,InformacionArco>();
		for(int i = 0; i < total.getArcosGrafoArreglo().size(); i++)
		{
			Edge<String,InformacionArco> e = total.getArcosGrafoArreglo().get(i);
			if(e.getCostArc().getCosto() > 0)
			{
				grafoMayorComparendos.addVertex(e.getIdVerticeInicio(), new InformacionVertice(total.getVerticesGrafoArreglo().get(e.getIdInicio()).getValorVertice().getLongitud(), total.getVerticesGrafoArreglo().get(e.getIdInicio()).getValorVertice().getLatitud()));
				grafoMayorComparendos.addVertex(e.getIdVerticeFinal(), new InformacionVertice(total.getVerticesGrafoArreglo().get(e.getIdDestino()).getValorVertice().getLongitud(), total.getVerticesGrafoArreglo().get(e.getIdDestino()).getValorVertice().getLatitud()));
				grafoMayorComparendos.addEdge(e.getIdVerticeInicio(), e.getIdVerticeFinal(), new InformacionArco(e.getCostArc().getCosto()));		   
			}
		}
		
		return grafoMayorComparendos;
	}
	
	/**
	 * Informacion de la red de comunicaciones de la instalacion de camaras en donde hay mas comparendos
	 * @param pVertices lugares en donde se instalaran las camaras de seguridad
	 */
	public void informacionRedComunicacionesInstalacioneCameraMayorComparendos(int pVertices)
	{
		UnGraph<String,InformacionVertice,InformacionArco> grafo =redComunicacionesInstalacionCamarasMayorComparendos(pVertices);
		UnGraph<String,InformacionVertice,InformacionArco> grafoDist =redComunicacionesInstalacionCamaras(pVertices);

		
		String cadenaVertices1 = "";
		String cadenaVertices2 = "";
		
		System.out.println("Identificadores de los vertices que hacen parte de la red de camaras: ");
		for(int i = 0; i < grafo.getVerticesGrafoArreglo().size(); i++)
		{
			Vertex<String,InformacionVertice,InformacionArco> verticeActual = grafo.getVerticesGrafoArreglo().get(i);
			cadenaVertices1 += verticeActual.getIdVertice() + ", ";
		}
		
		ordenVertices(grafo.getVerticesGrafoArreglo());
		for(int i = 0; i < grafo.getVerticesGrafoArreglo().size(); i++)
		{
			Vertex<String,InformacionVertice,InformacionArco> verticeActual = grafo.getVerticesGrafoArreglo().get(i);
			cadenaVertices2 += verticeActual.getIdVertice() + ", ";
		}
		System.out.println("El total de vertices que comprende la red es de: " + grafo.getVerticesGrafoArreglo().size());
		System.out.println("- Vertices desorganizados: ");
		System.out.println(cadenaVertices2);
		System.out.println("- Vertices organizados: ");
		System.out.println(cadenaVertices1);
		System.out.println(" ");
		
		String cadenaArcos = "";
		double costoAcomulado = 0;
		
		System.out.println("Arcos que comprenden la red de comunicaciones de la instalacion de camaras de video: ");
		for(int i = 0; i < grafo.getArcosGrafoArreglo().size(); i++)
		{
			Edge<String,InformacionArco> arcoActual = grafo.getArcosGrafoArreglo().get(i);
			Edge<String,InformacionArco> arcoActualDist = grafoDist.getArcosGrafoArreglo().get(i);
			cadenaArcos += arcoActual.getIdVerticeInicio() + "-" + arcoActual.getIdVerticeFinal() + ", ";
			costoAcomulado += arcoActualDist.getCostArc().getCosto();
		}
		
		System.out.println(cadenaArcos);
		System.out.println(" ");
		
		double precioKilometro = costoAcomulado * 10000;
		System.out.println("Costo monetario total de la instalacion de la red es de: " + precioKilometro + " dolares.");	
	}
	
	/**
	 * Numero de componentes conectados en el grafo
	 * @return Numero de componentes conectados
	 */
	public int cc()
	{
		ComponentConnected cc = new ComponentConnected(UnDiGraph);
		return cc.count();
	}
}
