package cl.ucn.sistemajuego.dominio;

public class Skin {
	private String nombre;
	private String calidad;
	private int precio;
	
	public Skin(String nombre, String calidad, int precio) {
		this.nombre = nombre;
		this.calidad = calidad;
		this.precio = precio;
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

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Calidad: " + calidad + ", Precio: " + precio + " RP";
	}
	
}
