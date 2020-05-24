package model.data_structures;

import model.InformacionArco;
import model.InformacionVertice;

/** 
 * Implementación tomada de Algorithms 4th edition by Robert Sedgewick and Kevin Wayne (2011)
 * Consultado el 15/11/19
 * Disponible en https://algs4.cs.princeton.edu/code/
 */
public class PrimMST<K extends Comparable<K>> 
{
	/**
	 * Constante flotante epsilon
	 */
	@SuppressWarnings("unused")
	private static final double FLOATING_POINT_EPSILON = 1E-12;

	// Atributos
	
	/**
	 *  edgeTo[v] = shortest edge from tree vertex to non-tree vertex
	 */
	private Edge<K,InformacionArco>[] edgeTo; 
	
	/**
	 * distTo[v] = weight of shortest such edge
	 */
	private double[] distTo;     
	
	/**
	 * marked[v] = true if v on tree, false otherwise
	 */
	private boolean[] marked;  
	
	/**
	 * Index Min PQ
	 */
	private IndexMinPQ<Double> pq;
	
	/**
	 * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
	 * @param G the edge-weighted graph
	 */
	@SuppressWarnings("unchecked")
	public PrimMST(UnGraph<K,InformacionVertice,InformacionArco> G) 
	{
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;

		for (int v = 0; v < G.V(); v++)      // run from each vertex to find
			if (!marked[v]) prim(G, v);      // minimum spanning forest
	}

	/**
	 * Run Prim's algorithm in graph G, starting from vertex s
	 * @param G Graph
	 * @param s Index start in the graph
	 */
	private void prim(UnGraph<K,InformacionVertice,InformacionArco> G, int s) {
		distTo[s] = 0.0;
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) 
		{
			int v = pq.delMin();
			scan(G,v);
		}
	}

	/**
	 * Scan vertex v
	 * @param G Graph
	 * @param v Index start in the graph
	 */
	private void scan(UnGraph<K,InformacionVertice,InformacionArco> G, int v) 
	{
		Vertex<K,InformacionVertice,InformacionArco> vertice = G.getInfoVertexId(v);
		marked[(int)v] = true;
		for (int idVertices : G.adjPrim(vertice)) 
		{
			Edge<K,InformacionArco> e = G.getInfoVertexId(v).buscarArcoA(idVertices);
			int w = e.other(v);
			if (marked[w]) continue;         // v-w is obsolete edge
				if (e.getCostArc().getCosto() < distTo[w]) 
				{
					distTo[w] = e.getCostArc().getCosto();
					edgeTo[w] = e;
					if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
					else                pq.insert(w, distTo[w]);
				}
		}
	}

	/**
	 * Returns the edges in a minimum spanning tree (or forest).
	 * @return the edges in a minimum spanning tree (or forest) as
	 *    an iterable of edges
	 */
	public Iterable<Edge<K,InformacionArco>> edges() 
	{
		LinkedQueue<Edge<K,InformacionArco>> mst = new LinkedQueue<Edge<K,InformacionArco>>();
		for (int v = 0; v < edgeTo.length; v++) {
			Edge<K,InformacionArco> e = edgeTo[v];
			if (e != null) {
				mst.enqueue(e);
			}
		}
		return mst;
	}
	
	/**
	 * Return the graph of the result of Prim Algorithm
	 * @param total UnGraph
	 * @return The minimun spanning three
	 */
	public UnGraph<K,InformacionVertice,InformacionArco>  arbolMST(UnGraph<K,InformacionVertice,InformacionArco>  total)
	{
		UnGraph<K,InformacionVertice,InformacionArco>  g = new UnGraph<K,InformacionVertice,InformacionArco>();
		for (int v = 0; v < edgeTo.length; v++) 
		{
//			System.out.println(edgeTo.length);
			Edge<K,InformacionArco> e = edgeTo[v];
			if (e != null)
			{
				g.addVertex(e.getIdVerticeInicio(), new InformacionVertice(total.getInfoVertexId(e.getIdInicio()).getValorVertice().getLongitud(), total.getInfoVertexId(e.getIdInicio()).getValorVertice().getLatitud()));
				g.addVertex(e.getIdVerticeInicio(), new InformacionVertice(total.getInfoVertexId(e.getIdDestino()).getValorVertice().getLongitud(), total.getInfoVertexId(e.getIdDestino()).getValorVertice().getLatitud()));
				g.addEdge(e.getIdVerticeInicio(), e.getIdVerticeFinal(), new InformacionArco(e.getCostArc().getCosto()));
			}
		}
		return g;
	}

	/**
	 * Returns the sum of the edge weights in a minimum spanning tree (or forest).
	 * @return the sum of the edge weights in a minimum spanning tree (or forest)
	 */
	public double weight() 
	{
		double weight = 0.0;
		for (Edge<K,InformacionArco> e : edges())
			weight += e.getCostArc().getCosto();
		return weight;
	}
}
