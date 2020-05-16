package model.data_structures;

import java.util.Iterator;

/**
 * Clase de la lista enlazada sencilla que implementa la interfaz IListaEnlazada
 * @author Julian Padilla
 * @param <E> Tipo Generico
 */
public class ListaEnlazada <E> implements IListaEnlazada <E>
{
	// Atributos

	/**
	 * Nodo que hacer referncia al primero
	 */
	private Nodo<E> primero;

	/**
	 * Tamanio de la lista
	 */
	private int tamanio;

	// Metodo Constructor

	/**
	 * El nodo primero se inicializa en null
	 * El tamanio comienza desde 0
	 */
	public ListaEnlazada()
	{
		primero = null;
		tamanio = 0;
	}

	// Metodos
	
	/**
	 * Retorna el nodo cabezera de la lista
	 * @return Nodo cabeza lista
	 */
	public Nodo<E> obtenerPrimero()
	{
		return primero;
	}

	/**
	 * Agregar un objeto a la lista encadenada al principio
	 * @param pObjeto Objeto a agregar al principio
	 */
	public void agregarNodoPrincipio(E pObjeto)
	{
		Nodo<E> nuevoNodo = new Nodo<E>(pObjeto);
		nuevoNodo.establecerNodoSiguiente(primero);
		primero = nuevoNodo;
		tamanio++;
	}

	/**
	 * Agregar un objeto a la lista encadenada al final de esta
	 * @param pObjeto Objeto a agregar al final
	 */
	public void agregarNodoFinal(E pObjeto)
	{
		Nodo<E> nuevoNodo = new Nodo<E>(pObjeto);

		if(primero == null)
		{
			primero = nuevoNodo;
		}
		else
		{
			Nodo<E> actual = primero;

			while(actual.obtenerNodoSiguiente() != null)
			{
				actual = actual.obtenerNodoSiguiente();
			}

			actual.establecerNodoSiguiente(nuevoNodo);
		}
		tamanio++;
	}

	/**
	 * Eliminar un objeto de la lista encadenada al principio
	 * @param pObjeto Objeto a eliminar al pricipio
	 */
	public void eliminarNodoPrincipio()
	{
		primero = primero.obtenerNodoSiguiente();
		tamanio--;
	}

	/**
	 * Eliminar un objeto de la lista encadenada donde se encuntre el objeto que ingresa por parametro
	 * @param pObjeto Objeto a eliminar
	 */
	public void eleminarNodo(E pObjeto)
	{
		Nodo<E> temporal = primero;

		while(temporal != null && !temporal.darObjeto().equals(pObjeto))
		{
			temporal = temporal.obtenerNodoSiguiente();
		}

		if(temporal != null && temporal.darObjeto().equals(pObjeto))
		{
			temporal.establecerNodoSiguiente(temporal.obtenerNodoSiguiente().obtenerNodoSiguiente());
		}

		temporal = temporal.obtenerNodoSiguiente();
		tamanio--;
	}

	/**
	 * Dar el tamanio de lista enlazada sencilla
	 */
	public int darTamnaioLista()
	{
		return tamanio;
	}

	/**
	 * Devuele el objeto en la posicion que ingreso por parametro
	 * @param Posicion en la lista
	 */
	public E verObjeto(int pPosicion)
	{
		Nodo<E> temporal = primero;

		for(int i = 0; i < pPosicion; i++)
		{
			temporal = temporal.obtenerNodoSiguiente();
		}

		return temporal.darObjeto();
	}

	/**
	 * Devuele el objeto en el nodo que ingresa por parametro
	 * @param pNodo Nodo actual
	 */
	public E verObjetoActual(Nodo<E> pNodo)
	{
		E respuesta =null;
		respuesta = pNodo.darObjeto();
		return respuesta;
	}

	/**
	 * Permite cambiar la referencia al nodo siguiente
	 * @param pNodo Nodo actual
	 */
	public void avanzarNodoLista(Nodo<E> pNodo)
	{	
		if(pNodo.obtenerNodoSiguiente() == null)
		{
			pNodo = null;
		}

		pNodo.obtenerNodoSiguiente();
	}
	
	/**
	 * Verifica si la lista enlazada se encuntra vacia
	 * @return True si la lista se encuntra vacio de los contrario False
	 */
	public boolean estaVacia()
	{
		if(tamanio == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Metodo to String
	 */
	public String toString()
	{
		String respuesta = " ";

		for(Nodo<E> temp = primero; temp != null; temp = temp.obtenerNodoSiguiente())
		{
			respuesta += temp.darObjeto().toString() + "\n";
		}

		return respuesta;
	}

	/**
	 * Crea el iterador para recorrer la lista
	 */
	@Override
	public Iterator<E> iterator() 
	{
		return new ListIterator2<E>(this);
	}
}
