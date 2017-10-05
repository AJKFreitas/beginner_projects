<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
        <title>${operacao} Projeto</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/bootstrap-datepicker.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <jsp:include page="includes/nav.jsp"/>

        <div id="main" class="container-fluid">
            <div class="row">
                <div class="form-group  col-xs-12 col-md-4 col-md-offset-4">
                    <h3 class="page-header">${operacao} Projeto ${projeto.getNome()} </h3>
                </div>
            </div>

            <form action="FrontController?action=Confirmar${operacao}Projeto" method="post">
                <div class="row">
                    <div class="form-group col-xs-6 col-md-1 col-md-offset-4">
                        <label for="txtCodProjeto">Código</label>
                        <input type="text" class="form-control" id="txtCodProjeto" name="txtCodProjeto" value="${projeto.getCodProjeto()}" 
                               maxlength="5" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                    </div>
                    <div class="form-group col-xs-12 col-md-3">
                        <label for="txtNomeProjeto">Nome</label>
                        <input type="text" class="form-control" name="txtNomeProjeto" value="${projeto.getNome()}" 
                           <c:if test="${operacao == 'Excluir' || operacao == 'Detalhar'}"> disabled</c:if>>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12 col-md-4 col-md-offset-4">
                        <label for="txtDescricaoProjeto">Descrição</label>
                        <textarea class="form-control" name="txtDescricaoProjeto" rows="5" id="descricao"
                        <c:if test="${operacao == 'Excluir' || operacao == 'Detalhar'}"> disabled</c:if>>${projeto.getDescricao()}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12 col-md-4 col-md-offset-4">
                        <label for="optCodDepartamento">Departamento</label>
                        <select class="form-control" name="optCodDepartamento" <c:if test="${operacao == 'Excluir' || operacao == 'Detalhar'}"> disabled</c:if>>
                            <option value="0" <c:if test="${projeto.getDepartamento().getCodDepartamento() == null}"> selected</c:if>>Selecione...</option>  
                            <c:forEach items="${departamentos}" var="departamento">
                                <option value="${departamento.getCodDepartamento()}" 
                                    <c:if test="${projeto.getDepartamento().getCodDepartamento() == departamento.getCodDepartamento()}"> selected</c:if>>
                                ${departamento.getNome()}</option>  
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12 col-md-2 col-md-offset-4">
                        <label for="txtClienteProjeto">Cliente</label>
                        <input type="text" class="form-control" name="txtClienteProjeto" value="${projeto.getCliente()}" 
                            <c:if test="${operacao == 'Excluir' || operacao == 'Detalhar'}"> disabled</c:if>>
                    </div>
                    <div class="form-group col-xs-12 col-md-2">
                        <label for="txtTelClienteProjeto">Tel Cliente</label>
                        <input type="text" class="form-control tel" name="txtTelClienteProjeto" placeholder="( )     -    " value="${projeto.getTelCliente()}" 
                           <c:if test="${operacao == 'Excluir' || operacao == 'Detalhar'}"> disabled</c:if>>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-6 col-md-2 col-md-offset-4">
                        <label for="txtDataInicio">Data Início</label>
                        <div class="input-group date" id="date">
                            <input type="text" id="txtDataInicio" class="form-control" name="txtDataInicio" placeholder="__/__/____" 
                                value="${projeto.getDataInicio()}" <c:if test="${operacao == 'Excluir' || operacao == 'Detalhar'}"> disabled</c:if> onkeypress="mascaraData(this)">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                        </div>
                    </div>
                    <div class="form-group col-xs-6 col-md-2">
                        <label for="txtDataTermino">Data Término</label>
                        <div class="input-group date">
                            <input type="text" id="txtDataTermino" class="form-control" name="txtDataTermino" placeholder="__/__/____" 
                                   value="${projeto.getDataTermino()}" <c:if test="${operacao == 'Excluir' || operacao == 'Detalhar'}"> disabled</c:if> onkeypress="mascaraData(this)">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12 col-md-4 col-md-offset-4">
                        <label for="optStatus">Status</label>
                        <select class="form-control" name="optStatus" <c:if test="${(operacao == 'Excluir')||(operacao == 'Detalhar')||(operacao == 'Incluir')}"> disabled</c:if>>
                            <option value="0" <c:if test="${projeto.status.getStatus() == null}"> selected</c:if>>Selecione...</option>  
                            <option value="Negociacao" <c:if test="${(projeto.status.getStatus() == 'Negociacao')||(operacao == 'Incluir')}"> selected</c:if>>Negociação</option>  
                            <option value="Desenvolvimento" <c:if test="${projeto.status.getStatus() == 'Desenvolvimento'}"> selected</c:if>>Desenvolvimento</option>
                            <option value="Entregue" <c:if test="${projeto.status.getStatus() == 'Entregue'}"> selected</c:if>>Entregue</option>
                            <option value="Cancelado" <c:if test="${projeto.status.getStatus() == 'Cancelado'}"> selected</c:if>>Cancelado</option>
                        </select>
                    </div>
                </div>
                <c:if test="${operacao != 'Incluir'}">
                <div class="row">
                    <div class="col-xs-12 col-md-4 col-md-offset-4">
                        <label for="">Funcionários Alocados</label>
                        <div class="row-fluid pull-right">
                            <a href="FrontController?action=PrepararIncluirFuncionarioProjeto&cod=${projeto.getCodProjeto()}" 
                               class="btn btn-success btn-xs <c:if test="${operacao != 'Editar'}">disabled</c:if>" title="Incluir Funcionário">
                                <span class="glyphicon glyphicon-plus"></span></a>
                        </div>
                        <div class="row-fluid">
                            <div class="table-responsive">
                                <table class="table">
                                    <tr>
                                        <th>Nome</th>
                                        <th>Função</th>
                                        <th>Ação</th>
                                    </tr>
                                <c:forEach items="${funcionariosProjeto}" var="funcionarioProjeto">
                                    <tr>
                                        <td><c:out value="${funcionarioProjeto.getFuncionario().getNome()}" /></td>
                                        <td><c:out value="${funcionarioProjeto.funcao.getFuncao()}" /></td>
                                        <td><a href="FrontController?action=PrepararExcluirFuncionarioProjeto&cod=${projeto.getCodProjeto()}&matricula=${funcionarioProjeto.getFuncionario().getMatricula()}"
                                                class="btn btn-danger btn-xs <c:if test="${operacao != 'Editar'}">disabled</c:if>" title="Excluir Funcionário">
                                                <span class="glyphicon glyphicon-remove"></span></a>
                                            <a href="FrontController?action=PrepararEditarFuncionarioProjeto&cod=${projeto.getCodProjeto()}&matricula=${funcionarioProjeto.getFuncionario().getMatricula()}" 
                                                class="btn btn-warning btn-xs <c:if test="${operacao != 'Editar'}">disabled</c:if>" >
                                                <span class="glyphicon glyphicon-edit"></span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                </c:if>
                <div class="row">
                    <div class="col-xs-12 col-md-4 col-md-offset-4">
                        <hr />
                        <c:if test="${operacao != 'Detalhar'}">
                            <button type="submit" class="btn btn-primary">Confirmar</button>
                        </c:if>
                        <a href="FrontController?action=ListarProjetos" class="btn btn-danger">Cancelar</a>
                    </div>
                </div>
            </form>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.maskedinput.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {
                $.mask.definitions['~'] = "[+-]";
                $(".tel").mask("(99) 9999-9999");
                $("#txtCPF").mask("999.999.999-99");
                $("#txtEndCEP").mask("99999-999");
            });
            
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