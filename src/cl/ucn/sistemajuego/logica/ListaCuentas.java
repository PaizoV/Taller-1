package cl.ucn.sistemajuego.logica;

import cl.ucn.sistemajuego.dominio.Cuenta;

public class ListaCuentas {
	private Cuenta[] lista;
	private int cantCuentas;
	private int max;
	
	public ListaCuentas(int max) {
		lista = new Cuenta[max];
		cantCuentas = 0;
		this.max = max;
	}
	
	public int getCantCuentas() {
		return cantCuentas;
	}
	
	public Cuenta getCuentaAt(int i) {
		if (i >= 0 && i < cantCuentas) {
			return lista[i];
		}
		else {
			return null;
		}
	}
	
	public Cuenta buscarCuenta(String nombreCuenta) {
		for (int i = 0; i < cantCuentas; i++) {
			if (lista[i].getNombreCuenta().equals(nombreCuenta)) {
				return lista[i];
			}
		}
		return null;
	}
	
	public boolean ingresarCuenta(Cuenta c) {
		if (cantCuentas < max) {
			lista[cantCuentas] = c;
			cantCuentas++;
			return true;
		}
		else {
			return false;
		}
	}
	//ORDENAMIENTO SEGUN NIVEL DE MAYOR A MENOR
	public void ordenar() {
		for( int i = 0; i < cantCuentas-1; i++) {
			for( int j = i+1 ; j < cantCuentas ; j++ ) {
				if(lista[i].getNivel() < lista[j].getNivel()) {
					Cuenta aux = lista[i];
					lista[i] = lista[j];
					lista[j] = aux;
				}
			}
		}
	}
}
