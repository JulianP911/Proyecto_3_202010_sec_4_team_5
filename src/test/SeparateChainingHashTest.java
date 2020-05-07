package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import model.data_structures.SeparateChainingHash;


public class SeparateChainingHashTest 
{
	/**
	 * Tabla de hash 1 - Separate chaining con llave y valor de tipo Integer
	 */
	private SeparateChainingHash<Integer, Integer> tablaHash1;

	/**
	 * Tabla de hash 2 - Separate chaining con llave de tipo Integer y valor de tipo String
	 */
	private SeparateChainingHash<Integer, String> tablaHash2;

	/**
	 * Tabla de hash 3 - Separate chaining con llave y valor de tipo double
	 */
	private SeparateChainingHash<Double, Double> tablaHash3;

	/**
	 * Escenario 1: Crea una tabla de hash con llaves y valores de tipo Integer
	 */
	@Before
	public void setupEscenario1( )
	{
		tablaHash1 = new SeparateChainingHash<Integer, Integer>();
		tablaHash1.put(1, 30);
		tablaHash1.put(2, 40);
		tablaHash1.put(3, 50);
		tablaHash1.put(4, 60);
		tablaHash1.put(5, 70);
	}

	/**
	 * Escenario 2: Crea una tabla de hash con llaves de tipo Integer y valores de tipo String
	 */
	@Before
	public void setupEscenario2( )
	{
		tablaHash2 = new SeparateChainingHash<Integer, String>();
		tablaHash2.put(1, "Java");
		tablaHash2.put(2, "Phyton");
		tablaHash2.put(3, "Swift");
		tablaHash2.put(4, "Xcode");
		tablaHash2.put(5, "C");
	}

	/**
	 * Escenario 3: Crea una tabla de hash con llaves y valores de tipo Double
	 */
	@Before
	public void setupEscenario3( )
	{
		tablaHash3 = new SeparateChainingHash<Double, Double>();
	}

	/**
	 * Prueba 1: Test del metodo constructor de la tabla de hash - Separate chaining
	 */
	@Test
	void testSeparateChainingHash()
	{
		tablaHash1 = new SeparateChainingHash<Integer, Integer>();
		tablaHash2 = new SeparateChainingHash<Integer, String>();
		tablaHash3 = new SeparateChainingHash<Double, Double>();
	}

	/**
	 * Prueba 2: Test para verificar el tamaño de las tablas de hash para los tres escenarios
	 */
	@Test
	void testSize() 
	{
		setupEscenario1();
		assertEquals(5, tablaHash1.size());

		setupEscenario2();
		assertEquals(5, tablaHash2.size());

		setupEscenario3();
		assertEquals(0, tablaHash3.size());
	}

	/**
	 * Prueba 3: Test para verificar si la tabla de hash esta vacia
	 */
	@Test
	void testIsEmpty() 
	{
		setupEscenario3();
		assertTrue(tablaHash3.isEmpty());
		tablaHash3.put(3.23, 3.24);
		assertFalse(tablaHash3.isEmpty());
	}

	/**
	 * Prueba 4: Test para verificar si la tabla de hash contiene esos valores
	 */
	@Test
	void testContains() 
	{
		setupEscenario1();
		boolean valor1 = tablaHash1.contains(5);
		assertEquals(true, valor1);

		setupEscenario1();
		boolean valor2 = tablaHash1.contains(30);
		assertEquals(false, valor2);

		setupEscenario2();
		boolean valor3 = tablaHash2.contains(4);
		assertEquals(true, valor3);
	}



	/**
	 * Prueba 6: Test insertar elemento en la tabla de hash - Separate chaining
	 */
	@Test
	void testPut() 
	{
		setupEscenario1();
		tablaHash1.put(6, 80);
		tablaHash1.put(7, 90);
		assertEquals(7, tablaHash1.size());

		setupEscenario2();
		tablaHash2.put(6, "C++");
		tablaHash2.put(7, "JavaScript");
		tablaHash2.put(8, "HTTML");
		assertEquals(8, tablaHash2.size());

		setupEscenario3();
		tablaHash3.put(12.1, 8.90);
		assertEquals(1, tablaHash3.size());
	}

	/**
	 * Prueba 7: Test obtener valor de un llave de la tabla de hash
	 */
	@Test
	void testGet() 
	{
		setupEscenario1();
		int valor1 = tablaHash1.get(5);
		assertEquals(70, valor1);

		setupEscenario2();
		String valor2 = tablaHash2.get(4);
		assertEquals("Xcode", valor2);
	}
}