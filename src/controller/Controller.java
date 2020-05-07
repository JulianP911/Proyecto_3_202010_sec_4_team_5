package controller;

import java.util.Scanner;

import Mapa.Mapa;
import Mapa.Mapa2;
import model.EstacionPolicia;
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
				view.printMessage("Información del grafo:");
				mallaVial.cargarGrafo();
				break;
				
			case 2:
				System.out.println("Ingresar la ruta par crear el archvio: ");
				String entrada1 = lector.next();
				System.out.println("Nombre del archivo añadir la extension (.json): ");
				String entrada2 = lector.next();
				System.out.println("El archivo .json tendrá: ");
				mallaVial.generarJSON(entrada1, entrada2);
				System.out.println("El archivo ha sido generado perfectamente en el direccion especificado");
				break;
				
			case 3:
				System.out.println("El grafo desde el archivo JSON se esta cargando ...");
				mallaVial.cargarGrafoJSON();
				System.out.println("Se cargo adecuadamente");
				break;

			case 4:
				@SuppressWarnings("unused") 
				Mapa temp = new Mapa("Grafo con Zona Delimitada");
				break;
				
			case 5:
				int numeroEstaciones = modelo.cargarDatosEstacionesPolicia().size();
				System.out.println("El numero de estaciones de policias en Bogotá es: " + numeroEstaciones);
				
				view.printMessage("Estas son las estaciones de policia en Bogotá:");
				for(int i = 0; i < numeroEstaciones; i++)
				{
					EstacionPolicia actual = modelo.cargarDatosEstacionesPolicia().get(i);
					view.printMessage(actual.getObjectId() + ", " + actual.getEpoNombre() + ", " + actual.getEpoIdentifi() + ", " + actual.getEpoDir_sitio() + ", " + actual.getEpoHorario() + ", " + actual.getEpoTelefono() + ", " + actual.getEpoCelectr() + ", Longitud: " + actual.getEpoLongitud() + ", Latitud: " + actual.getEpoLatitud());
				}
				break;
				
			case 6:
				@SuppressWarnings("unused") 
				Mapa2 temp1 = new Mapa2("Grafo con Estaciones de policia");
				break;
				
			case 7:
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
