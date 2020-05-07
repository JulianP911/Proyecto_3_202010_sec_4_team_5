package GrafoJSON;

import java.util.HashMap;
import java.util.Map;

public class Vertex 
{
	private String vertice;
	private Double longitud;
	private Double latitud;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getVertice() {
	return vertice;
	}

	public void setVertice(String vertice) {
	this.vertice = vertice;
	}

	public Double getLongitud() {
	return longitud;
	}

	public void setLongitud(Double longitud) {
	this.longitud = longitud;
	}

	public Double getLatitud() {
	return latitud;
	}

	public void setLatitud(Double latitud) {
	this.latitud = latitud;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
}
