package model;

import java.util.Comparator;

/**
 * Clase comparador de tipo de servicio
 * @author Julian Padilla Molina - Pablo Pastrana
 * Implementa Comparator de tipo comparendo
 */
public class ComparadorTipoServicio implements Comparator<Comparendo> 
{
	@Override
	public int compare(Comparendo p1, Comparendo p2) 
	{
		int resultado = 0;
		
		if(p1.getTipo_servi().compareTo(p2.getTipo_servi()) > 0)		
		{
			resultado = -1;
		}
		else if(p1.getTipo_servi().compareTo(p2.getTipo_servi()) == 0)
		{
			resultado = 0;
		}
		else if(p1.getTipo_servi().compareTo(p2.getTipo_servi()) < 0)
		{
			resultado = 1;
		}
		
		return resultado;
	}
}
