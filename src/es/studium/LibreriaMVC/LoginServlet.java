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
		name = "LoginServlet",
		urlPatterns = {"/login"}
		)

public class LoginServlet extends HttpServlet {

	/**
	 * Revisión de versión
	 */
	private static final long serialVersionUID = -7835560411816124803L;

	// Pool de conexiones a la base de datos
	private DataSource pool;
	
	/**
	* @see HttpServlet#HttpServlet()
	*/
	public LoginServlet() {
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
		Connection conn = null;
		Statement stmt = null;
	
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
					out.println("<img src='images/ico_error.png' style='width:100px !important;'>");
					out.println("<h1 class='h4 font-weight-normal titulo-tienda'>Datos Insuficientes</h1>");
	
				// Obtener una conexión del pool
				conn = pool.getConnection();
				stmt = conn.createStatement();
	
				// Recuperar los parámetros usuario y password de la petición request
				String usuario = request.getParameter("usuario");
				String password = request.getParameter("password");
				
				// Validar los parámetros de la petición request
				if(usuario.length() == 0) {
					out.println("<h5>Introduzca Nombre de Usuario...</h5>");
					out.println("<a href='controlusers.jsp' class='btn btn-sm btn-primary btn-block' style='margin-top:15px;'>Volver a Intentar...<a/>");
				} else if (password.length()==0) {
					out.println("<h5>Introduzca Contraseña de Acceso...</h5>");
					out.println("<a href='controlusers.jsp' class='btn btn-sm btn-primary btn-block' style='margin-top:15px;'>Volver a Intentar...<a/>");
				} else {
					// Verificar que existe el usuario y su correspondiente	clave
					String sqlLogin = "SELECT * FROM usuarios WHERE nombreUsuario = '" + usuario + "' AND passUsuario = '" + password + "'";
					ResultSet rset = stmt.executeQuery(sqlLogin);
	
					if(!rset.next()) {
						// Si el resultset está vacío
						out.println("<h5>Nombre de usuario o contraseña incorrectos</h5>");
						out.println("<a href='controlusers.jsp' class='btn btn-sm btn-primary btn-block' style='margin-top:15px;'>Volver a Intentar...<a/>");
					} else {
						// Si los datos introducidos son correctos
						// Crear una sesión nueva y guardar el usuario como variable de sesión
						// Primero, invalida la sesión si ya existe
						HttpSession session = request.getSession(false);
	
						if(session != null)	{
							session.invalidate();
						}
	
						session = request.getSession(true);
							synchronized(session)
	
						{
							session.setAttribute("usuario", usuario);
							session.setAttribute("idUser", rset.getString("idUsuario"));
						}
	
							//Comprobamos si el usuario es administrador
							String rol = rset.getString("perfiluser");
							if (rol.equals("administrador")) {
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("paneladmin.jsp");
						        requestDispatcher.forward(request, response);
							} else if (rol.equals("cliente")) {
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("/shopping");
						        requestDispatcher.forward(request, response);
							}
					}
			
				}
					out.println("</div>");
					out.println("</div>");
					out.println("</body>");
					out.println("</html>");
					
			} catch(SQLException ex) {
				out.println("<p>Servicio no disponible...</p>");
				out.println("<p><a href='controlusers.jsp' class=\"btn btn-sm btn-primary btn-block\">Volver a Página Principal</a></p>");
				out.println("</div>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
			} finally {
			
				// Cerramos objetos
				out.close();
			
				try {
					if(stmt != null) {
						stmt.close();
					}
			
					if(conn != null) {
						// Esto devolvería la conexión al pool
						conn.close();
					}
				} catch(SQLException ex) {}
			}
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
