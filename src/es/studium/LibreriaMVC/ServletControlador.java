package es.studium.LibreriaMVC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* Servlet implementation class ServletControlador
*/
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
    * @see HttpServlet#HttpServlet()
    */
    public ServletControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
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

                    if(unElementoPedido.getIdLibro() == nuevoElementoPedido.getIdLibro()) {
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

            // Coloca el precioTotal y la cantidadtotal en el request
            request.setAttribute("precioTotal", sb.toString());
            request.setAttribute("cantidadTotal", cantidadTotalOrdenada+"");

            //Redirige a checkout.jsp
            nextPage = "/checkout.jsp";
}
ServletContext servletContext = getServletContext();
RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
requestDispatcher.forward(request, response);
}
}

