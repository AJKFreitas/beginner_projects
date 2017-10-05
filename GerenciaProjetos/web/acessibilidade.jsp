<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
        <title>Acessibilidade</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <jsp:include page="/includes/nav.jsp"/>

        <div id="main" class="container-fluid" style="margin-top: 50px">
            <div id="top" class="row">
                <div class="col-xs-12 col-md-8 col-md-offset-2">
                    <h1>Acessibilidade</h1>
                </div>
            </div> <!-- /#top -->
            <hr />
            <div id="list" class="row">
                <div class="col-xs-12 col-md-8 col-md-offset-2">
                    <p class="text-muted lead">Este portal segue as diretrizes do e-MAG (Modelo de Acessibilidade em Governo Eletr�nico), conforme as normas do Governo Federal, em obedi�ncia ao Decreto 5.296, de 2.12.2004</p>

                    <p>O termo acessibilidade significa incluir a pessoa com defici�ncia na participa��o de atividades como o uso de produtos, servi�os e informa��es. Alguns exemplos s�o os pr�dios com rampas de acesso para cadeira de rodas e banheiros adaptados para deficientes.</p>
                    <p>Na internet, acessibilidade refere-se principalmente �s recomenda��es do WCAG (World Content Accessibility Guide) do W3C e no caso do Governo Brasileiro ao e-MAG (Modelo de Acessibilidade em Governo Eletr�nico). O e-MAG est� alinhado as recomenda��es internacionais, mas estabelece padr�es de comportamento acess�vel para sites governamentais.</p>
                    <p>Na parte superior do portal existe uma barra de acessibilidade onde se encontra atalhos de navega��o padronizados. Essas ferramentas est�o dispon�veis em todas as p�ginas do portal.</p>
                    <p>Os atalhos aqui utilizados s�o:</p>

                    <ul>
                        <li>Teclando-se <kbd><kbd>Alt</kbd> + <kbd>1</kbd></kbd> em qualquer p�gina do portal, chega-se diretamente ao come�o do conte�do principal da p�gina.</li>
                        <li>Teclando-se <kbd><kbd>Alt</kbd> + <kbd>2</kbd></kbd> em qualquer p�gina do portal, chega-se diretamente ao in�cio do menu principal.</li>
                        <li>Teclando-se <kbd><kbd>Alt</kbd> + <kbd>3</kbd></kbd> em qualquer p�gina do portal, chega-se diretamente ao rodap� do site.</li>
                        <li>Teclando-se <kbd><kbd>Alt</kbd> + <kbd>4</kbd></kbd> em qualquer p�gina do portal, chega-se diretamente a esta p�gina de acessibilidade.</li>
                    </ul>

                    <p>Esses atalhos valem para o navegador Chrome, mas existem algumas varia��es para outros navegadores.</p>
                    <p>Quem prefere utilizar o Internet Explorer � preciso apertar o bot�o Enter do seu teclado ap�s uma das combina��es acima. Portanto, para chegar ao rodap� do site � preciso pressionar <kbd><kbd>Alt</kbd> + <kbd>3</kbd></kbd> e depois <kbd>Enter</kbd>.</p>
                    <p>No caso do Firefox, em vez de <kbd><kbd>Alt</kbd> + <kbd>N�mero</kbd></kbd>, tecle simultaneamente <kbd><kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>N�mero</kbd></kbd>.</p>
                    <p>Sendo Firefox no Mac OS, em vez de <kbd><kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>N�mero</kbd></kbd>, tecle simultaneamente <kbd><kbd>Ctrl</kbd> + <kbd>Alt</kbd> + <kbd>N�mero</kbd></kbd>.</p>
                    <p>No Opera, as teclas s�o <kbd><kbd>Shift</kbd> + <kbd>Escape</kbd> + <kbd>N�mero</kbd></kbd>. Ao teclar apenas <kbd><kbd>Shift</kbd> + <kbd>Escape</kbd></kbd>, o usu�rio encontrar� uma janela com todas as alternativas de ACCESSKEY da p�gina.</p>
                    <p>Ao final desse texto, voc� poder� baixar alguns arquivos que explicam melhor o termo acessibilidade e como deve ser implementado nos sites da Internet.</p>

                    <h2>Leis e decretos sobre acessibilidade:</h2>

                    <ul>
                        <li><a href="http://www.planalto.gov.br/ccivil_03/_Ato2004-2006/2004/Decreto/D5296.htm" target="_blank" title="Decreto n� 5.296 de 02 de dezembro de 2004 ">Decreto n� 5.296 de 02 de dezembro de 2004 </a>(link externo)</li>
                        <li><a href="http://www.planalto.gov.br/ccivil_03/_ato2007-2010/2009/decreto/d6949.htm" target="_blank" title="Decreto n� 6.949, de 25 de agosto de 2009">Decreto n� 6.949, de 25 de agosto de 2009</a> (link externo) - Promulga a Conven��o Internacional sobre os Direitos das Pessoas com Defici�ncia e seu Protocolo Facultativo, assinados em Nova York, em 30 de mar�o de 2007 </li>
                        <li><a href="http://www.planalto.gov.br/ccivil_03/_ato2011-2014/2012/Decreto/D7724.htm" target="_blank" title="Decreto n� 7.724, de 16 de Maio de 2012">Decreto n� 7.724, de 16 de Maio de 2012</a> (link externo) - Regulamenta a Lei No 12.527, que disp�e sobre o acesso a informa��es.</li>
                        <li><a href="http://www.governoeletronico.gov.br/acoes-e-projetos/e-MAG" target="_blank" title="Modelo de Acessibilidade de Governo Eletr�nico">Modelo de Acessibilidade de Governo Eletr�nico</a> (link externo) </li>
                        <li><a href="http://www.governoeletronico.gov.br/biblioteca/arquivos/portaria-no-03-de-07-05-2007" target="_blank" title="Portaria n� 03, de 07 de Maio de 2007">Portaria n� 03, de 07 de Maio de 2007 - formato .pdf (35,5Kb)</a> (link externo) - Institucionaliza o Modelo de Acessibilidade em Governo Eletr�nico ? e-MAG </li>
                    </ul>
                    <h2>Dicas, links e recursos �teis: </h2>
                    <ul>
                        <li><a href="http://acessibilidadelegal.com/" target="_blank" title="Acessibilidade Legal">Acessibilidade Legal</a> (link externo)</li>
                        <li><a href="http://acessodigital.net/links.html" target="_blank" title="Acesso Digital">Acesso Digital</a> (link externo)</li>
                    </ul>
                </div>
            </div> 
        </div>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>