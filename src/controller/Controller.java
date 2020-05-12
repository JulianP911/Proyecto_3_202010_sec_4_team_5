package controller;

import java.util.Scanner;

import model.logic.MallaVialBogota;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia del Malla vial*/
	private MallaVialBogota mallaVial;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
		mallaVial = new MallaVialBogota();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				view.printMessage("");
				view.printMessage("Lectura de datos - Proyecto 3:");
				view.printMessage("");
				int numeroComparendos = modelo.cargarDatos().size();
				int numeroEstaciones = modelo.cargarDatosEstacionesPolicia().size();
				
				view.printMessage("El numero de comparendos tomados en el año 2018 es de: " + numeroComparendos);
				view.printMessage("El comparendo con el mayor OBJECTID es: ");
				view.printMessage(modelo.darObjectidMayorComparendo());
				view.printMessage("");
				view.printMessage("El numero de estaciones de policia en Bogota es de: " + numeroEstaciones);
				view.printMessage("La estacion de policia con el mayor OBJECTID es: ");
				view.printMessage(modelo.darObjectidMayorEstacion());
				view.printMessage("");
				view.printMessage("Información del grafo:");
				view.printMessage("El numero de vertices en el grafo de la malla vial de Bogota es: ");
				view.printMessage(mallaVial.cargarGrafo().V() + "");
				view.printMessage("El vertice con el mayor ID encontrado es: ");
				view.printMessage(mallaVial.darIdMayorVertice());
				view.printMessage("El numero de arcos en el grafo de la malla vial de Bogota es: ");
				view.printMessage(mallaVial.cargarGrafo().E() + "");
				view.printMessage("La información de los arcos con el mayor ID: ");
				view.printMessage(mallaVial.darIdMayorArco());
				view.printMessage("");
				break;
				
			case 2:
				view.printMessage("");
				view.printMessage("Por favor ingresar una longitud a consultar: ");
				double entrada1 = lector.nextDouble();
				view.printMessage("Por favor ingresar una latitud a consultar: ");
				double entrada2 = lector.nextDouble();
				view.printMessage("El vertice mas cercano a las cordenadas de longitud y latitud ingresadas en la malla vial es: ");
				view.printMessage(mallaVial.darIdLocalizacion(entrada2, entrada1));
				view.printMessage("");
				break;
				
			case 3:
				view.printMessage("");
				mallaVial.asociarComparendosVertice();
				view.printMessage("El numero de comparendos asociados a los vertice en total es de: " + modelo.cargarDatos2().getSize() + " en un total de 228046 vertices.");
				view.printMessage("");
				break;

			case 4:
				view.printMessage("");
				mallaVial.asociarComparendosArcos();
				view.printMessage("El numero de comparendos asociados a los arcos en total es de: " + modelo.cargarDatos2().getSize() + " en un total de 272457 arcos.");
				view.printMessage("");
				break;
				
			case 5:
				view.printMessage("");
				mallaVial.asociarEstacionesVertice();
				view.printMessage("El numero de estaciones de policia asociadas a los vertice en total es de: " + modelo.cargarDatosEstacionesPolicia2().getSize() + " en un total de 21 vertices (Estacion por vertice).");
				view.printMessage("");
				break;

			case 6:
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}
	}	
}
