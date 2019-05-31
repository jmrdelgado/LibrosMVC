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
	
	/**
	 * Constructor parametrizado
	 */
	public Pedido (int Pedido, String fechaPedido, int idUsuarioFK, boolean procesado) {
		this.setIdPedido(idPedido);
		this.setFechaPedido(fechaPedido);
		this.setIdUsuarioFK(idUsuarioFK);
		this.setProcesado(procesado);
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
	
}
