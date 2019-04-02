<%-- Página validación de usuarios --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
        <title>Libros MVC</title>
	</head>
	<body>
		<div class="fondo-form"> 
			<form class="form-signin" method="get" action="login">
				<div class="text-center mb-4 logotipo">
				<img src="images/img_login.png" alt="Imagen Libro MVC" class="logo-login">
					<h1 class="h5 font-weight-normal titulo-tienda">Acceso Usuarios Registrados</h1>
					<div class="form-field-login">
						<input type="text" name="usuario" class="form-control form-control-sm" type="text" placeholder="Nombre de Usuario"/>
						<input type="password" name="password" class="form-control form-control-sm" type="text" placeholder="Clave de Acceso"/>
					
						<div>
							<hr>
							<input class="btn btn-sm btn-primary btn-block" type="submit" name="Acceder" value="VAlidar Datos de Usuario"/>
							<input class="btn btn-sm btn-primary btn-block" type="reset" name="Cancelar" value="Reiniciar Formulario"/>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>