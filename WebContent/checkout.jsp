<%-- Página de órdenes de pedido --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*, es.studium.LibreriaMVC.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ES" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!-- metas -->
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <meta name="author" content="Jose Manuel Rufo Delgado">
    	<meta name="generator" content="Eclipse JEE">
    	<meta name="keywords" content="libros, página, aventura, acción, romance, intriga" />
    	<meta name="description" content="Tienda Online de Libros" />
    	<meta name="title" content="Librería MVC"/>
    
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    	<!-- Bootstrap CSS ================================================================================-->
    	<link rel="stylesheet" type="text/css" href="lib/css/bootstrap.min.css">
    	<link rel="stylesheet" type="text/css" href="lib/css/libreriamvc.css">
        <title>Tienda Libros MVC - Resumen de compra</title>
    </head>
    <body>
    	<div class="fondo-carrito">
    		<%@include file = "header.jsp"%>

    		<br>
	        <h3 class="h3 titulo-subrayado">CONFIRMACIÓN <strong>DE COMPRA</strong></h3>
	        
	        <p><strong>Resumen de compra efectuada:</strong></p>
	        <table border="1" cellspacing="0" cellpadding="5" class="table">
	        	<thead class="thead-dark">
		            <tr>
		                <th scope="col">Título</th>
		                <th scope="col">Autor</th>
		                <th scope="col" style="text-align:right;">Precio</th>
		                <th scope="col" style="text-align:center;">Cantidad</th>
		            </tr>
		        </thead>

	            <%
	            // Muestra los elementos del carrito
	            List<ElementoPedido> cesta = (List<ElementoPedido>)
	            session.getAttribute("carrito");
	            for(ElementoPedido item:cesta) {
	                %>
	                <tr class="table-light">
	                    <td><%= item.getTitulo() %></td>
	                    <td><%= item.getAutor() %></td>
	                    <td style="text-align:right;"><%= item.getPrecio() %> €</td>
	                    <td style="text-align:center;"><%= item.getCantidad() %></td>
	                </tr>
	                <%
	            }
	
	            session.invalidate();
	            %>
	            <tfoot>
		            <tr>
		                <th style="text-align:right;" colspan="2" class="table-dark">Total</th>
		                <th style="text-align:right;" class="table-info"><%= request.getAttribute("precioTotal") %> €</th>
		                <th style="text-align:center;" class="table-info"><%= request.getAttribute("cantidadTotal") %></th>
		            </tr>
		        </tfoot>
	        </table>
	        <br/>
	        <a href="shopping" class="btn btn-success">Seguir comprando</a>
	        
	        <%@include file = "footer.jsp"%>
   		
	        <!-- Optional JavaScript -->
	    	<!-- jQuery first, then Popper.js, then Bootstrap JS ==============================================-->
	    	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	    	<!-- ============================================================================================= -->
		</div>
    </body>
</html>
