package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.InformacionArco;
import model.InformacionVertice;
import model.data_structures.DijkstraSP;
import model.data_structures.Edge;
import model.data_structures.PrimMST;
import model.data_structures.PrimMST2;
import model.data_structures.SeparateChainingHash;
import model.data_structures.UnGraph;
import model.data_structures.Vertex;



public class PrimMSTTest 
{
	
	private UnGraph grafo;
	
	private PrimMST tree;

	
	@Before
	public void setupEscenario1( )
	{
		grafo = new UnGraph(5);
		//tree = new PrimMST(grafo);
	}
	
	/**
	 * Escenario 1: Crea una lista-cola de tipo String
	 */
	@Before
	public void setupEscenario2( )
	{
		grafo = new UnGraph(0);
		//tree = new PrimMST(null);
	}
	
	
	@Test
	public void testEdges() 
	{	

		setupEscenario1();
		//assertEquals( null, tree.edges() );
		
		setupEscenario2();
		//assertEquals( null, tree.edges() );
	}

	
	@Test
	public void testWeight() 
	{
		setupEscenario1();
		//assertEquals(00, tree.weight() );
		
		setupEscenario2();
		//assertEquals( 0.0, tree.weight() );
	}

	
	@Test
	public void testArbolVerticesMST()
	{
		UnGraph grafin = new UnGraph(0);
		setupEscenario1();
		//assertEquals( grafin, tree.arbolMST(grafo) );
		
		setupEscenario2();
		//assertEquals( grafin, tree.arbolMST(grafo) );
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testArbolMST()
	{
		UnGraph grafin = new UnGraph(0);
		setupEscenario1();
		//assertEquals( grafin, tree.arbolVerticesMST(grafo, 0) );
		
		setupEscenario2();
		//assertEquals( grafin, tree.arbolVerticesMST(grafo, 0) );

	}
}