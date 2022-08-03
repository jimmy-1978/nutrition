package com.jimmy.forms;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDao;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.vues.Calendrier;

import jakarta.servlet.http.HttpServletRequest;

public class ConnexionUtilisateurForm {

	private HttpServletRequest request;
	private UtilisateurForm utilisateurForm;

	public ConnexionUtilisateurForm(HttpServletRequest request) {
		this.request = request;
		utilisateurForm = (UtilisateurForm) request.getSession().getAttribute("utilisateurForm");
		if (utilisateurForm == null) {
			utilisateurForm = new UtilisateurForm();
			request.getSession().setAttribute("utilisateurForm", utilisateurForm);
		}
	}

	public void chargementDonneesUtilisateurSiConnecte() {

		if (utilisateurForm.getConnecte()) {

			Calendrier calendrier = new Calendrier(request);
			calendrier.chargementDuMoisEnCours();

		}
	}

	public void seConnecter() { // A appeler uniquement avec la méthode POST

		// On récupère les données du formulaire

		String nom = (String) request.getParameter("nom_param");
		String motDePasse = (String) request.getParameter("mot_de_passe_param");

		utilisateurForm.setNom(nom);
		utilisateurForm.setMotDePasse(motDePasse);

		// On contrôle les données du formulaire

		boolean controleOk = controleDonneesFormulaire();

		// Contrôle de l'existence de l'utilisateur en DB + bon mot de passe

		if (controleOk) {

			UtilisateurDao utilisateurDaoImpl = new UtilisateurDaoImpl();
			Utilisateur utilisateur = utilisateurDaoImpl.getByNom(utilisateurForm.getNom());

			if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {

				utilisateurForm.setConnecte(true);

			} else {

				utilisateurForm.setConnecte(false);
				request.setAttribute("messageConnexion", "Nom d'utilisateur et/ou mot de passe incorrect");

			}
		}
	}

	private boolean controleDonneesFormulaire() {

		System.out.println("Controles des paramètres A IMPLEMENTER et comment gérer les erreurs sur le front?");

		return true;
	}
}
