package com.jimmy.forms;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDao;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.vues.Calendrier;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ConnexionUtilisateurForm {

	private HttpServletRequest request;
	private Utilisateur utilisateur;

	public ConnexionUtilisateurForm(HttpServletRequest request) {
		this.request = request;
	}

	public void seConnecter() { // A appeler uniquement avec la méthode POST

		// On enlève toutes les variables de session

		HttpSession session = request.getSession();
		session.removeAttribute("utilisateur");
		session.removeAttribute("connecte");

		// On récupère les données du formulaire

		String nom = (String) request.getParameter("nom_param");
		String motDePasse = (String) request.getParameter("mot_de_passe_param");
		utilisateur = new Utilisateur(nom, motDePasse);
		session.setAttribute("utilisateur", utilisateur); // Permet de ré-afficher les données sur le formulaire, même
															// si la tentative de connexion échoue

		// On contrôle les données du formulaire

		boolean controleOk = controleDonneesFormulaire();

		// Contrôle de l'existence de l'utilisateur en DB + bon mot de passe

		if (controleOk) {

			UtilisateurDao utilisateurDaoImpl = new UtilisateurDaoImpl();
			utilisateur = utilisateurDaoImpl.getByNom(utilisateur.getNom());

			if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {

				session.setAttribute("connecte", true);
				session.setAttribute("utilisateur", utilisateur);

			} else {

				session.setAttribute("connecte", false);
				request.setAttribute("messageConnexion", "Nom d'utilisateur et/ou mot de passe incorrect");

			}
		}
	}

	private boolean controleDonneesFormulaire() {

		System.out.println("Controles des paramètres A IMPLEMENTER et comment gérer les erreurs sur le front?");

		return true;
	}

	public void chargementDonneesUtilisateurSiConnecte() {

		HttpSession session = request.getSession();
		Boolean connecte = (Boolean) session.getAttribute("connecte");
		if (connecte != null && connecte) {

			Calendrier calendrier = new Calendrier();
			calendrier.chargementDuMoisEnCours(request);

		}
	}
}
