package es.studium.LibreriaMVC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Indicamos datos de identificación del Servlet
 */
@WebServlet(
		name = "AdminLibros",
		urlPatterns = {"/adminlibros"}
		)

public class LibrosControlador extends HttpServlet {

	/**
	 * Revisión de versión
	 */
	private static final long serialVersionUID = -7835560411816124803L;

	// Pool de conexiones a la base de datos
	private DataSource pool;
	
	/**
	* @see HttpServlet#HttpServlet()
	*/
	public LibrosControlador() {
		super();
	}
	
	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
			
	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();


		//Instanciamos objetos para obtener conexión a DBase
    	Connection conn = null;
    	Statement stmt = null;
    	
    	// Recupera la sesión actual o crea una nueva si no existe
        HttpSession session = request.getSession(true);
        
        
	
		
				
				System.out.println("Enrutación realizada correctamente");
		
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("listbooks.jsp");
		        requestDispatcher.forward(request, response);
		        
				response.getWriter().append("Served at: ").append(request.getContextPath());
	}
			
	public void init(ServletConfig config) throws ServletException	{
			
		try	{
				
			// Crea un contecto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tiendalibros");
				
			if(pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_tiendalibros'");
			}
				
		} catch(NamingException ex){
			ex.printStackTrace();
		}
				
	}
				
		
}
