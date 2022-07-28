package com.jimmy.forms;

import java.time.LocalDate;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;

public class CreationUtilisateurForm {

	public boolean creerUtilisateur(HttpServletRequest request) {

		Utilisateur utilisateur = controleDonneesFormulaire(request);

		UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();
		utilisateurDaoImpl.create(utilisateur);

		request.setAttribute("messageConnexion", "Utilisateur " + utilisateur.getNom() + " créé");

		return true;

	}

	private Utilisateur controleDonneesFormulaire(HttpServletRequest request) {

		Utilisateur utilisateur = null;

		String nom = request.getParameter("nom_param");
		String motDePasse = request.getParameter("mot_de_passe_param");
		String confMotDePasse = request.getParameter("conf_mot_de_passe_param");
		String sexe = request.getParameter("sexe_param");
		String dateParam = request.getParameter("date_de_naissance_param"); // Ex. : 2022-06-29
		LocalDate dateDeNaissance = DateUtil.conversionDateRequete(dateParam);

		System.out.println("IMPLEMENTER les regles dans CreationUtilisateurForm/controleDonneesFormulaire()");

		utilisateur = new Utilisateur(nom, motDePasse, sexe, dateDeNaissance);

		return utilisateur;

	}

}
