package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.actions.AjouterAlimentForm;
import com.jimmy.forms.actions.ConnecterUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjouterAliment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjouterAliment() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterAlimentForm creerAlimentForm = new AjouterAlimentForm(request);
		boolean creationOk = creerAlimentForm.ajouter();

		if (creationOk) {
			ConnecterUtilisateurForm connexionUtilisateurForm = new ConnecterUtilisateurForm(request);
			connexionUtilisateurForm.chargementDonneesUtilisateurSiConnecte();
			request.getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/ajouterAliment.jsp").forward(request, response);
		}

	}

}
