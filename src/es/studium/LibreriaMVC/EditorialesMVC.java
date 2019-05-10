package es.studium.LibreriaMVC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
*
* @author Jose Manuel Rufo Delgado
* EditorialesMVC
* Encapsula la comunicación con la base de datos
* Almacena, Edita y Elimina nombre de la Editorial
*
*/

public class EditorialesMVC {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	/*
	 * Pool de Conexiones
	 * Solicitamos conexión a base de datos
	 */
	public Connection conectar() throws ServletException, SQLException {
		
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
			System.out.println("Error al conectar con origen de datos: " + ex.getMessage());
		}
		
		return conn = pool.getConnection();
	}

	/*
	 * Alta Editorial
	 */
	public boolean altaEditorial(Editoriales editorial) throws SQLException, ServletException {
		
		//Sql de insercción
		String strsql = "INSERT INTO editoriales (idEditorial, nombreEditorial) VALUES (?,?)";
		
		//Paso de parámetros a consulta y ejecución
		pstmt = conectar().prepareStatement(strsql);
		pstmt.setString(1, null);
		pstmt.setString(2, editorial.getNombreEditorial());
		
		//Comprobamos si el alta ha sido correcto
		boolean newEditorial = pstmt.executeUpdate() > 0;
		
		//Cerramos instancias
		pstmt.close();
		conectar().close();
		
		//Devolvemos estado consulta insercción
		return newEditorial;
	}
	
	/*
	 * Listado de Editoriales
	 */
	
	
	/*
	 * Editar Editorial
	 */
	
	
	/*
	 * Eliminar Editorial
	 */
}
