<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter une activité</title>
</head>
<body>
	
	<fieldset>
		<legend>Saisissez les informations de la nouvelle activité à ajouter</legend>
		
		<form method="post" action="ajouterActivite">
			<label for="nb_calories">Nombre de calories brûlées</label>
			<input type="text" id="nb_calories" name="nb_calories_param" value="${activiteForm.nbCaloriesBruleesForm}"><br>
			<label for="type_activite">Type d'activite</label>
			<select id="type_activite" name="type_activite_param">
				<c:forEach items="${activiteForm.tabTypeActivite}" var="typeActivite">
					<option <c:if test="${activiteForm.typeActivite == typeActivite}">selected="yes"</c:if>>${typeActivite}</option>
				</c:forEach>
			</select><br>		
			<label for="date_activite">Date de l'activité</label>
			<select id="date_activite" name="date_activite_param">
				<c:forEach items="${activiteForm.joursDeLaSemaineForm}" var = "jour">
					<option <c:if test="${activiteForm.dateActivite == jour}">selected="yes"</c:if>>${jour}</option>
				</c:forEach>
			</select><br>
			<input type="submit" value="Ajouter">			
		</form>
		
		<c:import url="annuler.html" />
		
	</fieldset>
	
	<c:if test="${!empty activiteForm.erreurAjout}">
		<p>${activiteForm.erreurAjout}</p>
	</c:if>	
	
</body>
</html>