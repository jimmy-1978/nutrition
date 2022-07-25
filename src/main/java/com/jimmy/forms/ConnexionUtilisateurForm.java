package com.jimmy.forms;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDao;
import com.jimmy.db.UtilisateurDaoImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ConnexionUtilisateurForm {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Utilisateur utilisateur;

	public ConnexionUtilisateurForm(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void seConnecter() { // A appeler uniquement avec la méthode POST

		// On enlève toutes les variables de session

		HttpSession session = request.getSession();
		session.removeAttribute("erreurDeConnexion");
		session.removeAttribute("utilisateur");
		session.removeAttribute("connecte");

		// On récupère les données du formulaire

		String nom = (String) request.getParameter("nom_param");
		String motDePasse = (String) request.getParameter("mot_de_passe_param");

		// On contrôle les données du formulaire

		boolean controleOk = controleDonneesFormulaire(nom, motDePasse);

		// Contrôle de l'existence de l'utilisateur en DB + bon mot de passe

		if (controleOk) {

			UtilisateurDao utilisateurDaoImpl = new UtilisateurDaoImpl();
			utilisateur = utilisateurDaoImpl.getByNom(nom);

			if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {

				session.setAttribute("connecte", true);
				session.setAttribute("utilisateur", utilisateur);

			} else {

				session.setAttribute("connecte", false);
				session.setAttribute("erreurDeConnexion", "Nom d'utilisateur et/ou mot de passe incorrect");

			}
		}
	}

	public void seDeconnecter() {
		HttpSession session = request.getSession();
		session.removeAttribute("erreurDeConnexion");
		session.removeAttribute("utilisateur");
		session.setAttribute("connecte", false);
	}

	private boolean controleDonneesFormulaire(String nom, String motDePasse) {

		System.out.println("Controles des paramètres A IMPLEMENTER et comment gérer les erreurs sur le front?");

		return true;
	}
}
