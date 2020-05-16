package model.data_structures;

/**
 * Clase Nodo para ser utilizada en Lista Enlazada Sencilla
 * @author julianpadilla
 * @param <E> Tipo de dato Genrico
 */
public class Nodo <E> 
{	
	// Atributos
	
	/**
	 * Objeto en el nodo correspondiente
	 */
	private E objeto;
	
	/**
	 * Nodo siguiente
	 */
	private Nodo<E> siguente;
	
	// Metodo Constructor
	
	/**
	 * Al nodo se le traspasa el objeto que ingresa por parametro
	 * El nodo siguiente es null por que no tiene una referencia en este punto
	 * @param pDato Dato a almacenar en el nodo
	 */
	public Nodo(E pObjeto)
	{
		this.objeto = pObjeto;
		this.siguente = null;
	}
	
	// Metodos
	
	/**
	 * Indica y apunta al nodo siguiente 
	 * @return Nodo siguiente
	 */
	public Nodo<E> obtenerNodoSiguiente()
	{
		return this.siguente;
	}
	
	/**
	 * Establece el nodo siguiente al que tengo
	 * @param pSiguiente Nodo siguiente
	 */
	public void establecerNodoSiguiente(Nodo<E> pSiguiente)
	{
		this.siguente = pSiguiente;
	}
	
	/**
	 * Devuelve el objeto almacenado en el nodo actual 
	 * @return Dato almacenado en el nodo
	 */
	public E darObjeto()
	{
		return this.objeto;
	}
	
	/**
	 * Ingresa el objeto por parametro para establecerse en el nodo actual
	 * @param pObjeto Objeto a colocar en el nodo
	 */
	public void establecerObjeto(E pObjeto)
	{
		this.objeto = pObjeto;
	}
}
