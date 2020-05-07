package model;

import java.util.Date;

/**
 * Clase de tipo comparendo el cual almacena todas sus caracteristicas y detalles
 * @author Julian Padilla - Pablo Pastrana
 */
public class Comparendo implements Comparable<Comparendo>
{
	// Atributos
	
	/**
	 * Numero de reconocimiento del comparendo
	 */
	private int objectId;
	
	/**
	 * Fecha en la cual fue tomado el comparendo
	 */
	private Date fecha_hora;
	
	/**
	 * Descripcion del comparendo detalladamente
	 */
	private String des_infrac;
	
	/**
	 * Medio por el cual se tomo el comparendo
	 */
	private String medio_dete;
	
	/**
	 * Clase de vehiculo al cual se le aplico el comparendo
	 */
	private String clase_vehi;
	
	/**
	 * Tipo de servicio del vehiculo al cual se le aplico el comparendo
	 */
	private String tipo_servi;
	
	/**
	 * Infraccion impuesta al vehiculo
	 */
	private String infraccion;
	
	/**
	 * Localida en la cual fue realizado el comparendo
	 */
	private String localidad;
	
	/**
	 * Municipio en la cual fue realizado el comparendo
	 */
	private String municipio;

	/**
	 * Latitud de donde fue tomado el comparendo
	 */
	private double latitud;
	
	/**
	 * Longitud de donde fue tomado el comparendo
	 */
	private double longitud;

	// Metodo Constructor

	/**
	 * Metodo constructor del comparendo
	 * @param pObjective Objective del comparendo
	 * @param pFecha_hora Fecha_hora del comparendo
	 * @param pMedio_dete Medio_ Dete del comparendo
	 * @param pClase_vehi Clase_vehi del comparendo
	 * @param pTipo_servi Tipo_servi del comparendo
	 * @param pInfraccion Infraccion del comparendo
	 * @param pDes_infrac Des_infrac del comparendo
	 * @param pLocalidad Localidad del comparendo
	 * @param PMunicipio Municipio del comparendo
	 * @param pLatitud Latitud del comparendo
	 * @param pLongitud Longitud del comparendo
	 */
	public Comparendo(int objeId, Date fecha, String descripcion, String detencion, String claseVeh, String tipoSer, String codInfraccion, String localidadP, String municipioP, double lonP, double latP)
	{
		objectId = objeId;
		fecha_hora = fecha;
		des_infrac = descripcion;
		medio_dete = detencion;
		clase_vehi = claseVeh;
		tipo_servi = tipoSer;
		infraccion = codInfraccion;
		municipio = municipioP;
		localidad = localidadP;
		longitud = lonP;
		latitud = latP;
	}

	// Metodos

	/**
	 * Da el objective del comparendo
	 * @return Objective
	 */
	public int getObjective() 
	{
		return objectId;
	}

	/**
	 * Cambia el objective del comparendo
	 * @param objective Nuevo objective
	 */
	public void setObjective(int objective) 
	{
		this.objectId = objective;
	}

	/**
	 * Da la fecha y la hora a la cual se tomo el comparendo
	 * @return Fecha y Hora
	 */
	public Date getFecha_hora() 
	{
		return fecha_hora;
	}

	/**
	 * Cambia la fecha y la hora del comparendo
	 * @param fecha_hora Nueva Fecha_Hora
	 */
	public void setFecha_hora(Date fecha_hora) 
	{
		this.fecha_hora = fecha_hora;
	}

	/**
	 * Da el Medio_dete del comparendo
	 * @return Medio_dete 
	 */
	public String getMedio_dete() 
	{
		return medio_dete;
	}

	/**
	 * Cambia el medio en el cual se tomo el comparendo
	 * @param medio_dete Nuevo medio_dete
	 */
	public void setMedio_dete(String medio_dete) 
	{
		this.medio_dete = medio_dete;
	}

	/**
	 * Da la clase del vehiculo al cual se le aplico el comparendo
	 * @return Clase_vehi
	 */
	public String getClase_vehi() 
	{
		return clase_vehi;
	}

	/**
	 * Cambia la clase del vehiculo al cual se le aplico el comparendo
	 * @param clase_vehi Nuevo clase_vehi
	 */
	public void setClase_vehi(String clase_vehi) 
	{
		this.clase_vehi = clase_vehi;
	}

	/**
	 * Da el tipo del vehiculo al cual se le aplico el comparendo
	 * @return Tipo_vehi
	 */
	public String getTipo_servi()
	{
		return tipo_servi;
	}

	/**
	 * Cambia el tipo del vehiculo al cual se le aplico el comparendo
	 * @param tipo_servi Nuevo Tipo_servi
	 */
	public void setTipo_servi(String tipo_servi) 
	{
		this.tipo_servi = tipo_servi;
	}

	/**
	 * Da el tipo del infraccion la cual se le aplico el comparendo
	 * @return Infraccion
	 */
	public String getInfraccion() 
	{
		return infraccion;
	}

	/**
	 * Cambia el tipo del infraccion la cual se le aplico el comparendo
	 * @param infraccion Nueva infraccion
	 */
	public void setInfraccion(String infraccion) 
	{
		this.infraccion = infraccion;
	}

	/**
	 * Da la descripcion del comparendo que se aplico
	 * @return Des_infrac
	 */
	public String getDes_infrac() 
	{
		return des_infrac;
	}

	/**
	 * Cambia la descripcion del comparendo que se aplico
	 * @param des_infrac Nuevo des_infrac
	 */
	public void setDes_infrac(String des_infrac) 
	{
		this.des_infrac = des_infrac;
	}

	/**
	 * Da la localidad donde se aplico el comparendo
	 * @return Localida
	 */
	public String getLocalidad() 
	{
		return localidad;
	}

	/**
	 * Cambia la localidad donde se aplico el comparendo
	 * @param localidad Nueva localida
	 */
	public void setLocalidad(String localidad) 
	{
		this.localidad = localidad;
	}

	/**
	 * Da el municipio donde se aplico el comparendo
	 * @return Municipio
	 */
	public String getMunicipio() 
	{
		return municipio;
	}

	/**
	 * Cambia el municipio donde se aplico el comparendo
	 * @param municipio Nuevo municipio
	 */
	public void setMunicipio(String municipio) 
	{
		this.municipio = municipio;
	}

	/**
	 * Da la latitud del comparendo
	 * @return Latitud
	 */
	public double getLatitud() 
	{
		return latitud;
	}

	/**
	 * Cambia la latitud del comparendo
	 * @param Latitud Nueva latitud
	 */
	public void setLatitud(double latitud) 
	{
		this.latitud = latitud;
	}

	/**
	 * Da la longitud del comparendo
	 * @return Longitud
	 */
	public double getLongitud() 
	{
		return longitud;
	}

	/**
	 * Cambia la longitud del comparendo
	 * @param Longitud Nueva longitud
	 */
	public void setLongitud(double longitud) 
	{
		this.longitud = longitud;
	}

	/**
	 * Compara los comparendos por la latitud criterio de prioridad
	 * Si las fechas_horas son iguales se compara por el objectid
	 */
	@Override
	public int compareTo(Comparendo pItem) 
	{
		int resultado = 0;

		if(this.getLatitud() < pItem.getLatitud())         // Compara la latitud si es menor
		{
			resultado  = 1;
		}
		else if(this.getLatitud() == pItem.getLatitud())   // Compara la latitud si es igual
		{
			resultado = 0;
		}
		else if(this.getLatitud() > pItem.getLatitud())    // Compara la latitud si es mayor
		{
			resultado = -1;
		}

		return resultado;
	}

	/**
	 * Metodo toString, proporciona la informacion del comparendo actual
	 */
	@Override
	public String toString() 
	{
		return "Comparendo [OBJECTID=" + objectId + ", FECHA_HORA=" + fecha_hora + ", DES_INFRAC=" + des_infrac
				+ ", MEDIO_DETE=" + medio_dete + ", CLASE_VEHI=" + clase_vehi + ", TIPO_SERVI=" + tipo_servi
				+ ", INFRACCION=" + infraccion + ", LOCALIDAD=" + localidad + ", MUNICIPIO=" + municipio + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}

}
