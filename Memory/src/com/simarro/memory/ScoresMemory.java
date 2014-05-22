package com.simarro.memory;

public class ScoresMemory 
{
	private String nombre;
	private String tiempo;
	private String dificultad;
	
	public ScoresMemory(String nombre, String dificultad, String tiempo)
	{
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.dificultad = dificultad;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
}
