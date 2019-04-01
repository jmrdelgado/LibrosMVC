package es.studium.LibreriaMVC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement stmt = null;
	
		try {
			out.println("<html>");
				out.println("<head>");
					out.println("<title>Login</title>");
				out.println("</head>");
				out.println("<body>");
					out.println("<h2>Login</h2>");
	
				// Obtener una conexión del pool
				conn = pool.getConnection();
				stmt = conn.createStatement();
	
				// Recuperar los parámetros usuario y password de la petición request
				String usuario = request.getParameter("usuario");
				String password = request.getParameter("password");
				
				// Validar los parámetros de la petición request
				if(usuario.length() == 0) {
					out.println("<h3>Debes introducir tu usuario...</h3>");
				} else if (password.length()==0) {
					out.println("<h3>Debes introducir tu contraseña...</h3>");
				} else {
					// Verificar que existe el usuario y su correspondiente	clave
					StringBuilder sqlStr = new StringBuilder();
					sqlStr.append("SELECT * FROM usuarios WHERE ");
					sqlStr.append("STRCMP(usuarios.nombreUsuario,'").append(usuario).append("') = 0");
					sqlStr.append(" AND STRCMP(usuarios.passUsuario,PASSWORD('").append(password).append("')) = 0");
					out.println("<p>"+sqlStr.toString()+"</p>");
					//String sqlLogin = "SELECT * FROM usuarios WHERE usuarios.nombreUsuario = " + usuario + " AND usuarios.passUsuario = " + password;
					String sqlLogin = "SELECT * FROM usuarios WHERE nombreUsuario = '" + usuario + "' AND passUsuario = '" + password + "'";
					ResultSet rset = stmt.executeQuery(sqlLogin);
	
					if(!rset.next()) {
						// Si el resultset está vacío
						out.println("<h3>Nombre de usuario o contraseña incorrectos</h3>");
						out.println("<p><a href='index.jsp'>Volver a Login</a></p>");
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
						}
	
						out.println("<p>Hola, " + usuario + "!</p>");
						out.println("<p>Menú de opciones</p>");
						out.println("<ul>");
						out.println("<li><a href='#'>Libros</a></li>");
						out.println("<li><a href='#'>Autores</a></li>");
						out.println("<li><a href='#'>Editoriales</a></li>");
						out.println("<li><a href='#'>Pedidos</a></li>");
						out.println("<p><a href='hazalgo'>Haz algo</a></p>");
					}
			
				}
			
					out.println("</body>");
					out.println("</html>");
					
			} catch(SQLException ex) {
				out.println("<p>Servicio no disponible...</p>");
				out.println("<p><a href='index.jsp'>Volver a Login</a></p>");
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
			
	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// TODO Auto-generated method stub
		doGet(request, response);
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
				
		} catch(NamingException ex){}
				
	}
				
		
}
