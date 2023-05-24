
<%@page import="java.util.List"%>
<%@page import="com.mycompany.modelo.Aviso"%>
<%
    List<Aviso> avisos = (List<Aviso>)request.getAttribute("avisos");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <table border="1">
            <tr>
                <th>SEGUNDO PARCIAL TEM-742 <br>
                    Nombre: SARA TICONIPA ARUQUIPA<br>
                    CARNET: 7016462 LP 
                </th>
            </tr>
        </table>
    
        <h1>GESTION DE PRODUCTOS</h1>
        </center>
    <p><a href="Inicio?action=add">NUEVO</a> </p>
    <table border ="1">
        <tr>
            <th>ID</th>
            <th>DESCRIPCION</th>
            <th>CANTIDAD</th>
            <th>PRECIO</th>
            <th>CATEGORIA</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="item" items="${productos}">
            <tr>
                <td>${item.id}</td> 
                <td>${item.descripcion}</td>
                <td>${item.cantidad}</td>
                <td>${item.precio}</td>
                <td>${item.categoria}</td>
                <td><a href= "inicio?action=edit&id=${item.id}">Editar</a></td>
                <td><a href="inicio?action=delete&id=${item.id}"onclick="return(confirm('Esta seguro(a)'))">Eliminar</a></td>
            </tr> 
        </c:forEach>
    </table>
    </body>
</html>
