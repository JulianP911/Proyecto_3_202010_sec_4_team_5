package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxColaCP<T extends Comparable<T>> implements Iterable<T>
{
	// Atributos

	/**
	 * Arreglo que guarda desde 1 a n elementos
	 */
	private T[] pq; 

	/**
	 * Numero de objetos presentes en la cola de prioridad
	 */
	private int n;                       

	/**
	 * Comparador de tipo comparable
	 */
	private Comparable<T> comparador; 

	// Metodo constructor

	/**
	 * Inicializa la cola de prioridad vacia con la capacidad inicial
	 * @param  capacity Capacidad inicial del la cola de prioridad
	 */
	@SuppressWarnings("unchecked")
	public MaxColaCP(int capacity) 
	{
		pq = (T[]) new Comparable[capacity + 1];
		n = 0;	
	}

	/**
	 * Inicializa la cola de prioridad vacia
	 */
	public MaxColaCP() 
	{
		this(1);
	}

	/**
	 * Inicializa la cola de prioridad con una capacidad inicial
	 * @param  initCapacity La capacidad de la cola de prioridad inicial
	 * @param  comparator El orden en el cual se va a organizar la cola de prioridad
	 */
	@SuppressWarnings("unchecked")
	public MaxColaCP(int initCapacity, Comparable<T> comparator) 
	{
		this.comparador = comparator;
		pq = (T[]) new Comparable[initCapacity + 1];
		n = 0;
	}

	// Metodos

	/**
	 * Retorna true si la cola de prioridad es vacio, de lo contrario retorna false
	 */
	public boolean isEmpty() 
	{
		return n == 0;
	}

	/**
	 * Retorna el numero total de objetos en la cola de prioridad
	 */
	public int size() 
	{
		return n;
	}

	/**
	 * Retorna la llave mayor de la cola de prioridad
	 */
	public T max() 
	{
		if (isEmpty())
		{
			throw new NoSuchElementException("La cola de prioridad se encuntra vacia y no hay elementos");
		}
		return pq[1];
	}

	/**
	 * Ayuda a aumentar el tamaño del arreglo 
	 * @param capacity Capacidad del arrego
	 */
	private void resize(int capacity) 
	{
		assert capacity > n;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	/**
	 * Inserta un elemento a la cola de prioridad
	 * @param x Elemento a insertar en la cola de prioridad
	 */
	public void insert(T x)
	{
		if (n == pq.length - 1) resize(2 * pq.length);

		pq[++n] = x;
		swim(n);
	}

	/**
	 * Retorna la llave mayor de la cola de prioridad que va a ser eliminada
	 */
	public T delMax() 
	{
		if (isEmpty()) 
		{
			throw new NoSuchElementException("La cola de prioridad se encuntra vacia y no hay elementos");
		}
		T max = pq[1];
		exch(1, n--);
		sink(1);
		pq[n+1] = null;    
		if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
		return max;
	}

	/**
	 * organiza los elementos de la cola de prioridad
	 * @param k Posicion actual de la cola de prioridad
	 */
	private void swim(int k) 
	{
		while (k > 1 && less(k/2, k)) 
		{
			exch(k, k/2);
			k = k/2;
		}
	}

	/**
	 * Intercambia el el ultimo con el primero para poder eliminar y organizarse la cola de prioridad
	 * @param k Posicion actual de la cola de prioridad
	 */
	private void sink(int k) 
	{
		while (2*k <= n) 
		{
			int j = 2*k;
			if (j < n && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	/**
	 * 
	 * @param i 
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j) 
	{
		if (comparador == null)
		{
			return ((Comparable<T>) pq[i]).compareTo(pq[j]) < 0;
		}
		else 
		{
			return ((Comparable<T>) pq[i]).compareTo(pq[j]) < 0;
		}
	}

	/**
	 * Intercambia dos objetos de la cola de priorida
	 * @param i Posicion del objeto 1 a intercambiar
	 * @param j Posicion del objeto 2 a intercambiar
	 */
	private void exch(int i, int j) 
	{
		T swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	/**
	 * Iterador de la cola de prioridad
	 */
	@Override
	public Iterator<T> iterator() 
	{
		return new HeapIterator();
	}

	/**
	 * Clase del iterador para la cola de prioridad
	 * @author Julian Padilla - Pablo Pastrana
	 * Obtenido del la pagina web: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/MaxPQ.java.html
	 * Autores originales:Robert Sedgewick y Kevin Wayne
	 */
	private class HeapIterator implements Iterator<T>
	{
		/**
		 * Crea una copia cola de priorida
		 */
		private MaxColaCP<T> copy;

		/**
		 * Añade los elementos a la cola de prioridad
		 */
		public HeapIterator() 
		{
			if (comparador == null) 
			{
				copy = new MaxColaCP<T>(size());
			}
			else                    
			{
				copy = new MaxColaCP<T>(size(), comparador);
			}
			for (int i = 1; i <= n; i++)
			{
				copy.insert(pq[i]);
			}
		}

		/**
		 * Verifica que la cola de prioridad tenga un elemento siguiente
		 */
		public boolean hasNext() 
		{ 
			return !copy.isEmpty();       
		}

		/**
		 * Manda un error que no se puede aplicar la operacion de eliminar
		 */
		public void remove()      
		{
			throw new UnsupportedOperationException();  
		}

		/**
		 * Muestra el elemento siguiente 
		 */
		public T next() 
		{
			if (!hasNext()) 
			{
				throw new NoSuchElementException();
			}
			return copy.delMax();
		}
	}

}
