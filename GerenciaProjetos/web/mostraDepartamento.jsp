<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
        <title>Departamentos</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>


        <jsp:include page="/includes/nav.jsp"/>

        <div id="main" class="container-fluid" style="margin-top: 50px">
            <div id="top" class="row">
                <div class="col-xs-12 col-md-8 col-md-offset-2">
                    <h2>Departamentos</h2>
                    <c:if test="${totalNotificacoes>0}">
                        <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                            Notificacões <span class="badge">${totalNotificacoes}</span>
                        </a>
                        <div class="collapse" id="collapseExample">
                            <c:forEach items="${notificacoes}" var="notificacao">
                                <c:if test="${notificacao.lido==0}">
                                    <div class="alert alert-warning alert-dismissible" role="alert">
                                        <a href="FrontController?action=ConfirmarNotificacao&idProjetoObservado=${notificacao.idProjetoObservado}" 
                                           class="close" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </a>
                                        ${notificacao.notificacao}
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div> <!-- /#top -->
            <hr />
            <div id="list" class="row">
                <div class="table-responsive col-xs-12 col-md-8 col-md-offset-2">
                    <table class="table table-striped" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Telefone</th>
                                    <c:if test="${logado.funcao.getFuncao()!='Analista'}">
                                    <th class="actions" colspan="2">Ações</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${departamentos}" var="departamento">
                                <tr>
                                    <td><c:out value="${departamento.codDepartamento}" /></td>
                                    <td><c:out value="${departamento.nome}" /></td>
                                    <td><c:out value="${departamento.telefone}" /></td>
                                    <c:if test="${logado.funcao.getFuncao()!='Analista'}">
                                        <td><a class="btn btn-warning btn-xs" href="FrontController?action=PrepararEditarDepartamento&codDepartamento=${departamento.codDepartamento}">Editar</a></td>
                                        <td><a class="btn btn-danger btn-xs" href="FrontController?action=PrepararExcluirDepartamento&codDepartamento=${departamento.codDepartamento}">Excluir</a></td>
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