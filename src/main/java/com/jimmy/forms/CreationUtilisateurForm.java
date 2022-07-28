package com.jimmy.forms;

import java.time.LocalDate;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDaoImpl;

import jakarta.servlet.http.HttpServletRequest;

public class CreationUtilisateurForm {

	public boolean creerUtilisateur(HttpServletRequest request) {

		Utilisateur utilisateur = controleDonneesFormulaire(request);

		UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();
		utilisateurDaoImpl.create(utilisateur);

		request.getSession().setAttribute("erreurDeConnexion", "Utilisateur créé");

		return true;

	}

	private Utilisateur controleDonneesFormulaire(HttpServletRequest request) {

		Utilisateur utilisateur = null;

		String nom = request.getParameter("nom_param");
		String motDePasse = request.getParameter("mot_de_passe_param");
		String confMotDePasse = request.getParameter("conf_mot_de_passe_param");
		String sexe = request.getParameter("sexe_param");
		String dateParam = request.getParameter("date_de_naissance_param"); // Ex. : 2022-06-29
		String[] tabChaine = dateParam.split("-");
		LocalDate dateDeNaissance = null;
		if (tabChaine.length == 3) { // A METTRE DANS DATE UTIL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			int annee = 0;
			int mois = 0;
			int jour = 0;

			annee = Integer.parseInt(tabChaine[0]);
			mois = Integer.parseInt(tabChaine[1]);
			jour = Integer.parseInt(tabChaine[2]);

			dateDeNaissance = LocalDate.of(annee, mois, jour);
		}

		System.out.println("IMPLEMENTER les regles dans CreationUtilisateurForm/controleDonneesFormulaire()");

		utilisateur = new Utilisateur(nom, motDePasse, sexe, dateDeNaissance);

		return utilisateur;

	}

}
