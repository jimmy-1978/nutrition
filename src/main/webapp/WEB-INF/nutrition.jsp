<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de connexion</title>
</head>
<body>
	<c:choose>
		<c:when test="${connecte}"> <!-- "connecte" = variable de session -->  
			<p>Vous êtes connecté en tant que <c:out value="${utilisateur.nom}"></c:out></p>
			<form method="get" action="deconnexion">
				<input type="submit" value="Se déconnecter">
			</form>
		</c:when>
		<c:otherwise>
			<form method="post" action="connexion"> <!-- Effectuera un POST sur l'URL "connexion" -->
				<label for="nom">Nom d'utilisateur</label>
				<input type="text" id="nom" name="nom_param" value="${utilisateur.nom}"/><br>
				<label for="mot_de_passe">Mot de passe</label> 
				<input	type="password" id="mot_de_passe" name="mot_de_passe_param" /><br>
				<input type="submit" value="Se connecter" />
				<c:if test="${!empty erreurDeConnexion}"> 
					<p><c:out value="${erreurDeConnexion}"></c:out></p> <!-- erreurDeConnexion = variable de session -->
				</c:if>
			</form>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${!empty listeJournee}">
		<h1>Liste des journées</h1>
		<ul>
			<c:forEach items="${listeJournee}" var="journee">
				<li>
					<c:out value="${journee.vueDate()}"></c:out>
				</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
</body>
</html>