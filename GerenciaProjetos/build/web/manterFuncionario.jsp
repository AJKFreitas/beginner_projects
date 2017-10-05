<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
        <title>${operacao} Funcionário</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <jsp:include page="/includes/nav.jsp"/>

        <div id="main" class="container">
            <div class="row">
                <div class="form-group col-xs-12 col-sm-8 col-md-10 ">
                    <h3 class="page-header">${operacao} Funcionário ${funcionario.getNome()} </h3>
                </div>
            </div>
            <form action="FrontController?action=Confirmar${operacao}Funcionario" method="post">
                <div class="form-group">
                    <div class="form-group col-xs-2 col-sm-2 col-md-3 ">
                        <label for="txtMatricula">Matrícula</label>
                        <input type="text" class="form-control" name="txtMatricula" id="txtMatricula" maxlength="5" value="${funcionario.getMatricula()}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-2 col-sm-2 col-md-3 ">
                            <label for="txtNome">Nome Completo</label>
                            <input type="text" class="form-control" name="txtNome" value="${funcionario.getNome()}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-2 col-sm-2 col-md-3 ">
                            <label for="txtCPF">CPF</label>
                            <input type="text" class="form-control" name="txtCPF" id="txtCPF" value="${funcionario.getCpf()}" placeholder="000.000.000-00"
                               <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-2 col-sm-2 col-md-3 ">
                            <label for="txtTelefone">Telefone</label>
                            <table class="dynamic_field" id="dynamic_field">  
                                <tr>  
                                <c:if test="${funcionario.getTelefones().size()>0}">
                                    <c:forEach items="${funcionario.getTelefones()}" var="telefone">
                                    <input type="text" id="tel" name="tel0" placeholder="( )     -    " class="form-control" 
                                           value="${telefone.getNumTelefone()}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>/>
                                </c:forEach>
                            </c:if>
                            <c:if test="${funcionario.getTelefones().size()==0 || funcionario.getTelefones().size()==null}">
                                <td>
                                    <input type="text" id="tel" name="tel0" placeholder="( )     -    " class="form-control" 
                                           <c:if test="${operacao == 'Excluir'}"> readonly</c:if>/>
                                    </td> 
                            </c:if>
                            <td>
                                <button type="button" name="add" id="add" class="btn btn-success btn-sm" 
                                        <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        </button>
                                </td>  
                                </tr>  
                            </table>  
                        </div>
                        <div class="form-group col-xs-2 col-sm-2 col-md-3 ">
                            <label for="txtEndLogradouro">Logradouro</label>
                            <input type="text" class="form-control" name="txtEndLogradouro" id="txtEndLogradouro" value="${funcionario.endereco.getLogradouro()}"
                               <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-2 col-sm-2 col-md-1">
                            <label for="txtEndNum">Número</label>
                            <input type="text" class="form-control" name="txtEndNum" maxlength="5" id="txtEndNum" value="${funcionario.endereco.getNumero()}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-2 col-sm-2 col-md-3">
                            <label for="txtEndBairro">Bairro</label>
                            <input type="text" class="form-control" name="txtEndBairro" id="txtEndBairro" value="${funcionario.endereco.getBairro()}"
                               <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-2 col-sm-2 col-md-3">
                            <label for="txtEndCidade">Cidade</label>
                            <input type="text" class="form-control" name="txtEndCidade" id="txtEndCidade" value="${funcionario.endereco.getCidade()}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-4 col-sm-2 col-md-2">
                            <label for="txtEndUF">UF</label>
                            <input type="text" class="form-control" name="txtEndUF" maxlength="2" id="txtEndUF" value="${funcionario.endereco.getUf()}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                        <div class="form-group col-xs-2 col-sm-6 col-md-2">
                            <label for="txtEndCEP">CEP</label>
                            <input type="text" class="form-control" name="txtEndCEP" id="txtEndCEP" value="${funcionario.endereco.getCep()}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        </div>
                    </div>
                    <div class="form-group col-xs-2 col-sm-4 col-md-2 ">
                        <label for="optFormAcad">Formação Acadêmica</label>
                        <select class="form-control" name="optFormAcad" id="optFormAcad" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        <option value="null" <c:if test="${funcionario.getFormAcademica() == null}"> selected</c:if>>Selecione...</option>  
                        <option value="Fundamental" <c:if test="${funcionario.getFormAcademica() =='Fundamental'}"> selected</c:if>>Fundamental</option>  
                        <option value="Médio" <c:if test="${funcionario.getFormAcademica() =='Médio'}"> selected</c:if>>Médio</option>  
                        <option value="Técnico" <c:if test="${funcionario.getFormAcademica() =='Técnico'}"> selected</c:if>>Técnico</option>  
                        <option value="Superior" <c:if test="${funcionario.getFormAcademica() =='Superior'}"> selected</c:if>>Superior</option>
                        </select>
                    </div>
                    <div class="form-group col-xs-12 col-sm-4 col-md-2 ">
                        <label for="optArea">Área de Atuação</label>
                        <select class="form-control" name="optArea" id="optArea" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        <option value="null" <c:if test="${funcionario.getAreaAtuacao() == null}"> selected</c:if>>Selecione...</option>  
                        <option value="RH" <c:if test="${funcionario.getAreaAtuacao() =='RH'}"> selected</c:if>>RH</option>  
                        <option value="Finanças" <c:if test="${funcionario.getAreaAtuacao() =='Finanças'}"> selected</c:if>>Finanças</option>  
                        <option value="TI" <c:if test="${funcionario.getAreaAtuacao() =='TI'}"> selected</c:if>>TI</option>  
                        </select>
                    </div>

                    <div class="form-group col-xs-12 col-sm-4 col-md-2">
                        <label for="codDepartamento">Departamento</label>
                        <select class="form-control" name="optCodDepartamento" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                        <option value="0" <c:if test="${funcionario.getDepartamento().getCodDepartamento() == null}"> selected</c:if>>Selecione...</option>  
                        <c:forEach items="${departamentos}" var="departamento">
                            <option value="${departamento.getCodDepartamento()}" <c:if test="${funcionario.getDepartamento().getCodDepartamento() == departamento.getCodDepartamento()}"> selected</c:if>>${departamento.getNome()}</option>  
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-xs-12 col-sm-4 col-md-3 ">
                    <label for="optFuncao">Função</label>
                    <select class="form-control" name="optFuncao" id="optFuncao" <c:if test="${operacao == 'Excluir' || logado == null}"> readonly</c:if>>
                        <option value="null" <c:if test="${funcionario.funcao.getFuncao() == null}"> selected</c:if>>Selecione...</option>  
                        <option value="Diretor" <c:if test="${funcionario.funcao.getFuncao() =='Diretor'}"> selected</c:if>>Diretor</option>  
                        <option value="Gerente" <c:if test="${funcionario.funcao.getFuncao() =='Gerente'}"> selected</c:if>>Gerente</option>  
                        <option value="Analista" <c:if test="${funcionario.funcao.getFuncao() =='Analista'}"> selected</c:if>>Analista</option>  
                        </select>
                    </div>
                    <div class="form-group col-xs-12  col-sm-4 col-md-2 ">
                        <label for="login">Login</label>
                        <input type="text" class="form-control" placeholder="nome.sobrenome" name="txtLogin" value="${funcionario.getLogin()}" 
                           <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                    </div>
                <c:if test="${operacao == 'Incluir'}">
                    <div class="form-group col-xs-12 col-sm-4 col-md-2 ">
                        <label for="senha">Senha</label>
                        <div class="input-group">
                            <input type="password" class="form-control" placeholder="******" name="txtSenha"
                                   <c:if test="${operacao == 'Incluir'}"> required</c:if>/>
                            </div>
                        </div>
                </c:if>
        </div>

        <div class="form-group">
            <div class=" col-xs-12 col-sm-8 col-md-4">
                <hr />
                <button type="submit" class="btn btn-primary">Confirmar</button>
                <c:if test="${logado!=null}">
                    <a href="FrontController?action=ListarFuncionarios" class="btn btn-danger">Cancelar</a>
                </c:if>
                <c:if test="${logado==null}">
                    <a href="FrontController?action=Login" class="btn btn-danger">Cancelar</a>
                </c:if>
            </div>
        </div>

    </form>
</div>


<jsp:include page="includes/footer.jsp"/>
<script src="js/jquery.maskedinput.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $.mask.definitions['~'] = "[+-]";
        $("#tel").mask("(99) 9999-9999");
        $("#txtCPF").mask("999.999.999-99");
        $("#txtEndCEP").mask("99999-999");
    });

    $(document).ready(function () {
        var i = 1;
        $('#add').click(function () {
            if (i < 3) {
                $('#dynamic_field').append(
                        '<tr id="row' + i + '">\n\
                        <td>\n\
                            <input type="text" name="tel' + i + '" placeholder="( )     -    " class="form-control" \n\/></td>\n\
                            <td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove btn-sm">\n\
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>\n\
                                </button>\n\
                        </td>\n\
                    </tr>');
                i++;
            }
        });
        $(document).on('click', '.btn_remove', function () {
            var button_id = $(this).attr("id");
            $('#row' + button_id + '').remove();
            i--;
        });
    });
</script>  
</body>
</html>