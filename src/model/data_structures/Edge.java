package model.data_structures;

import model.InformacionArco;

/**
 * Clase de vetice de tipo generica K
 * @author Julian Padilla - Pablo Pastrana
 * Obtuvimos metodos de internet en la implementación con variaciones implementadas por nosotros
 * @param <K> Vertice de tipo generico
 */
public class Edge<K extends Comparable<K>, V>
{	
	// Atributos
	
	/**
	 * Vertice de incio en el grafo no dirigido
	 */
	private K idVerticeInicio;
	
	/**
	 * Vertice de final en el grafo no dirigido
	 */
	private K idVerticeFinal;
	
	/**
	 * Costo de distancia entre arcos
	 */
	private InformacionArco costoArco;
	
	// Metodo constructor
	
	/**
	 * Inicializa los datos para tener los vertices que se conectan mediante un arco
	 * @param pVerIni Vertice de inicio 
	 * @param pVerFin Verice del fina
	 * @param V Costo del arco que hace referencia a la distancia
	 */
	public Edge(K pVerIni, K pVerFin, V pCost)
	{
		idVerticeInicio = pVerIni;
		idVerticeFinal = pVerFin;
		costoArco = (InformacionArco) pCost;
	}
	
	/**
	 * Obtener el vertice de inicio del grafo no dirigido
	 * @return Vertice de inicio
	 */
	public K getIdVerticeInicio()
	{
		return idVerticeInicio;
	}
	
	/**
	 * Obtener el vertice de final del grafo no dirigido
	 * @return Vertice de final
	 */
	public K getIdVerticeFinal()
	{
		return idVerticeFinal;
	}
	
	/**
     * Retorna el costo que tiene el arco
     * @return Costo que tiene el arco
     */
    @SuppressWarnings("unchecked")
	public V getCostArc() 
    {
        return (V) costoArco;
    }
    
    /**
     * cambia el costo que tiene el arco
     */
    public void setCostArc(V pCost) 
    {
        costoArco = (InformacionArco) pCost;
    }
}
