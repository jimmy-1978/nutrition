package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.actions.ConnecterUtilisateurForm;
import com.jimmy.forms.actions.CreerAlimentForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreerAliment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreerAliment() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CreerAlimentForm creerAlimentForm = new CreerAlimentForm(request);
		boolean creationOk = creerAlimentForm.ajouter();

		if (creationOk) {
			ConnecterUtilisateurForm connexionUtilisateurForm = new ConnecterUtilisateurForm(request);
			connexionUtilisateurForm.chargementDonneesUtilisateurSiConnecte();
			request.getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/creerAliment.jsp").forward(request, response);
		}

	}

}
