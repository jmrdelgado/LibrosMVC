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
		<h2>Autenticación de Usuarios</h2>
		<form method="get" action="login">
			<table>
				<tr>
					<td>Introduce tu nombre de usuario:</td>
					<td><input type="text" name="usuario"/></td>
				</tr>
				<tr>
					<td>Introduce tu clave:</td>
					<td><input type="password" name="password"/></td>
				</tr>
			</table>
			<br/>
			<input type="submit" name="Acceder"/>
			<input type="reset" name="Cancelar"/>
		</form>
	</body>
</html>