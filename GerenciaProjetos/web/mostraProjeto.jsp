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
        <title>Projetos</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/includes/nav.jsp"/>

        <div id="main" class="container-fluid" style="margin-top: 50px">
            <div id="top" class="row">
                <div class="col-xs-3 col-xs-offset-2">
                    <h2>Projetos</h2>
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
                                <th>Data Início</th>
                                <th>Prazo</th>
                                <th>Status</th>
                                <th class="actions" colspan="3">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${projetos}" var="projeto">
                                <tr>
                                    <td><c:out value="${projeto.codProjeto}" /></td>
                                    <td><c:out value="${projeto.nome}" /></td>
                                    <td><c:out value="${projeto.dataInicio}" /></td>
                                    <td><c:out value="${projeto.status.calculaPrazo()}" /></td>
                                    <td><c:out value="${projeto.status.getStatus()}" /></td>
                                    <td><a class="btn btn-info btn-xs" href="FrontController?action=DetalharProjeto&codProjeto=${projeto.codProjeto}">Detalhar</a></td>
                                    <c:if test="${logado.funcao.getFuncao()!='Analista'}">
                                        <td><a class="btn btn-warning btn-xs" href="FrontController?action=PrepararEditarProjeto&codProjeto=${projeto.codProjeto}">Editar</a></td>
                                        <td><a class="btn btn-danger btn-xs" href="FrontController?action=PrepararExcluirProjeto&codProjeto=${projeto.codProjeto}">Excluir</a></td>
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