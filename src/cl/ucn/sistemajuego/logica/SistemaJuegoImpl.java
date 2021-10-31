package cl.ucn.sistemajuego.logica;

import cl.ucn.sistemajuego.dominio.*;

public class SistemaJuegoImpl implements SistemaJuego {
	private ListaCuentas cuentas;
	private ListaPersonajes personajes;
	private ListaSkins skins;
	
	public SistemaJuegoImpl() {
		cuentas = new ListaCuentas(10_000);
		personajes = new ListaPersonajes();
		skins = new ListaSkins(10_000);
	}

	@Override
	public boolean ingresarPersonaje(String nombre, String rol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ingresarCuenta(String nombreCuenta, String contrasena, String nick, int nivel, double rp,
			String region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean asociarPersonajeCuenta(String nombreCuenta, String nombrePersonaje) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ingresarSkin(String nombre, int costo, String calidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void asociarRecaudacion(double recaudacion, String nombrePersonaje) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validarCliente(String nombreCuenta, String contrasena) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean comprarSkin(String nombrePersonaje, String nombreCuenta, String nombreSkin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean comprarPersonaje(String nombrePersonaje, String nombreCuenta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String obtenerSkinsDisponibles(String nombreCuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerInventario(String nombreCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recargarRp(String nombreCuenta, int cantidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public String obtenerDatosCuenta(String nombreCuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cambiarClave(String nombreCuenta, String claveNueva) {
		// TODO Auto-generated method stub

	}

	@Override
	public double obtenerRecaudacionRol() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double obtenerRecaudacionRegion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int obtenercantPersonajesRol() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void bloquearJugador(String nombreCuenta) {
		// TODO Auto-generated method stub

	}

	@Override
	public String obtenerInfoCuentas() {
		// TODO Auto-generated method stub
		return null;
	}
}
