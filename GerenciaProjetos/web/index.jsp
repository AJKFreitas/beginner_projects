<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
        <title>Login Form</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="login">
            <c:if test="${msgFalha!=null}">
                <div class="alert alert-danger alert-dismissible text-center" role="alert">
                    ${msgFalha}
                </div>
            </c:if>
            <h1>Login</h1>
            <form method="post" action="FrontController?action=AutenticaFuncionario">
                <input type="text" name="login" placeholder="Nome do usuário"
                       required="required" /> 
                <input type="password" name="senha"
                       placeholder="Senha" required="required" />
                <input type="submit" class="btn btn-primary btn-block btn-large" value="Entrar"/>
            </form>
            <a href="FrontController?action=PrepararIncluirFuncionario" class="btn btn-link btn-block">Cadastre-se</a>
        </div>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
