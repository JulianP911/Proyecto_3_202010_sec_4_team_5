package model.data_structures;

/**
 * Clase de la tabla de hash - SeparateChaining
 * @author Julian Padilla - Pablo Pastrana
 * Obtuvimos metodos de la pagina web: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/LinearProbingHashST.java.html
 * Autores de los metodos obtenidos de Algorithms 4th edition: Robert Sedgewick y Kevin Wayne.
 * @param <K> Key (llave)
 * @param <V> Value (Valor)
 */
public class SeparateChainingHash <K extends Comparable<K>, V >
{	 

	private static final int INIT_CAPACITY = 4;       //Constante del numero inicial de la tabla de hash
	private int n;                                // numero de parejas llave-valor
	private int m;          					 // tamaño de la tabla de hash
	private SequentialSearchST<K,V>[] st;  // array of linked-list symbol tables



	public SeparateChainingHash()
	{
		this(INIT_CAPACITY);
	} 

	/**
	 * Initializes an empty symbol table with {@code m} chains.
	 * @param m the initial number of chains
	 */
	@SuppressWarnings("unchecked")
	public SeparateChainingHash(int m) 
	{
		this.m = m;
		st = (SequentialSearchST<K, V>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<K, V>();
	} 

	// resize the hash table to have the given number of chains,
	// rehashing all of the keys
	private void resize(int chains) 
	{
		SeparateChainingHash<K, V> temp = new SeparateChainingHash<K, V>(chains);
		for (int i = 0; i < m; i++) 
		{
			for (K key : st[i].keys()) 
			{
				temp.put(key, st[i].get(key));
			}
		}
		this.m  = temp.m;
		this.n  = temp.n;
		this.st = temp.st;
	}

	// hash value between 0 and m-1
	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % m;
	} 

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 *
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() 
	{
		return n;
	} 

	/**
	 * Returns true if this symbol table is empty.
	 * @return {@code true} if this symbol table is empty;
	 *         {@code false} otherwise
	 */
	public boolean isEmpty() 
	{
		return size() == 0;
	}

	/**
	 * Returns true if this symbol table contains the specified key.
	 *
	 * @param  key the key
	 * @return {@code true} if this symbol table contains {@code key};
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	} 

	/**
	 * Returns the value associated with the specified key in this symbol table.
	 *
	 * @param  key the key
	 * @return the value associated with {@code key} in the symbol table;
	 *         {@code null} if no such value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public V get(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].get(key);
	} 

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(K key, V val) 
	{
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) 
		{
			delete(key);
			return;
		}

		// double table size if average length of list >= 10
		if (n >= 10*m) resize(2*m);

		int i = hash(key);
		if (!st[i].contains(key)) n++;
		st[i].put(key, val);
	} 

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");

		int i = hash(key);
		if (st[i].contains(key)) n--;
		st[i].delete(key);

		// halve table size if average length of list <= 2
		if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
	} 


	public Iterable<K> keys() 
	{
		LinkedQueue<K> queue = new LinkedQueue<K>();
		for (int i = 0; i < m; i++)
		{
			if (st[i] != null) 
			{
				for (int j = 0; j < st[i].size(); j++)
				{

					if (st[i].keys().get(j) != null)
						queue.enqueue(st[i].keys().get(j));
				}
			}
		}


		//for (int ñ = 0; ñ < st[0].keys().size() ; ñ++)
		//{
		//	System.out.println( "este: " + st[0].keys().get(ñ) );
		//}

		return queue;
	}

	public Iterable<V> vals() 
	{
		LinkedQueue<V> queue = new LinkedQueue<V>();
		for (int i = 0; i < m; i++)
		{
			if (st[i] != null) 
			{
				for (int j = 0; j < m; j++)
				{
					if (st[i].vals().get(j) != null)
						queue.enqueue(st[i].vals().get(j));
				}
			}
		}

		return queue;
	}
}