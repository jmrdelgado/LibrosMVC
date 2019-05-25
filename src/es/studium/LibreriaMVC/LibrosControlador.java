package es.studium.LibreriaMVC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
    	
    	// Recuperamos la sesión actual o crea una nueva si no existe
        HttpSession session = request.getSession(true);

        //Obtenemos petición y Establemos vista para mostrar listados de libros 
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("listbooks.jsp");
		
		try {
			List<Libro> mostrarLibros = LibrosMVC.consultaLibros();
			//request.setAttribute("libros", mostrarLibros);
			session.setAttribute("libros", mostrarLibros);
			requestDispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
			
	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}				
		
}
