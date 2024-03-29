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
		<c:when test="${utilisateurForm.connecte}">
			<fieldset>
				<legend>Vous êtes connecté en tant que <c:out value="${utilisateurForm.nom}"></c:out></legend>
				
				<form method="get" action="deconnexion">
					<input type="submit" value="Se déconnecter">
				</form>
				
			</fieldset>

		</c:when>
		<c:otherwise>
		
			<fieldset>
				<legend>Saisissez le nom d'utilisateur et le mot de passe pour vous connecter</legend>
				
				<form method="post" action="connexion"> <!-- Effectuera un POST sur l'URL "connexion" -->
					<label for="nom">Nom d'utilisateur</label>
					<input type="text" id="nom" name="nom_param" value="<c:out value="${utilisateurForm.nom}"></c:out>"/><br>
					<label for="mot_de_passe">Mot de passe</label> 
					<input	type="password" id="mot_de_passe" name="mot_de_passe_param" value="<c:out value="${utilisateurForm.motDePasse}"></c:out>" /><br>
					<input type="submit" value="Se connecter" />
				</form>
				
			</fieldset>
			
			<c:if test="${!empty messageConnexion}"> 
				<p><c:out value="${messageConnexion}"></c:out></p>
			</c:if>
			
			<fieldset>
			<legend>Créez un nouvel utilisateur</legend>
			
				<form method="get" action="creationUtilisateur">
					<input type="submit" value="Créer un utilisateur">
				</form>
				
			</fieldset>

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
		<tr> <!--  Une ligne -->
		<c:forEach items="${tabNomLongJour}" var="nomLongJour">
			 <th><c:out value="${nomLongJour}"></c:out></th> <!--  Une cellule d'entête -->
		</c:forEach>
		<tr>
		<c:forEach items="${mois.listeSemaine}" var="semaine">
			<tr>
			<c:forEach items="${semaine.tabJournee}" var = "journee">
				<td> <!--  Une cellule -->
					<c:forEach items="${journee.vueLigne()}" var="ligne">
						<c:out value ="${ligne}"></c:out><br>
					</c:forEach>
				</td>
			</c:forEach>
			<td>
				<form method="post" action="ajouter">
					<label for="select_ajouter">Ajouter...</label><br>
					<select id="select_ajouter" name="select_ajouter_semaine_${semaine.numero}_param">
						<c:forEach items="${listeTypeAjout}" var="typeAjout">
							<option>${typeAjout}</option>
						</c:forEach>
					</select><br>
					<input type="submit" value="Ajouter">
				</form>
			</td>
			</tr>
		</c:forEach>
		</table>			
	</c:if>
	
	<c:if test="${utilisateurForm.connecte}">
		<form methode="get" action="ajouter">
			<input type="submit" value="Créer un nouvel aliment">
		</form>
	</c:if>
	
</body>
</html>