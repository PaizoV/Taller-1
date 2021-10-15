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
	
	public int buscarCuenta(String nombreCuenta) {
		for (int i = 0; i < cantCuentas; i++) {
			if (lista[i].getNombreCuenta().equals(nombreCuenta)) {
				return i;
			}
		}
		return -1;
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
}
