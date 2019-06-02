package es.studium.LibreriaMVC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import sun.management.ConnectorAddressLink;

/**
*
* @author Jose Manuel Rufo Delgado
* EditorialesMVC
* Encapsula la comunicación con la base de datos
* Almacena, Edita y Elimina nombre de la Editorial
*
*/

public class EditorialesMVC {
	 
	PreparedStatement pstmt = null;
	
	/*
	 * Pool de Conexiones
	 * Solicitamos conexión a base de datos
	 */
	public static Connection conectar() throws ServletException, SQLException {
		
		DataSource pool = null;
		Connection conn = null;
		
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
	public boolean altaEditorial(Editorial editorial) throws SQLException, ServletException {
		
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
	
	/**
	 * Método para listar Editoriales existentes en DBase
	 * @throws SQLException 
	 * @throws ServletException 
	 */
	public static List<Editorial> consultaEditoriales() throws SQLException, ServletException {
				    
	    //Instanciamos objeto de la clase libros
	    List<Editorial> mostrarEditoriales = new ArrayList<Editorial>();
	    
	    String sqlEditoriales = "SELECT * FROM editoriales ORDER BY nombreEditorial ASC";
	    
	    //Obtener conexión del pool y ejecutamos consulta
		Statement stmt = conectar().createStatement();
		
		ResultSet rst = stmt.executeQuery(sqlEditoriales);
		
		while (rst.next()) {
			int idEditorial = rst.getInt("idEditorial");
			String nombreEditorial = rst.getString("nombreEditorial");
			
			Editorial editorial = new Editorial(idEditorial,nombreEditorial);
			
			mostrarEditoriales.add(editorial);
		}
		
		/**
		 * Cerramos conexiones
		 */
	    if(stmt != null)    {
	        stmt.close();
	        conectar().close();
	    }
	    
	    //Retornamos colección de objetos
		return mostrarEditoriales;
	}
	
	
	/*
	 * Editar Editorial
	 */
	
	
	/*
	 * Eliminar Editorial
	 */
}
