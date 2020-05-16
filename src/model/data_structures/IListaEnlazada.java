package model.data_structures;

/**
 * Interfaz de la lista encadenada
 * @author julianpadilla
 * @param <E> Tipo Generico
 */
public interface IListaEnlazada <E> extends Iterable<E>
{
	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * @return El tamanio de la lista
	 */
	int darTamnaioLista();
	
	/**
	 * Agregar un objeto a la lista enlazada sencilla al principio
	 * @param pObjeto Nuevo Objeto
	 */
	public void agregarNodoPrincipio(E pObjeto);
	
	/**
	 * Agregar un objeto al final de la lista enlazada sencilla
	 * @param pObjeto Nuevo Objeto
	 */
	public void agregarNodoFinal(E pObjeto);
	
	/**
	 * Elimina el nodo cabezera de la lista enlazada sencilla
	 */
	public void eliminarNodoPrincipio();
	
	/**
	 * Elimina el objeto de la lista enlazada sencilla
	 * @param pObjeto Objeto a eliminar
	 */
	public void eleminarNodo(E pObjeto);
	
	/**
	 * Devuelve el objeto en la posicion indicada de la lista enlazada sencilla
	 * @param pPosicion Posicion en la lista
	 * @return Objeto en la posicion especificada
	 */
	public Object verObjeto(int pPosicion);
	
	/**
	 * Devuelve el objeto actual del nodo que entra como parametro
	 * @param pNodo Nodo actual
	 * @return El objeto actual del nodo
	 */
	public Object verObjetoActual(Nodo<E> pNodo);
}
