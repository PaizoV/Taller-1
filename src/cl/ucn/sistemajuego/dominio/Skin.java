package cl.ucn.sistemajuego.dominio;

public class Skin {
	private String nombre;
	private String calidad;
	private int precio;
	private Personaje personaje;
	
	public Skin(String nombre, String calidad) {
		this.nombre = nombre;
		this.calidad = calidad;
		switch (calidad) {
		case "M":
			precio = 3250;
		case "D":
			precio = 2750;
		case "L":
			precio = 1820;
		case "E":
			precio = 1350;
		case "N":
			precio = 975;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public Personaje getPersonaje() {
		return personaje;
	}
	
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", calidad: " + calidad + ", precio: " + precio + " RP";
	}
	
}
