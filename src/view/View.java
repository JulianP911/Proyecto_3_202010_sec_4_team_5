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
		System.out.println("6. Requerimiento 1A - Obtener el camino de costo mínimo entre dos ubicaciones geográficas por distancia");
	    System.out.println("7. Requerimiento 2A - Determinar la red de comunicaciones que soporte la instalación de cámaras de video en los M puntos donde se presentan los comparendos de mayor gravedad");
	    System.out.println("8. Requerimineto 1B - Obtener el camino de costo mínimo entre dos ubicaciones geográficas por número de comparendos");
	    System.out.println("9. Requerimineto 2B - Determinar la red de comunicaciones que soporte la instalación de cámaras de video en los M puntos donde se presenta el mayor número de comparendos en la ciudad");
	    System.out.println("10. Requerimineto 1C - Obtener los caminos más cortos para que los policías puedan atender los M comparendos más graves");
	    System.out.println("11. Requerimiento 2C - Identificar las zonas de impacto de las estaciones de policía.");
	    System.out.println("12. Requerimiento 2C 'Extra' - Identificar las zonas de impacto de las estaciones de policía.");
	    System.out.println("13. Cerrar programa");
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