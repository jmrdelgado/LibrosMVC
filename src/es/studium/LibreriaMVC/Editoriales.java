package es.studium.LibreriaMVC;

/**
*
* @author Jose Manuel Rufo
* Editoriales
* Representa un elemento de la tabla Editorial
* Incluye identificador de la editorial y nombre
*
*/

public class Editoriales {

	/*
	 * Propiedades y atributos
	 */
	private int idEditorial;
	private String nombreEditorial;
	
	/*
	 * Constructor parametrizado
	 * @param idEditorial
	 * @param nombreEditorial
	 */
	public Editoriales(int idEditorial, String nombreEditorial) {
		
		this.setIdEditorial(idEditorial);
		this.setNombreEditorial(nombreEditorial);
	}
	
	/*
	 * Métodos Getter y Setter
	 */
	public int getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}

	public String getNombreEditorial() {
		return nombreEditorial;
	}

	public void setNombreEditorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}
	
}
