package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.InformacionArco;
import model.InformacionVertice;
import model.data_structures.DijkstraSP;
import model.data_structures.Edge;
import model.data_structures.LinkedQueue;
import model.data_structures.UnGraph;
import model.data_structures.Vertex;

public class DijkstraSPTest<K, V, E> 
{

	
	private UnGraph grafo;
	
	private DijkstraSP dijk;
	
	private Vertex vertice;
	 

	
	@Before
	public void setupEscenario1( )
	{
		grafo = new UnGraph(5);
		dijk = new DijkstraSP(grafo, 1);
		vertice = new Vertex(1, 10, 1);
	}
	
	/**
	 * Escenario 1: Crea una lista-cola de tipo String
	 */
	@Before
	public void setupEscenario2( )
	{
		grafo = new UnGraph(0);
		dijk = new DijkstraSP(grafo, 0);
		vertice = new Vertex(0, 0, 0);
		
	}
	
	
	@Test
	void testDistTo() 
	{	
		setupEscenario1();
		System.out.println(dijk.distTo(vertice));
		assertEquals( 1, dijk.distTo(vertice) );
		
		setupEscenario2();
		System.out.println(dijk.distTo(vertice));
		assertEquals( 1, dijk.distTo(vertice) );
	}

	
	@Test
	void testHasPathTo() 
	{
		setupEscenario1();
		System.out.println(dijk.hasPathTo(vertice));
		assertEquals( true, dijk.hasPathTo(vertice) );
		
		setupEscenario2();
		System.out.println(dijk.hasPathTo(vertice));
		assertEquals( false, dijk.distTo(vertice) );
	}

	
	@Test
	void tesPathTo() 
	{
		setupEscenario1();
		System.out.println(dijk.pathTo(vertice, grafo));
		assertEquals( true, dijk.pathTo(vertice, grafo) );
		
		setupEscenario2();
		System.out.println(dijk.pathTo(vertice, grafo));
		assertEquals( false, dijk.pathTo(vertice, grafo) );
	}
	
	@Test
	void testValidateVertex() 
	{
		dijk.validateVertex(vertice); 

	}
}

	