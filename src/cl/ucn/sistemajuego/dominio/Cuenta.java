package cl.ucn.sistemajuego.dominio;

import cl.ucn.sistemajuego.logica.*;

public class Cuenta {
	private String nombreCuenta;
	private String contrasena;
	private String nick;
	private int nivel;
	private int rp;
	private String region;
	private ListaPersonajes personajes;
	private ListaSkins skins;
	private boolean estaBloqueado;
	
	public Cuenta(String nombreCuenta, String contrasena, String nick, int nivel, int rp, 
			String region) {
		this.nombreCuenta = nombreCuenta;
		this.contrasena = contrasena;
		this.nick = nick;
		this.nivel = nivel;
		this.rp = rp;
		this.region = region;
		personajes = new ListaPersonajes();
		skins = new ListaSkins(1000);
		estaBloqueado = false;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public ListaPersonajes getPersonajes() {
		return personajes;
	}

	public void setPersonajes(ListaPersonajes personajes) {
		this.personajes = personajes;
	}

	public ListaSkins getSkins() {
		return skins;
	}

	public void setSkins(ListaSkins skins) {
		this.skins = skins;
	}

	public boolean isEstaBloqueado() {
		return estaBloqueado;
	}

	public void setEstaBloqueado(boolean estaBloqueado) {
		this.estaBloqueado = estaBloqueado;
	}
	
}
