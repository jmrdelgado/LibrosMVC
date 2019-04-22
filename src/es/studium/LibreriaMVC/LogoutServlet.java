package es.studium.LibreriaMVC;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

/**
 * Indicamos datos de identificación del Servlet
 */
@WebServlet(
		name = "LogoutServlet",
		urlPatterns = {"/logout"}
		)

/**
* Servlet implementation class LogoutServlet
*/
public class LogoutServlet extends HttpServlet {
	
	//Revisión de Versión
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			out.println("<html>");
				out.println("<head>");
					out.println("<title>Libros MVC</title>");
					out.println("<link rel='stylesheet' type='text/css' href='lib/css/bootstrap.min.css'>");
			    	out.println("<link rel='stylesheet' type='text/css' href='lib/css/libreriamvc.css'>");
				out.println("</head>");
				out.println("<body style='background-color:#F5F5F5;'>");
					out.println("<div class='fondo-form'>");
					out.println("<div class='text-center mb-4 logotipo' style='padding:20px;'>");
		
			HttpSession session = request.getSession(false);
		
			if(session == null)	{
				out.println("<img src='images/ico_error.png' style='width:100px !important;'>");
				out.println("<h1 class='h4 font-weight-normal titulo-tienda'>No has iniciado sesión.</h1>");
			} else {
				session.invalidate();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("controlusers.jsp");
		        requestDispatcher.forward(request, response);
			}
		
			out.println("</body>");
			out.println("</html>");
			
		} finally {
			// Cerramos objetos
			out.close();
		}
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	
		/**
		* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		*/
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
		
}
