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
                     <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/profile?username=${sessionScope.user }">Profil</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/friends">Přátelé</a></li>
                     <li role="presentation"><a href="${pageContext.request.contextPath}/users">Uživatelé</a></li>
                  </ul>
               </li>
            </ul>
            <ul class="nav nav-pills nav-stacked hidden-xs">
               <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/wall">Zeď</a></li>
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
         <div class="col-sm-8">
            <h1>            Zeď</h1>
            <hr />
            <!-- Status -->
            <div class="row">
               <div class="panel panel-default">
                  <div class="panel-body">
                     <div class="col-md-2">
                        <div class="well status-avatar">
                           <div class="status-name"><c:out value="${sessionScope.user }" /></div>
                           <img src="${pageContext.request.contextPath}${requestScope.userAvatar}" class="img-circle" height="55" width="55" alt="Avatar">
                        </div>
                     </div>
                     <div class="col-md-10">
                     	<form action="${pageContext.request.contextPath}/wall" method="post">
                        <div class="form-group">
                        	<textarea class="form-control" rows="5" id="text" name="text" placeholder="Napište ostatním, jak se cítíte!"></textarea>
                        </div>
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
            
            <c:if test="${not empty requestScope.statusList}">
            
            <c:forEach items="${requestScope.statusList }" var="item">
           	 <div class="row">
               <div class="panel panel-default">
                  <div class="panel-body">
                     <div class="col-md-2">
                        <div class="well status-avatar">
           					<div class="status-name"><a href="${pageContext.request.contextPath}/profile?username=${item.owner.username}">${item.owner.username}</a></div>
           				<img src="${pageContext.request.contextPath}${item.owner.avatar}" class="img-circle" height="55" width="55" alt="Avatar">	
             			</div>
                     </div>
           <div class="col-md-10">
                        <div class="well status-status">
                        <p>${item.text}</p>
                           <div class="status-date">${item.dateOfStatus}</div>
           				 </div>
                        <div class="well text-right status-buttons">
                           <a class="btn btn-success" href="${pageContext.request.contextPath}/like?id=${item.id}">
                           <i class="fa fa-thumbs-up"></i>
                           Lajkovat</a>
                           <a class="btn btn-danger" href="${pageContext.request.contextPath}/hate?id=${item.id}">
                           <i class="fa fa-thumbs-down"></i>
                           Hejtovat</a>
                           
                        </div>
                         <div class="status-info">
                           <strong>
                           <span class="lajk"><c:out value="${item.likes.size() }" /></span> | <span class="hejt"><c:out value="${item.hates.size() }" /></span> <br>
                           </strong>
                           
                           <c:if test="${not empty item.likes }">
                          <strong> Líbilo se: </strong><br>
                           	<c:forEach items="${item.likes }" var="like">
                           	<a href="${pageContext.request.contextPath}/profile?username=${like.username}"><c:out value="${like.username }" /></a> <br>
                           	</c:forEach>
                           
                           </c:if>
                           <c:if test="${not empty item.hates }">
                           <strong>Nelíbilo se:</strong> <br>
                           <c:forEach items="${item.hates }" var="hate">
                           <a href="${pageContext.request.contextPath}/profile?username=${hate.username}"><c:out value="${hate.username }" /></a> <br>
                           </c:forEach>
                           </c:if>
                           
                        </div>
                        
                        
                     </div>
                  </div>
               </div>
            </div>
           </c:forEach>
            </c:if>
            
            
            
             </div>
             
             
             
             <div class="col-sm-2">
             
             <h3>Notifikace</h3>
          			<hr />
          				<c:if test="${not empty requestScope.friendshipList }">
          				
          				<c:forEach items="${requestScope.friendshipList}" var="item">
          				
          				<c:choose>
          				<c:when test="${item.initiator.username == sessionScope.user }">
          				
          				<div class="row">
          				
          				<div class="panel panel-info">
          				<div class="panel panel-heading">Odeslaná žádost</div>
          				<div class="panel panel-body">Uživatel: <a href="${pageContext.request.contextPath}/profile?username=${item.target.username}" ><c:out value="${item.target.username }" /> </a>
          				<br><a class="btn btn-danger" href="${pageContext.request.contextPath}/friendDelete?id=${item.id}">Stáhnout</a> </div>
          				</div>
          				
          				</div>
          				
          			
          				</c:when>
          				
          				<c:otherwise>
          				<div class="row">
          				<div class="panel panel-warning">
          				<div class="panel panel-heading">Přijatá žádost</div>
          				<div class="panel panel-body">Uživatel: <a href="${pageContext.request.contextPath}/profile?username=${item.initiator.username}" ><c:out value="${item.initiator.username }" /></a>
          				<br> <a class="btn btn-success" href="${pageContext.request.contextPath}/friendApprove?id=${item.id}">Přijmout</a><a class="btn btn-danger" href="${pageContext.request.contextPath}/friendDelete?id=${item.id}">Ignorovat</a>
          				</div>
          				
          				</div>
          				
          				</div>
          				
          				</c:otherwise>
          				
          				</c:choose>
          				
          				
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