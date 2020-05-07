package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Comparendo;
import model.EstacionPolicia;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_D.C_small.geojson";
	//	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_50000_.geojson";
    //	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_D.C.geojson";
	
	/**
	 * Metodo que hace la carga de los datos comparendos
	 * @return Una lista con los comparendos leidos
	 */
	public List<Comparendo> cargarDatos() 
	{
		List<Comparendo> datos = new ArrayList<Comparendo>();

		JsonReader reader;
		try 
		{
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			for(JsonElement e: e2) 
			{
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();
				String cambio1 = s.replaceFirst("T", " ");
				String cambio2 = cambio1.replaceAll("Z", "");
				Date FECHA_HORA = parser.parse(cambio2); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo nuevo = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, MUNICIPIO, longitud, latitud);
				datos.add(nuevo);
			}

		} 
		catch (FileNotFoundException | ParseException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return datos;			
	}
	
	/**
	 * Ruta del archvio que contiene las estaciones de policia
	 */
	public static String PATH2 = "./data/estacionpolicia.geojson.json";
	
	/**
	 * Lectura del archivo json de las estaciones de policia
	 * @return Una lista de estaciones de policia
	 */
	public List<EstacionPolicia> cargarDatosEstacionesPolicia() 
	{
		List<EstacionPolicia> datos = new ArrayList<EstacionPolicia>();

		JsonReader reader;
		try 
		{
			reader = new JsonReader(new FileReader(PATH2));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();

			for(JsonElement e: e2) 
			{
				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();
				
				
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				String DES_ESTACION = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPODESCRIP").getAsString();
				String EPODIR = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPODIR_SITIO").getAsString();
				String EPOCOD = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOCOD_SITIO").getAsString();
				double EPOLAT = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOLATITUD").getAsDouble();
				double EPOLON = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOLONGITU").getAsDouble();
				String EPOSER = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOSERVICIO").getAsString();	
				String EPOHOR = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOHORARIO").getAsString();
				String EPOTEL = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOTELEFON").getAsString();
				String EPOCEL = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOCELECTR").getAsString();
				String EPOFUN = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOFUNCION").getAsString();
				String EPOTEQ = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOTEQUIPA").getAsString();
				String EPONOM = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPONOMBRE").getAsString();
				String EPOIDE = e.getAsJsonObject().get("properties").getAsJsonObject().get("EPOIDENTIF").getAsString();

				EstacionPolicia nuevo = new EstacionPolicia(longitud, latitud, OBJECTID, DES_ESTACION, EPODIR, EPOCOD, EPOLAT, EPOLON, EPOSER, EPOHOR, EPOTEL, EPOCEL, EPOFUN, EPOTEQ, EPONOM, EPOIDE);
				datos.add(nuevo);
			}

		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return datos;			
	}
}
