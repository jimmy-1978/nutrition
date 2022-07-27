package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.classes.Utilisateur;
import com.jimmy.forms.ConnexionUtilisateurForm;
import com.jimmy.vues.Calendrier;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Connexion() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		chargementDonneesUtilisateurSiConnecte(request);

		request.getServletContext().getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnexionUtilisateurForm connexionUtilisateur = new ConnexionUtilisateurForm(request, response);
		connexionUtilisateur.seConnecter();

		chargementDonneesUtilisateurSiConnecte(request);

		request.getServletContext().getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

	}

	private void chargementDonneesUtilisateurSiConnecte(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Boolean connecte = (Boolean) session.getAttribute("connecte");
		if (connecte != null && connecte) {

			Calendrier calendrier = new Calendrier();
			calendrier.chargementDuMoisEnCours(request, (Utilisateur) session.getAttribute("utilisateur"));

		}
	}
}
