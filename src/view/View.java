package view;

import model.logic.Modelo;

public class View 
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}

	/**
	 * Imprime los mensajes iniciales que se le muestran al usuario
	 */
	public void printMenu()
	{
		System.out.println("Taller 7 -  Grafo No Dirigido");
		System.out.println("1. Carga del Grafo");
		System.out.println("2. Generar archivo json del Grafo No Dirigido");
		System.out.println("3. Cargar grafo desde el archivo JSON generado en el punto anterior");
		System.out.println("4. Mostrar mapa con las cordenadas asignadas en la guia");
		System.out.println("5. Carga archivo estaciones de policia");
		System.out.println("6. Mostrar mapa con las estaciones de policias");
		System.out.println("7. Cerrar");

	}

	/**
	 * Imprime mensajes de tipo string
	 * @param mensaje Mensaje correspondiente
	 */
	public void printMessage(String mensaje) 
	{
		System.out.println(mensaje);
	}		

	/**
	 * Imprime mensajes de tipo modelo, sacando la informacion
	 * @param modelo Modelo clase
	 */
	public void printModelo(Modelo modelo)
	{
		System.out.println(modelo);
	}
}