package GrafoJSON;

import java.util.HashMap;
import java.util.Map;

public class Arco 
{
	private String arcoEntrada;
	private Double distanciaHaversiana;
	private String arcoSalida;
	
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getArcoEntrada() {
	return arcoEntrada;
	}

	public void setArcoEntrada(String arcoEntrada) {
	this.arcoEntrada = arcoEntrada;
	}

	public Double getDistanciaHaversiana() {
	return distanciaHaversiana;
	}

	public void setDistanciaHaversiana(Double distanciaHaversiana) {
	this.distanciaHaversiana = distanciaHaversiana;
	}

	public String getArcoSalida() {
	return arcoSalida;
	}

	public void setArcoSalida(String arcoSalida) {
	this.arcoSalida = arcoSalida;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
}
