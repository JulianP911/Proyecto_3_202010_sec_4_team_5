package model;

public class Arco 
{
	private String arcoSalida;
	private String arcoEntrada;
	public String getArcoSalida() {
		return arcoSalida;
	}

	public void setArcoSalida(String arcoSalida) {
		this.arcoSalida = arcoSalida;
	}

	public String getArcoEntrada() {
		return arcoEntrada;
	}

	public void setArcoEntrada(String arcoEntrada) {
		this.arcoEntrada = arcoEntrada;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	private double costo;
	
	public Arco(String arcoSalida, String arcoEntrada, double costo) {
		super();
		this.arcoSalida = arcoSalida;
		this.arcoEntrada = arcoEntrada;
		this.costo = costo;
	}
}
