package GrafoJSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example 
{
	private String name;
	private String type;
	private List<Vertex> vertices = null;
	private List<Arco> arcos = null;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getType() {
	return type;
	}

	public void setType(String type) {
	this.type = type;
	}

	public List<Vertex> getVertices() {
	return vertices;
	}

	public void setVertices(List<Vertex> vertices) {
	this.vertices = vertices;
	}

	public List<Arco> getArcos() {
	return arcos;
	}

	public void setArcos(List<Arco> arcos) {
	this.arcos = arcos;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
}
