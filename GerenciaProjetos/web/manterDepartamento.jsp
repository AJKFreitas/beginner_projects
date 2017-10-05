<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
        <title>${operacao} Departamento</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <jsp:include page="/includes/nav.jsp"/>

        <div id="main" class="container-fluid">
            <div class="row">
                <div class="form-group col-xs-12 col-md-4 col-md-offset-4">
                    <h3 class="page-header">${operacao} Departamento ${departamento.nome} </h3>
                </div>
            </div>

            <form action="FrontController?action=Confirmar${operacao}Departamento" method="post">
                <div class="row">
                    <div class="form-group col-xs-4 col-md-1 col-md-offset-4">
                        <label for="codDepartamento">Código</label>
                        <input type="text" class="form-control" name="txtCodDepartamento" value="${departamento.codDepartamento}" required 
                               <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-8 col-md-3">
                            <label for="nomeDepartamento">Nome</label>
                            <input type="text" class="form-control" name="txtNomeDepartamento" value="${departamento.nome}" required 
                               <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-12 col-md-4 col-md-offset-4">
                            <label for="tel">Telefone</label>
                            <input type="text" class="form-control" id="tel" name="txtTelefoneDepartamento" value="${departamento.telefone}" 
                               <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4 col-md-offset-4">
                            <hr />
                            <button type="submit" class="btn btn-primary">Confirmar</button>
                            <a href="FrontController?action=ListarDepartamentos" class="btn btn-danger">Cancelar</a>
                        </div>
                    </div>

                </form>
            </div>
        <jsp:include page="/includes/footer.jsp"/>
        <script src="js/jquery.maskedinput.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {
                $.mask.definitions['~'] = "[+-]";
                $("#tel").mask("(99) 9999-9999");
            });
        </script>
    </body>
</html>