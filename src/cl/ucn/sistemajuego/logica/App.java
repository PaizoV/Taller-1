package cl.ucn.sistemajuego.logica;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class App {
	
	public static void leerArchivoPersonajes(SistemaJuego sistema) throws IOException {
		Scanner scan = new Scanner(new File("Personajes.txt"));
		while (scan.hasNextLine()) {
			String[] parts = scan.nextLine().split(",");
			String nombrePersonaje = parts[0];
			String rol = parts[1];
			if (!sistema.ingresarPersonaje(nombrePersonaje, rol)) {
				System.out.println("No hay espacio para mas personajes en el sistema");
				break;
			}
			int cantSkins = Integer.parseInt(parts[2]);
			int pos = 3;
			for (int i = 0; i < cantSkins; i++) {
				String nombreSkin = parts[pos];
				String calidad = parts[pos + 1];
				if (!sistema.ingresarSkin(nombreSkin, calidad)) {
					System.out.println("No hay espacio para mas skins en el sistema");
					break;
				}
				try {
					if (!sistema.asociarSkinPersonaje(nombrePersonaje, nombreSkin)) {
						System.out.println("No hay espacio para mas skins en el personaje");
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
				System.out.println("No hay espacio para mas cuentas en el sistema");
				break;
			}
			int cantPersonajes = Integer.parseInt(parts[5]);
			int pos = 6;
			for (int i = 0; i < cantPersonajes; i++) {
				String nombrePersonaje = parts[pos++];
				try {
					if (!sistema.asociarPersonajeCuenta(nombreCuenta, nombrePersonaje)) {
						System.out.println("No hay espacio para mas personajes en la cuenta");
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
							System.out.println("No hay espacio para mas skins en la cuenta");
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
	
	public static void menuPrincipal(SistemaJuego sistema, Scanner scan) {
		System.out.println(">--- MENU PRINCIPAL ---<\n");
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
			cerrarSistema(sistema);
			break;
		default:
			System.out.println("Opcion invalida. Intente nuevamente\n");
			menuPrincipal(sistema, scan);
		}
	}

	public static void iniciarSesion(SistemaJuego sistema, Scanner scan) {
		
	}
	
	public static void registrarUsuario(SistemaJuego sistema, Scanner scan) {
		
	}
	
	public static void cerrarSistema(SistemaJuego sistema) {
		
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		SistemaJuego sistema = new SistemaJuegoImpl();
		/*
		leerArchivoPersonajes(sistema);
		leerArchivoCuentas(sistema);
		leerArchivoEstadisticas(sistema);
		*/
		menuPrincipal(sistema, scan);
	}
}
