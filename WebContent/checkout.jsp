<%-- Página de confirmación del pedido --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.LibreriaMVC.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ES" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmación</title>
    </head>
    <body>
        <h1>Librería MVC: Confirmación</h1>
        <hr/>
        <br/>
        <p><strong>Vas a comprar los siguientes libros:</strong></p>
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <th>Título</th><th>Autor</th><th>Precio</th><th>Cantidad</th>
            </tr>
            <%
            // Muestra los elementos del carrito
            List<ElementoPedido> cesta = (List<ElementoPedido>)
            session.getAttribute("carrito");
            for(ElementoPedido item:cesta) {
                %>
                <tr>
                    <td><%= item.getTitulo() %></td>
                    <td><%= item.getAutor() %></td>
                    <td align="right"><%= item.getPrecio() %> €</td>
                    <td align="right"><%= item.getCantidad() %></td>
                </tr>
                <%
            }

            session.invalidate();
            %>
            <tr>
                <th align="right" colspan="2">Total</th>
                <th align="right"><%= request.getAttribute("precioTotal") %></th>
                <th align="right"><%= request.getAttribute("cantidadTotal") %></th>
            </tr>
        </table>
        <br/>
        <a href="shopping">Pulsa aquí para comprar más libros</a>
    </body>
</html>
