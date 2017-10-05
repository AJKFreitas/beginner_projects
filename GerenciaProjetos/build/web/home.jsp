<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="shortcut icon" type="image/x-icon" href="includes/favicon.ico">
 <title>Gerência de Projetos</title>

 <link href="css/bootstrap.min.css" rel="stylesheet">
 <link href="css/style.css" rel="stylesheet">
</head>
<body>

 <jsp:include page="/includes/nav.jsp"/>

 <div id="main" class="container-fluid">
  
    <div class="row text-center ">
        <div class="col-xs-12 col-md-8 col-md-offset-2">
            <h3 class="page-header">Olá ${logado.getNome()}</h3>
            <img src="includes/home.jpg" class="img-responsive" style="margin: 0 auto;" alt="Gerência de Projetos"> 
        </div>
    </div>
 </div>

<jsp:include page="/includes/footer.jsp"/>
</body>
</html>