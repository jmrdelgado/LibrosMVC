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
* @author Jorge
* LibrosMVC
* Encapsula la comunicación con la base de datos
* Almacena títulos, autores y precios en tres tablas
*
*/

public class LibrosMVC {

    private static final int MAX_SIZE = 5;
    private static int[] ids = new int[MAX_SIZE];
    private static String[] titulos = new String[MAX_SIZE];
    private static String[] autores = new String[MAX_SIZE];
    private static String[] precios = new String[MAX_SIZE];
    
    public static void cargarDatos() throws ServletException {
    	
    	// Pool de conexiones a la base de datos
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

        try {
        	
        	// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();

            // Ejecutamos sentencia sql
            String sqlStr = "SELECT `libros`.`*`,`autores`.`autorLibro` FROM `libros` INNER JOIN `autores` ON `libros`.`idAutorFK` = `autores`.`idAutor`";
            ResultSet rs = stmt.executeQuery(sqlStr);

            // Paso 5: Recoger los resultados y procesarlos
            int cont = 0;

            while(rs.next()) {
            	ids[cont] = rs.getInt("idlibro");
                titulos[cont] = rs.getString("tituloLibro");
                autores[cont] = rs.getString("autorLibro");
                precios[cont] = rs.getString("precioLibro");
                cont++;
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        finally {

            try {
                // Cerramos el resto de recursos
                if(stmt != null)    {
                    stmt.close();
                }

                if(conn != null) {
                    conn.close();
                }

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

/**
 * Creamos métodos para listar libros existentes en DBase
 * @throws SQLException 
 * @throws ServletException 
 */
public static List<Libro> consultaLibros() throws SQLException, ServletException {
	
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
    List<Libro> mostrarLibros = new ArrayList<Libro>();
    
    String sqllibros = "SELECT * FROM libros ORDER BY tituloLibro ASC";
    
    //Obtener una conexión del pool
	conn = pool.getConnection();
	stmt = conn.createStatement();
	
	ResultSet rst = stmt.executeQuery(sqllibros);
	
	while (rst.next()) {
		int idlibro = rst.getInt("idlibro");
		String tituloLibro = rst.getString("tituloLibro");
		double precioLibro = rst.getDouble("precioLibro");
		int existenciasLibro = rst.getInt("existenciasLibro");
		String isbn = rst.getString("isbn");
		int idEditorialFK = rst.getInt("idEditorialFK");
		int idAutorFK = rst.getInt("idAutorFK");
		
		Libro libro = new Libro(idlibro,tituloLibro,precioLibro,existenciasLibro,isbn,idEditorialFK,idAutorFK);
		
		mostrarLibros.add(libro);
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
	return mostrarLibros;
}
    
    /**
    * Devuelve el número de libros obtenidos
    */
    public static int tamano() {
        return titulos.length;
    }
    
    /**
     * Devuelve el id del libro identificado con idLibro
     */
     public static int getIdlibro(int idLibro) {
         return ids[idLibro];
     }

    /**
    * Devuelve el título del libro identificado con idLibro
    */
    public static String getTitulo(int idLibro) {
        return titulos[idLibro];
    }

    /**
    * Devuelve el autor del libro identificado con idLibro
    */
    public static String getAutor(int idLibro) {
        return autores[idLibro];
    }

    /**
    * Devuelve el precio del libro identificado con idLibro
    */
    public static String getPrecio(int idLibro) {
        return precios[idLibro];
    }
}

