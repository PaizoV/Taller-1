package cl.ucn.sistemajuego.logica;

public interface SistemaJuego {

	public boolean ingresarPersonaje(String nombre,String rol);
	
	public boolean ingresarCuenta(String nombreCuenta,String contrasena, String nick, 
								  String region, int nivel, double rp);
	
	public boolean asociarPersonajeCuenta(String nombreCuenta,String nombrePersonaje);
	
	public boolean ingresarSkin(String nombre, int costo, String calidad);
	
	public boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin);
	
	public boolean asociarSkinPersonajeCuenta(String nombrePersonaje, String nombreSkin,
											  String nombreCuenta);
	
	public boolean asociarRecaudacion(double recaudacion, String nombrePersonaje);
	
	public boolean validarCliente(String nombreCuenta);
	
	public boolean validarAcceso(String nombreCuenta, String contrasena);
	
	public void comprarSkin(String nombrePersonaje, String nombreCuenta, String nombreSkin);
	
	public boolean comprarPersonaje(String nombrePersonaje, String nombreCuenta);
	
	public String obtenerSkinsDisponibles(String nombreCuenta);
	
	public String obtenerInventario(String nombreCliente);
	
	public void recargarRp(String nombreCuenta, int cantidad);
	
	public String obtenerDatosCuenta(String nombreCuenta);
	
	public void cambiarClave(String nombreCuenta, String claveNueva);
	
	public String  obtenerRecaudacionRol();
	
	public String obtenerRecaudacionRegion();
	
	public String obtenerRecaudacionPersonaje();
	
	public String obtenerCantPersonajeRol();
	
	public void bloquearJugador(String nombreCuenta);
	
	public String obtenerInfoCuentas();
	
}
