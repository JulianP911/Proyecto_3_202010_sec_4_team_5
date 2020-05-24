package model.data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import model.InformacionArco;
import model.InformacionVertice;

public class LazyPrimMST <K extends Comparable<K>> 
{
	// Atributos
	
	/**
	 * Lista con los vertices no visitados
	 */
	private List<Vertex<K,InformacionVertice,InformacionArco>> unVisitedVertexes;
	
	/**
	 * Lista con el spanning tree
	 */
	private List<Edge<K, InformacionArco>> spanningTree;
	
	/**
	 * Heap con los arcos correspondientes
	 */
	private PriorityQueue<Edge<K,InformacionArco>> edgeHeap;
	
	/**
	 * Costo del spanning tree
	 */
	private double costoTotal;
	
	// Metodo constructor
	
	/**
	 * Inicializa los parametros definidos anteriormente
	 * @param unVisitedVertexes Lista con los vertices no visitados
	 */
	public LazyPrimMST(ArrayList<Vertex<K,InformacionVertice,InformacionArco>> unVisitedVertexes)
	{
		super();
		this.unVisitedVertexes = unVisitedVertexes;
		this.spanningTree = new ArrayList<Edge<K,InformacionArco>>();
		this.edgeHeap =  new PriorityQueue<Edge<K,InformacionArco>>();
	}
	
	// Metodos
	
	/**
	 * Este metodo ejecuta el algoritmo de Prim
	 * @param vertex Vertice incio del spanning tree
	 */
	@SuppressWarnings("unchecked")
	public void primsAlgorithm(Vertex<K,InformacionVertice,InformacionArco> vertex, UnGraph<K,InformacionVertice,InformacionArco> G)
	{
		this.unVisitedVertexes.remove(vertex);
		while (!unVisitedVertexes.isEmpty()) 
		{
			for (Edge<K,InformacionArco> edge : vertex.getArcosSaliente()) 
			{
				Vertex<K,InformacionVertice,InformacionArco> vertice = G.getInfoVertexId(edge.getIdDestino());
				if (this.unVisitedVertexes.contains(vertice)) 
				{
					this.edgeHeap.add(edge);
				}
			}

			Edge<K,InformacionArco> minEdge = this.edgeHeap.remove();

			this.spanningTree.add(minEdge);
			this.costoTotal += minEdge.getCostArc().getCosto();

			vertex = (Vertex<K, InformacionVertice, InformacionArco>) minEdge.getIdVerticeFinal();
	        this.unVisitedVertexes.remove(vertex);
		}
	}
	
	/**
	 * Metodo que imprime el minimo spaning tree del grafo
	 */
	public void showMST()
	{
		System.out.println("El minimo costo del spanning tree: " + this.costoTotal);
		for(Edge<K,InformacionArco> edge: spanningTree)
		{
			System.out.println(edge.getIdVerticeInicio() + " - " + edge.getIdVerticeFinal());
		}
	}

}
