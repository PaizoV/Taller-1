package cl.ucn.sistemajuego.logica;

public interface SistemaJuego {

	boolean ingresarPersonaje(String nombre, String rol);
	
	boolean ingresarCuenta(String nombreCuenta, String contrasena, String nick, int nivel,
			double rp, String region);
	
	boolean asociarPersonajeCuenta(String nombreCuenta, String nombrePersonaje);
	
	boolean ingresarSkin(String nombre, String calidad);
	
	boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin);
	
	void asociarRecaudacion(double recaudacion, String nombrePersonaje);
	
	boolean validarCliente(String nombreCuenta, String contrasena);
	
	boolean comprarSkin(String nombrePersonaje, String nombreCuenta, String nombreSkin);
	
	boolean comprarPersonaje(String nombrePersonaje, String nombreCuenta);
	
	String obtenerSkinsDisponibles(String nombreCuenta);
	
	String obtenerInventario(String nombreCliente);
	
	void recargarRp(String nombreCuenta, int cantidad);
	
	String obtenerDatosCuenta(String nombreCuenta);
	
	void cambiarClave(String nombreCuenta, String claveNueva);
	
	double obtenerRecaudacionRol();
	
	double obtenerRecaudacionRegion();
	
	int obtenercantPersonajesRol();
	
	void bloquearJugador(String nombreCuenta);
	
	String obtenerInfoCuentas();
}
