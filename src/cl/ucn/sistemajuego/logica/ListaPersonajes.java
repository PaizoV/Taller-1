package cl.ucn.sistemajuego.logica;

import cl.ucn.sistemajuego.dominio.Personaje;

public class ListaPersonajes {
	private Personaje[] lista;
	private int cantPersonajes;
	private int max;
	
	public ListaPersonajes() {
		max = 155;
		lista = new Personaje[max];
		cantPersonajes = 0;
	}
	
	public int getCantPersonajes() {
		return cantPersonajes;
	}
	
	public Personaje getPersonajeAt(int i) {
		if (i >= 0 && i < cantPersonajes) {
			return lista[i];
		}
		else {
			return null;
		}
	}
	
	public Personaje buscarPersonaje(String nombre) {
		for (int i = 0; i < cantPersonajes; i++) {
			if (lista[i].getNombre().equals(nombre)) {
				return lista[i];
			}
		}
		return null;
	}
	
	public boolean ingresarPersonaje(Personaje p) {
		if (cantPersonajes < max) {
			lista[cantPersonajes] = p;
			cantPersonajes++;
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < cantPersonajes; i++) {
			str += lista[i].toString() + "\n";
		}
		return str;
	}
	
}
