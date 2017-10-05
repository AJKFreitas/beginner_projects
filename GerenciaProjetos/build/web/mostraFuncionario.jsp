<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
        <title>Funcionários</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/nav.jsp"/>

        <div id="main" class="container-fluid" style="margin-top: 50px">
            <div id="top" class="row">
                <div class="col-xs-3 col-xs-offset-2">
                    <h2>Funcionários</h2>
                </div>
            </div> <!-- /#top -->
            <hr />
            <div id="list" class="row">
                <div class="table-responsive col-xs-12 col-md-8 col-md-offset-2">
                    <table class="table table-striped" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th>Matrícula</th>
                                <th>Nome</th>
                                <th>Função</th>
                                <th>Departamento</th>
                                    <c:if test="${logado.funcao.getFuncao()!='Analista'}">
                                    <th class="actions" colspan="2">Ações</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <tr>
                                    <td><c:out value="${funcionario.getMatricula()}" /></td>
                                    <td><c:out value="${funcionario.getNome()}" /></td>
                                    <td><c:out value="${funcionario.getFuncao().getFuncao()}" /></td>
                                    <td><c:out value="${funcionario.getDepartamento().getNome()}" /></td>
                                    <c:if test="${logado.funcao.getFuncao()!='Analista'}">
                                        <td><a class="btn btn-warning btn-xs" href="FrontController?action=PrepararEditarFuncionario&matricula=${funcionario.matricula}">Editar</a></td>
                                        <td><a class="btn btn-danger btn-xs" href="FrontController?action=PrepararExcluirFuncionario&matricula=${funcionario.matricula}">Excluir</a></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div> <!-- /#list -->

            <div id="bottom" class="row">
                <div class="col-xs-12 col-md-8 col-md-offset-2">
                    <ul class="pagination">
                        <li class="disabled"><a>&lt; Anterior</a></li>
                        <li class="disabled"><a>1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li class="next"><a href="#" rel="next">Próximo &gt;</a></li>
                    </ul><!-- /.pagination -->
                </div>
            </div> <!-- /#bottom -->
        </div> <!-- /#main -->
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>