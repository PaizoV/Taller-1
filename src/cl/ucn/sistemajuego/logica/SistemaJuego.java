package cl.ucn.sistemajuego.logica;

public interface SistemaJuego {

	/**
	 * Enters a character to the system.
	 * @param nombre The name of the character.
	 * @param rol The role of the character.
	 * @return True if the list of characters has any space. False otherwise.
	 */
	public boolean ingresarPersonaje(String nombre,String rol);
	
	/** 
	 * Enters an account to the system.
	 * @param nombreCuenta The name of the account.
	 * @param contrasena The password ot the account.
	 * @param nick The nickname of the account.
	 * @param nivel The level of the account.
	 * @param rp The amount of RP the account has.
	 * @param region The region of the account.
	 * @return True if the list of accounts has any space. False otherwise.
	 */
	public boolean ingresarCuenta(String nombreCuenta,String contrasena, String nick, 
								  int nivel, int rp, String region);
	
	/**
	 * Associates a character with an account. The character is inserted into the account's
	 * list of characters.
	 * @param nombreCuenta The name of the account.
	 * @param nombrePersonaje The name of the character.
	 * @return True if the account's list of characters has any space. False otherwise.
	 */
	public boolean asociarPersonajeCuenta(String nombreCuenta,String nombrePersonaje);
	
	/**
	 * Enters a skin to the system.
	 * @param nombre The name of the skin.
	 * @param calidad The quality of the skin.
	 * @return True if the list of skins has any space. False otherwise.
	 */
	public boolean ingresarSkin(String nombre, String calidad);
	
	/**
	 * Associates a skin with a character. The skin is inserted into the character's list
	 * of skins, and the character is set as the character of the skin.
	 * @param nombrePersonaje The name of the character.
	 * @param nombreSkin The name of the skin.
	 * @return True if the character's list of skins has any space. False otherwise.
	 */
	public boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin);
	
	/**
	 * Associates a skin with an account. The skin is inserted into the account's list
	 * of skins.
	 * @param nombreCuenta The name of the account.
	 * @param nombreSkin The name of the skin.
	 * @return True if the account's list of skins has any space. False otherwise.
	 */
	public boolean asociarSkinCuenta(String nombreCuenta, String nombreSkin);
	
	/**
	 * Sets the given amount as the recaudation a character has achieved.
	 * @param recaudacion The recaudation.
	 * @param nombrePersonaje The name of the character.
	 */
	public void asociarRecaudacion(int recaudacion, String nombrePersonaje);
	
	/**
	 * Checks if a user is registered in the system.
	 * @param nombreCuenta The name of the account.
	 * @param contrasena The password of the account.
	 * @return True if the user is registered. False otherwise.
	 */
	public boolean validarCliente(String nombreCuenta, String contrasena);
	
	// Start new methods
	/**
	 * Verifies if an account is registered in the system.
	 * @param nombreCuenta The name of the account.
	 * @return True if the account exists. False otherwise.
	 */
	boolean verificarExistenciaCuenta(String nombreCuenta);
	
	/**
	 * Verifies if a character is registered in the system.
	 * @param nombrePersonaje The name of the character.
	 * @return True if the character exists. False otherwise.
	 */
	boolean verificarExistenciaPersonaje(String nombrePersonaje);
	
	/**
	 * Verifies if an account has any characters.
	 * @param nombreCuenta The name of the account.
	 * @return True if the account has at least 1 character. False if has none.
	 */
	boolean verificarPersonajesCuenta(String nombreCuenta);
	
	/**
	 * Obtains the names of the characters of an account.
	 * @param nombreCuenta The name of the account.
	 * @return A string with the information.
	 */
	String obtenerPersonajesCuenta(String nombreCuenta);
	
	/**
	 * Obatins the names of the skins of a character of an account.
	 * @param nombreCuenta The name of the account.
	 * @param nombrePersonaje The name of the character.
	 * @return A string with the information.
	 */
	String obtenerSkinsPersonaje(String nombreCuenta, String nombrePersonaje);
	
	/**
	 * Verifies if an account has a certain skin.
	 * @param nombreCuenta The name of the account.
	 * @param nombreSkin The name of the skin.
	 * @return True if the account has the skin. False otherwise.
	 */
	boolean verificarSkinCuenta(String nombreCuenta, String nombreSkin);
	
	/**
	 * Obtains the names of all the characters in the system.
	 * @return A string with the names.
	 */
	String obtenerPersonajes();
	
	/**
	 * Obtains the amount of RP an account has.
	 * @param nombreCuenta The name of the account.
	 * @return The amount of RP.
	 */
	int obtenerRp(String nombreCuenta);
	
	/**
	 * Verifies if an account has a certain character.
	 * @param nombreCuenta The name of the account.
	 * @param nombrePersonaje The name of the character.
	 * @return True if the account has the character. False otherwise.
	 */
	boolean verificarPersonajeCuenta(String nombreCuenta, String nombrePersonaje);
	
	/**
	 * Verifies that the given password is the password of the account.
	 * @param nombreCuenta The name of the account.
	 * @param contrasena The password.
	 * @return True if the passwords match. False otherwise.
	 */
	boolean verificarContrasenaCuenta(String nombreCuenta, String contrasena);
	
	/**
	 * Changes the account's password for the given password.
	 * @param nombreCuenta The name of the account.
	 * @param contrasena The new password.
	 */
	void cambiarContrasena(String nombreCuenta, String contrasena);
	
	/**
	 * Obtains the information of the characters that will be stored in a text file.
	 * @return The string with the information.
	 */
	String obtenerTxtPersonajesActualizado();

	/**
	 * Obtains the information of the accounts that will be stored in a text file.
	 * @return The string with the information.
	 */
	String obtenerTxtCuentasActualizado();

	/**
	 * Obtains the information of the recaudations that will be stored in a text file.
	 * @return The string with the information.
	 */
	String obtenerTxtEstadisticasActualizado();
	// End new methods
	
	/**
	 * Buys a skin for the given account. All the related information is updated.
	 * @param nombreCuenta The name of the account.
	 * @param nombreSkin The name of the skin.
	 * @return True if the account's list of skins has any space. False otherwise.
	 */
	boolean comprarSkin(String nombreCuenta, String nombreSkin);
	
	/**
	 * Buys a character for the given account. All the related information is updated.
	 * @param nombrePersonaje The name of the character.
	 * @param nombreCuenta The name of the account.
	 * @return True if the account's list of characters has any space. False otherwise.
	 */
	boolean comprarPersonaje(String nombrePersonaje, String nombreCuenta);
	
	/**
	 * Obtains all the avalaible skins for an account.
	 * @param nombreCuenta The name of the account.
	 * @return A string with the information.
	 */
	String obtenerSkinsDisponibles(String nombreCuenta);
	
	/**
	 * Obtains the inventory of an account.
	 * @param nombreCuenta The name of the account.
	 * @return A string with the information.
	 */
	String obtenerInventario(String nombreCuenta);
	
	/**
	 * Recharges RP to an account.
	 * @param nombreCuenta The name of the account.
	 * @param cantidad The amount of RP that will be recharged.
	 */
	void recargarRp(String nombreCuenta, int cantidad);
	
	/**
	 * Obtains the information of an account.
	 * @param nombreCuenta The name of the account.
	 * @return A string with the information.
	 */
	String obtenerDatosCuenta(String nombreCuenta);
	
	/**
	 * Obtains the recaudation by role.
	 * @return A string with the recaudation.
	 */
	String  obtenerRecaudacionRol();
	
	/**
	 * Obtains the recaudation by region.
	 * @return A string with the recaudation.
	 */
	String obtenerRecaudacionRegion();
	
	/**
	 * Obtains the recaudation by character.
	 * @return A string with the recaudation.
	 */
	String obtenerRecaudacionPersonaje();
	
	/**
	 * Obtains the number of characters each role has.
	 * @return A string with the information.
	 */
	String obtenerCantPersonajeRol();
	
	/**
	 * Blocks an account. This prevents the account from logging.
	 * @param nombreCuenta The name of the account.
	 */
	void bloquearJugador(String nombreCuenta);
	
	/**
	 * Obtains the names of the accounts sorted by their account's level.
	 * @return A string with the information.
	 */
	String obtenerCuentasOrdenadas();
}
