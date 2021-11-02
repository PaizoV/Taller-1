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
		Personaje personaje=new Personaje(nombre, rol);
		if(personajes.ingresarPersonaje(personaje)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean ingresarCuenta(String nombreCuenta, String contrasena, String nick, String region, int nivel,
			int rp) {
		Cuenta cuenta = new Cuenta(nombreCuenta, contrasena, nick, nivel, rp,region);
		if(cuentas.ingresarCuenta(cuenta)){
			return true;
		}
		else {
			return false;			
		}
	}
	
	@Override
	public boolean asociarPersonajeCuenta(String nombreCuenta, String nombrePersonaje) {
		Cuenta cuenta= cuentas.buscarCuenta(nombreCuenta);
		Personaje personaje= personajes.buscarPersonaje(nombrePersonaje);
		
		if(cuenta != null && personaje != null) {
			cuenta.getPersonajes().ingresarPersonaje(personaje);
			return true;
		}
		else {
			throw new NullPointerException("Personaje o/y Cuenta no existe");
		}
	}	
	@Override
	public boolean ingresarSkin(String nombre,String calidad) {
		Skin skin = new Skin(nombre, calidad);
		if(skins.ingresarSkin(skin)) {
			return true;
		}
		return false;
	}
	@Override
	public boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin) {
		Personaje personaje= personajes.buscarPersonaje(nombrePersonaje);
		Skin skin= skins.buscarSkin(nombreSkin);
		if( skin != null && personaje != null) {
			skin.setPersonaje(personaje);
			return true;
		}
		else {
			throw new NullPointerException("El personaje y/o skin no existe");
		}
		
		
	}
	@Override
	public boolean asociarRecaudacion(double recaudacion, String nombrePersonaje) {
		Personaje personaje= personajes.buscarPersonaje(nombrePersonaje);
		if(personaje != null) {
			personaje.setRecaudacion(recaudacion);
			return true;
		}
		else {
			throw new NullPointerException("El personaje no existe");
		}
		
	}
	@Override
	public boolean validarCliente(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		if(cuenta != null) {
			return true;
		}
		return false;
	}
	@Override
	public void comprarSkin(String nombrePersonaje, String nombreCuenta, String nombreSkin) {
		Skin skin = skins.buscarSkin(nombreSkin);
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Personaje personaje= cuenta.getPersonajes().buscarPersonaje(nombrePersonaje);
		
		if(skin != null && personaje != null) {
			ListaSkins listaSkins= cuenta.getSkins();
			listaSkins.ingresarSkin(skin);
			int price= skin.getPrecio();
			int  saldo= (int) cuenta.getRp();
			saldo = saldo - price;
			cuenta.setRp(saldo);
			double recaudacion = personaje.getRecaudacion() + (price*6.15);//nuevo
			personaje.setRecaudacion(recaudacion);
		}else {
			throw new NullPointerException("El personaje y/o skin no existe");
		}
	}
	@Override
	public boolean comprarPersonaje(String nombrePersonaje, String nombreCuenta) {
		
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Personaje personaje = personajes.buscarPersonaje(nombrePersonaje);
		
		if(personaje != null) {
			ListaPersonajes listaPersonajes = cuenta.getPersonajes();
			listaPersonajes.ingresarPersonaje(personaje);
			int rpUpdate = (int) (cuenta.getRp() - 975);
			cuenta.setRp(rpUpdate);
			int nivelUpdate = cuenta.getNivel() + 1;
			cuenta.setRp(nivelUpdate);
			double recaudacionesUpdate= personaje.getRecaudacion() + (975*6.15);//en CLP
			personaje.setRecaudacion(recaudacionesUpdate);
			return true;
		}
		else {
			throw new NullPointerException("El personaje no existe");
		}
		
	}
	@Override
	public String obtenerSkinsDisponibles(String nombreCuenta) {
		String salida="";
		boolean bool = false;
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		for(int i = 0 ; i < skins.getCantSkins() ; i++ ) { 
			Skin skin = skins.getSkinAt(i);
			for(int j = 0; j < cuenta.getSkins().getCantSkins() ; j++) {
				Skin skinCuenta = cuenta.getSkins().getSkinAt(j);
				if( skinCuenta.getNombre().equals(skin.getNombre())) {
					bool = false;
					break;
				}else {
					bool = true;
				}
			}
			if(bool) {
				salida +=" "+skin.getNombre()+"\n";
			}
		}		
		return salida;
	}
	@Override
	public String obtenerInventario(String nombreCliente) {
		String salida= "";
		Cuenta cuenta = cuentas.buscarCuenta(nombreCliente);
		for(int i = 0; i < cuenta.getPersonajes().getCantPersonajes(); i++) {
			Personaje personaje = cuenta.getPersonajes().getPersonajeAt(i);
			salida +="El personaje "+ personaje.getNombre() + " posee las siguientes skins: \n";
			salida += personaje.getSkins().toString();
		}
		return salida;
	}
	@Override
	public void recargarRp(String nombreCuenta, int cantidad) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		int rpUpdate = (int) (cantidad + cuenta.getRp());
		cuenta.setRp(rpUpdate);
		
	}
	@Override
	public String obtenerDatosCuenta(String nombreCuenta) {
		String salida = "";
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		salida +="Nombre de cuenta: "+ cuenta.getNombreCuenta() + "\nNick: "+ cuenta.getNick();
		salida+="\nContraseña: ";
		String [] lista = cuenta.getContrasena().split("");
		for(int i = 0; i < lista.length; i++) {
			if(i< lista.length-3) {
				lista[i] = "*";
				salida += lista[i];
			}
			else {
				salida += lista[i];
			}
		}
		return salida;
	}
	@Override
	public void cambiarClave(String nombreCuenta, String claveNueva) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		cuenta.setContrasena(claveNueva);
	}
	//CAMBIO DE DOUBLE A STRING
	@Override
	public String obtenerRecaudacionRol() {
		String salida="";
		int contSup=0, contAdc=0, contTop=0, contMid=0, contJg=0;
		
		for(int i = 0; i < personajes.getCantPersonajes(); i++) {
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
		salida += "Recaudacion de personajes SUPORT (SUP): "+ contSup + "\n";
		salida += "Recaudacion de personajes ATACK DAMAGE CARRY (ADC): "+ contAdc+ "\n";
		salida += "Recaudacion de personajes TOP LANER (TOP): "+ contTop+ "\n";
		salida += "Recaudacion de personajes MIDDLE LANER (MID): "+ contMid+ "\n";
		salida += "Recaudacion de personajes JUNGLER (JG): "+ contJg;
		return salida;
	}
	//CAMBIO DE DOUBLE A STRING
	@Override
	public String obtenerRecaudacionRegion() {
		String salida = "";
		int contLas=0, contLan=0, contEuw=0, contKr=0, contNa=0, contRu=0;
		for(int i = 0; i < cuentas.getCantCuentas(); i++) {
			Cuenta cuenta = cuentas.getCuentaAt(i);
			int recaudacionTotalPersonajes = cuenta.getPersonajes().recaudaciones();
			switch (cuenta.getRegion()) {
			case "LAS": 
				contLas += recaudacionTotalPersonajes;
				break;
			case "LAN": 
				contLan += recaudacionTotalPersonajes;
				break;
			case "EUW": 
				contEuw += recaudacionTotalPersonajes;
				break;
			case "KR": 
				contKr += recaudacionTotalPersonajes;
				break;
			case "NA": 
				contNa += recaudacionTotalPersonajes;
				break;
			case "RU": 
				contRu += recaudacionTotalPersonajes;
				break;
			}
		}
		salida += "Recaudacion en la region LAS: "+ contLas + "\n";
		salida += "Recaudacion en la region LAN: "+ contLan+ "\n";
		salida += "Recaudacion en la region EUW: "+ contEuw+ "\n";
		salida += "Recaudacion en la region KR: "+ contKr+ "\n";
		salida += "Recaudacion en la region NA: "+ contNa+ "\n";
		salida += "Recaudacion en la region RU: "+ contRu;
		return salida;
	}
	//CAMBIO DE DOUBLE A STRING
	@Override
	public String obtenerRecaudacionPersonaje() {
		String salida = "";
		for(int i = 0; i < personajes.getCantPersonajes(); i++) {
			Personaje personaje = personajes.getPersonajeAt(i);
			salida += "El personaje "+ personaje.getNombre();
			double recaudacion = personaje.getRecaudacion(); //duda sobre conversion
			salida+=" tuvo una recaudacion de "+ recaudacion + " CLP \n" ;
		}
		return salida;
	}
	@Override
	//CAMBIO DE INT A STRING 
	public String obtenerCantPersonajeRol() {
		String salida="";
		int contSup=0, contAdc=0, contTop=0, contMid=0, contJg=0;
		
		for(int i = 0; i < personajes.getCantPersonajes(); i++) {
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
		salida += "Cantidad de personajes SUPORT (SUP): "+ contSup + "\n";
		salida += "Cantidad de personajes ATACK DAMAGE CARRY (ADC): "+ contAdc+ "\n";
		salida += "Cantidad de personajes TOP LANER (TOP): "+ contTop+ "\n";
		salida += "Cantidad de personajes MIDDLE LANER (MID): "+ contMid+ "\n";
		salida += "Cantidad de personajes JUNGLER (JG): "+ contJg;
		return salida;
	}
	@Override
	public void bloquearJugador(String nombreCuenta) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		cuenta.setEstaBloqueado(true);
		
	}
	@Override
	public String obtenerInfoCuentas() {
		String salida = "";
		cuentas.ordenar();
		for (int i = 0; i< cuentas.getCantCuentas(); i++) {
			Cuenta cuenta = cuentas.getCuentaAt(i);
			salida += cuenta.getNick() + " " + cuenta.getNivel() +"\n"; 
		}
		return salida;
	}
	// nuevo
	@Override
	public boolean validarAcceso(String nombreCuenta, String contrasena) {
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		if(cuenta != null) {
			String clave= cuenta.getContrasena();
			if(clave.equals(contrasena)) {
				return true;
			}
		}else 
			if(nombreCuenta.equals("ADMIN")){
				if(contrasena.equals("ADMIN")) {
					return true;
				}
		}
				
		return false;
	}
	// nuevo
	@Override
	public boolean asociarSkinPersonajeCuenta(String nombrePersonaje, String nombreSkin, String nombreCuenta) {
		Skin skin= skins.buscarSkin(nombreSkin);
		Cuenta cuenta = cuentas.buscarCuenta(nombreCuenta);
		Personaje personaje = cuenta.getPersonajes().buscarPersonaje(nombrePersonaje);
		if( skin != null && personaje != null) {
			personaje.getSkins().ingresarSkin(skin);
			return true;
		}
		else {
			throw new NullPointerException("El personaje y/o skin no existe");
		}
		
		
	}

}
