package controller;

import java.util.Scanner;

import Mapa.Mapa;
import Mapa.Mapa3;
import model.InformacionArco;
import model.InformacionVertice;
import model.data_structures.Vertex;
import model.logic.MallaVialBogota;
import model.logic.Modelo;
import view.View;

public class Controller 
{

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
				view.printMessage("El numero de comparendos asociados a los vertice en total es de: " + modelo.cargarDatos3().getSize() + " en un total de 228046 vertices.");
				view.printMessage("");
				break;

			case 4:
				view.printMessage("");
				mallaVial.asociarComparendosArcos();
				view.printMessage("El numero de comparendos asociados a los arcos en total es de: " + modelo.cargarDatos3().getSize() + " en un total de 272457 arcos.");
				view.printMessage("");
				break;

			case 5:
				view.printMessage("");
				mallaVial.asociarEstacionesVertice();
				view.printMessage("El numero de estaciones de policia asociadas a los vertice en total es de: " + modelo.cargarDatosEstacionesPolicia2().getSize() + " en un total de 21 vertices (Estacion por vertice).");
				view.printMessage("");
				break;

				// Requerimiento 1A
			case 6:
				view.printMessage("Por favor ingresar la informacion del punto de origen (Formato: latitud,longitud):");
				String puntoOrigen1 = lector.next();
				view.printMessage("Por favor ingresar la informacion del punto de destino (Formato: latitud,longitud):");
				String puntoDestino1 = lector.next();
				String limites = mallaVial.darCoordenadasMaxYMinBogota();

				String[] arr1 = puntoOrigen1.split(",");
				String[] arr2 = puntoDestino1.split(",");
				String[] arr3 = limites.split(",");

				if(Double.parseDouble(arr1[0]) >= Double.parseDouble(arr3[0]) && Double.parseDouble(arr1[0]) <= Double.parseDouble(arr3[1]) && Double.parseDouble(arr1[1]) <= Double.parseDouble(arr3[2]) && Double.parseDouble(arr1[1]) >= Double.parseDouble(arr3[3])
						&& Double.parseDouble(arr2[0]) >= Double.parseDouble(arr3[0]) && Double.parseDouble(arr2[0]) <= Double.parseDouble(arr3[1]) && Double.parseDouble(arr2[1]) <= Double.parseDouble(arr3[2]) && Double.parseDouble(arr2[1]) >= Double.parseDouble(arr3[3]))
				{
					Vertex<String,InformacionVertice,InformacionArco> origen1 = mallaVial.aproximarCordenadasVerticesGrafoVer(puntoOrigen1);
					Vertex<String,InformacionVertice,InformacionArco> destino1 = mallaVial.aproximarCordenadasVerticesGrafoVer(puntoDestino1);

					view.printMessage("");
					mallaVial.grafoMenorDistancia(origen1, destino1);
					view.printMessage("");

					@SuppressWarnings("unused") 
					Mapa mapa1 = new Mapa(mallaVial.grafoMenorDistanciaPintar(origen1, destino1) , "Grafo");

				}
				else
				{
					view.printMessage("Las coordenadas ingresadas no se encuantran dentro de los limites establecidos");
				}
				break;

				// Requerimiento 2A
			case 7:
				view.printMessage("Ingrese el numero de vertices en el cual se quiere instalar la red de camaras de acuerdo a los comparendos de mayor gravedad: ");
				int verticesM = lector.nextInt();
				view.printMessage("");
				long startTime = System.currentTimeMillis();
				view.printMessage("Informacion detallada de la solución: ");
				mallaVial.informacionRedComunicacionesInstalacioneCamera(verticesM);
				long endTime = System.currentTimeMillis();
				long duration = endTime - startTime;
				view.printMessage("Tiempo que le toma al algoritmo en encontrar la solución: " + duration + " en milisegundos \n");
				@SuppressWarnings("unused") 
				Mapa3 actual1 = new Mapa3(mallaVial.redComunicacionesInstalacionCamaras(verticesM) , "Grafo MST");
				break;

				// Requerimiento 1B
			case 8:
				view.printMessage("Por favor ingresar la informacion del punto de origen (Formato: latitud,longitud):");
				String puntoOrigen2 = lector.next();
				view.printMessage("Por favor ingresar la informacion del punto de destino (Formato: latitud,longitud):");
				String puntoDestino2 = lector.next();
				String limites2 = mallaVial.darCoordenadasMaxYMinBogota();

				String[] arr4 = puntoOrigen2.split(",");
				String[] arr5 = puntoDestino2.split(",");
				String[] arr6 = limites2.split(",");

				if(Double.parseDouble(arr4[0]) >= Double.parseDouble(arr6[0]) && Double.parseDouble(arr4[0]) <= Double.parseDouble(arr6[1]) && Double.parseDouble(arr4[1]) <= Double.parseDouble(arr6[2]) && Double.parseDouble(arr4[1]) >= Double.parseDouble(arr6[3])
						&& Double.parseDouble(arr5[0]) >= Double.parseDouble(arr6[0]) && Double.parseDouble(arr5[0]) <= Double.parseDouble(arr6[1]) && Double.parseDouble(arr5[1]) <= Double.parseDouble(arr6[2]) && Double.parseDouble(arr5[1]) >= Double.parseDouble(arr6[3]))
				{
					Vertex<String,InformacionVertice,InformacionArco> origen2 = mallaVial.aproximarCordenadasVerticesGrafoVer(puntoOrigen2);
					Vertex<String,InformacionVertice,InformacionArco> destino2 = mallaVial.aproximarCordenadasVerticesGrafoVer(puntoDestino2);

					view.printMessage("");
					mallaVial.grafoMenorComparendos(origen2, destino2);
					view.printMessage("");

					@SuppressWarnings("unused") 
					Mapa mapa1 = new Mapa(mallaVial.grafoMenorDistanciaPintar(origen2, destino2) , "Grafo");

				}
				else
				{
					view.printMessage("Las coordenadas ingresadas no se encuantran dentro de los limites establecidos");
				}
				break;

				// Requerimiento 2B
			case 9:
				view.printMessage("Ingrese el numero de vertices en el cual se quiere instalar la red de camaras en donde se presentan el mayor numero de comparendos: ");
				int verticesComM = lector.nextInt();
				view.printMessage("");
				long startTime1 = System.currentTimeMillis();
				view.printMessage("Informacion detallada de la solución: ");
				mallaVial.informacionRedComunicacionesInstalacioneCameraMayorComparendos(verticesComM);
				mallaVial.redComunicacionesInstalacionCamarasMayorComparendosSinMallaVial(verticesComM);
				long endTime1 = System.currentTimeMillis();
				long duration1 = endTime1 - startTime1;
				view.printMessage("Tiempo que le toma al algoritmo en encontrar la solución: " + duration1 + " en milisegundos \n");
				@SuppressWarnings("unused") 
				Mapa3 actual2 = new Mapa3(mallaVial.redComunicacionesInstalacionCamarasMayorComparendos(verticesComM) , "Grafo MST");
				break;

				// Requerimiento 1C
			case 10:
				break;

				// Requerimiento 2C
			case 11:
				break;

			case 12:
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
