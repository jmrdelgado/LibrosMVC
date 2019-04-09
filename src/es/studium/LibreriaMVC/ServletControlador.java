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
		name = "Controlador",
		urlPatterns = {"/shopping"}
)

public class ServletControlador extends HttpServlet {

    private static final long serialVersionUID = 1L;
 	
    //Pool de conexiones a la base de datos
 	private DataSource pool;
    
    /**
    * @see HttpServlet#HttpServlet()
    */
    public ServletControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig conf) throws ServletException {   	
    	super.init(conf);
    	
    	try	{
			
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			
			// Busca el recurso DataSource en el contexto
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_tiendalibros");
				
			if(pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_tiendalibros'");
			}
				
		} catch(NamingException ex){
			ex.printStackTrace();
			System.out.println("Error al conectar con origen de datos: " + ex.getMessage());
		}
    	
        LibrosMVC.cargarDatos();
        
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
    	
    	// Recupera la sesión actual o crea una nueva si no existe
        HttpSession session = request.getSession(true);
        // Recupera el carrito de la sesión actual
        List<ElementoPedido> elCarrito = (ArrayList<ElementoPedido>)
        session.getAttribute("carrito");
        // Determina a qué página jsp se redirigirá
        String nextPage = "";
        String todo = request.getParameter("todo");
        if(todo==null) {
            // Primer acceso, redirigir a order.jsp
            nextPage = "/order.jsp";
        }
        else if(todo.equals("add")) {
            // Mandado por order.jsp con los parámetros idLibro y cantidad
            // Creamos un elementoPedido y lo añadimos al carrito
            ElementoPedido nuevoElementoPedido = new ElementoPedido(Integer.parseInt(request.getParameter("idLibro")),Integer.parseInt(request.getParameter("cantidad")));

            if(elCarrito==null) {
                // El carrito está vacío
                elCarrito = new ArrayList<>();
                elCarrito.add(nuevoElementoPedido);
                // Enlazar el carrito con la sesión
                session.setAttribute("carrito", elCarrito);
            } else {
                // Comprueba si el libro está ya en el carrito
                // Si lo está, actualizamos la cantidad
                // Si no está, lo añadimos
                boolean encontrado = false;
                Iterator<ElementoPedido> iter = elCarrito.iterator();

                while(!encontrado&&iter.hasNext()) {
                    ElementoPedido unElementoPedido = (ElementoPedido)iter.next();

                    if(unElementoPedido.getIdlibro() == nuevoElementoPedido.getIdlibro()) {
                        unElementoPedido.setCantidad(unElementoPedido.getCantidad() + nuevoElementoPedido.getCantidad());
                        encontrado = true;
                    }
                }

                if(!encontrado) {
                    // Lo añade al carrito
                    elCarrito.add(nuevoElementoPedido);
                }
            }

            // Volvemos a order.jps para seguir con la compra
            nextPage = "/order.jsp";
        } else if(todo.equals("remove")) {

            // Enviado por order.jsp con el parámetro indiceElemento
            // Borra el elemento indiceElemento del carrito
            int indiceCarrito = Integer.parseInt(request.getParameter("indiceElemento"));
            elCarrito.remove(indiceCarrito);

            // Vuelve a order.jsp para seguir con la compra
            nextPage = "/order.jsp";
        } else if (todo.equals("checkout")) {

            // Enviado por order.jsp
            // Calcula el precio total de todos los elementos del carrito
            float precioTotal = 0;
            int cantidadTotalOrdenada = 0;

            for(ElementoPedido item: elCarrito) {
                float precio = Float.parseFloat(item.getPrecio());
                int cantidadOrdenada = item.getCantidad();
                precioTotal += precio * cantidadOrdenada;
                cantidadTotalOrdenada += cantidadOrdenada;
            }

            // Da formato al precio con dos decimales
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb);
            formatter.format("%.2f", precioTotal);
            formatter.close();

            /**
             * Iniciamos Alta de Pedido
             */
            //Obtenmos Fecha Actual y damos formato
            Date hoy = new Date();
			SimpleDateFormat formatohoy = new SimpleDateFormat("yyyy-MM-dd");
			String fechapedido = formatohoy.format(hoy);
			
			//Construimos consulta
            String sqlpedido = "INSERT INTO pedidos (fechaPedido,idUsuarioFK) VALUES ('"+ fechapedido +"', '1')";
            
			try {
				//Solicito conexión al Pool
				conn = pool.getConnection();
				
				//Instancio objeto para consulta preparada
				stmt = conn.prepareStatement(sqlpedido,Statement.RETURN_GENERATED_KEYS);

				//Compruebo si el alta ha sido correcto y Obtenemos ID del pedido creado
				int count = stmt.executeUpdate();
				if (count > 0) {
					
					ResultSet recuperaIDs = stmt.getGeneratedKeys();
					int idgenerado = 0;
					if (recuperaIDs.next()) {
						idgenerado = recuperaIDs.getInt(1);
					}
					
					//Procedo al alta de los artículos del pedido
					String sqldetallepedido = "INSERT INTO lineapedidos (idLibroFK, idPedidoFK, cantidadPedido) VALUES (?,?,?)";
					int totalarticulos = 0;

					Iterator<ElementoPedido> iter = elCarrito.iterator();
					
					while(iter.hasNext()){
						ElementoPedido altaPedido = (ElementoPedido)iter.next();
					    
					    stmt = conn.prepareStatement(sqldetallepedido);
						stmt.setInt(1, altaPedido.getIdlibro());
						stmt.setInt(2, idgenerado);
						stmt.setInt(3, altaPedido.getCantidad());
						stmt.executeUpdate();
						totalarticulos++;
					}
					
		            // Coloca el precioTotal y la cantidadTotal en el request
		            request.setAttribute("precioTotal", sb.toString());
		            request.setAttribute("cantidadTotal", cantidadTotalOrdenada + "");
		            nextPage = "/checkout.jsp";
				}
	            
	            /**
	             * Cerramos objetos
	             */
	            if(stmt != null) {
	            	stmt.close();
				}
		
				if(conn != null) {
					// Esto devolvería la conexión al pool
					conn.close();
				}
				
			} catch(SQLException ex) {
				System.out.println("Error al realizar la insercción de datos: " + ex.getMessage());
			}

        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
        requestDispatcher.forward(request, response);
    }
    
}

