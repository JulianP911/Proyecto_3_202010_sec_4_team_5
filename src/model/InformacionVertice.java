package model;

/**
 * Clase con la informacion de cada vertice del grafo
 * @author Julian Padilla - Pablo Pastrana
 * Incluye la longitud y latitud del vertice
 */
public class InformacionVertice 
{
	// Atributos
	
	/**
	 * Longitud del vertice
	 */
	private double longitud;
	
	/**
	 * Latitud del vertice
	 */
	private double latitud;
	
	// Metodo constructor
	
	/**
	 * Metodo constructor con la informacion que contiene un vertice del grafo
	 * @param pLongitud
	 * @param pLatitud
	 */
	public InformacionVertice(double pLongitud, double pLatitud)
	{
		this.longitud = pLongitud;
		this.latitud = pLatitud;
	}
	
	// Metodos
	
	/**
	 * Obtener la longitud de un vertice
	 * @return Longitud del vertice
	 */
	public double getLongitud()
	{
		return longitud;
	}
	
	/**
	 * Obtener latitud de un vertice
	 * @return Latitud de un vertice
	 */
	public double getLatitud()
	{
		return latitud;
	}
	
	/**
	 * Cambiar Longitud del vertice por la nueva que ingresa
	 * @param pLongitud Nueva longitud
	 */
	public void setLongitud(double pLongitud)
	{
		longitud = pLongitud;
	}
	
	/**
	 * Cambiar Latitud del vertice por la nueva que ingresa
	 * @param pLatitud Nueva latitud
	 */
	public void setLatitud(double pLatitud)
	{
		latitud = pLatitud;
	}
}
