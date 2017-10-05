<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
        <title>${operacao} Funcionário no Projeto</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <jsp:include page="includes/nav.jsp"/>

        <div id="main" class="container-fluid">
            <div class="row">
                <div class="form-group  col-xs-12 col-md-12 col-md-offset-12">
                    <h3 class="page-header">${operacao} Funcionário no Projeto ${projeto.getNome()} </h3>
                </div>
            </div>

            <form action="FrontController?action=Confirmar${operacao}FuncionarioProjeto" method="post">
                <div class="row">
                    <div class="form-group col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-2">
                        <label for="txtNomeProjeto">Projeto</label>
                        <input type="text" class="form-control" name="txtNomeProjeto" id="txtNomeProjeto" value="${projeto.getNome()}" readonly >
                        <input type="hidden" name="txtCodProjeto" value="${projeto.getCodProjeto()}">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12  col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-2">
                        <label for="optFuncionario">Funcionário</label>
                        <select class="form-control" name="optFuncionario" id="optFuncionario" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                            <option value="0" <c:if test="${funcionarioProjeto.getFuncionario().getMatricula() == null}"> selected</c:if>>Selecione...</option>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.getMatricula()}" 
                                    <c:if test="${funcionario.getMatricula() == funcionarioProjeto.getFuncionario().getMatricula()}"> selected</c:if>>
                                    ${funcionario.getNome()}
                                </option>  
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-6 col-sm-4 col-md-2 col-sm-offset-2 col-md-offset-2">
                        <label for="optFuncao">Função</label>
                        <select class="form-control" name="optFuncao" id="optFuncao" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <option value="null" <c:if test="${funcionarioProjeto.funcao.getFuncao() == null}"> selected</c:if>>Selecione...</option>  
                            <option value="Diretor" <c:if test="${funcionarioProjeto.funcao.getFuncao() =='Diretor'}"> selected</c:if>>Diretor</option>  
                            <option value="Gerente" <c:if test="${funcionarioProjeto.funcao.getFuncao() =='Gerente'}"> selected</c:if>>Gerente</option>  
                            <option value="Analista" <c:if test="${funcionarioProjeto.funcao.getFuncao() =='Analista'}"> selected</c:if>>Analista</option>  
                        </select>
                    </div>
                    <div class="form-group col-xs-6 col-sm-4 col-md-2">
                        <label for="txtCargaHoraria">Carga Horária</label>
                        <div class="input-group">
                            <input type="text" id="txtCargaHoraria" class="form-control" name="txtCargaHoraria" placeholder="x horas disponiveis" 
                                   value="${funcionarioProjeto.getCargaHoraria()}" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-6 col-sm-4 col-md-2 col-sm-offset-2 col-md-offset-4">
                        <label for="txtDataInicio">Data Início</label>
                        <div class="input-group date" id="date">
                            <input type="text" id="txtDataInicio" class="form-control" name="txtDataInicio" value="${funcionarioProjeto.getDataInicio()}" 
                               <c:if test="${operacao == 'Excluir'}"> disabled</c:if> onkeypress="mascaraData(this)">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group col-xs-6 col-sm-4 col-md-2">
                        <label for="txtDataTermino">Data Término</label>
                        <div class="input-group date">
                            <input type="text" id="txtDataTermino" class="form-control" name="txtDataTermino" value="${funcionarioProjeto.getDataTermino()}"
                               <c:if test="${operacao == 'Excluir'}"> disabled</c:if> onkeypress="mascaraData(this)">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12  col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                        <label for="txtObservacao">Observação</label>
                        <textarea class="form-control" name="txtObservacao" rows="3" id="txtObservacao"
                        <c:if test="${operacao == 'Excluir' || logado.getFuncao().getFuncao() != 'Gerente'}"> readonly</c:if>>${funcionarioProjeto.getObservacao()}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12  col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                        <hr />
                        <button type="submit" class="btn btn-primary">Confirmar</button>
                        <a href="FrontController?action=PrepararEditarProjeto&codProjeto=${projeto.codProjeto}" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div>
        <jsp:include page="/includes/footer.jsp"/>
        <script src="js/bootstrap-datepicker.js"></script>
        <script>
            $(document).ready(function () {
                $('#txtDataTermino').datepicker({
                    format: "dd/mm/yyyy",
                    language: "br",
                    autoclose: true,
                    todayHighlight: true,
                    todayBtn: true,
                    orientation: "bottom"
                });
                $('#txtDataInicio').datepicker({
                    format: "dd/mm/yyyy",
                    language: "br",
                    autoclose: true,
                    todayHighlight: true,
                    todayBtn: true,
                    orientation: "bottom"
                });
            });
        </script>
    </body>
</html>