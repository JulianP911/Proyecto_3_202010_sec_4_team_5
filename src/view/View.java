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
		System.out.println("2. Cerrar");
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