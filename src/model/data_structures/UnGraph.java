package model.data_structures;

import java.util.ArrayList;

/**
 * Clase del Grafo no dirigido para Taller 7
 * @author Julian Padilla - Pablo Pastrana
 * Los metodos de está clase fueron obtenidos del libro Algorthims 4 edition - Universidad de Princeton
 * Otros metodos se obtuvieron de GeeksAndGeeks and partes de StackOverFlowcon ciertas variaciones de acuerdo con los requisitos establecidos
 * Asimismo se realizaron variaciones en algunos de ellos
 * Autores de los metodos son: Robert Sedgewick y Kevin Wayne
 */
public class UnGraph<K extends Comparable<K>,V,E>
{
	// Constantes

	/** 
	 * Esta constante establece en el sistema una linea de separador
	 */
	@SuppressWarnings("unused")
	private static final String NEWLINE = System.getProperty("line.separator");

	// Atributos

	/**
	 * Numero de vertices del grafo
	 */
	private int numeroVertices;

	/**
	 * Numero de aristas entre los vertices del grafo no dirigido
	 */
	private int numeroAristas;

	/**
	 * Los vertices del grafo
	 */
	private SeparteChainingHashST<K, Vertex<K,V,E>> vertices;

	/**
	 * Bolsa de enteros con las adjacencias de los vertices del grafo no dirigido
	 */
	private Bag<Integer>[] adj;

	/**
	 * Areglo con el camino trazado por DFS
	 */
	private int edgeToDfs[];

	/**
	 * Areglo con los verices marcados con true or false
	 */
	private boolean markedDfs[];

	/**
	 * Referencia del numero del vertice
	 */
	private int idReferenciaNum;

	// Metodos constructor

	/**
	 * Metodo que inicializa el UnGraph con la tabla de hash con sus valores de vertices
	 * Numero de vertices = 0
	 * Numero de aristas = 0
	 */
	public UnGraph() 
	{
		vertices = new SeparteChainingHashST<K, Vertex<K,V,E>>();
		numeroVertices = 0;
		numeroVertices = 0;
		idReferenciaNum = 0;
	}

	/**
	 * Inicializa el grafo vacio con vertices que entran como parametro y con cero aristas.
	 * @param  V Numero de vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	@SuppressWarnings("unchecked")
	public UnGraph(int V) 
	{
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.numeroVertices = V;
		this.numeroAristas = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	/**
	 * Inicializar arreglos utilizados en el Dfs
	 */
	public void iniciarDfs()
	{
		edgeToDfs = new int[vertices.size()];
		markedDfs = new boolean[vertices.size()];
	}

	/**
	 * Arreglo de los vertices marcados
	 * @return Arreglo boolean de los vertices marcados
	 */
	public boolean[] getMarkedDfs()
	{
		return markedDfs;
	}

	/**
	 * Retorna el numero de vertices en el grafo
	 * @return Numero de vertices en el grafo
	 */
	public int V() 
	{
		return vertices.size();
	}

	/**
	 * Retorna el numero de aristas en el grafo
	 * Cuando se inicializa con el metodo constructor 1
	 * @return Numero de aristas en el grafo
	 */
	public int E() 
	{
		return numeroAristas;
	}

	/**
	 * Cuenta el numero de arcos en el el grafo no dirigido
	 * Cuando se inicializa con el metodo constuctor 2
	 * @return Numero de arcos en el el grafo no dirigido
	 */
	public int contadorArcosUnGraph() 
	{ 
		int sum = 0; 

		for (int i = 0; i < numeroVertices; i++) 
		{
			sum += adj[i].size(); 
		}

		return sum / 2; 
	}

	/**
	 * Anadir vertice en un tabla de hash donde el id es el numero y el valor es la long y lat
	 * @param idVertex Id de vertice
	 * @param infoVertex Valor del vertice
	 */
	public void addVertex(K idVertex, V infoVertex)
	{
		Vertex<K,V,E> v = new Vertex<K,V,E>(idVertex, infoVertex, idReferenciaNum);
		vertices.put(idVertex, v);
		numeroVertices++;
		idReferenciaNum++;
	}

	/**
	 * Añade un arista sin direccion en este grafo
	 * @param  v un vertice del arco
	 * @param  el otro vertice en el arco
	 * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(K idVertexIni, K idVertexFin, E pCost) 
	{
		Vertex<K,V,E> startVertice = vertices.get(idVertexIni);
		Vertex<K,V,E> endVertice = vertices.get(idVertexFin);

		if(!idVertexIni.equals(idVertexFin))
		{
			if(startVertice != null && endVertice != null)
			{
				Edge<K,E> arco = new Edge<K,E>(idVertexIni, idVertexFin, pCost);
				startVertice.anadirArcoSaliente(arco);
				endVertice.anadirArcoEntrante(arco);
				numeroAristas++;
			}							
		}
	}

	/**
	 * Obtener informacion de un vertice si existe de lo contrario null
	 * @param idVertex Id del vertice
	 * @return la informacion del vertice si existe de lo contrario null
	 */
	public V getInfoVertex(K idVertex)
	{
		Vertex<K,V,E> v = vertices.get(idVertex);
		if(v != null)
		{
			return v.getValorVertice();
		}
		else
		{
			return null;
		}	
	}

	/**
	 * Cambia la informacion que contiene el vertice
	 * @param idVertex El vertice a ser modificado
	 * @param infoVertex Nueva informacion del vertice
	 */
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		Vertex<K,V,E> v = vertices.get(idVertex);
		if(v != null)
		{
			v.setValorVertice(infoVertex);;
		}
	}

	/**
	 * Obtener costo entre dos vertices
	 * @param idVertexIni Vertice de inicio en el grafo
	 * @param idVertexFin Vertice final en el grafo
	 * @return El valor de costo entre dos vertices
	 */
	public double getCostArc(K idVertexIni, K idVertexFin)
	{
		double costArc = 0.0;
		Edge<K,E> arista = getArco(idVertexIni, idVertexFin);
		if(arista != null)
		{
			costArc = (double) arista.getCostArc();
		}
		else if(arista == null)
		{
			costArc = -1.0;
		}	
		return costArc;
	}

	/**
	 * Cambiar costo entre dos vertices es decir en el arco
	 * @param idVertexIni Vertice de inicio en el grafo
	 * @param idVertexFin Vertice final en el grafo
	 * @param pCost Nuevo costo entre los vertices
	 */
	public void setCostArc(K idVertexIni, K idVertexFin, E pCost)
	{
		Edge<K,E> v = getArco(idVertexIni, idVertexFin);
		if(v != null)
		{
			v.setCostArc(pCost);
		}	
	}

	/**
	 * obtener el arista entre dos vertices en el grafo
	 * @param idVertexIni Vertice de inicio en el grafo
	 * @param idVertexFin Vertice final en el grafo
	 * @return Arista entre los arcos
	 */
	public Edge<K,E> getArco(K idVertexIni, K idVertexFin)
	{
		Vertex<K,V,E> verticeActual = vertices.get(idVertexIni);
		Edge<K,E> arista = null;
		if(verticeActual != null)
		{			
			Edge<K,E> aristaActual = verticeActual.getEdge(idVertexFin);
			if(aristaActual != null)
			{
				arista = aristaActual;
			}
		}
		return arista;
	}

	/**
	 * Desmarca todos los vertices del grafo
	 */
	public void uncheck()
	{
		iniciarDfs();
		if(vertices != null)
		{
			for (int i = 0; i < vertices.size(); i++) 
			{
				markedDfs[i] = false;
			}
		}
	}

	/**
	 * DFS para el grafo no dirigido - se utilizo partes de codigo de internet
	 * Derechos de autor: Geeks for Geeks y StackOverFlow
	 * @param vertex Vertice donde se empieza hacer el Dfs
	 */
	public void dfs(K vertex)
	{
		iniciarDfs();
		Vertex<K,V,E> verticeActual = vertices.get(vertex);
		markedDfs[verticeActual.getIdNumeroVertice()] = true;

		ArrayList<Vertex<K,V,E>> arreglo = getAdjacencias(vertex);
		for(int i = 0; i < arreglo.size(); i++)
		{
			Vertex<K,V,E> vertice = arreglo.get(i);
			if (! markedDfs[vertice.getIdNumeroVertice()])
			{
				edgeToDfs[vertice.getIdNumeroVertice()] = verticeActual.getIdNumeroVertice();
				dfs(vertice.getIdVertice());
			}
		}
	}

	/**
	 * Componenetes conectados en el UnGrap
	 * @return Numero de componentes conectados en el grafo
	 */
	public int CC()
	{
		int componentesConectados = 0;
		for(int j = 0; j < vertices.size(); j++)
		{
			if(markedDfs[j] = true && idReferenciaNum == j)
			{
				componentesConectados++;
			}
			else
			{
				idReferenciaNum++;
			}
		}
		return componentesConectados;
	}

	/**
	 * Arreglo de las adjacencias del vertice indicado
	 * @param idVertex Verice desde el punto inicial
	 * @return Arreglo con las adjacencias del vertice que entra como parametro
	 */
	public ArrayList<Vertex<K,V,E>> getAdjacencias(K idVertex)
	{
		ArrayList<Vertex<K,V,E>> arreglo = new ArrayList<Vertex<K,V,E>>();
		Vertex<K,V,E> v = vertices.get(idVertex);
		if(v != null)
		{
			for(int i = 0; i < v.getArcosSaliente().size(); i++)
			{
				arreglo.add(vertices.get(v.getArcosSaliente().get(i).getIdVerticeFinal()));
			}
		}
		return arreglo;
	}

	/**
	 * Retorna los vertices adyacente del vertice de entrada
	 * @param  v El vertice de entrada
	 */
	public Iterable<K> adj(K idVertex) 
	{
		LinkedQueue<K> cola = new LinkedQueue<K>();
		Vertex<K,V,E> verticeActual = vertices.get(idVertex);
		if(verticeActual != null)
		{
			for(int i = 0; i < verticeActual.getArcosSaliente().size(); i++)
			{
				cola.enqueue(verticeActual.getArcosSaliente().get(i).getIdVerticeFinal());
			}
		}
		return cola; 
	}

	/**
	 * Iterable de vertices por dfs y componentes conectadas
	 * @param idVertex Vertice desde donde se empieza a iterar
	 * @return Un cola iterable que tiene en cuenta el dfs() y cc()
	 */
	public Iterable<K> getCC(K idVertex) 
	{
		LinkedQueue<K> cola = new LinkedQueue<K>();
		Vertex<K,V,E> verticeActual = vertices.get(idVertex);
		if(verticeActual != null)
		{
			dfs(idVertex);
			for(int i = 0; i < vertices.size(); i++)
			{
				for(int j = 0; j < CC(); j++)
				{
					if(verticeActual.getComponenteConectado() == j)
					{
						cola.enqueue(verticeActual.getArcosSaliente().get(i).getIdVerticeFinal());
					}
				}
			}
		}
		return cola; 
	}
}
