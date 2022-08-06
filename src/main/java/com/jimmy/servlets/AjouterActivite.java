package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.actions.AjouterActiviteForm;
import com.jimmy.forms.actions.ConnexionUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjouterActivite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjouterActivite() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterActiviteForm ajouterActiviteForm = new AjouterActiviteForm(request);
		boolean creationOk = ajouterActiviteForm.ajouter();

		if (creationOk) {
			ConnexionUtilisateurForm connexionUtilisateurForm = new ConnexionUtilisateurForm(request);
			connexionUtilisateurForm.chargementDonneesUtilisateurSiConnecte();
			request.getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/ajouterActivite.jsp").forward(request, response);
		}

	}
}
