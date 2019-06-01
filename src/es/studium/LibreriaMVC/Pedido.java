package es.studium.LibreriaMVC;

/**
 * 
 * @author jmrufo
 * Pedidos
 * Representa un objeto pedido de la tablas pedidos
 *
 */

public class Pedido {
	
	/**
	 * Atributos y Propiedades
	 */
	private int idPedido;
	private String fechaPedido;
	private int idUsuarioFK;
	private boolean procesado;
	private int libros;
	private int ejemplares;
	private String nombreUsuario;
	
	/**
	 * Constructor parametrizado
	 */
	public Pedido (int idPedido, String fechaPedido, int idUsuarioFK, boolean procesado, int libros, int ejemplares, String nombreUsuario) {
		this.setIdPedido(idPedido);
		this.setFechaPedido(fechaPedido);
		this.setIdUsuarioFK(idUsuarioFK);
		this.setProcesado(procesado);
		this.setLibros(libros);
		this.setEjemplares(ejemplares);
		this.setNombreUsuario(nombreUsuario);
	}

	/**
	 * Métodos Getter y Setter
	 */
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public int getIdUsuarioFK() {
		return idUsuarioFK;
	}

	public void setIdUsuarioFK(int idUsuarioFK) {
		this.idUsuarioFK = idUsuarioFK;
	}

	public boolean isProcesado() {
		return procesado;
	}

	public void setProcesado(boolean procesado) {
		this.procesado = procesado;
	}
	
	public int getLibros() {
		return libros;
	}

	public void setLibros(int libros) {
		this.libros = libros;
	}

	public int getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(int ejemplares) {
		this.ejemplares = ejemplares;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nomUsuario) {
		this.nombreUsuario = nomUsuario;
	}
	
}
