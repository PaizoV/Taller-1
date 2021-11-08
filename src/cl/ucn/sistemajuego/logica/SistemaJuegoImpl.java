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
		Personaje personaje = new Personaje(nombre, rol);
		return personajes.ingresarPersonaje(personaje);
	}
	
	@Override
	public boolean verificarExistenciaPersonaje(String nombrePersonaje) {
		Personaje personaje = personajes.buscarPersonaje(nombrePersonaje);
		if (personaje == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public boolean ingresarCuenta(String nombreCuenta, String contrasena, String nick, 
			int nivel, int rp, String region) {
		Cuenta cuenta = new Cuenta(nombreCuenta, contrasena, nick, nivel, rp, region);
		return cuentas.ingresarCuenta(cuenta);
	}
	
	@Override
	public boolean asociarPersonajeCuenta(String nombreCuenta, String nombrePersonaje) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Personaje personaje = personajes.buscarPersonaje(nombrePersonaje);
		if (cuenta != null && personaje != null) {
			cuenta.setGasto(cuenta.getGasto() + 975);
			return cuenta.getPersonajes().ingresarPersonaje(personaje);
		}
		else {
			throw new NullPointerException("Personaje y/o cuenta no existe");
		}
	}
	
	@Override
	public boolean ingresarSkin(String nombre, String calidad) {
		Skin skin = new Skin(nombre, calidad);
		return skins.ingresarSkin(skin);
	}
	
	@Override
	public boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin) {
		Personaje personaje = personajes.buscarPersonaje(nombrePersonaje);
		Skin skin = skins.buscarSkin(nombreSkin);
		if (skin != null && personaje != null) {
			skin.setPersonaje(personaje);
			return personaje.getSkins().ingresarSkin(skin);
		}
		else {
			throw new NullPointerException("El personaje y/o skin no existe");
		}
	}
	
	@Override
	public void asociarRecaudacion(int recaudacion, String nombrePersonaje) {
		Personaje personaje = personajes.buscarPersonaje(nombrePersonaje);
		if (personaje != null) {
			personaje.setRecaudacion(recaudacion);
		}
		else {
			throw new NullPointerException("El personaje no existe");
		}
	}
	
	@Override
	public boolean verificarExistenciaCuenta(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		if (cuenta == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public boolean validarCliente(String nombreCuenta, String contrasena) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		if (cuenta == null) {
			throw new NullPointerException("Usuario no registrado");
		}
		else if (cuenta.getBan()) {
			throw new IllegalArgumentException("Usuario bloqueado");
		}
		else if (!cuenta.getContrasena().equals(contrasena)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	// Retorna false si la cuenta no tiene personajes; retorna true si la cuenta
	// tiene al menos un personaje.
	@Override
	public boolean verificarPersonajesCuenta(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		ListaPersonajes pjes = cuenta.getPersonajes();
		if (pjes.getCantPersonajes() == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public String obtenerPersonajesCuenta(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		ListaPersonajes pjes = cuenta.getPersonajes();
		String str = "";
		for (int i = 0; i < pjes.getCantPersonajes(); i++) {
			str += "- " + pjes.getPersonajeAt(i).getNombre() + "\n";
		}
		return str.trim();
	}
	
	@Override
	public String obtenerSkinsPersonaje(String nombreCuenta, String nombrePersonaje) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Personaje personaje = personajes.buscarPersonaje(nombrePersonaje);
		if (personaje == null) {
			throw new NullPointerException("El personaje no existe");
		}
		else {
			ListaSkins skinsPje = personaje.getSkins();
			ListaSkins skinsCuenta = cuenta.getSkins();
			String str = "";
			for (int i = 0; i < skinsPje.getCantSkins(); i++) {
				Skin skin = skinsPje.getSkinAt(i);
				if (skinsCuenta.buscarSkin(skin.getNombre()) == null) {
					str += "- " + skin.getNombre() + "\n";
				}
			}
			return str.trim();
		}		
	}
	
	@Override
	public boolean verificarSkinCuenta(String nombreCuenta, String nombreSkin) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Skin skin = skins.buscarSkin(nombreSkin);
		if (skin == null) {
			throw new NullPointerException("La skin no existe");
		}
		else {
			ListaSkins skinsCuenta = cuenta.getSkins();
			if (skinsCuenta.buscarSkin(skin.getNombre()) == null) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	@Override
	public boolean comprarSkin(String nombreCuenta, String nombreSkin) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Skin skin = skins.buscarSkin(nombreSkin);
		if (cuenta.getRp() >= skin.getPrecio()) {
			cuenta.setRp(cuenta.getRp() - skin.getPrecio());
			cuenta.setGasto(cuenta.getGasto() + skin.getPrecio());
			cuenta.setNivel(cuenta.getNivel() + 1);
			skin.getPersonaje().setRecaudacion(skin.getPersonaje().getRecaudacion() +
					skin.getPrecio());
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String obtenerPersonajes() {
		String salida = "";
		for (int i = 0; i < personajes.getCantPersonajes(); i++) {
			Personaje personaje = personajes.getPersonajeAt(i);
			salida += "- " + personaje.getNombre() + "\n";
		}
		return salida.trim();
	}
	
	@Override
	public boolean verificarPersonajeCuenta(String nombreCuenta, String nombrePersonaje) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Personaje personaje = personajes.buscarPersonaje(nombrePersonaje);
		if (personaje == null) {
			throw new NullPointerException("El personaje no existe");
		}
		else {
			ListaPersonajes pjesCuenta = cuenta.getPersonajes();
			if (pjesCuenta.buscarPersonaje(nombrePersonaje) == null) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	@Override
	public boolean comprarPersonaje(String nombrePersonaje, String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Personaje personaje = personajes.buscarPersonaje(nombrePersonaje);
		if (cuenta.getRp() >= 975) {	
			cuenta.setRp(cuenta.getRp() - 975);
			cuenta.setGasto(cuenta.getGasto() + 975);
			cuenta.setNivel(cuenta.getNivel() + 1);
			cuenta.getPersonajes().ingresarPersonaje(personaje);
			personaje.setRecaudacion(personaje.getRecaudacion() + 975);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String obtenerSkinsDisponibles(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		ListaSkins skinsCuenta = cuenta.getSkins();
		String str = "";
		for (int i = 0; i < skins.getCantSkins(); i++) {
			Skin skin = skins.getSkinAt(i);
			if (skinsCuenta.buscarSkin(skin.getNombre()) == null) {
				str += "- " + skin.getNombre() + "\n";
			}
		}
		return str.trim();
	}
	
	@Override
	public String obtenerInventario(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		ListaPersonajes pjesCuenta = cuenta.getPersonajes();
		ListaSkins skinsCuenta = cuenta.getSkins();
		String str = "";
		for (int i = 0; i < pjesCuenta.getCantPersonajes(); i++) {
			Personaje personaje = pjesCuenta.getPersonajeAt(i);
			str += "* " + personaje.getNombre() + ":\n";
			for (int j = 0; j < skinsCuenta.getCantSkins(); j++) {
				Skin skin = skinsCuenta.getSkinAt(j);
				if (skin.getPersonaje() == personaje) {
					str += "- " + skin.getNombre() + "\n";
				}
			}
		}
		return str.trim();
	}
	
	@Override
	public int obtenerRp(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		return cuenta.getRp();
	}
	
	@Override
	public void recargarRp(String nombreCuenta, int cantidad) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		cuenta.setRp(cuenta.getRp() + cantidad);
	}
	
	private String ocultarContrasena(String contrasena) {
		String[] letras = contrasena.split("");
		String contrasenaNueva = "";
		for (int i = 0; i < contrasena.length(); i++) {
			if (i < contrasena.length() - 3) {
				contrasenaNueva += "*";
			}
			else {
				contrasenaNueva += letras[i];
			}
		}
		return contrasenaNueva;
	}
	
	@Override
	public String obtenerDatosCuenta(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		return "- Nombre de cuenta: " + cuenta.getNombreCuenta() + "\n- Nick: " 
				+ cuenta.getNick() + "\n- Contraseña: " + 
				ocultarContrasena(cuenta.getContrasena());
	}
	
	@Override
	public boolean verificarContrasenaCuenta(String nombreCuenta, String contrasena) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		if (cuenta.getContrasena().equals(contrasena)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void cambiarContrasena(String nombreCuenta, String contrasena) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		cuenta.setContrasena(contrasena);
	}
	
	private double cambioClp(int recaudacion) {
		return (double)(recaudacion * 6.15);
	}
	
	@Override
	public String obtenerRecaudacionRol() {
		String salida = "";
		int contSup = 0, contAdc = 0, contTop = 0, contMid = 0, contJg = 0;
		
		for (int i = 0; i < personajes.getCantPersonajes(); i++) {
			Personaje personaje = personajes.getPersonajeAt(i);
			switch (personaje.getRol()) {
			case "SUP": 
				contSup += personaje.getRecaudacion();
				break;
			case "ADC": 
				contAdc += personaje.getRecaudacion();
				break;
			case "TOP": 
				contTop += personaje.getRecaudacion();
				break;
			case "MID": 
				contMid += personaje.getRecaudacion();
				break;
			case "JG": 
				contJg += personaje.getRecaudacion();
				break;
			}
		}
		salida += "- SUP: $ " + cambioClp(contSup) + " CLP\n";
		salida += "- ADC: $ " + cambioClp(contAdc) + " CLP\n";
		salida += "- TOP: $ " + cambioClp(contTop) + " CLP\n";
		salida += "- MID: $ " + cambioClp(contMid) + " CLP\n";
		return salida + "- JG: $ " + cambioClp(contJg) + " CLP";
	}
	
	@Override
	public String obtenerRecaudacionRegion() {
		String salida = "";
		int contLas = 0, contLan = 0, contEuw = 0, contKr = 0, contNa = 0, contRu = 0;
		for (int i = 0; i < cuentas.getCantCuentas(); i++) {
			Cuenta cuenta = cuentas.getCuentaAt(i);
			switch (cuenta.getRegion()) {
			case "LAS": 
				contLas += cuenta.getGasto();
				break;
			case "LAN": 
				contLan += cuenta.getGasto(); 
				break;
			case "EUW": 
				contEuw += cuenta.getGasto(); 
				break;
			case "KR": 
				contKr += cuenta.getGasto(); 
				break;
			case "NA": 
				contNa += cuenta.getGasto(); 
				break;
			case "RU": 
				contRu += cuenta.getGasto(); 
				break;
			}
		}
		salida += "- LAS: $ "+ cambioClp(contLas) + " CLP\n";
		salida += "- LAN: $ "+ cambioClp(contLan) + " CLP\n";
		salida += "- EUW: $ "+ cambioClp(contEuw) + " CLP\n";
		salida += "- KR: $ "+ cambioClp(contKr) + " CLP\n";
		salida += "- NA: $ "+ cambioClp(contNa) + " CLP\n";
		return salida + "- RU: $ "+ cambioClp(contRu) + " CLP";
	}
	
	@Override
	public String obtenerRecaudacionPersonaje() {
		String salida = "";
		for (int i = 0; i < personajes.getCantPersonajes(); i++) {
			Personaje personaje = personajes.getPersonajeAt(i);
			salida += "- " + personaje.getNombre() + ": $ " + 
					cambioClp(personaje.getRecaudacion()) + " CLP\n";
		}
		return salida.trim();
	}
	
	@Override
	public String obtenerCantPersonajeRol() {
		int contSup = 0, contAdc = 0, contTop = 0, contMid = 0, contJg = 0;
		for (int i = 0; i < personajes.getCantPersonajes(); i++) {
			Personaje personaje = personajes.getPersonajeAt(i);
			switch (personaje.getRol()) {
			case "SUP": 
				contSup++;
				break;
			case "ADC": 
				contAdc++;
				break;
			case "TOP": 
				contTop++;
				break;
			case "MID": 
				contMid++;
				break;
			case "JG": 
				contJg++;
				break;
			}
		}
		return "- SUP: " + contSup + "\n- ADC: " + contAdc + "\n- TOP: " + contTop +
				"\n- MID: " + contMid + "\n- JG: " + contJg;
	}
	
	@Override
	public void bloquearJugador(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		if (cuenta == null) {
			throw new NullPointerException("La cuenta no existe");
		}
		else {
			cuenta.setBan(true);;
		}	
	}
	
	@Override
	public String obtenerCuentasOrdenadas() {
		String salida = "";
		cuentas.ordenar();
		for (int i = 0; i < cuentas.getCantCuentas(); i++) {
			Cuenta cuenta = cuentas.getCuentaAt(i);
			salida += cuenta.getNick() + " - " + cuenta.getNivel() + "\n"; 
		}
		return salida.trim();
	}
	
	@Override
	public boolean asociarSkinCuenta(String nombreCuenta, String nombreSkin) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Skin skin = skins.buscarSkin(nombreSkin);
		if (cuenta == null || skin == null) {
			throw new NullPointerException("La cuenta y/o skin no existe");
		}
		else {
			cuenta.setGasto(cuenta.getGasto() + skin.getPrecio());
			return cuenta.getSkins().ingresarSkin(skin);
		}
	}

	@Override
	public String obtenerTxtPersonajesActualizado() {
		String salida = "";
		for (int i = 0; i < personajes.getCantPersonajes(); i++) {
			Personaje personaje = personajes.getPersonajeAt(i);
			ListaSkins skinsPje = personaje.getSkins();
			salida += personaje.getNombre() + "," + personaje.getRol() + ","
					+ skinsPje.getCantSkins();
			for (int j = 0; j < skinsPje.getCantSkins(); j++) {
				Skin skin = skinsPje.getSkinAt(j);
				salida += "," + skin.getNombre() + "," + skin.getCalidad();
			}
			salida += "\n";
		}
		return salida.trim();
	}
	
	private ListaSkins obtenerSkinsPersonajeCuenta(Personaje personaje, ListaSkins skinsCuenta) {
		ListaSkins skinsReturn = new ListaSkins(1000);
		for (int i = 0; i < skinsCuenta.getCantSkins(); i++) {
			Skin skin = skinsCuenta.getSkinAt(i);
			if (skin.getPersonaje() == personaje) {
				skinsReturn.ingresarSkin(skin);
			}
		}
		return skinsReturn;
	}

	@Override
	public String obtenerTxtCuentasActualizado() {
		String salida = "";
		for (int i = 0; i < cuentas.getCantCuentas(); i++) {
			Cuenta cuenta = cuentas.getCuentaAt(i);
			ListaPersonajes pjesCuenta = cuenta.getPersonajes();
			ListaSkins skinsCuenta = cuenta.getSkins();
			salida += cuenta.getNombreCuenta() + "," + cuenta.getContrasena() +
					"," + cuenta.getNick() + "," + cuenta.getNivel() + "," + cuenta.getRp()
					+ "," + pjesCuenta.getCantPersonajes();
			for (int j = 0; j < pjesCuenta.getCantPersonajes(); j++) {
				Personaje pje = pjesCuenta.getPersonajeAt(j);
				ListaSkins skinsPje = obtenerSkinsPersonajeCuenta(pje, skinsCuenta);
				salida += "," + pje.getNombre() + "," + skinsPje.getCantSkins();
				for (int k = 0; k < skinsPje.getCantSkins(); k++) {
					Skin skin = skinsPje.getSkinAt(k);
					salida += "," + skin.getNombre();
				}
			}
			salida += "," + cuenta.getRegion() + "\n";
		}
		return salida.trim();
	}

	@Override
	public String obtenerTxtEstadisticasActualizado() {
		String salida = "";
		for (int i = 0; i < personajes.getCantPersonajes(); i++) {
			Personaje personaje = personajes.getPersonajeAt(i);
			salida += personaje.getNombre() + "," + personaje.getRecaudacion() + "\n";
		}
		return salida.trim();
	}
}
