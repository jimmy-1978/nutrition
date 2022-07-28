<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création utilisateur</title>
</head>
<body>
	<h1>Création utilisateur</h1>
	<form method="post" action = "creationUtilisateur">
		<label for="nom">Nom d'utilisateur</label>
		<input type="text" id="nom" name="nom_param"><br>
		<label for="mot_de_passe">Mot de passe</label>
		<input type="password" id="mot_de_passe" name="mot_de_passe_param"><br>
		<label for="conf_mot_de_passe">Confirmer mot de passe</label>
		<input type="password" id="conf_mot_de_passe" name="conf_mot_de_passe_param"><br>
		<label for="sexe">Sexe</label>	
		<input type="text" id="sexe" name="sexe_param"><br>
		<label for="date_de_naissance">Date de naissance</label>
		<input type="date" id="date_de_naissance" name="date_de_naissance_param"><br>		
		<input type="submit" value="Confirmer">	
	</form>
</body>
</html>