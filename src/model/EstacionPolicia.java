package model;

/**
 * Clase de Estacion de policia el cual almacena todas sus caracteristicas y detalles
 * @author Julian Padilla - Pablo Pastrana
 */
public class EstacionPolicia 
{
	// Atributos 
	
	/**
	 * Longitud de la estación de policia
	 */
	private double longitud;
	
	/**
	 * Latitud de la estación de policia
	 */
	private double latitud;
	
	/**
	 * Object Id de la estacion de polica
	 */
	private int objectId;
	
	/**
	 * Descripcion de la estacion de policia
	 */
	private String epodDescrip;
	
	/**
	 * Direccion de la estacion de policia
	 */
	private String epoDir_sitio;
	
	/**
	 * Codigo de la estacion de policia
	 */
	private String epoCod_sitio;
	
	/**
	 * Latitud de la estacion de policia
	 */
	private double epoLatitud;
	
	/**
	 * Longitud de la estacion de policia
	 */
	private double epoLongitud;
	
	/**
	 * Servicio de la estacion de policia
	 */
	private String epoServicio;
	
	/**
	 * Horario de la estacion de policia
	 */
	private String epoHorario;
	
	/**
	 * Telefono de la estacion de policia
	 */
	private String epoTelefono;
	
	/**
	 * Correo electronico de la estacion de policia
	 */
	private String epoCelectr;
	
	/**
	 * Funcion pincipal de la estacion de policia
	 */
	private String epoFuncion;
	
	/**
	 * Tequipa de la estacion de policia
	 */
	private String epoTequipa;
	
	/**
	 * Nombre oficial de la estacion de policia
	 */
	private String epoNombre;
	
	/**
	 * Identificador de la estacion de policia
	 */
	private String epoIdentifi;
	
	// Metodo Constructor
	
	/**
	 * Crea la estacion de policia cons sus caracteristicas y su información basica
	 * @param longitud Longitud 
	 * @param latitud Latitud
	 * @param objectId Object Id de la estacion de polica
	 * @param epodDescrip Descripcion de la estacion de policia
	 * @param epoDir_sitio Direccion de la estacion de policia
	 * @param epoCod_sitio Codigo de la estacion de policia
	 * @param epoLatitud Latitud de la estacion de policia
	 * @param epoLongitud Longitud de la estacion de policia
	 * @param epoServicio Servicio de la estacion de policia
	 * @param epoHorario Horario de la estacion de policia
	 * @param epoTelefono Telefono de la estacion de policia
	 * @param epoCelectr Correo electronico de la estacion de policia
	 * @param epoFuncion Funcion pincipal de la estacion de policia
	 * @param epoTequipa Tequipa de la estacion de policia
	 * @param epoNombre Nombre oficial de la estacion de policia
	 * @param epoIdentifi Identificador de la estacion de policia
	 */
	public EstacionPolicia(double longitud, double latitud, int objectId, String epodDescrip, String epoDir_sitio,
			String epoCod_sitio, double epoLatitud, double epoLongitud, String epoServicio, String epoHorario,
			String epoTelefono, String epoCelectr, String epoFuncion, String epoTequipa, String epoNombre,
			String epoIdentifi) 
	{
		super();
		this.longitud = longitud;
		this.latitud = latitud;
		this.objectId = objectId;
		this.epodDescrip = epodDescrip;
		this.epoDir_sitio = epoDir_sitio;
		this.epoCod_sitio = epoCod_sitio;
		this.epoLatitud = epoLatitud;
		this.epoLongitud = epoLongitud;
		this.epoServicio = epoServicio;
		this.epoHorario = epoHorario;
		this.epoTelefono = epoTelefono;
		this.epoCelectr = epoCelectr;
		this.epoFuncion = epoFuncion;
		this.epoTequipa = epoTequipa;
		this.epoNombre = epoNombre;
		this.epoIdentifi = epoIdentifi;
	}
	
	// Metodos Getters and Setters de los atributos establecidos
	
	public double getLongitud() 
	{
		return longitud;
	}
	
	public void setLongitud(double longitud)
	{
		this.longitud = longitud;
	}
	
	public double getLatitud() 
	{
		return latitud;
	}
	
	public void setLatitud(double latitud) 
	{
		this.latitud = latitud;
	}
	
	public int getObjectId() 
	{
		return objectId;
	}
	
	public void setObjectId(int objectId) 
	{
		this.objectId = objectId;
	}
	
	public String getEpodDescrip()
	{
		return epodDescrip;
	}
	
	public void setEpodDescrip(String epodDescrip) 
	{
		this.epodDescrip = epodDescrip;
	}
	
	public String getEpoDir_sitio() 
	{
		return epoDir_sitio;
	}
	
	public void setEpoDir_sitio(String epoDir_sitio) 
	{
		this.epoDir_sitio = epoDir_sitio;
	}
	
	public String getEpoCod_sitio() 
	{
		return epoCod_sitio;
	}
	
	public void setEpoCod_sitio(String epoCod_sitio)
	{
		this.epoCod_sitio = epoCod_sitio;
	}
	
	public double getEpoLatitud() 
	{
		return epoLatitud;
	}
	
	public void setEpoLatitud(double epoLatitud)
	{
		this.epoLatitud = epoLatitud;
	}
	
	public double getEpoLongitud() 
	{
		return epoLongitud;
	}
	
	public void setEpoLongitud(double epoLongitud) 
	{
		this.epoLongitud = epoLongitud;
	}
	
	public String getEpoServicio() 
	{
		return epoServicio;
	}
	
	public void setEpoServicio(String epoServicio) 
	{
		this.epoServicio = epoServicio;
	}
	
	public String getEpoHorario() 
	{
		return epoHorario;
	}
	
	public void setEpoHorario(String epoHorario) 
	{
		this.epoHorario = epoHorario;
	}
	
	public String getEpoTelefono() 
	{
		return epoTelefono;
	}
	
	public void setEpoTelefono(String epoTelefono) 
	{
		this.epoTelefono = epoTelefono;
	}
	
	public String getEpoCelectr() 
	{
		return epoCelectr;
	}
	
	public void setEpoCelectr(String epoCelectr)
	{
		this.epoCelectr = epoCelectr;
	}
	
	public String getEpoFuncion()
	{
		return epoFuncion;
	}
	
	public void setEpoFuncion(String epoFuncion)
	{
		this.epoFuncion = epoFuncion;
	}
	
	public String getEpoTequipa() 
	{
		return epoTequipa;
	}
	
	public void setEpoTequipa(String epoTequipa)
	{
		this.epoTequipa = epoTequipa;
	}
	
	public String getEpoNombre() 
	{
		return epoNombre;
	}
	
	public void setEpoNombre(String epoNombre) 
	{
		this.epoNombre = epoNombre;
	}
	
	public String getEpoIdentifi() 
	{
		return epoIdentifi;
	}
	
	public void setEpoIdentifi(String epoIdentifi) 
	{
		this.epoIdentifi = epoIdentifi;
	}
}
