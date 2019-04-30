package es.studium.LibreriaMVC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.eclipse.jdt.internal.compiler.IDebugRequestor;

import com.mysql.jdbc.Statement;

/**
* Servlet implementation class ServletControlador
*/
@WebServlet(
		name = "PanelDashboard",
		urlPatterns = {"/dashboard"}
)

public class DashBoardControlador extends HttpServlet {

    private static final long serialVersionUID = 1L;
 	
    //Pool de conexiones a la base de datos
 	private DataSource pool;
    
    /**
    * @see HttpServlet#HttpServlet()
    */
    public DashBoardControlador() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	//Instanciamos objetos para obtener conexión a DBase
    	Connection conn = null;
		PreparedStatement stmt = null;
    	
    	//Recupera la sesión actual o crea una nueva si no existe
        HttpSession session = request.getSession(true);
        
        //Redirigimos a DashBoard
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/paneladmin.jsp");
        requestDispatcher.forward(request, response);
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

