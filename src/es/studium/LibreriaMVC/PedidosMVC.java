package es.studium.LibreriaMVC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
*
* @author Jorge
* LibrosMVC
* Encapsula la comunicación con la base de datos
* Almacena títulos, autores y precios en tres tablas
*
*/

public class PedidosMVC {

/**
 * Creamos método para listar pedidos pendientes de gestionar existentes en DBase
 * @throws SQLException 
 * @throws ServletException 
 * @throws ParseException 
 */
public static List<Pedido> consultaPedidos() throws SQLException, ServletException, ParseException {
	
	//Instanciamos objeto para realizar un Pool de conexiones a la base de datos
 	DataSource pool = null;
		
		try	{
				
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tiendalibros");
				
			if(pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_tiendalibros'");
			}
				
		} catch(NamingException ex){
			ex.printStackTrace();
		}
 	
 	// Creamos objetos para la conexión
    Connection conn = null;
    Statement stmt = null;
    
    //Instanciamos objeto de la clase libros
    List<Pedido> mostrarPedidos = new ArrayList<Pedido>();
    
    String sqlpedidos = "SELECT idPedido, fechaPedido, idUsuarioFK, COUNT(idLibroFK) AS libros, SUM(cantidadPedido) AS ejemplares, procesado, nombreUsuario\r\n" + 
    		"FROM ((pedidos\r\n" + 
    		"INNER JOIN lineapedidos\r\n" + 
    		"ON idPedido = idPedidoFK)\r\n" + 
    		"INNER JOIN usuarios\r\n" + 
    		"ON idUsuario = idUsuarioFK)\r\n" +
    		"GROUP BY idPedido\r\n" + 
    		"ORDER BY idPedido ASC";
    
    //Obtener una conexión del pool
	conn = pool.getConnection();
	stmt = conn.createStatement();
	
	ResultSet rst = stmt.executeQuery(sqlpedidos);
	
	while (rst.next()) {
		Boolean procesado = rst.getBoolean("procesado");
		
		if (procesado == false) {
			int idPedido = rst.getInt("idPedido");
			String fechaPedido = rst.getString("fechaPedido");
			int idUsuarioFK = rst.getInt("idUsuarioFK");
			procesado = rst.getBoolean("procesado");
			int nlibros = rst.getInt("libros");
			int ejemplares = rst.getInt("ejemplares");
			String nombreUsuario = rst.getString("nombreUsuario");
			
			Pedido pedidos = new Pedido(idPedido,fechaPedido,idUsuarioFK,procesado,nlibros,ejemplares,nombreUsuario);		
		
			mostrarPedidos.add(pedidos);
		}
	}
	
	/**
	 * Cerramos conexiones
	 */
    if(stmt != null)    {
        stmt.close();
    }

    if(conn != null) {
        conn.close();
    }
    
    //Retornamos colección de objetos
	return mostrarPedidos;
	}


/**
 * Método de confirmación de pedidos
 * @throws ServletException 
 * @throws SQLException 
 */
public static void confirmaPedidos(int idPedido) throws ServletException, SQLException {
	//Instanciamos objeto para realizar un Pool de conexiones a la base de datos
 	DataSource pool = null;
		
		try	{
				
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tiendalibros");
				
			if(pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_tiendalibros'");
			}
				
		} catch(NamingException ex){
			ex.printStackTrace();
		}
 	
 	// Creamos objetos para la conexión
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    int pedido = idPedido; 
    String sqlconfirmapedido = "UPDATE pedidos SET procesado = ? WHERE idpedido = ?"; 
    
    //Obtener una conexión del pool
	conn = pool.getConnection();
	pstmt = conn.prepareStatement(sqlconfirmapedido);
	
	pstmt.setInt(1, 1);
	pstmt.setInt(2, pedido);
	
	/**
	 * Cerramos conexiones
	 */
    if(pstmt != null)    {
        pstmt.close();
    }

    if(conn != null) {
        conn.close();
    }
}

}
   
