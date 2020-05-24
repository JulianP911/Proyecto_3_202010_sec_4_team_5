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
	
	/**
	 * Costo de comparendos entre los arcos
	 */
	private int costoComparendos;

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
		costoComparendos = 0;
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
	
	/**
	 * Ingresar numero de comparendos en el arco
	 * @param pComparendos Comparendos en el arco
	 */
	public void setCostoCom(int pComparendos)
	{
		costoComparendos = pComparendos;
	}

	/**
	 * Obtener el numero de arcos
	 * @return Costo comparendos
	 */
	public int getCostoCom()
	{
		return costoComparendos;
	}

	/*
	 * Cambio al otro vertice utilizado en Lazy Prim
	 */
	public int other(int pVertex)
	{
		int v = (int) idVerticeInicio;
		int w= (int) idVerticeFinal;

		if(pVertex == v) 
		{
			return w;
		}
		else if (pVertex == w)
		{
			return v;
		}
		else 
		{
			throw new IllegalArgumentException("Illegal endpoint");
		}
	}
	
	/**
	 * Da el vertice final del arco
	 * @return Numero del vertice final
	 */
	public int getIdDestino()
	{
		return (int) getIdVerticeFinal();
	}
	
	/**
	 * Da el vertice inicio del arco
	 * @return Numero del vertice inicial
	 */
	public int getIdInicio()
	{
		return (int) getIdVerticeInicio();
	}
}
