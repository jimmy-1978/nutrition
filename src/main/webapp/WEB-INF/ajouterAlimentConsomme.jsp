<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un aliment consommé</title>
</head>
<body>
	<h1>Ajouter un aliment consommé</h1>
	
	<form method="post" action="filtrerListeAlimentConsomme">
		<fieldset>
			<legend>Sélectionnez un filtre pour limiter la liste des aliments</legend>
			<c:forEach items="${alimentConsommeForm.tabTypeAliment}" var="type_aliment" varStatus="status">
				<input type="radio" id="${type_aliment}" 
					name="filtre_param" 
					value="${type_aliment}"
					<c:if test="${status.count == 1}">checked</c:if>>
				<label for="${type_aliment}">${type_aliment.libelle}</label>			
			</c:forEach>
	    	<br><button type="submit">Màj</button>
		</fieldset>
	</form>
	
	<form method="post" action="ajouterAlimentConsomme">
		<label for="nom_aliment">Nom de l'aliment</label>
		<select id="nom_aliment" name="nom_aliment_param">
			<c:forEach items="${alimentConsommeForm.listeNomAliment}" var="nom_aliment">
				<option <c:if test="${alimentConsommeForm.nom == nom_aliment}">selected="yes"</c:if>>${nom_aliment}</option>
			</c:forEach>
		</select><br>	
		<label for="quantite_aliment">Quantité (en ml ou gr)</label>
		<input type="text" id="quantite_aliment" name="quantite_aliment_param" value="${alimentConsommeForm.quantiteForm}"><br>	
		<label for="date_aliment">Date</label>
		<select id="date_aliment" name="date_aliment_param">
			<c:forEach items="${alimentConsommeForm.tabJoursDeLaSemaineForm}" var = "jour">
				<option <c:if test="${alimentConsommeForm.date == jour}">selected="yes"</c:if>>${jour}</option>
			</c:forEach>
		</select><br>
		<input type="submit" value="Ajouter">
		<c:if test="${!empty alimentConsommeForm.erreurAjout}">
			<p>${alimentConsommeForm.erreurAjout}</p>
		</c:if>	
	</form>	
</body>
</html>