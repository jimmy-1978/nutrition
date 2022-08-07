<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un aliment</title>
</head>
<body>
	<fieldset>
		<legend>Saisissez les informations du nouvel aliment à ajouter</legend>
	
		<form method="post" action="creerAliment">
			<label for="type_aliment">Type aliment</label>
			<select id="type_aliment" name="type_aliment_param">
				<c:forEach items="${alimentForm.tabTypeAliment}" var="type_aliment">
					<option <c:if test="${alimentForm.typeAliment == type_aliment}">selected="yes"</c:if>>
					<c:out value="${type_aliment}"></c:out>
					</option>
				</c:forEach>		
			</select><br>		
			<label for="nom">Nom</label>
			<input type="text" id="nom" name="nom_param" value="<c:out value="${alimentForm.nom}"></c:out>"><br>
			<label for="kcal">Kcal par unité de mesure</label>
			<input type="text" id="kcal" name="kcal_param" value="<c:out value="${alimentForm.kiloCalForm}"></c:out>"><br>
			<label for="unite_de_mesure">Unité de mesure</label>
			<select id="unite_de_mesure" name="unite_de_mesure_param">
				<c:forEach items="${alimentForm.tabUniteDeMesure}" var="unite_de_mesure">
					<option <c:if test="${alimentForm.uniteDeMesure == unite_de_mesure}">selected="yes"</c:if>>
					<c:out value="${unite_de_mesure}"></c:out>
					</option>
				</c:forEach>		
			</select><br>
			<input type="submit" value="Ajouter">
		</form>
		
		<c:import url="annuler.html" />
	
	</fieldset>
		
	<c:if test="${!empty alimentForm.erreurAjout}">
		<p>${alimentForm.erreurAjout}</p>
	</c:if>	
		
</body>
</html>