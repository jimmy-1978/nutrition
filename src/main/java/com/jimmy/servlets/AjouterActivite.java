package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.AjouterActiviteForm;
import com.jimmy.vues.Calendrier;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AjouterActivite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjouterActivite() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterActiviteForm ajouterActiviteForm = new AjouterActiviteForm();
		boolean creationOk = ajouterActiviteForm.ajouter(request);

		if (creationOk) {
			chargementDonneesUtilisateurSiConnecte(request);
			request.getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/ajouterActivite.jsp").forward(request, response);
		}

	}

	private void chargementDonneesUtilisateurSiConnecte(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Boolean connecte = (Boolean) session.getAttribute("connecte");
		if (connecte != null && connecte) {

			Calendrier calendrier = new Calendrier();
			calendrier.chargementDuMoisEnCours(request);

		}
	}

}
