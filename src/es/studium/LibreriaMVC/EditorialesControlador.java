package es.studium.LibreriaMVC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* Servlet implementation class DashBoardControlador
*/
@WebServlet(
		name = "AdminEditorial",
		urlPatterns = {"/admineditorial"}
)

public class EditorialesControlador extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /**
    * @see HttpServlet#HttpServlet()
    */
    public EditorialesControlador() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
    	
    	// Recupera la sesión actual o crea una nueva si no existe
    	HttpSession session = request.getSession(true);
    	
    	//Redirigimos al usuario al panel de administración
    	RequestDispatcher dispatcher = request.getRequestDispatcher("listpublishers.jsp");
		
    	try {
			List<Editorial> mostrarEditoriales = EditorialesMVC.consultaEditoriales();
			session.setAttribute("editoriales", mostrarEditoriales);
			dispatcher.forward(request, response);
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
        doGet (request, response);
    }

}

