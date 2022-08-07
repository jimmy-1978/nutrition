<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un aliment consommé</title>
</head>
<body>
	<fieldset>
		
		<form method="post" action="filtrerListeAlimentConsomme">
			<fieldset>
				<legend>Sélectionnez un filtre pour limiter la liste des aliments</legend>
				<c:forEach items="${alimentConsommeForm.tabTypeAliment}" var="type_aliment">
					<input type="radio" id="${type_aliment}" 
						name="filtre_param" 
						value="${type_aliment}"
						<c:if test="${type_aliment == filtreListeAlimentConsomme}">checked</c:if>>
					<label for="${type_aliment}">${type_aliment.libelle}</label>			
				</c:forEach>
		    	<br><button type="submit">Màj</button>
			</fieldset>
		</form>
		
		<fieldset>
			<legend>Saisissez les informations du nouvel aliment consommé à ajouter</legend>
		
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
			</form>	
		
			<c:import url="annuler.html" />
			
		</fieldset>
		
		<c:if test="${!empty alimentConsommeForm.erreurAjout}">
			<p>${alimentConsommeForm.erreurAjout}</p>
		</c:if>	
	
	</fieldset>

</body>
</html>