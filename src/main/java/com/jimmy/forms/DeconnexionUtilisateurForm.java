package com.jimmy.forms;

import com.jimmy.classes.Utilisateur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DeconnexionUtilisateurForm {

	private HttpServletRequest request;

	public DeconnexionUtilisateurForm(HttpServletRequest request) {
		this.request = request;
	}

	public void seDeconnecter() {

		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		String nom = utilisateur.getNom();

		session.removeAttribute("utilisateur");
		session.setAttribute("connecte", false);

		request.setAttribute("messageConnexion", "Utilisateur " + nom + " déconnecté");
	}

}
