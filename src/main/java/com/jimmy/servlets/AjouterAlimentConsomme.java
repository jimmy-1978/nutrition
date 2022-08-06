package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.actions.AjouterAlimentConsommeForm;
import com.jimmy.forms.actions.ConnexionUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjouterAlimentConsomme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjouterAlimentConsomme() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterAlimentConsommeForm ajouterAlimentConsommeForm = new AjouterAlimentConsommeForm(request, false);

		boolean creationOk = ajouterAlimentConsommeForm.ajouter();
		if (creationOk) {
			ConnexionUtilisateurForm connexionUtilisateurForm = new ConnexionUtilisateurForm(request);
			connexionUtilisateurForm.chargementDonneesUtilisateurSiConnecte();
			request.getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/ajouterAlimentConsomme.jsp").forward(request, response);
		}
	}
}
