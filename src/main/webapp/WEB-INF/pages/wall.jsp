<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Zeď</title>
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
               <!-- Logo -->
               <div class="navbar-header">
                  <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
                  <span><img alt="Logo" src="img/logo.png" width="40" height="25">
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
                     <li><a href="${pageContext.request.contextPath}/profile"><i class="fa fa-user"></i>&nbsp;<%= request.getSession().getAttribute("user") %></a></li>
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
         <!-- Left panel menu -->
         <div class="col-sm-2">
            <ul class="nav nav-pills hidden-xl hidden-lg hidden-sm hidden-md">
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Zobrazit menu
                  <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/app/profile">Profil</a></li>
                     <li role="presentation"><a href="#">Nastavení</a></li>
                  </ul>
               </li>
            </ul>
            <ul class="nav nav-pills nav-stacked hidden-xs">
               <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
               <li role="presentation"><a href="${pageContext.request.contextPath}/profile">Profil</a></li>
               <li role="presentation"><a href="#">Nastavení</a></li>
            </ul>
         </div>
          <!-- Center panel -->
         <div class="col-sm-10 text-center">
            <h1 class="text-left">Zeď</h1>
            <hr />
            <!-- Status -->
            <div class="row">
               <div class="panel panel-default">
                  <div class="panel-body">
                     <div class="col-md-2">
                        <div class="well status-avatar">
                           <p class="status-name"><%= request.getSession().getAttribute("user") %></p>
                           <img src="img/avatars/default.png" class="img-circle" height="55" width="55" alt="Avatar">
                        </div>
                     </div>
                     <div class="col-md-10">
                     	<form action="${pageContext.request.contextPath}/status" method="post">
                        
                        	<input type="text" name="text" placeholder="Napište ostatním, jak se cítíte!" class="well status-status">
                        
                        <div class="well text-right status-buttons">
                           <button type="submit" class="btn btn-primary">
                           <i class="fa fa-check-square-o"></i> Statusovat
                          </button>
                        </div>
                     </form>
                     </div>
                  </div>
               </div>
            </div>
            
            
            
            
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