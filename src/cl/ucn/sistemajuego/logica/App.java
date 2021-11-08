package cl.ucn.sistemajuego.logica;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {
	
	public static void leerArchivoPersonajes(SistemaJuego sistema) throws IOException {
		Scanner scan = new Scanner(new File("Personajes.txt"));
		while (scan.hasNextLine()) {
			String[] parts = scan.nextLine().split(",");
			String nombrePersonaje = parts[0];
			String rol = parts[1];
			if (!sistema.ingresarPersonaje(nombrePersonaje, rol)) {
				System.out.println("# No hay espacio para mas personajes en el sistema");
				break;
			}
			int cantSkins = Integer.parseInt(parts[2]);
			int pos = 3;
			for (int i = 0; i < cantSkins; i++) {
				String nombreSkin = parts[pos];
				String calidad = parts[pos + 1];
				if (!sistema.ingresarSkin(nombreSkin, calidad)) {
					System.out.println("# No hay espacio para mas skins en el sistema");
					break;
				}
				try {
					if (!sistema.asociarSkinPersonaje(nombrePersonaje, nombreSkin)) {
						System.out.println("# No hay espacio para mas skins en el personaje");
						break;
					}
				}
				catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
				pos += 2;
			}
		}
		scan.close();
	}
	
	public static void leerArchivoCuentas(SistemaJuego sistema) throws IOException {
		Scanner scan = new Scanner(new File("Cuentas.txt"));
		while (scan.hasNextLine()) {
			String[] parts = scan.nextLine().split(",");
			String nombreCuenta = parts[0];
			String contrasena = parts[1];
			String nick = parts[2];
			int nivel = Integer.parseInt(parts[3]);
			int rp = Integer.parseInt(parts[4]);
			String region = parts[parts.length - 1];
			if (!sistema.ingresarCuenta(nombreCuenta, contrasena, nick, nivel, rp, region)) {
				System.out.println("# No hay espacio para mas cuentas en el sistema");
				break;
			}
			int cantPersonajes = Integer.parseInt(parts[5]);
			int pos = 6;
			for (int i = 0; i < cantPersonajes; i++) {
				String nombrePersonaje = parts[pos++];
				try {
					if (!sistema.asociarPersonajeCuenta(nombreCuenta, nombrePersonaje)) {
						System.out.println("# No hay espacio para mas personajes en la cuenta");
						break;
					}
				}
				catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
				int cantSkins = Integer.parseInt(parts[pos++]);
				for (int j = 0; j < cantSkins; j++) {
					String nombreSkin = parts[pos++];
					try {
						if (!sistema.asociarSkinCuenta(nombreCuenta, nombreSkin)) {
							System.out.println("# No hay espacio para mas skins en la cuenta");
							break;
						}
					}
					catch (NullPointerException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		scan.close();
	}
	
	public static void leerArchivoEstadisticas(SistemaJuego sistema) throws IOException {
		Scanner scan = new Scanner(new File("Estadisticas.txt"));
		while (scan.hasNextLine()) {
			String[] parts = scan.nextLine().split(",");
			String nombrePersonaje = parts[0];
			int recaudacion = Integer.parseInt(parts[1]);
			try {
				sistema.asociarRecaudacion(recaudacion, nombrePersonaje);
			}
			catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
		scan.close();
	}
	
	public static void menuPrincipal(SistemaJuego sistema, Scanner scan) throws IOException {
		System.out.println("\n>--- MENU PRINCIPAL ---<\n");
		System.out.println("[1] Iniciar sesion");
		System.out.println("[2] Registrarse");
		System.out.println("[3] Cerrar sistema");
		System.out.print("\nSeleccione una opcion: >>> ");
		String opcion = scan.nextLine();
		switch (opcion) {
		case "1":
			iniciarSesion(sistema, scan);
			break;
		case "2":
			registrarUsuario(sistema, scan);
			break;
		case "3":
			scan.close();
			cerrarSistema(sistema);
			break;
		default:
			System.out.println("\n# Opcion invalida. Intente nuevamente");
			menuPrincipal(sistema, scan);
		}
	}
	
	public static void menuAdmin(SistemaJuego sistema, Scanner scan) throws IOException {
		System.out.println("\n>--- MENU DE ADMIN ---<\n");
		System.out.println("[1] Recaudacion de ventas por rol");
		System.out.println("[2] Recaudacion de ventas por region");
		System.out.println("[3] Recaudacion de ventas por personaje");
		System.out.println("[4] Cantidad de personajes por rol");
		System.out.println("[5] Agregar un personaje");
		System.out.println("[6] Agregar una skin");
		System.out.println("[7] Bloquear jugador");
		System.out.println("[8] Desplegar cuentas por nivel de cuenta");
		System.out.println("[9] Volver al menu principal");
		System.out.print("\nSeleccione una opcion: >>> ");
		
		String opcion = scan.nextLine();
		switch (opcion) {
		case "1":
			System.out.println("\n>--- RECAUDACION POR ROL ---<\n");
			System.out.println(sistema.obtenerRecaudacionRol());
			break;
		case "2":
			System.out.println("\n>--- RECAUDACION POR REGION ---<\n");
			System.out.println(sistema.obtenerRecaudacionRegion());
			break;
		case "3":
			System.out.println("\n>--- RECAUDACION POR PERSONAJE ---<\n");
			System.out.println(sistema.obtenerRecaudacionPersonaje());
			break;
		case "4":
			System.out.println("\n>--- PERSONAJES POR ROL ---<\n");
			System.out.println(sistema.obtenerCantPersonajeRol());
			break;
		case "5":
			agregarPersonaje(sistema, scan);
			break;
		case "6":
			agregarSkin(sistema, scan);
			break;
		case "7":
			bloquearJugador(sistema, scan);
			break;
		case "8":
			System.out.println("\n>--- CUENTAS POR NIVEL ---<\n");
			System.out.println(sistema.obtenerCuentasOrdenadas());
			break;
		case "9":
			menuPrincipal(sistema, scan);
		default:
			System.out.println("\n# Opcion invalida. Intente nuevamente");
			menuAdmin(sistema, scan);
		}
	}
	
	public static void agregarSkin(SistemaJuego sistema, Scanner scan) throws IOException {
		System.out.println("\n>--- AGREGAR SKIN ---<\n");
		System.out.print("Nombre del personaje: >>> ");
		String nombrePersonaje = scan.nextLine();
		if (!sistema.verificarExistenciaPersonaje(nombrePersonaje)) {
			System.out.println("\n# Este personaje no existe. Volviendo al menu...");
		}
		else {
			System.out.print("\nNombre de la skin: >>> ");
			String nombreSkin = scan.nextLine();
			String calidad;
			while (true) {
				System.out.print("\nCalidad: [M] / [D] / [L] / [E] / [N] >>> ");
				calidad = scan.nextLine();
				switch (calidad) {
				case "M":
				case "D":
				case "L":
				case "E":
				case "N":
					sistema.ingresarSkin(nombreSkin, calidad);
					sistema.asociarSkinPersonaje(nombrePersonaje, nombreSkin);
					break;
				default:
					System.out.println("\n# Calidad invalida. Intente nuevamente");
					continue;
				}
				break;
			}
		}
		menuAdmin(sistema, scan);
	}
	
	public static void agregarPersonaje(SistemaJuego sistema, Scanner scan) throws IOException {
		System.out.println("\n>--- AGREGAR PERSONAJE ---<\n");
		System.out.print("Nombre del personaje: >>> ");
		String nombrePersonaje = scan.nextLine();
		if (sistema.verificarExistenciaPersonaje(nombrePersonaje)) {
			System.out.println("\n# Este personaje ya existe. Volviendo al menu...");
		}
		else {
			String rol;
			while (true) {
				System.out.println("Seleccione un rol:\n");
				System.out.println("[1] SUP");
				System.out.println("[2] ADC");
				System.out.println("[3] TOP");
				System.out.println("[4] MID");
				System.out.print("[5] JG\n\n>>> ");
				String opcion = scan.nextLine();
				switch (opcion) {
				case "1":
					rol = "SUP";
					break;
				case "2":
					rol = "ADC";
					break;
				case "3":
					rol = "TOP";
					break;
				case "4":
					rol = "MID";
					break;
				case "5":
					rol = "JG";
					break;
				default:
					System.out.println("\n# Opcion invalida. Intente nuevamente\n");
					continue;
				}
				break;
			}
			sistema.ingresarPersonaje(nombrePersonaje, rol);
			int cantSkins;
			while (true) {
				System.out.print("\nCantidad de skins: >>> ");
				try {
					cantSkins = Integer.parseInt(scan.nextLine());
					if (cantSkins <= 0) {
						System.out.println("\n# Ingrese un numero positivo");
						continue;
					}
					else {
						break;
					}
				}
				catch (NumberFormatException e) {
					System.out.println("\n# Ingrese un numero");
					continue;
				}
			}
			for (int i = 1; i <= cantSkins; i++) {
				System.out.print("\nNombre de la skin " + i + ": >>> ");
				String nombreSkin = scan.nextLine();
				String calidad;
				while (true) {
					System.out.print("\nCalidad: [M] / [D] / [L] / [E] / [N] >>> ");
					calidad = scan.nextLine();
					switch (calidad) {
					case "M":
					case "D":
					case "L":
					case "E":
					case "N":
						sistema.ingresarSkin(nombreSkin, calidad);
						sistema.asociarSkinPersonaje(nombrePersonaje, nombreSkin);
						break;
					default:
						System.out.println("\n# Calidad invalida. Intente nuevamente");
						continue;
					}
					break;
				}
			}
		}
		menuAdmin(sistema, scan);
	}
	
	public static void bloquearJugador(SistemaJuego sistema, Scanner scan) throws IOException {
		System.out.println("\n>--- BLOQUEAR JUGADOR ---<\n");
		System.out.print("Nombre de la cuenta: >>> ");
		String nombreCuenta = scan.nextLine();
		if (!sistema.verificarExistenciaCuenta(nombreCuenta)) {
			System.out.println("\n# Esta cuenta no existe. Volviendo al menu...");
		}
		else {
			sistema.bloquearJugador(nombreCuenta);
		}
		menuAdmin(sistema, scan);
	}
	
	public static void iniciarSesion(SistemaJuego sistema, Scanner scan) throws IOException {
		System.out.println("\n>--- INICIAR SESION ---<\n");
		System.out.print("Nombre de la cuenta: >>> ");
		String nombreCuenta = scan.nextLine();
		System.out.print("Contraseña: >>> ");
		String contrasena = scan.nextLine();
		if (nombreCuenta.equals("ADMIN") && contrasena.equals("ADMIN")) {
			menuAdmin(sistema, scan);
		}
		else {
			try {
				boolean ingreso = sistema.validarCliente(nombreCuenta, contrasena);
				if (ingreso) {
					menuUsuario(sistema, scan, nombreCuenta);
				}
				else {
					System.out.println("\n# Contraseña incorrecta");
					menuPrincipal(sistema, scan);
				}
			}
			catch (NullPointerException e) {
				System.out.println("\n# " + e.getMessage());
				System.out.print("# Desea registrarse? SI [1] / NO [2]: >>> ");
				String opcion = scan.nextLine();
				while (true) {
					if (opcion.equals("1")) {
						registrarUsuario(sistema, scan);
					}
					else if (opcion.equals("2")) {
						menuPrincipal(sistema, scan);
					}
					else {
						System.out.print("\n# Opcion invalida. Intente nuevamente: >>> ");
						opcion = scan.nextLine();
					}
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println("\n# " + e.getMessage());
				menuPrincipal(sistema, scan);
			}
		}
	}
	
	public static void menuUsuario(SistemaJuego sistema, Scanner scan, String nombreCuenta) 
			throws IOException {
		System.out.println("\n>--- MENU DE USUARIO ---<\n");
		System.out.println("[1] Comprar skin");
		System.out.println("[2] Comprar personaje");
		System.out.println("[3] Skins disponibles");
		System.out.println("[4] Mostrar inventario");
		System.out.println("[5] Recargar RP");
		System.out.println("[6] Mostrar datos de la cuenta");
		System.out.println("[7] Volver al menu principal");
		System.out.print("\nSeleccione una opcion: >>> ");
		
		String opcion = scan.nextLine();
		switch (opcion) {
		case "1":
			comprarSkin(sistema, scan, nombreCuenta);
			break;
		case "2":
			comprarPersonaje(sistema, scan, nombreCuenta);
			break;
		case "3":
			mostrarSkinsDisponibles(sistema, scan, nombreCuenta);
			break;
		case "4":
			mostrarInventario(sistema, scan, nombreCuenta);
			break;
		case "5":
			recargarRp(sistema, scan, nombreCuenta);
			break;
		case "6":
			mostrarDatosCuenta(sistema, scan, nombreCuenta);
			break;
		case "7":
			menuPrincipal(sistema, scan);
		default:
			System.out.println("\n# Opcion invalida. Intente nuevamente");
			menuUsuario(sistema, scan, nombreCuenta);
		}
	}
	
	public static void comprarSkin(SistemaJuego sistema, Scanner scan, String nombreCuenta) 
			throws IOException {
		System.out.println("\n>--- COMPRAR SKIN ---<\n");
		if (!sistema.verificarPersonajesCuenta(nombreCuenta)) {
			System.out.println("# No tienes personajes. Volviendo al menu...");
		}
		else {
			System.out.println("# Personajes disponible:\n");
			System.out.println(sistema.obtenerPersonajesCuenta(nombreCuenta));
			System.out.print("\nElije un personaje: >>> ");
			String nombrePersonaje = scan.nextLine();
			try {
				System.out.println("\n# Skins disponible:\n");
				sistema.obtenerSkinsPersonaje(nombreCuenta, nombrePersonaje);
				System.out.print("Elije una skin: >>> ");
				String nombreSkin = scan.nextLine();
				try {
					if (sistema.verificarSkinCuenta(nombreCuenta, nombreSkin)) {
						System.out.println("\n# Ya tienes esta skin. Volviendo al menu...");
					}
					else {
						boolean compra = sistema.comprarSkin(nombreCuenta, nombreSkin);
						if (compra) {
							System.out.println("\n# Compra exitosa!");
						}
						else {
							System.out.println("\n# RP insuficiente. Volviendo al menu...");
						}
					}
				}
				catch (NullPointerException e) {
					System.out.println("\n# " + e.getMessage() + ". Volviendo al menu...");
				}
			}
			catch (NullPointerException e) {
				System.out.println("\n# " + e.getMessage());
			}
		}
		menuUsuario(sistema, scan, nombreCuenta);
	}
	
	public static void comprarPersonaje(SistemaJuego sistema, Scanner scan, 
			String nombreCuenta) throws IOException {
		System.out.println("\n>--- COMPRAR PERSONAJE ---<\n");
		System.out.println("# Personajes disponibles:\n");
		System.out.println(sistema.obtenerPersonajes());
		System.out.print("Elije un personaje: >>> ");
		String nombrePersonaje = scan.nextLine();
		try {
			if (sistema.verificarPersonajeCuenta(nombreCuenta, nombrePersonaje)) {
				System.out.println("\n# Ya tienes este personaje. Volviendo al menu...");
			}
			else {
				boolean compra = sistema.comprarPersonaje(nombrePersonaje, nombreCuenta);
				if (!compra) {
					System.out.println("\n# RP insuficiente. Volviendo al menu...");
				}
				else {
					System.out.println("\n# Compra exitosa!");
				}
			}
		}
		catch (NullPointerException e) {
			System.out.println("\n# " + e.getMessage() + ". Volviendo al menu...");
		}
		menuUsuario(sistema, scan, nombreCuenta);
	}
	
	public static void mostrarSkinsDisponibles(SistemaJuego sistema, Scanner scan,
			String nombreCuenta) throws IOException {
		System.out.println("\n>--- SKINS DISPONIBLES ---<\n");
		System.out.println(sistema.obtenerSkinsDisponibles(nombreCuenta));
		menuUsuario(sistema, scan, nombreCuenta);
	}
	
	public static void mostrarInventario(SistemaJuego sistema, Scanner scan,
			String nombreCuenta) throws IOException {
		System.out.println("\n>--- INVENTARIO ---<\n");
		System.out.println(sistema.obtenerInventario(nombreCuenta));
		menuUsuario(sistema, scan, nombreCuenta);
	}
	
	public static void recargarRp(SistemaJuego sistema, Scanner scan, String nombreCuenta) 
			throws IOException {
		System.out.println("\n>--- RECARGAR RP ---<\n");
		System.out.println("# RP en la cuenta: " + sistema.obtenerRp(nombreCuenta) + " RP\n");
		while (true) {
			System.out.print("Cantidad: >>> ");
			try {
				int cantidad = Integer.parseInt(scan.nextLine());
				if (cantidad <= 0) {
					System.out.println("\n# Monto invalido. Intente nuevamente");
					continue;
				}
				else {
					System.out.println("\n# Recarga exitosa! Nuevo saldo: " +
							sistema.obtenerRp(nombreCuenta) + " RP");
					break;
				}
			}
			catch (NumberFormatException e) {
				System.out.println("\n# Valor invalido. Intente nuevamente");
				continue;
			}
		}
		menuUsuario(sistema, scan, nombreCuenta);
	}
	
	public static void mostrarDatosCuenta(SistemaJuego sistema, Scanner scan,
			String nombreCuenta) throws IOException {
		System.out.println("\n>--- DATOS DE LA CUENTA ---<\n");
		System.out.println(sistema.obtenerDatosCuenta(nombreCuenta));
		while (true) {
			System.out.print("\nDesea cambiar la contraseña? SI [1] / NO [2] >>> " );
			String opcion = scan.nextLine();
			if (opcion.equals("1")) {
				System.out.print("\nContraseña actual: >>> ");
				String contraActual = scan.nextLine();
				if (!sistema.verificarContrasenaCuenta(nombreCuenta, contraActual)) {
					System.out.println("\n# Contraseña incorrecta. Volviendo al menu...");
				}
				else {
					System.out.print("\nIngrese contraseña nueva: >>> ");
					String intento1 = scan.nextLine();
					System.out.print("\nIngrese contraseña nueva de nuevo: >>> ");
					String intento2 = scan.nextLine();
					if (intento1.equals(intento2)) {
						System.out.println("\n# Cambio de contraseña exitoso!");
						sistema.cambiarContrasena(nombreCuenta, intento1);
					}
					else {
						System.out.println("\n# Las contraseñas no coinciden. Volviendo al"
								+ " menu...");
					}
				}
				break;
			}
			else if (opcion.equals("2")) {
				break;
			}
			else {
				System.out.println("\n# Opcion invalida. Intente nuevamente");
				continue;
			}
		}
		menuUsuario(sistema, scan, nombreCuenta);
	}
	
	public static void registrarUsuario(SistemaJuego sistema, Scanner scan) throws IOException {
		System.out.println("\n>--- REGISTRAR USUARIO ---<\n");
		System.out.print("Nombre de la cuenta: >>> ");
		String nombreCuenta = scan.nextLine();
		if (sistema.verificarExistenciaCuenta(nombreCuenta)) {
			System.out.println("\n# Este usuario ya esta registrado");
		}
		else {
			System.out.print("Contraseña: >>> ");
			String contrasena = scan.nextLine();
			System.out.print("Nick: >>> ");
			String nick = scan.nextLine();
			System.out.print("Region: >>> ");
			String region = scan.nextLine();
			if (!sistema.ingresarCuenta(nombreCuenta, contrasena, nick, 0, 0, region)) {
				System.out.println("\n# No hay espacio para mas cuentas en el sistema");
			}
			else {
				System.out.println("\n# Registro exitoso!");
			}
		}
		menuPrincipal(sistema, scan);
	}
	
	public static void cerrarSistema(SistemaJuego sistema) throws IOException {
		FileWriter writer = new FileWriter("Personajes.txt");
		writer.write(sistema.obtenerTxtPersonajesActualizado());
		writer.close();
		
		writer = new FileWriter("Cuentas.txt");
		writer.write(sistema.obtenerTxtCuentasActualizado());
		writer.close();
		
		writer = new FileWriter("Estadisticas.txt");
		writer.write(sistema.obtenerTxtEstadisticasActualizado());
		writer.close();
		
		System.exit(0);
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		SistemaJuego sistema = new SistemaJuegoImpl();
		
		leerArchivoPersonajes(sistema);
		leerArchivoCuentas(sistema);
		leerArchivoEstadisticas(sistema);

		menuPrincipal(sistema, scan);
	}
}
