<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"">
	<title>Application nutrition</title>
</head>
<body>
	<c:choose>
		<c:when test="${connecte}"> <!-- "connecte" = variable de session -->  
			<h1>Vous êtes connecté en tant que <c:out value="${utilisateur.nom}"></c:out></h1>
			<form method="get" action="deconnexion">
				<input type="submit" value="Se déconnecter">
			</form>
		</c:when>
		<c:otherwise>
			<h1>Connexion</h1>
			<form method="post" action="connexion"> <!-- Effectuera un POST sur l'URL "connexion" -->
				<label for="nom">Nom d'utilisateur</label>
				<input type="text" id="nom" name="nom_param" value="${utilisateur.nom}"/><br>
				<label for="mot_de_passe">Mot de passe</label> 
				<input	type="password" id="mot_de_passe" name="mot_de_passe_param" /><br>
				<input type="submit" value="Se connecter" />
				<c:if test="${!empty messageConnexion}"> 
					<p><c:out value="${messageConnexion}"></c:out></p>
				</c:if>
			</form>
			
			<form method="get" action="creationUtilisateur">
				<input type="submit" value="Création utilisateur">
			</form>
			
		</c:otherwise>
	</c:choose>
	
	<c:if test="${!empty mois}">
		<h1><c:out value = "${mois.nom}"></c:out> <c:out value="${mois.annee}"></c:out></h1>
		
		<table>
			<tr>
				<td>
					<form method="get" action="moisPrecedent">
						<input type="submit" value=" << Mois précédent">
					</form>
				</td>
				<td>
					<form method="get" action="moisSuivant" >
						<input type="submit" value="Mois suivant >>">
					</form>			
				</td>
			</tr>
		</table>
		
		<table>
		<tr>
		<c:forEach items="${tabNomLongJour}" var="nomLongJour">
			 <th><c:out value="${nomLongJour}"></c:out></th>
		</c:forEach>
		<tr>
		<c:forEach items="${mois.listeSemaine}" var="semaine">
			<tr>
			<c:forEach items="${semaine.tabJournee}" var = "journee">
				<td>
					<c:forEach items="${journee.vueLigne()}" var="ligne">
						<c:out value ="${ligne}"></c:out><br>
					</c:forEach>
				</td>
			</c:forEach>
			</tr>
		</c:forEach>
		</table>
				
	</c:if>
	
</body>
</html>