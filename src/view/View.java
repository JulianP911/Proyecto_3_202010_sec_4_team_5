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
		System.out.println("Proyecto 3 - Estructura de Datos");
		System.out.println("1. Lectura de datos");
		System.out.println("Parte Inicial - Requerimeintos");
		System.out.println("2. Parte Inicial - Requerimeinto 1: Encontrar vertice de la malla vial más cercano por distacia haversiana");
		System.out.println("3. Parte Inicial - Requerimeinto 2: Asociar comparendos a los vertices teniendo en cuenta un radio de 25m");
		System.out.println("4. Parte Inicial - Requerimeinto 3: Asociar costo de distancia haversiana y numero de comparendos a los arcos");
		System.out.println("5. Parte Inicial - Requerimeinto 4: Adicionar informacion de las estaciones de policia al vertice mas cercano");
		System.out.println("6. Cerrar programa");
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