<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Registrace</title>
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
                     <li role="presentation"><a href="${pageContext.request.contextPath}/index">Hlavní stránka</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/information">Informace</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/reaction">Reakce uživatelů</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/users">Prohlížení</a></li>
                  </ul>
               </li>
            </ul>
            <ul class="nav nav-pills nav-stacked hidden-xs">
              <li role="presentation"><a href="${pageContext.request.contextPath}/index">Hlavní stránka</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/information">Informace</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/reaction">Reakce uživatelů</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/users">Prohlížení</a></li>
            </ul>
         </div>
         </c:otherwise>
         </c:choose>
         <!-- Center panel -->
 
         <div class="col-sm-10">
        
                  <c:if test="${not empty requestScope.err}">
     					<p class="error">
     					${requestScope.err}
     					</p>
     					</c:if>
         
            <!-- Sign up Form -->
            <c:choose>
            <c:when test="${empty sessionScope.user }" >
            
            <form class="form-horizontal" action="${pageContext.request.contextPath}/register" method="post" >
               <fieldset>
                  <legend>Registrace</legend>
                  <div class="form-group">
                     <label class="control-label col-sm-2" for="username">Jméno:*</label>
                     <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" placeholder="Zadat přihlašovací jméno" name="username" required value="<c:if test="${not empty requestScope.usernameField}">${requestScope.usernameField}</c:if>" autofocus>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-2" for="pwd">Heslo:*</label>
                     <div class="col-sm-10">          
                        <input type="password" class="form-control" id="pwd" placeholder="Zadat heslo" name="password" required>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-2" for="pwd-check">Kontrola hesla:*</label>
                     <div class="col-sm-10">          
                        <input type="password" class="form-control" id="pwd-check" placeholder="Zadat heslo podruhé" name="password-check" required>
                     </div>
                  </div>
               </fieldset>
               <fieldset>
                  <legend>Další informace</legend>
                  <div class="form-group">
                     <label class="control-label col-sm-2">Pohlaví:</label>
                     <div class="col-sm-10">
                        <label class="radio-inline"><input type="radio" name="sex" value="male" <c:if test="${not empty requestScope.genderMaleField}">checked</c:if>>Muž</label>
                        <label class="radio-inline"><input type="radio" name="sex" value="female" <c:if test="${not empty requestScope.genderFemaleField}">checked</c:if>>Žena</label>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-2">Datum narození:</label>
                     <div class="col-sm-10">
                        <input type="text" name="birthday" value="" id="birthdayPicker"/>
                     </div>
                  </div>
                <div class="form-group">
                     <label class="control-label col-sm-2" for="captcha">Napište číslici 4:*</label>
                     <div class="col-sm-10">
                        <input type="text" class="form-control" id="captcha" placeholder="Dokažte, že nejste robot" name="captcha" required 
                       <c:if test="${not empty requestScope.captchaField}">value="${requestScope.captchaField}"</c:if> >
                     </div>
                  </div>
               </fieldset>
               <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                     <div class="checkbox">
                        <label><input type="checkbox" name="agree" required <c:if test="${not empty requestScope.agreeField}">checked</c:if>>Souhlasím s obchodními podmínkami</label>
                     </div>
                  </div>
               </div>
               <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                     <button type="submit" class="btn btn-default">Registrovat</button>
                  </div>
               </div>
            </form>
            
            </c:when>
            <c:when test="${not empty sessionScope.user }">
            <h2>Registrace</h2>
            <hr />
            
            <p>Nemůžete registrovat uživatele, dokud jste přihlášen. </p>
            
            </c:when>
            </c:choose>
         </div>
      </div>
      <!-- Footer -->
      <footer class="footer text-muted">
         KIVBOOK 2017
      </footer>
      <!-- Scripts -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/bootstrap-birthday.js" type="text/javascript"></script>
       <script src="${pageContext.request.contextPath}/js/reg.js" type="text/javascript"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
         integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
         crossorigin="anonymous"></script>
   </body>
</html>
