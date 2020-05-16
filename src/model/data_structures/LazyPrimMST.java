package model.data_structures;

import java.util.ArrayList;

import model.InformacionArco;
import model.InformacionVertice;

public class LazyPrimMST <K extends Comparable<K>> 
{
	// Atributos
	
	/**
	 * Lista con los vertices no visitados
	 */
	private ArrayList<Vertex<K,InformacionVertice,InformacionArco>> unVisitedVertexes;
	
	/**
	 * Lista con el spanning tree
	 */
	private ListaEnlazada<Edge<K, InformacionArco>> spanningTree;
	
	/**
	 * Heap con los arcos correspondientes
	 */
	private MinPQ<Edge<K,InformacionArco>> edgeHeap;
	
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
		this.spanningTree = new ListaEnlazada<Edge<K, InformacionArco>>();
		this.edgeHeap =  new MinPQ<Edge<K,InformacionArco>>();
	}
	
	// Metodos
	
	/**
	 * Este metodo ejecuta el algoritmo de Prim
	 * @param vertex Vertice incio del spanning tree
	 */
	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
	public void primsAlgorithm(Vertex<K,InformacionVertice,InformacionArco> vertex)
	{
		this.unVisitedVertexes.remove(vertex);
		while(!unVisitedVertexes.isEmpty())
		{
			for(Edge<K,InformacionArco> edge: vertex.getArcosSaliente())
			{
				if(this.unVisitedVertexes.contains(edge.getIdVerticeFinal()))
				{
					this.edgeHeap.insert(edge);
				}
			}
			
			Edge<K,InformacionArco> minEdge = this.edgeHeap.delMin();
			
			this.spanningTree.agregarNodoFinal(minEdge);
			this.costoTotal += minEdge.getCostArc().getCosto();
			
			vertex = (Vertex<K, InformacionVertice, InformacionArco>) minEdge.getIdVerticeFinal();
			this.unVisitedVertexes.remove(vertex);;
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
