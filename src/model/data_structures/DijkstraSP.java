package model.data_structures;

import java.util.ArrayList;

import model.InformacionArco;
import model.InformacionVertice;

public class DijkstraSP<K extends Comparable<K>> 
{
	// Atributos

	/**
	 * distTo[v] = distance  of shortest s->v path
	 */
	private double[] distTo;

	/**
	 * edgeTo[v] = last edge on shortest s->v path
	 */
	private Edge<K,InformacionArco>[] edgeTo;

	/**
	 * priority queue of vertices
	 */
	private IndexMinPQ<Double> pq;

	//Metodo constructor

	/**
	 * Computes a shortest-paths tree from the source vertex {@code s} to every other
	 * vertex in the edge-weighted undigraph {@code G}.
	 * @param  G the edge-weighted undigraph
	 * @param  s the source vertex
	 * @throws IllegalArgumentException if an edge weight is negative
	 * @throws IllegalArgumentException unless {@code 0 <= s < V}
	 */
	@SuppressWarnings("unchecked")
	public DijkstraSP(UnGraph<K,InformacionVertice,InformacionArco> G, K s) 
	{
		distTo = new double[G.V()];
		edgeTo = new Edge[G.V()];

		Vertex<K,InformacionVertice,InformacionArco> vertice = G.getInfoVertexInfo(s);

		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[vertice.getIdNumeroVertice()] = 0.0;

		// relax vertices in order of distance from s
		pq = new IndexMinPQ<Double>(G.V());
		pq.insert(vertice.getIdNumeroVertice(), distTo[vertice.getIdNumeroVertice()]);
		while (!pq.isEmpty()) 
		{
			int indiceVertice = pq.delMin();
			vertice = G.getInfoVertexId(indiceVertice);
			ArrayList<Vertex<K,InformacionVertice,InformacionArco>> arcosSalientes = G.getAdjacencias(vertice.getIdVertice());
			for (int i =0; i < arcosSalientes.size(); i++)
			{
				Vertex<K,InformacionVertice,InformacionArco> salienteV = vertice;
				Vertex<K,InformacionVertice,InformacionArco> salienteW = arcosSalientes.get(i);
				Edge<K,InformacionArco> arco = G.getEdge(salienteV.getIdVertice(), salienteW.getIdVertice());
				
				if(distTo[salienteW.getIdNumeroVertice()] > distTo[salienteV.getIdNumeroVertice()] + arco.getCostArc().getCosto())
				{
					distTo[salienteW.getIdNumeroVertice()] = distTo[salienteV.getIdNumeroVertice()] + arco.getCostArc().getCosto();
					edgeTo[salienteW.getIdNumeroVertice()] = arco;
					
					if(pq.contains(salienteW.getIdNumeroVertice()))
					{
						pq.decreaseKey(salienteW.getIdNumeroVertice(), distTo[salienteW.getIdNumeroVertice()]);
					}
		            else 
		            {
		            	pq.insert(salienteW.getIdNumeroVertice(), distTo[salienteW.getIdNumeroVertice()]);
		            }	
				}
			}
		}
	}

	/**
	 * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
	 * @param  v the destination vertex
	 * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};{@code Double.POSITIVE_INFINITY} if no such path
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public double distTo(Vertex<K,InformacionVertice,InformacionArco> vertice) 
	{
		validateVertex(vertice);
		return distTo[vertice.getIdNumeroVertice()];
	}

	/**
	 * Returns true if there is a path from the source vertex {@code s} to vertex {@code v}.
	 * @param  v the destination vertex
	 * @return {@code true} if there is a path from the source vertex; {@code s} to vertex {@code v}; {@code false} otherwise
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public boolean hasPathTo(Vertex<K,InformacionVertice,InformacionArco> vertice) 
	{
		validateVertex(vertice);
		return distTo[vertice.getIdNumeroVertice()] < Double.POSITIVE_INFINITY;
	}

	/**
	 * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
	 * @param  v the destination vertex
	 * @return a shortest path from the source vertex {@code s} to vertex {@code v}; as an iterable of edges, and {@code null} if no such path
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<Edge<K,InformacionArco>> pathTo(Vertex<K,InformacionVertice,InformacionArco> vertice, UnGraph<K,InformacionVertice,InformacionArco> G) {
		validateVertex(vertice);
		if (!hasPathTo(vertice))
		{
			return null;
		}
		LinkedStack<Edge<K,InformacionArco>> path = new LinkedStack<Edge<K,InformacionArco>>();
		for (Edge<K,InformacionArco> e = edgeTo[vertice.getIdNumeroVertice()]; e != null; e = edgeTo[G.getInfoVertexV(e.getIdVerticeInicio()).getIdNumeroVertice()]) 
		{
			path.push(e);
		}
		return path;
	}

	/**
	 * throw an IllegalArgumentException unless {@code 0 <= v < V}
	 * @param Vertex vertice that enters as paramter
	 */
	@SuppressWarnings("unused")
	private void validateVertex(Vertex<K,InformacionVertice,InformacionArco> vertice) 
	{
		int V = distTo.length;
		if (vertice.getIdNumeroVertice() < 0 || vertice.getIdNumeroVertice() >= V)
		{
			throw new IllegalArgumentException("vertex " + vertice + " is not between 0 and " + (V-1));
		}
	}
}
