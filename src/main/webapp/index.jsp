<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>KIVBOOK Hlavní stránka</title>
      <!-- CSS -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
         integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
         crossorigin="anonymous">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
</head>
 <body>
      <div class="container">
         <!-- Menu -->
         <nav class="navbar navbar-inverse">
            <div class="container-fluid">
               <div class="navbar-header">
                  <a class="navbar-brand" href="#">
                  <span><img alt="Logo" src="${pageContext.request.contextPath}/img/logo.png" width="40" height="25">
                  &nbsp;KIVBOOK - sociální síť</span>
                  </a>
               </div>
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menuNavbar">
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span> 
               </button>
            <c:choose>
               <c:when test="${not empty sessionScope.user }">
               <div class="collapse navbar-collapse" id="menuNavbar">
                  <ul class="nav navbar-nav navbar-right">
                       <li><a href="${pageContext.request.contextPath}/profile?username=${sessionScope.user}"><i class="fa fa-user"></i>&nbsp;<c:out value="${sessionScope.user }" /></a></li>
                     <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out"></i>&nbsp;Odhlásit</a></li>
                  </ul>
               </div>
               </c:when>
               <c:otherwise>
               <div class="collapse navbar-collapse" id="menuNavbar">
                  <ul class="nav navbar-nav navbar-right">
                     <li><a href="${pageContext.request.contextPath}/register"><i class="fa fa-user-circle"></i>&nbsp;Registrovat</a></li>
                     <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-in"></i>&nbsp;Přihlásit</a></li>
                  </ul>
               </div>
               </c:otherwise>
               </c:choose>
            </div>
         </nav>
         <!-- Page content -->
         <!-- Left panel -->
                  <c:choose>
         <c:when test="${not empty sessionScope.user }" >
          <div class="col-sm-2">
            <ul class="nav nav-pills hidden-xl hidden-lg hidden-sm hidden-md">
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Zobrazit menu
                  <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <li role="presentation"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/profile?username=${sessionScope.user }">Profil</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/friends">Přátelé</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/users">Uživatelé</a></li>
                  </ul>
               </li>
            </ul>
            <ul class="nav nav-pills nav-stacked hidden-xs">
               <li role="presentation"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
               <li role="presentation"><a href="${pageContext.request.contextPath}/profile?username=${sessionScope.user}">Profil</a></li>
               <li role="presentation"><a href="${pageContext.request.contextPath}/friends">Přátelé</a></li>
               <li role="presentation"><a href="${pageContext.request.contextPath}/users">Uživatelé</a></li>
            </ul>
            </div>
         </c:when>
         <c:otherwise>
         <div class="col-sm-2">
            <ul class="nav nav-pills hidden-xl hidden-lg hidden-sm hidden-md">
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Zobrazit menu
                  <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index">Hlavní stránka</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/information">Informace</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/reaction">Reakce uživatelů</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/users">Prohlížení</a></li>
                  </ul>
               </li>
            </ul>
            <ul class="nav nav-pills nav-stacked hidden-xs">
              <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index">Hlavní stránka</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/information">Informace</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/reaction">Reakce uživatelů</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/users">Prohlížení</a></li>
            </ul>
         </div>
         </c:otherwise>
         </c:choose>
         <!-- Center panel -->
         <div class="col-sm-8">
            <h1>
               Vítejte
            </h1>
            <hr />
            <p>Vítejte v KIVBOOK. Tato sociální síť umožňuje sdílet zážitky lidem z různých částí světa v reálném čase.</p>
            <p>
               <a href="${pageContext.request.contextPath}/login">Přihlašte se</a> nebo pokud nemáte účet, tak se <a href="${pageContext.request.contextPath}/register">zaregistruje</a> a poznejte krásu KIVBOOK již dnes.
            </p>
            <h2>
               Co by vás mohlo zajímat
            </h2>
            <hr />
            <p>
               Pokud potřebujete více informací, přečtěte si <a href="${pageContext.request.contextPath}/information">informace</a> o naší soicální síti. Pokud snad potřebujete znát názory našich uživatelů, 
               neváhejte se na ně podívat na stránkách <a href="${pageContext.request.contextPath}/reaction">Reakce uživatelů!</a>. Pokud vás ani to nepřesvědčí, můžete si prohlédnout naše stránky i 
               jako nepřihlášený uživatel na stránce <a href="${pageContext.request.contextPath}/users">Prohlížení</a>.
            </p>
         </div>
      </div>
      <!-- Footer -->
      <footer class="footer text-muted">
         KIVBOOK 2017
      </footer>
      <!-- Scripts -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
         integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
         crossorigin="anonymous"></script>
   </body>
</html>