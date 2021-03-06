<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Profil</title>
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
         <!-- Left panel menu -->
 		         <c:choose>
         <c:when test="${not empty sessionScope.user }" >
          <div class="col-sm-2">
            <ul class="nav nav-pills hidden-xl hidden-lg hidden-sm hidden-md">
               <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Zobrazit menu
                  <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <li role="presentation"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
                     <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/profile?username=${sessionScope.user }">Profil</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/friends">Přátelé</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/users">Uživatelé</a></li>
                  </ul>
               </li>
            </ul>
            <ul class="nav nav-pills nav-stacked hidden-xs">
               <li role="presentation"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
               <li role="presentation"  class="active"><a href="${pageContext.request.contextPath}/profile?username=${sessionScope.user}">Profil</a></li>
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
         
         
         <div class="col-sm-10">
         
        	<c:choose>
        	<c:when test="${empty requestScope.err }" >
        	<c:choose>
        			<c:when test="${requestScope.profileMode == 0}" >
        			
        			<h1>Úprava profilu</h1>
        			<hr />
        			
        			<div class="panel panel-info">
        			<div class="panel panel-heading">Profilový obrázek</div>
        			<div class="panel panel-body"><img src="${pageContext.request.contextPath}${requestScope.editAvatar}" alt="avatar" class="img-circle" height="95" width="95"></div>
        			</div>		
        			
        			<form class="form-horizontal" action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        			
        			<div class="form-group">
        			<label class="control-label col-sm-2" for="avatar">Nahrát nový profilový obrázek:</label>
        				<div class="col-sm-10">
        				<input type="file" class="form-control" id="avatar" name="avatar">
        				</div>
        			</div>
        			
        				<div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                     <button type="submit" class="btn btn-default">Nahrát</button>
                  </div>
               </div>
        			
        			
        			</form>
        			
        			<form class="form-horizontal" action="${pageContext.request.contextPath}/profile" method="post">
        			
        			
        			<div class="form-group">
                     <label class="control-label col-sm-2" for="password">Změnit heslo:</label>
                     <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" placeholder="Změna hesla" name="password">
                     </div>
                  </div>
                  
                  <div class="form-group">
                     <label class="control-label col-sm-2" for="email">E-mail:</label>
                     <div class="col-sm-10">          
                        <input type="email" class="form-control" id="email" placeholder="Emailová adresa" name="email" <c:if test="${not empty requestScope.editEmail }" >value="${requestScope.editEmail}" </c:if>>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-2">Pohlaví:</label>
                     <div class="col-sm-10">
                        <label class="radio-inline"><input type="radio" name="sex" value="male" <c:if test="${(not empty requestScope.editGender) && (requestScope.editGender == 'male') }">checked </c:if>>Muž</label>
                        <label class="radio-inline"><input type="radio" name="sex" value="female" <c:if test="${(not empty requestScope.editGender) && (requestScope.editGender == 'female') }">checked </c:if>>Žena</label>
                     </div>
                  </div>
                  
        			<div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                     <button type="submit" class="btn btn-default">Uložit změny</button>
                  </div>
               </div>
        			</form>
        			</c:when>
        		
        		<c:otherwise>
        		<h1>Profil</h1>
        		<hr />
        			<div class="panel-group">
        				<div class="panel panel-primary">
        					<div class="panel-heading">Uživatel <c:out value="${requestScope.profileUser }" /></div>
        					<div class="panel-body"><img src="${pageContext.request.contextPath}${requestScope.profileAvatar }" class="img-circle" height="55" width="55">
        			</div>
        				</div>
        				
        				<div class="panel panel-info">
        				<div class="panel-heading">Email</div>
        				<div class="panel-body"> <c:out value="${requestScope.profileEmail}" /> </div>
        				</div>
        				
        				<div class="panel panel-info">
        				<div class="panel-heading">Pohlaví</div>
        				<div class="panel-body"> <c:out value="${requestScope.profileGender}" /> </div>
        				</div>
        				
        				<div class="panel panel-info">
        				<div class="panel-heading">Věk</div>
        				<div class="panel-body"> <c:out value="${requestScope.profileAge}" /> </div>
        				<div class="panel-footer">Členem KIVBOOK od <c:out value="${requestScope.profileReg }" /> </div>
        				</div>
        			</div>
        		
        		
        		<c:if test="${requestScope.profileMode == 3 }" >
        				<a class="btn btn-success" href="${pageContext.request.contextPath}/friend?username=${requestScope.profileUser}">Poslat žádost o přátelství</a>
        		</c:if>
        		<c:if test="${requestScope.profileMode == 2 }" >
        				<a class="btn btn-danger" href="${pageContext.request.contextPath}/friendDelete?id=${requestScope.profileId}">Zrušit přátelství</a>
        		</c:if>
        		
        		
        		</c:otherwise>
        		</c:choose>
        		
         
         
        	
        	</c:when>
        	
        	<c:otherwise>
        	
        	<h1>Chyba profilu</h1>
        	<p class="error">
        		<c:out value="${requestScope.err }" />
        	</p>
        	
        	</c:otherwise>
        	</c:choose>	
        		
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