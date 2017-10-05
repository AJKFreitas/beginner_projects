<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="col-md-10 visible-md visible-lg">
                <ul class="list-inline">
                    <li><a accesskey="1" href="#main" class="navbar-link">Ir para o conteúdo [1]</a></li>
                    <li><a accesskey="2" href="#navbar" class="navbar-link">Ir para o menu [2]</a></li>
                    <li><a accesskey="3" href="#footer" class="navbar-link">Ir para o rodapé [3]</a></li>
                </ul>						
            </div>
            <div class="col-sm-12 col-md-2">				
                <ul class="text-center">
                    <li><a accesskey="4" href="acessibilidade.jsp" class="navbar-link">Acessibilidade [4]</a></li>
                </ul>
            </div>
        </div>
        <div class="row-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" accesskey="h" href="FrontController?action=Home"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>&nbsp;  Ger&ecirc;ncia de Projetos</a>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${logado!=null}">
                        <li class="dropdown">
                            <a accesskey="p" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp; Projetos <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="FrontController?action=ListarProjetos"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>&nbsp; Listar</a></li>
                                <li><a href="FrontController?action=PrepararIncluirProjeto"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp; Incluir</a></li>
                            </ul>
                        </li>
                        <li>
                            <a accesskey="f" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp; Funcion&aacute;rios <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="FrontController?action=ListarFuncionarios"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>&nbsp; Listar</a></li>
                                <li><a href="FrontController?action=PrepararIncluirFuncionario"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp; Incluir</a></li>
                            </ul>
                        </li>
                        <li>
                            <a accesskey="d" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp; Departamentos <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="FrontController?action=ListarDepartamentos"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>&nbsp; Listar</a></li>
                                <li><a href="FrontController?action=PrepararIncluirDepartamento"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp; Incluir</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>&nbsp; <strong>${logado.getLogin()}</strong></a>
                            <ul class="dropdown-menu">
                                <li><a href="FrontController?action=Logout" accesskey="q"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp; Sair</a></li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${logado==null}">
                        <li>
                            <a href="FrontController?action=Login"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>&nbsp; Fazer Login</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</nav>
<div class="row visible-xs"><div class="col-xs-12"><br><br></div></div>