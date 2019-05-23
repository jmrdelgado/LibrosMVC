package es.studium.LibreriaMVC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
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
		System.out.println("Comprobando enrutador");
	}
			
	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement stmt = null;
	
		/*
			out.println("<html>");
				out.println("<head>");
					out.println("<title>Libros MVC</title>");
					out.println("<link rel='stylesheet' type='text/css' href='lib/css/bootstrap.min.css'>");
			    	out.println("<link rel='stylesheet' type='text/css' href='lib/css/libreriamvc.css'>");
				out.println("</head>");
				out.println("<body style='background-color:#F5F5F5;'>");
					out.println("<div class='fondo-form'>");
					out.println("<div class='text-center mb-4 logotipo' style='padding:20px;'>");
					out.println("<img src='images/ico_error.png' style='width:100px !important;'>");
					out.println("<h1 class='h4 font-weight-normal titulo-tienda'>Listado de Libros</h1>");
					out.println("</div>");
					out.println("</div>");
					out.println("</body>");
					out.println("</html>");*/
				
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
