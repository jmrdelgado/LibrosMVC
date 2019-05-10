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
    		<%@include file = "header.jsp"%>
			
			<!--  Panel de navegación -->			
			<ul class="nav nav-justified">
			    <li class="nav-item dropdown"><a href="#datepicker.html" class="nav-link" data-toggle="dropdown">Libros</a></li>
			    <li class="nav-item dropdown"><a href="#datepicker.html" class="nav-link" data-toggle="dropdown">Autores</a></li>
			    <li class="nav-item dropdown"><a href="#datepicker.html" class="nav-link" data-toggle="dropdown">Consultar Pedidos</a></li>
			    <li class="nav-item dropdown"><a href="#datepicker.html" class="nav-link" data-toggle="dropdown">Editoriales</a></li>
			</ul>
			
    		<br>
	        <h3 class="h3 titulo-subrayado">PANEL <strong>DASHBOARD</strong></h3>
			
			<!-- Bienvenida -->	
			<div class="left" style="margin: 0 0 0 0px; padding: 0px 0px 10px 0px;">
				<div style="width:800px; margin:0 auto; background-color: #FFF;padding:20px;20px;border-radius:5px;">
					<div>
						<p style="padding-top: 10px; padding-bottom: 13px; font-size: 26px; color:#ff6000;">Bienvenidos/@s ...</p>
						<div>
							<div id="bienvenida" style="min-height:100%;">
								<div class="imgpresentacion">
									<img src="images/libros_infantiles.jpg" style="height:185px; margin-right: 15px;">
									<img src="images/sagas.jpg" height="185px">
								</div>
								<br>
									<div>
										<p style="font-size: 20px; font-weight:bold;">Sumérgete en el universo de los libros</p>
										<p style="text-align:justify">Déjate envolver por el maravilloso mundo de la lectura. Gracias a la amplia selección que encontrarás en nuestra sección de libros nutrirás tu mente y disfrutarás de los mejores momentos de desconexión de la rutina con las historias más apasionantes. Sea cual sea tu gusto, te aseguramos que nosotros tenemos el libro perfecto para ti.</p></div>
								<br>
								   <div>
								   <p style="font-size: 20px; font-weight:bold;">Disfruta de tus libros favorítos.</p>
								   <p style="text-align:justify">Gran responsabilidad de que un libro te apasione es su autor. Aquí podrás encontrar libros de diferente temática que te inspirarán; muchos de ellos firmados por una gran variedad de afamados autores tanto nacionales como internacionales. Escoge entre perfectos ensayos así como libros de autoayuda con los que sentirás inspiración para llegar muy lejos, <strong>¡No te los pierdas!</strong></p></div>
							</div>
				    	</div>
					</div>			
				</div>
			</div>
			<!-- End Bienvenida -->
			
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