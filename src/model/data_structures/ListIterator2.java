package model.data_structures;

import java.util.Iterator;

/**
 * Obtenido desde: https://www.geeksforgeeks.org/java-implementing-iterator-and-iterable-interface/
 * @param <E> Tipo Generico
 */
public class ListIterator2<E> implements Iterator<E> 
{ 
    Nodo<E> current; 
      
    // initialize pointer to head of the list for iteration 
    public ListIterator2(ListaEnlazada<E> list) 
    { 
        current = list.obtenerPrimero(); 
    } 
      
    // returns false if next element does not exist 
    public boolean hasNext() 
    { 
        return current != null; 
    } 
      
    // return current data and update pointer 
    public E next() 
    { 
        E data = current.darObjeto(); 
        current = current.obtenerNodoSiguiente(); 
        return data; 
    } 
      
    // implement if needed 
    public void remove() 
    { 
        throw new UnsupportedOperationException(); 
    } 
} 
