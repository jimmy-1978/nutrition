package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.actions.ConnexionUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Nutrition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Nutrition() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnexionUtilisateurForm connexionUtilisateurForm = new ConnexionUtilisateurForm(request);
		connexionUtilisateurForm.chargementDonneesUtilisateurSiConnecte();

		request.getServletContext().getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

	}
}
