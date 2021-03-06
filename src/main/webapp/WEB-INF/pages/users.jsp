<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Uživatelé</title>
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
         <!-- Left panel Menu -->
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
                     <li role="presentation"  class="active"><a href="${pageContext.request.contextPath}/users">Uživatelé</a></li>
                  </ul>
               </li>
            </ul>
            <ul class="nav nav-pills nav-stacked hidden-xs">
               <li role="presentation"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
               <li role="presentation"><a href="${pageContext.request.contextPath}/profile?username=${sessionScope.user}">Profil</a></li>
               <li role="presentation"><a href="${pageContext.request.contextPath}/friends">Přátelé</a></li>
               <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/users">Uživatelé</a></li>
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
                     <li role="presentation"><a href="${pageContext.request.contextPath}/index">Hlavní stránka</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/information">Informace</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/reaction">Reakce uživatelů</a></li>
                     <li role="presentation"  class="active"><a href="${pageContext.request.contextPath}/users">Prohlížení</a></li>
                  </ul>
               </li>
            </ul>
            <ul class="nav nav-pills nav-stacked hidden-xs">
              <li role="presentation"><a href="${pageContext.request.contextPath}/index">Hlavní stránka</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/information">Informace</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/reaction">Reakce uživatelů</a></li>
                     <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/users">Prohlížení</a></li>
            </ul>
         </div>
         </c:otherwise>
         </c:choose>
         
         
         
          
         <!-- Center panel -->
         <div class="col-sm-10">
         
         <h1>Uživatelé KIVBOOK</h1>
         <hr />
         
         <c:if test="${empty requestScope.userList }" > <p>Žádní uživatelé nejsou zaregistrováni! :( <br> Buďte <a href="${pageContext.request.contextPath}/register">první!</a> </p> </c:if>
         
         <c:if test="${not empty requestScope.userList }" >
         
         <c:forEach items="${requestScope.userList }" var="item" >
         
         	<c:choose>
         	<c:when test="${empty item.gender }" >
         		<c:set var="pan" value="panel panel-info" />
         		<c:set var="fav" value="fa fa-user-circle" />
         	</c:when>
         	<c:when test="${item.gender == 'male' }" >
         		<c:set var="pan" value="panel panel-success" />
         		<c:set var="fav" value="fa fa-male" />
         	</c:when>
         	<c:when test="${item.gender == 'female' }" >
         		<c:set var="pan" value="panel panel-warning" />
         		<c:set var="fav" value="fa fa-female" />
         	</c:when>
         	</c:choose>
         
        	<div class="${pan }">
        	<div class="panel-heading">Uživatel <i class="${fav}" aria-hidden="true"></i></div>
        	<div class="panel-body"> <img src="${pageContext.request.contextPath}${item.avatar}" class="img-circle" height="30" width="30" alt="Avatar"> <a href="${pageContext.request.contextPath}/profile?username=${item.username}" ><c:out value="${item.username }" /></a>  </div>
        	
        	</div>
         
         </c:forEach>
         
         </c:if>
         
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