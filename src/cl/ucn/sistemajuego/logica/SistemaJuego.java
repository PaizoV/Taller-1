package cl.ucn.sistemajuego.logica;

public interface SistemaJuego {

	public boolean ingresarPersonaje(String nombre,String rol);
	
	public boolean ingresarCuenta(String nombreCuenta,String contrasena, String nick, 
								  int nivel, int rp, String region);
	
	public boolean asociarPersonajeCuenta(String nombreCuenta,String nombrePersonaje);
	
	public boolean ingresarSkin(String nombre, String calidad);
	
	public boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin);
	
	public boolean asociarSkinCuenta(String nombreCuenta, String nombreSkin);
	
	public void asociarRecaudacion(int recaudacion, String nombrePersonaje);
	
	public boolean validarCliente(String nombreCuenta, String contrasena);
	
	// Start new methods
	boolean verificarPersonajesCuenta(String nombreCuenta);
	
	String obtenerPersonajesCuenta(String nombreCuenta);
	
	String obtenerSkinsPersonaje(String nombreCuenta, String nombrePersonaje);
	
	boolean verificarSkinCuenta(String nombreCuenta, String nombreSkin);
	
	boolean verificarPersonajeCuenta(String nombreCuenta, String nombrePersonaje);
	
	boolean verificarContrasenaCuenta(String nombreCuenta, String contrasena);
	
	boolean verificarCoincidenciaContrasena(String contrasena1, String contrasena2);
	
	String obtenerTxtPersonajesActualizado();
	
	String obtenerTxtCuentasActualizado();
	
	String obtenerTxtEstadisticasActualizado();
	// End new methods
	
	boolean comprarSkin(String nombreCuenta, String nombreSkin);
	
	public boolean comprarPersonaje(String nombrePersonaje, String nombreCuenta);
	
	public String obtenerSkinsDisponibles(String nombreCuenta);
	
	public String obtenerInventario(String nombreCuenta);
	
	public void recargarRp(String nombreCuenta, int cantidad);
	
	public String obtenerDatosCuenta(String nombreCuenta);
	
	public String  obtenerRecaudacionRol();
	
	public String obtenerRecaudacionRegion();
	
	public String obtenerRecaudacionPersonaje();
	
	public String obtenerCantPersonajeRol();
	
	public void bloquearJugador(String nombreCuenta);
	
	public String obtenerCuentasOrdenadas();
}
