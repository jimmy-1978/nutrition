<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter une activité</title>
</head>
<body>
	<h1>Ajouter une activité</h1>
	<form method="post" action="ajouterActivite">
		<label for="nb_calories">Nombre de calories brûlées</label>
		<input type="text" id="nb_calories" name="nb_calories_param"><br>
		<label for="date_activite">Date de l'activité</label>
		<select id="date_activite" name="date_activite_param">
			<c:forEach items="${joursDeLaSemaine}" var = "jour">
				<option>${jour}</option>
			</c:forEach>
		</select><br>
		<input type="submit" value="Ajouter">	
	</form>
</body>
</html>