package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import model.InformacionArco;
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
		UnGraph1.addVertex("1", new ArrayList<Integer>());
		UnGraph1.addVertex("3", new ArrayList<Integer>());
		UnGraph1.addVertex("6", new ArrayList<Integer>());
		UnGraph1.addVertex("4", new ArrayList<Integer>());
		
		UnGraph1.addEdge("1", "2", new InformacionArco(1.0));
		UnGraph1.addEdge("3", "6", new InformacionArco(3.0));
		UnGraph1.addEdge("1", "7", new InformacionArco(6.0));
		UnGraph1.addEdge("7", "6", new InformacionArco(1.0));
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
		fail("Not yet implemented");
	}

	/**
	 * Test 2: Verificar que el arreglo utilizado por Dfs si este funcionando correctamente
	 */
	@Test
	void testGetMarkedDfs() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 3: Verifica que el numero de vertices sea el correcto
	 */
	@Test
	void testV() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 4: Verifica que el numero de aristas sea el correcto
	 */
	@Test
	void testE()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 5: Verifica que el numero de vertices sea el correcto con otro porcesamineto utilizado
	 */
	@Test
	void testContadorArcosUnGraph() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 6: Añadir vertice al grafo con su respectiva informacion
	 */
	@Test
	void testAddVertex() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 7: Añadir arista entre dos arcos selecionados que entran por parametro esto incluye el costo de la arista
	 */
	@Test
	void testAddEdge() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 8: Obtener correcta la informacion del veritce esto incluye 
	 */
	@Test
	void testGetInfoVertex() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 9: Cambiar la informacion del vertice que tenia en su interior
	 */
	@Test
	void testSetInfoVertex() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 10: Obtener el costo de un arco segun la informacion de este
	 */
	@Test
	void testGetCostArc()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 10: Cambiar el costo del arco con nuevos parametros
	 */
	@Test
	void testSetCostArc()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 11: Obtener el arco entre dos vertices indicados por el usuario
	 */
	@Test
	void testGetArco() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 11: Desmarcar todos los vertices de markeDfs en false
	 */
	@Test
	void testUncheck() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 12: Prueba el metodo Dfs para el grafo no dirigido 
	 */
	@Test
	void testDfs()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 13: Numero de componentes conectados en el grafo
	 */
	@Test
	void testCC() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test 14: Verifica las adyacencias de un vertice que se indica
	 */
	@Test
	void testAdj() 
	{
		fail("Not yet implemented");
	}
}
