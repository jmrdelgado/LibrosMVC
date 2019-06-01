<%-- Página que muestra listado de pedido realizados y no tramitados --%>
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
    		<%@include file = "header.jsp"%>
    		
    		<!--  Panel de navegación -->			
			<ul class="nav nav-justified">
			    <li class="nav-item dropdown"><a href="adminlibros" class="nav-link">Libros</a></li>
			    <li class="nav-item dropdown"><a href="#" class="nav-link" data-toggle="dropdown">Autores</a></li>
			    <li class="nav-item dropdown"><a href="adminpedidos" class="nav-link">Consultar Pedidos</a></li>
			    <li class="nav-item dropdown"><a href="#datepicker.html" class="nav-link" data-toggle="dropdown">Editoriales</a></li>
			</ul>

    		<br>
	        <h3 class="h3 titulo-subrayado">PEDIDOS <strong> REALIZADOS</strong></h3>

	        <p><strong>Listado de pedidos pendientes de confirmación:</strong></p>
		
	        <table border="1" cellspacing="0" cellpadding="5" class="table">
	        	<thead class="thead-dark">
		            <tr>
		            	<th scope="col" style="text-align:center;">Id</th>
		                <th scope="col" style="text-align:center;">Fecha Pedido</th>
		                <th scope="col" style="text-align:center;">Nº Libros</th>
		                <th scope="col" style="text-align:center;">Nº Ejemplares</th>
		                <th scope="col" style="text-align:center;">Usuario</th>
		                <th scope="col" style="text-align:center;"></th>
		            </tr>
		        </thead>
				<tbody>
					<%
					//Recuperamnos contenido de Array almacenado en sesión
					List<Pedido> consultaPedidos = (List<Pedido>) session.getAttribute("pedidos");
			        if(consultaPedidos != null && consultaPedidos.size() > 0) {
			        %>
			        
			        <%
			        //Mostramos datos obtenidos
			        for (int i = 0; i < consultaPedidos.size(); i++) {
			        	Pedido regpedido = consultaPedidos.get(i);
			        %>        
						<tr class="table-light">
							<form name="procesapedido" action="adminpedidos" method="POST">
								<input type="hidden" name="indiceregistro" value="<%= regpedido.getIdPedido()%>">
								<td style="text-align:center; padding:5px !important;"><%= regpedido.getIdPedido() %></td>
								<td style="text-align:center; padding:5px !important;"><%= regpedido.getFechaPedido() %></td>
								<td style="text-align:center; padding:5px !important;"><%= regpedido.getLibros() %></td>
								<td style="text-align:center; padding:5px !important;"><%= regpedido.getEjemplares() %></td>
								<td style="text-align:center; padding:5px !important;"><%= regpedido.getNombreUsuario() %></td>
								<td style="text-align:center; padding:5px !important;">
									<input class="btn btn-warning btn-sm" type="submit" value="Procesar Pedido">
								</td>
							</form>
						</tr>
	            	<%}} %>
	            </tbody>
	        </table>
	        <br/>

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