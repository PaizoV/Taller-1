package cl.ucn.sistemajuego.logica;

import cl.ucn.sistemajuego.dominio.Skin;

public class ListaSkins {
	private Skin[] lista;
	private int cantSkins;
	private int max;
	
	public ListaSkins(int max) {
		lista = new Skin[max];
		cantSkins = 0;
		this.max = max;
	}
	
	public int getCantSkins() {
		return cantSkins;
	}
	
	public Skin getSkinAt(int i) {
		if (i >= 0 && i < cantSkins) {
			return lista[i];
		}
		else {
			return null;
		}
	}
	
	public Skin buscarSkin(String nombre) {
		for (int i = 0; i < cantSkins; i++) {
			if (lista[i].getNombre().equals(nombre)) {
				return lista[i];
			}
		}
		return null;
	}
	
	public boolean ingresarSkin(Skin s) {
		if (cantSkins < max) {
			lista[cantSkins] = s;
			cantSkins++;
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < cantSkins; i++) {
			str +=	lista[i].toString() + "\n";
		}
		return str;
	}
}
