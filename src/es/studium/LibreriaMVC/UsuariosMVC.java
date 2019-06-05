package es.studium.LibreriaMVC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * 
 * @author jmrufo
 * UsuariosMVC
 * Encapsula la comunicación con la base de datos
 * Consulta los datos del usuario y los valida
 */

public class UsuariosMVC {
	
	/**
	 * Método encargado de validar datos usuario logado
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	public static List<Usuario> validarUsuario(String nomuser, String passuser) throws ServletException, SQLException {
		
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
	    
	    //Instanciamos array de objetos de la clase Usuario 
	    List <Usuario> listaUsuarios = new ArrayList<Usuario>();
	    
	    //Configuramos consulta parametrizada
	    String user = nomuser;
	    String pass = passuser;
	    String sqlUser = "SELECT * FROM usuarios WHERE nombreUsuario = user AND passUsuario = pass";
	    
	    //Establecemos conexión y ejecutamos consultada
	    conn = pool.getConnection();
	    stmt = conn.createStatement();
	    
	    ResultSet rst = stmt.executeQuery(sqlUser);
	    
		while (rst.next()) {
			int idUsuario = rst.getInt("idUsuario");
			String nombreUsuario = rst.getString("nombreUsuario");
			String passUsuario = rst.getString("passUsuario");
			String emailUsuario = rst.getString("emailUsuario");
			String perfiluser = rst.getString("perfiluser");
			
			Usuario userlogeado = new Usuario(idUsuario,nombreUsuario,passUsuario,emailUsuario,perfiluser);
		
			//Cargamos objeto en colección
			listaUsuarios.add(userlogeado);
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
		return listaUsuarios;
	}

}
