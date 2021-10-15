package cl.ucn.sistemajuego.dominio;

import cl.ucn.sistemajuego.logica.ListaSkins;

public class Personaje {
	private String nombre;
	private String rol;
	private ListaSkins skins;
	private double recaudacion;
	
	public Personaje(String nombre, String rol) {
		this.nombre = nombre;
		this.rol = rol;
		skins = new ListaSkins(1000);
		recaudacion = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public ListaSkins getSkins() {
		return skins;
	}

	public void setSkins(ListaSkins skins) {
		this.skins = skins;
	}

	public double getRecaudacion() {
		return recaudacion;
	}

	public void setRecaudacion(double recaudacion) {
		this.recaudacion = recaudacion;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", rol: " + rol +", recaudacion: $" + recaudacion + 
				", skins:\n" + skins;
	}
	
}
