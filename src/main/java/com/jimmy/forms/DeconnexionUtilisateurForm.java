package com.jimmy.forms;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DeconnexionUtilisateurForm {

	private HttpServletRequest request;

	public DeconnexionUtilisateurForm(HttpServletRequest request) {
		this.request = request;
	}

	public void seDeconnecter() {

		HttpSession session = request.getSession();
		UtilisateurForm utilisateurForm = (UtilisateurForm) session.getAttribute("utilisateurForm");
		String nom = utilisateurForm.getNom();

		session.removeAttribute("utilisateurForm");

		request.setAttribute("messageConnexion", "Utilisateur " + nom + " déconnecté");
	}

}
