package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase del Bag utilizada por la clase graph
 * @author Julian Padilla - Pablo Pastrana
 * Los metodos de está clase fueron obtenidos del libro Algorthims 4 edition - Universidad de Princeton
 * Autores de los metodos son: Robert Sedgewick y Kevin Wayne
 */
public class Bag<Item> implements Iterable<Item>
{
	// Atributos
	
	/**
	 * Comienzo de la bolsa
	 */
	private Node<Item> first;    
	
	/**
	 * Numero de elementos en la bolsa
	 */
    private int n;               

    /**
     * Clase de node utilizada por bag
     * @author Julian Padilla - Pablo Pastrana
     * Autores de los metodos son: Robert Sedgewick y Kevin Wayne
     * @param <Item>
     */
    private static class Node<Item> 
    {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Inicialiiza la bolsa vacia
     */
    public Bag() 
    {
        first = null;
        n = 0;
    }

    /**
     * Verifica si la bolsa esta vacio.
     * @return {@code true} Si la bolsa esta vacio
     *         {@code false} La bolsa no se encuentra vacia
     */
    public boolean isEmpty() 
    {
        return first == null;
    }

    /**
     * Retorna el numero de elementos de la bolsa.
     * @return Numero de elementos de la bolsa.
     */
    public int size() 
    {
        return n;
    }

    /**
     * Añadir elemento a la bolsa.
     * @param  Elemento a ser añadido a la bolsa
     */
    public void add(Item item) 
    {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


    /**
     * Retorna un iterador que itera sobre los elementos dentro de la bolsa arbitrariamente.
     * @return Un iterador que itera sobre los elementos dentro de la bolsa arbitrariamente.
     */
    public Iterator<Item> iterator()  
    {
        return new LinkedIterator(first);  
    }

    private class LinkedIterator implements Iterator<Item> 
    {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  
        { 
        	return current != null;              
        }
        
        public void remove()      
        { 
        	throw new UnsupportedOperationException(); 
        }

        public Item next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

}
