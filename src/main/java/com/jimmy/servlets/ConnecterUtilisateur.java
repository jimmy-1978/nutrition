package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.actions.ConnecterUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ConnecterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConnecterUtilisateur() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnecterUtilisateurForm connexionUtilisateurForm = new ConnecterUtilisateurForm(request);
		connexionUtilisateurForm.seConnecter();
		connexionUtilisateurForm.chargementDonneesUtilisateurSiConnecte();

		request.getServletContext().getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

	}
}
