package com.jimmy.forms.actions;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDao;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.exceptions.ConnecterUtilisateurFormControleException;
import com.jimmy.forms.classes.UtilisateurForm;
import com.jimmy.listes.Liste;
import com.jimmy.util.GestionSession;
import com.jimmy.vues.Calendrier;

import jakarta.servlet.http.HttpServletRequest;

public class ConnecterUtilisateurForm {

	private HttpServletRequest request;
	private UtilisateurForm utilisateurForm;

	public ConnecterUtilisateurForm(HttpServletRequest request) {
		this.request = request;
		utilisateurForm = (UtilisateurForm) GestionSession.recupererAttribut(request, "utilisateurForm");
		if (utilisateurForm == null) {
			utilisateurForm = new UtilisateurForm();
			GestionSession.ajouterAttribut(request, "utilisateurForm", utilisateurForm);
		}
	}

	public void chargementDonneesUtilisateurSiConnecte() {

		if (utilisateurForm.getConnecte()) {

			Calendrier calendrier = new Calendrier(request);
			calendrier.chargementDuMoisEnCours();

			request.setAttribute("listeTypeAjout", Liste.getListeTypeAjout());

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

	private Utilisateur controleDonneesFormulaire() throws ConnecterUtilisateurFormControleException {

		Utilisateur utilisateur = null;

		String nom = (String) request.getParameter("nom_param");
		String motDePasse = (String) request.getParameter("mot_de_passe_param");

		utilisateurForm.setNom(nom);
		utilisateurForm.setMotDePasse(motDePasse);

		if (nom.isBlank()) {
			throw new ConnecterUtilisateurFormControleException("Nom obligatoire");
		}

		if (motDePasse.isBlank()) {
			throw new ConnecterUtilisateurFormControleException("Mot de passe obligatoire");
		}

		UtilisateurDao utilisateurDaoImpl = new UtilisateurDaoImpl();
		try {
			utilisateur = utilisateurDaoImpl.getByNom(utilisateurForm.getNom());
		} catch (Exception e) {

			throw new ConnecterUtilisateurFormControleException("Erreur technique accès DB");
		}

		if (utilisateur == null || !utilisateur.getMotDePasse().equals(motDePasse)) {
			throw new ConnecterUtilisateurFormControleException("Nom et/ou mot de passe erroné");
		}

		return utilisateur;
	}
}
