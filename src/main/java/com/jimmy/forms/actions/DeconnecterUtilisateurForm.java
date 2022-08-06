package com.jimmy.forms.actions;

import com.jimmy.forms.classes.UtilisateurForm;
import com.jimmy.util.GestionSession;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DeconnecterUtilisateurForm {

	private HttpServletRequest request;

	public DeconnecterUtilisateurForm(HttpServletRequest request) {
		this.request = request;
	}

	public void seDeconnecter() {

		HttpSession session = request.getSession();
		UtilisateurForm utilisateurForm = (UtilisateurForm) session.getAttribute("utilisateurForm");
		String nom = utilisateurForm.getNom();

		GestionSession.effacerTousLesAttributs(request);

		request.setAttribute("messageConnexion", "Utilisateur " + nom + " déconnecté");
	}

}
