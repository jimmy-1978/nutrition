package com.jimmy.forms;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDao;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.exceptions.ControleConnexionUtilisateurException;
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

	public boolean seConnecter() { // A appeler uniquement avec la méthode POST

		Utilisateur utilisateur = null;

		try {
			utilisateur = controleDonneesFormulaire();
		} catch (Exception e) {
			request.setAttribute("messageConnexion", e.getMessage());

			return false;

		}

		if (utilisateur != null) {
			utilisateurForm.setConnecte(true);

			return true;

		} else {
			utilisateurForm.setConnecte(false);

			return false;

		}
	}

	private Utilisateur controleDonneesFormulaire() throws ControleConnexionUtilisateurException {

		Utilisateur utilisateur = null;

		String nom = (String) request.getParameter("nom_param");
		String motDePasse = (String) request.getParameter("mot_de_passe_param");

		utilisateurForm.setNom(nom);
		utilisateurForm.setMotDePasse(motDePasse);

		if (nom.isBlank()) {
			throw new ControleConnexionUtilisateurException("Nom obligatoire");
		}

		if (motDePasse.isBlank()) {
			throw new ControleConnexionUtilisateurException("Mot de passe obligatoire");
		}

		UtilisateurDao utilisateurDaoImpl = new UtilisateurDaoImpl();
		utilisateur = utilisateurDaoImpl.getByNom(utilisateurForm.getNom());
		if (utilisateur == null || !utilisateur.getMotDePasse().equals(motDePasse)) {
			throw new ControleConnexionUtilisateurException("Nom et/ou mot de passe erroné");
		}

		return utilisateur;
	}
}
