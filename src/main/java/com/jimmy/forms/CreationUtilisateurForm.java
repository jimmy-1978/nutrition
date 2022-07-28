package com.jimmy.forms;

import java.time.LocalDate;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.exceptions.ExceptionControleCreationUtilisateur;
import com.jimmy.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;

public class CreationUtilisateurForm {

	public boolean creerUtilisateur(HttpServletRequest request) {

		Utilisateur utilisateur = null;

		try {
			utilisateur = controleDonneesFormulaire(request);
		} catch (ExceptionControleCreationUtilisateur e) {
			request.setAttribute("erreurCreationUtilisateur", e.getMessage());

			return false;

		}

		UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();
		utilisateurDaoImpl.create(utilisateur);

		request.setAttribute("messageConnexion", "Utilisateur " + utilisateur.getNom() + " créé");

		return true;

	}

	private Utilisateur controleDonneesFormulaire(HttpServletRequest request)
			throws ExceptionControleCreationUtilisateur {

		Utilisateur utilisateur = null;

		String nom = request.getParameter("nom_param");
		String motDePasse = request.getParameter("mot_de_passe_param");
		String confMotDePasse = request.getParameter("conf_mot_de_passe_param");
		String sexe = request.getParameter("sexe_param");
		String dateParam = request.getParameter("date_de_naissance_param"); // Ex. : 2022-06-29
		LocalDate dateDeNaissance = DateUtil.conversionDateRequete(dateParam);

		utilisateur = new Utilisateur(nom, motDePasse, sexe, dateDeNaissance);

		request.setAttribute("utilisateur", utilisateur); // Afin de pouvoir ré-initialiser les champs du formulaire
															// avec les valeurs déjà saisies

		// Contrôles nom
		if (nom.trim().equals("")) {
			throw new ExceptionControleCreationUtilisateur("Le nom est obligatoire");
		}

		UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();
		if (utilisateurDaoImpl.getByNom(nom) != null) {
			throw new ExceptionControleCreationUtilisateur("L'utilisateur existe déjà");
		}

		// Contrôles mot de passe
		if (motDePasse.trim().equals("")) {
			throw new ExceptionControleCreationUtilisateur("Le mot de passe est obligatoire");
		}

		if (!motDePasse.equals(confMotDePasse)) {
			throw new ExceptionControleCreationUtilisateur("Les deux mots de passe saisis ne correspondent pas");
		}

		// Contrôle date de naissance
		if (dateDeNaissance == null) {
			throw new ExceptionControleCreationUtilisateur("La date de naissance est obligatoire");
		}

		return utilisateur;

	}

}
