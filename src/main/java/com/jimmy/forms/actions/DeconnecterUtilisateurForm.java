package com.jimmy.forms.actions;

import com.jimmy.forms.classes.UtilisateurForm;
import com.jimmy.util.GestionSession;

import jakarta.servlet.http.HttpServletRequest;

public class DeconnecterUtilisateurForm {

	private HttpServletRequest request;

	public DeconnecterUtilisateurForm(HttpServletRequest request) {
		this.request = request;
	}

	public void seDeconnecter() {

		UtilisateurForm utilisateurForm = (UtilisateurForm) GestionSession.recupererAttribut(request,
				"utilisateurForm");
		String nom = utilisateurForm.getNom();

		GestionSession.enleverTousLesAttributs(request);

		request.setAttribute("messageConnexion", "Utilisateur " + nom + " déconnecté");
	}

}
