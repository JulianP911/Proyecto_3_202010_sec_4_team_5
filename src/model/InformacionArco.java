package model;

/**
 * Clase con la informacion de cada arco del grafo
 * @author Julian Padilla - Pablo Pastrana
 * Incluye el costo que hace referencia a la distancia
 */
public class InformacionArco 
{
	// Attributo
	
	/**
	 * Costo que hace referencia a la distancia
	 */
	private double costo;
	
	// Metodo constructor
	
	/**
	 * Metodo constructor que inicializa el costo del arco que hace referencia a la distancia
	 * @param pCosto Costo a inicializar en el arco
	 */
	public InformacionArco(double pCosto)
	{
		costo = pCosto;
	}
	
	// Metodos
	
	/**
	 * Obtener el costo del arco en el grafo
	 * @return Costo del arco que hace referencia a la distancia
	 */
	public double getCosto()
	{
		return costo;
	}
	
	/**
	 * Cambiar el costo por un nuevo costo en el arco del grafo
	 * @param pCosto Nuevo Costo a guardar
	 */
	public void setCosto(double pCosto)
	{
		costo = pCosto;
	}
}
