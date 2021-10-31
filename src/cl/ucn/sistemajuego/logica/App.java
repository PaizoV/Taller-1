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
			int cantSkins = Integer.parseInt(parts[2]);
			if (!sistema.ingresarPersonaje(nombrePersonaje, rol)) {
				System.out.println("No hay espacio para mas personajes");
				break;
			}
			int posInicial = 3;
			for (int i = 0; i < cantSkins; i++) {
				String nombreSkin = parts[posInicial];
				String calidad = parts[posInicial + 1];
				if (!sistema.ingresarSkin(nombreSkin, calidad)) {
					System.out.println("No hay espacio para mas skins");
					break;
				}
				try {
					sistema.asociarSkinPersonaje(nombrePersonaje, nombreSkin);
				}
				catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
				posInicial += 2;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		SistemaJuego sistema = new SistemaJuegoImpl();
		
		leerArchivoPersonajes(sistema);
	}
}
