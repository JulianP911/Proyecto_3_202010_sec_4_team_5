package model.data_structures;

import java.util.ArrayList;

import model.Comparendo;
import model.EstacionPolicia;

/**
 * Clase de vetice de tipo generica K
 * @author Julian Padilla - Pablo Pastrana
 * Obtuvimos metodos de internet en la implementación con variaciones implementadas por nosotros
 * @param <K> Vertice de tipo generico
 */
public class Vertex<K extends Comparable<K>,V,E>
{
	// Atributos
	
	/**
	 * Identificador del vertice
	 */
	private K idVeritice;
	
	/**
	 * Informacion del vertice
	 */
	public V valorVertice;
	
	/**
	 * Identificador numero del vertice
	 */
    private int idNumumeroVertice;
    
    /**
     * Componente conectada
     */
    private int componenteConectado;
    
    /**
     * Arreglo con los arcos que salen del vertice
     */
    private ArrayList<Edge<K, E>> arcosSalen;
    
    /**
     * Arreglo con los arcos que entran al vertice
     */
    private ArrayList<Edge<K, E>> arcosEntran;
    
    /**
     * Comparendos asociados al vertice
     */
    private LinkedQueue<Comparendo> cola;
    
    /**
     * Estacion de policia asociados al vertice
     */
    private LinkedQueue<EstacionPolicia> estaciones;

    // Metodo constructor
    
	public Vertex(K pIdVertice, V pValorVertice, int pIdNumero)
	{
		this.idVeritice = pIdVertice;
		this.valorVertice = pValorVertice;
		arcosSalen = new ArrayList<Edge<K,E>>();
		arcosEntran =  new ArrayList<Edge<K,E>>();
		componenteConectado = -1;
		idNumumeroVertice = pIdNumero;
		cola = new LinkedQueue<Comparendo>();
		estaciones = new LinkedQueue<EstacionPolicia>();
	}
	
	/**
	 * Obtener el numero del vertice dentro del grafo
	 * @return Numero del vertice
	 */
	public K getIdVertice()
	{
		return idVeritice;
	}
	
	/**
	 * Obtener el numero del vertice dentro del grafo
	 * @return Numero del vertice
	 */
	public int getIdVerticeInt()
	{
		return (int) idVeritice;
	}
	
	/**
	 * Obtener el numero vertice con el valor en el grafo
	 * @return Numero vertice con el valor
	 */
	public int getIdNumeroVertice()
	{
		return idNumumeroVertice;
	}
	
	/**
	 * Obtener valor del vertice 
	 * @return Valor vertice
	 */
	public V getValorVertice()
	{
		return valorVertice;
	}
	
	/**
	 * Cambiar el valor del vertice
	 * @param pValor Nuevo valor al vertice
	 */
	public void setValorVertice(V pValor)
	{
		valorVertice = pValor;
	}
	
	/**
	 * Obtener componente conectado del vertice
	 * @return Numero de la componente conectado
	 */
	public int getComponenteConectado()
	{
		return componenteConectado;
	}
	
	/**
	 * Cambiar componente conectado del vertice con la nueva que ingresa
	 * @param Nueva componete a asociarle al vertice
	 */
	public void setComponenteConectado(int pComponente)
	{
		componenteConectado = pComponente;
	}
	
	/**
	 * Obtener arreglo con loa arcos que salen del vertice
	 */
	public ArrayList<Edge<K, E>> getArcosSaliente()
	{
		return arcosSalen;
	}
	
	/**
	 * Anadir a un array list los arcos que entran al vertice
	 * @param pArco Arco entrante al vertice
	 */
	public void anadirArcoEntrante(Edge<K,E> pArco)
	{
		arcosEntran.add(pArco);
	}
	
	/**
	 * Anadir a un array list los arcos que salen del vertice
	 * @param pArco Arco saliente al vertice
	 */
	public void anadirArcoSaliente(Edge<K,E> pArco)
	{
		arcosSalen.add(pArco);
	}
	
	/**
	 * Obtener arco entre dos vertices especificos
	 * @param idVertexFin Vertice final
	 * @return Arista entre los vertices que se especificaron
	 */
	public Edge<K,E> getEdge(K idVertexFin)
	{
		Edge<K,E> encontrado = null;
		for (int i = 0; i < arcosSalen.size() && encontrado == null; i++) 
		{
			if(arcosSalen.get(i).getIdVerticeFinal().compareTo(idVertexFin) == 0)
			{
				encontrado = arcosSalen.get(i);
			}
		}
		return encontrado;
	}
	
	/**
	 * Adiccionar comparendo al vertice en la cola
	 * @param pComparendo
	 */
	public void adicionarComparendo(Comparendo pComparendo)
	{
		cola.enqueue(pComparendo);
	}
	
	/**
	 * Adiccionar comparendo al vertice en la cola
	 * @param pEstacion de policia
	 */
	public void adicionarEstacion(EstacionPolicia pEstacion)
	{
		estaciones.enqueue(pEstacion);
	}
	
	/**
	 * Tamaño de la cola
	 * @param pComparendo
	 */
	public int tamañoComparendos()
	{
		return cola.getSize();
	}
	
	/**
	 * Retorna la lista de IDs a los que es adyacente el vertice.
	 * @return
	 */
	public int[] adj()
	{
		int[] listaAdyacentes = new int[arcosSalen.size()];
		for(int i = 0; i< arcosSalen.size(); i++)
		{
			Edge<K,E> arco = arcosSalen.get(i);
			if(arco!=null)
			listaAdyacentes[i] = arco.getIdDestino();	
		}
		return listaAdyacentes;
	}
	
	/**
	 * Buscar el arco conectado a A
	 * @param pIdVertice Numero del vertice
	 * @return Arco entre la conexión
	 */
	public Edge<K,E> buscarArcoA(int pIdVertice)
	{
		boolean seEncontro = false;
		Edge<K,E> aRetornar = null;
		for(int i = 0; i < arcosSalen.size()&&!seEncontro; i++)
		{
			if(arcosSalen.get(i).getIdDestino() == pIdVertice)
			{
				aRetornar = arcosSalen.get(i);
			}
		}
		return aRetornar;
	}
}
