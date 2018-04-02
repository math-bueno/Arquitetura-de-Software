<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Chamados</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="Menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
        <h3 class="page-header">${fila.nome }</h3>
        <c:if test="${not empty chamados }">
	        <table class="table">
	        	<thead>
	        		<tr>
	        			<th>Número</th>
	        			<th>Descrição</th>
	        			<th>Abertura</th>
	        			<th>Fechamento</th>
	        			<th>Status</th>
	        			<th>Tempo</th>
	        		</tr>
	        	</thead>
	        	<tbody>
	        		<c:forEach items="${chamados }" var="c">
	        			<tr>
	        				<td>${c.id }</td>
	        				<td>${c.descricao }</td>
	        				<td><fmt:formatDate value="${c.dataAbertura }" pattern="dd/MM/yyyy"/></td>
	        				<td><fmt:formatDate value="${c.dataFechamento }" pattern="dd/MM/yyyy"/></td>
	        				<td>${c.status }</td>
	        				<jsp:useBean id="now" class="java.util.Date"/>  
	        				<td><fmt:formatNumber value="${not empty c.dataFechamento? (c.dataFechamento.time - c.dataAbertura.time)/86400000: (now.time - c.dataAbertura.time)/86400000}" maxFractionDigits="0"/></td>
	        			</tr>
	        		</c:forEach>
	        	</tbody>	
	        </table>
        </c:if>
        <c:if test="${empty chamados }">
        	<h4>Nenhum chamado encontrado</h4>
        </c:if>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>