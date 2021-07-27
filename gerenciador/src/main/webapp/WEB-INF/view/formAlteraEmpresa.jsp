<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<c:url value="/entrada?acao=AlteraEmpresa" var="linkServletAlteraEmpresa"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alteração de Cadastro de Empresa</title>
</head>
<body>
<c:import url="logout-parcial.jsp"/>

	<form action = "${linkServletAlteraEmpresa}" method ="post">
	
		Nome: <input type="text" name="nome" value="${empresa.nome}"/>
		Data Abertura: <input type="text" name="data" value = "<fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/>"/>
		<input type="hidden" name="id" value="${empresa.id}" />
		<input type="submit" />
	
	</form>

</body>
</html>