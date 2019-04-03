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
        <title>Tienda Libros MVC</title>
    </head>
    <body>
    	<div class="fondo-carrito">
    		<div class="text-center mb-4 logotipo">
	        	<img src="images/cab_librosmvc.svg" alt="icono-login">
    		</div>
    		<div class="acceso_panel">
        		<a href="controlusers.jsp" alt="Acceso Usuarios"><img src="images/ico_acceso.png" alt="Acceso Usuarios"> Acceso Usuarios</a>
        	</div>
    		<br>
	        <h3 class="h3 titulo-subrayado">LIBROS <strong>RECOMENDADOS</strong></h3>

	        <p><strong>Elegir un libro y la cantidad:</strong></p>
	
	        <form  class="form-inline" name="AgregarForm" action="shopping" method = "POST">
	            <input type="hidden" name="todo" value="add">
	            Título:
	            <select name="idLibro" class="form-control">
	                <%
	                // Scriplet 1: Carga los libros en el SELECT
	                for(int i = 0; i <LibrosMVC.tamano();i++) {
	                    out.println("<option value='" + i + "'>");
	                    out.println(LibrosMVC.getTitulo(i) + " | " + LibrosMVC.getAutor(i)
	                    + " | " + LibrosMVC.getPrecio(i));
	                    out.println("</option>");
	                }
	                %>
	            </select>
	            Cantidad: <input class="form-control" type="text" name="cantidad" size="10" value="1">
	            <input class="btn btn-success" type="submit" value="Añadir a la cesta">
	        </form>
	
	        <%
	        // Scriplet 2: Chequea el contenido de la cesta
	        List<ElementoPedido> cesta = (List<ElementoPedido>)
	        session.getAttribute("carrito");
	        if(cesta != null && cesta.size() > 0) {
	        %>
	
			<br>
			<h3 class="h3 titulo-subrayado">MI <strong>CARRITO</strong></h3>
	        <p><strong>Tu cesta contiene:</strong></p>
	
	        <table border="1" cellspacing="0" cellpadding="5" class="table">
	        	<thead class="thead-dark">
		            <tr>
		                <th scope="col">Título</th>
		                <th scope="col">Autor</th>
		                <th scope="col">Precio</th>
		                <th scope="col">Cantidad</th><th>&nbsp;</th>
		            </tr>
		        </thead>
	
	            <%
	            // Scriplet 3: Muestra los libros del carrito
	            for(int i = 0; i<cesta.size(); i++) {
	                ElementoPedido elementoPedido = cesta.get(i);
	            %>
	            <tr class="table-light">
	            <form name="borrarForm" action="shopping" method="POST">
	                <input type="hidden" name="todo" value="remove">
	                <input type="hidden" name="indiceElemento"  value="<%= i%>">
	                <td><%= elementoPedido.getTitulo() %></td>
	                <td><%= elementoPedido.getAutor() %></td>
	                <td align="right"><%= elementoPedido.getPrecio()%> €</td>
	                <td align="center"><%=elementoPedido.getCantidad() %></td>
	                <td align="center"><input class="btn btn-warning" type="submit" value="Eliminar de la cesta"></td>
	            </form>
	            </tr>
	
	            <%}%>
	        </table>
	        <br/>
	        <form name="checkoutForm" action="shopping" method="POST">
	            <input type="hidden" name="todo" value="checkout">
	            <input class="btn btn-success" type="submit" value="Confirmar compra">
	        </form>
	        <%}%>
	        
	        <div class="piepagina">
        		<p>Librería MVC S.L., CIF.:B91458752 | Avda. La Revuelta, nº 25 | Pol. Ind. Los Perdidos | 41500, Alcalá de Guadaira (Sevilla)</p>
   			</div>
   		
	        <!-- Optional JavaScript -->
	    	<!-- jQuery first, then Popper.js, then Bootstrap JS ==============================================-->
	    	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	    	<!-- ============================================================================================= -->
   		</div>
    </body>
</html>
