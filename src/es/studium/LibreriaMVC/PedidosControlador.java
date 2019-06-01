package es.studium.LibreriaMVC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Indicamos datos de identificación del Servlet
 */
@WebServlet(
		name = "AdminPedidos",
		urlPatterns = {"/adminpedidos"}
		)

public class PedidosControlador extends HttpServlet {

	/**
	 * Revisión de versión
	 */
	private static final long serialVersionUID = -7835560411816124803L;
	
	/**
	* @see HttpServlet#HttpServlet()
	*/
	public PedidosControlador() {
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
        
        //Obtenemos petición y Establemos vista para mostrar listados de pedidos sin gestionar 
      	RequestDispatcher requestDispatcher = request.getRequestDispatcher("listorders.jsp");
        
        /*
         * Comprobamos si hay confirmación de pedidos
         */
        int confirmapedido = 0;
        
        if (request.getParameter("indiceregistro") != null) {
        	confirmapedido = Integer.parseInt(request.getParameter("indiceregistro"));
        	
        	try {
				PedidosMVC.confirmaPedidos(confirmapedido);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        	try {
    			List<Pedido> mostrarPedidos = PedidosMVC.consultaPedidos();
    			session.setAttribute("pedidos", mostrarPedidos);
    			requestDispatcher.forward(request, response);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (ParseException e) {
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
