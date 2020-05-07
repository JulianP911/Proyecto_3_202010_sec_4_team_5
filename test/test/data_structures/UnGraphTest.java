package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import model.InformacionArco;
import model.data_structures.Edge;
import model.data_structures.UnGraph;

/**
 * Clase de pruebas para el grafo no dirigido 
 * @author Julian Padilla - Pablo Pastrana
 * Incluye tres escenarios para realizar las correspondientes verificaciones
 */
class UnGraphTest 
{
	// Atributo
	
	/**
	 * Creacion del grafo no dirigido 1 - StringArrayInfo
	 */
	private UnGraph<String, ArrayList<Integer>, InformacionArco> UnGraph1;
	
	/**
	 * Creacion del grafo no dirigido 2 - StringIntInfo
	 */
	private UnGraph<String, Integer, InformacionArco> UnGraph2;
	
	/**
	 * Creacion del grafo no dirigido 3 - IntegerStringString
	 */
	private UnGraph<Integer, String, String> UnGraph3;
	
	// Escenarios
	
	/**
	 * Escenario 1 para UnGraph1
	 */
	@Before
	void setUp1()
	{
		UnGraph1 = new UnGraph<String, ArrayList<Integer>, InformacionArco>();
		UnGraph1.addVertex("1", new ArrayList<Integer>());
		UnGraph1.addVertex("5", new ArrayList<Integer>());
		UnGraph1.addVertex("2", new ArrayList<Integer>());
		UnGraph1.addVertex("3", new ArrayList<Integer>());
		UnGraph1.addVertex("6", new ArrayList<Integer>());
		UnGraph1.addVertex("4", new ArrayList<Integer>());
		
		UnGraph1.addEdge("1", "2", new InformacionArco(1.0));
		UnGraph1.addEdge("3", "6", new InformacionArco(3.0));
		UnGraph1.addEdge("1", "6", new InformacionArco(6.0));
		UnGraph1.addEdge("4", "2", new InformacionArco(2.0));
		UnGraph1.addEdge("3", "5", new InformacionArco(2.0));
	}
	
	/**
	 * Escenario 2 para UnGraph2
	 */
	@Before
	void setUp2()
	{
		UnGraph2 = new UnGraph<String, Integer, InformacionArco>();
		UnGraph2.addVertex("1", 1000);
		UnGraph2.addVertex("2", 2000);
		UnGraph2.addVertex("3", 3000);
		UnGraph2.addVertex("4", 4000);
		UnGraph2.addVertex("5", 5000);
		UnGraph2.addVertex("6", 6000);
		UnGraph2.addVertex("7", 7000);
		UnGraph2.addVertex("8", 8000);
		UnGraph2.addVertex("9", 9000);
		UnGraph2.addVertex("10", 10000);
		
		UnGraph2.addEdge("1", "2", new InformacionArco(1.0));
		UnGraph2.addEdge("1", "10", new InformacionArco(10.0));
		UnGraph2.addEdge("2", "7", new InformacionArco(5.0));
		UnGraph2.addEdge("7", "3", new InformacionArco(3.0));
		UnGraph2.addEdge("7", "9", new InformacionArco(2.0));
		UnGraph2.addEdge("3", "6", new InformacionArco(3.0));
		UnGraph2.addEdge("6", "1", new InformacionArco(6.0));
		UnGraph2.addEdge("1", "5", new InformacionArco(5.0));
		UnGraph2.addEdge("5", "8", new InformacionArco(3.0));
		UnGraph2.addEdge("3", "8", new InformacionArco(5.0));
	}
	
	/**
	 * Escenario 3 para UnGraph3
	 */
	@Before
	void setUp3()
	{
		UnGraph3 = new UnGraph<Integer, String, String>();
		UnGraph3.addVertex(1, "hola");
		UnGraph3.addVertex(5, "como");
		UnGraph3.addVertex(9, "bien");
		UnGraph3.addVertex(6, "programa");
		UnGraph3.addVertex(3, "Industrial");
		UnGraph3.addVertex(12, "Sistema");
		UnGraph3.addVertex(11, "que");
		UnGraph3.addVertex(15, "estudias");
		UnGraph3.addVertex(20, "semestre");
		UnGraph3.addVertex(13, "entre 3 y 4");
		
		UnGraph3.addEdge(1, 5, "Hola");
		UnGraph3.addEdge(5, 9, "como");
		UnGraph3.addEdge(9, 11, "Que");
		UnGraph3.addEdge(11, 15, "estudias");
		UnGraph3.addEdge(15, 20, "carrera");
		UnGraph3.addEdge(20, 6, "IIND");
		UnGraph3.addEdge(20, 12, "ISIS");
		UnGraph3.addEdge(12, 13, "3 o 4");
	}
	
	/**
	 * Test para verificar que el grafo si se esta inicializando
	 */
	@Test
	void testUnGraph() 
	{
		UnGraph1 = new UnGraph<String, ArrayList<Integer>, InformacionArco>();
		UnGraph2 = new UnGraph<String, Integer, InformacionArco>();
		UnGraph3 = new UnGraph<Integer, String, String>();
	}

	/**
	 * Test 1: Iniciar el DFS del grafo no dirigido
	 */
	@Test
	void testIniciarDfs() 
	{
		setUp1();
		setUp2();
		//setUp3();
		
		UnGraph1.iniciarDfs();
		UnGraph2.iniciarDfs();
		//UnGraph3.iniciarDfs();
	}

	/**
	 * Test 2: Verificar que el arreglo utilizado por Dfs si este funcionando correctamente
	 */
	@Test
	void testGetMarkedDfs() 
	{
		setUp1();
		setUp2();

		boolean[]w = UnGraph1.getMarkedDfs();
		assertEquals(null, w);
		
		boolean[]ww = UnGraph2.getMarkedDfs();
		assertEquals(null, ww);

		//boolean[]www = UnGraph3.getMarkedDfs();
		//System.out.print("3"+www);
				
		//for (int i = 0; i < w.length; i++)
		//{
		//	System.out.println(w[i]);
		//}
	}

	/**
	 * Test 3: Verifica que el numero de vertices sea el correcto
	 */
	@Test
	void testV() 
	{
		setUp1();
		setUp2();
		
		assertEquals(6, UnGraph1.V());
		assertEquals(10, UnGraph2.V());
	}

	/**
	 * Test 4: Verifica que el numero de aristas sea el correcto
	 */
	@Test
	void testE()
	{
		setUp1();
		setUp2();
		
		assertEquals(5, UnGraph1.E());
		assertEquals(10, UnGraph2.E());
	}

	/**
	 * Test 5: Verifica que el numero de vertices sea el correcto con otro porcesamineto utilizado
	 */
	@Test
	void testContadorArcosUnGraph() 
	{
		setUp1();
		setUp2();
 
		assertEquals(0, UnGraph1.contadorArcosUnGraph());
		assertEquals(0, UnGraph2.contadorArcosUnGraph());
	}

	/**
	 * Test 6: Añadir vertice al grafo con su respectiva informacion
	 */
	@Test
	void testAddVertex() 
	{
		setUp1();
		setUp2();
		
		UnGraph1.addVertex("99", new ArrayList<Integer>());
		UnGraph2.addVertex("99", 99000);

		assertEquals(7, UnGraph1.V());
		assertEquals(11, UnGraph2.V());

	}

	/**
	 * Test 7: Añadir arista entre dos arcos selecionados que entran por parametro esto incluye el costo de la arista
	 */
	@Test
	void testAddEdge() 
	{
		setUp1();
		setUp2();
		
		UnGraph1.addEdge("1", "5", new InformacionArco(4.0));
		UnGraph2.addEdge("10", "2", new InformacionArco(1.0));
		
		assertEquals(6, UnGraph1.E());
		assertEquals(11, UnGraph2.E());

	}

	/**
	 * Test 8: Obtener correcta la informacion del veritce esto incluye 
	 */
	@Test
	void testGetInfoVertex() 
	{
		setUp1();
		setUp2();
		
		assertEquals(UnGraph1.getInfoVertex("1"), UnGraph1.getInfoVertex("4") );
		assertEquals(3000, UnGraph2.getInfoVertex("3").intValue() );

	}

	/**
	 * Test 9: Cambiar la informacion del vertice que tenia en su interior
	 */
	@Test
	void testSetInfoVertex() 
	{
		setUp1();
		setUp2();
		
		ArrayList <Integer> nueva = new ArrayList <Integer>();
		nueva.add(7);
		
		UnGraph1.setInfoVertex("3", nueva);
		UnGraph2.setInfoVertex("3", 37);
		
		assertEquals(7, UnGraph1.getInfoVertex("3").get(0).intValue() );
		assertEquals(37, UnGraph2.getInfoVertex("3").intValue() );
	}

	/**
	 * Test 10: Obtener el costo de un arco segun la informacion de este
	 */
	@Test
	void testGetCostArc()
	{
		setUp1();
		setUp2();
		
		assertEquals(-1.0, UnGraph1.getCostArc("4", "5") );
		assertEquals(-1.0, UnGraph2.getCostArc("4", "5") );

	}

	/**
	 * Test 10: Cambiar el costo del arco con nuevos parametros
	 */
	@Test
	void testSetCostArc()
	{
		setUp1();
		setUp2();
		
		UnGraph1.setCostArc("3", "6", new InformacionArco(333.0));
		UnGraph2.setCostArc("3", "6", new InformacionArco(333.0));
		
		assertEquals(-1.0, UnGraph1.getCostArc("3", "1") );
	 	assertEquals(-1.0, UnGraph2.getCostArc("3", "1") );
	}

	/**
	 * Test 11: Obtener el arco entre dos vertices indicados por el usuario
	 */
	@Test
	void testGetArco() 
	{
		setUp1();
		setUp2();
		
		@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
		Edge<String, InformacionArco> edy = new Edge ("3", "6", new InformacionArco(3.0));
		
		//assertEquals(edy, UnGraph1.getArco("3", "6"));
	 	assertEquals(null, UnGraph2.getArco("3", "1") );
	}

	/**
	 * Test 11: Desmarcar todos los vertices de markeDfs en false
	 */
	@Test
	void testUncheck() 
	{
		setUp1();
		setUp2();
		
		UnGraph1.uncheck();
		UnGraph2.uncheck();
	}

	/**
	 * Test 12: Prueba el metodo Dfs para el grafo no dirigido 
	 */
	@Test
	void testDfs()
	{
		setUp1();
		setUp2();
		
		UnGraph1.dfs("1");
		UnGraph2.dfs("4");
	}

	/**
	 * Test 13: Numero de componentes conectados en el grafo
	 */
	@Test
	void testCC() 
	{
		setUp1();
		setUp2();
		
		//assertEquals(5, UnGraph1.CC());
	 	//assertEquals(7, UnGraph2.CC());
	}

	/**
	 * Test 14: Verifica las adyacencias de un vertice que se indica
	 */
	@Test
	void testAdj() 
	{
		setUp1();
		setUp2();
		
		UnGraph1.adj("1");
		UnGraph2.adj("1");
	}
}

