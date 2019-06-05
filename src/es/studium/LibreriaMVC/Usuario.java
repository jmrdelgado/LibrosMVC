package es.studium.LibreriaMVC;

/**
*
* @author Jose Manuel Rufo
* Usuarios
* Representa un elemento de la tabla Usuarios
* Incluye identificador de usuario, nombreusuario, password, email y rol
*
*/

public class Usuario {
	
	/**
	 * Propiedades y Atributos
	 */
	private int idUsuario;
	private String nombreUsuario;
	private String passUsuario;
	private String emailUsuario;
	private String perfiluser;
	
	/**
	 * Controlador parametrizado
	 */
	public Usuario(int idUsuario, String nombreUsuario, String passUsuario, String emailUsuario, String perfiluser) {
		this.setIdUsuario(idUsuario);
		this.setNombreUsuario(nombreUsuario);
		this.setPassUsuario(passUsuario);
		this.setEmailUsuario(emailUsuario);
		this.setPerfiluser(perfiluser);
	}

	/**
	 * Métodos Getter y Setter
	 * @return
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassUsuario() {
		return passUsuario;
	}

	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getPerfiluser() {
		return perfiluser;
	}

	public void setPerfiluser(String perfiluser) {
		this.perfiluser = perfiluser;
	}

}
