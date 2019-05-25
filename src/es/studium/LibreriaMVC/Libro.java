package es.studium.LibreriaMVC;

/**
*
* @author Jose Manuel Rufo
* Editoriales
* Representa un elemento de la tabla Libros 
*
*/

public class Libro {
	
	/**
	 * Propiedades y Atributos
	 */
	private int idlibro;
	private String tituloLibro;
	private double precioLibro;
	private int existenciasLibro;
	private String isbn;
	private int idEditorialFK;
	private int idAutorFK;
	
	/**
	 * Constructor parametrizado
	 */
	public Libro (int idlibro, String tituloLibro, double precioLibro, int existenciasLibro, String isbn, int idEditorialFK, int idAutorFK) {
		this.setIdlibro(idlibro);
		this.setTituloLibro(tituloLibro);
		this.setPrecioLibro(precioLibro);
		this.setExistenciasLibro(existenciasLibro);
		this.setIsbn(isbn);
		this.setIdEditorialFK(idEditorialFK);
		this.setIdAutorFK(idAutorFK);
	}

	/**
	 * Getter y Setter de encapsulamiento
	 * @return
	 */
	public int getIdlibro() {
		return idlibro;
	}

	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

	public double getPrecioLibro() {
		return precioLibro;
	}

	public void setPrecioLibro(double precioLibro) {
		this.precioLibro = precioLibro;
	}

	public int getExistenciasLibro() {
		return existenciasLibro;
	}

	public void setExistenciasLibro(int existenciasLibro) {
		this.existenciasLibro = existenciasLibro;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getIdEditorialFK() {
		return idEditorialFK;
	}

	public void setIdEditorialFK(int idEditorialFK) {
		this.idEditorialFK = idEditorialFK;
	}

	public int getIdAutorFK() {
		return idAutorFK;
	}

	public void setIdAutorFK(int idAutorFK) {
		this.idAutorFK = idAutorFK;
	}

}
