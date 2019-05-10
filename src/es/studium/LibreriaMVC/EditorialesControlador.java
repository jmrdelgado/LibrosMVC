package es.studium.LibreriaMVC;

import java.io.IOException;

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
		name = "Editoriales",
		urlPatterns = {"/editoriales"}
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
        
    	System.out.println("Servlet Controlador DashBoard Editoriales");
    	
    	// Recupera la sesión actual o crea una nueva si no existe
    	HttpSession session = request.getSession(true);
    	
    	//Redirigimos al usuario al panel de administración
    	RequestDispatcher dispatcher= request.getRequestDispatcher("paneladmin.jsp");
		dispatcher.forward(request, response);    	
        
        //Comprobamos petición realizadad y llamamos a método correspondiente
        String action = request.getParameter("action");
		System.out.println(action);
		switch (action) {
		case "index":
			index(request, response);
			break;
		case "nuevo":
			nuevo(request, response);
			break;
		case "register":
			System.out.println("entro");
			registrar(request, response);
			break;
		case "mostrar":
			mostrar(request, response);
			break;
		case "showedit":
			showEditar(request, response);
			break;	
		case "editar":
			editar(request, response);
			break;
		case "eliminar":
			eliminar(request, response);
			break;
		default:
			break;
		}
    }


	/**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet (request, response);
    }

    
	/*
     * Creamos métodos de respuesta
     */
    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

    private void registrar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
    
    private void mostrar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

    private void showEditar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
    
    private void editar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}

