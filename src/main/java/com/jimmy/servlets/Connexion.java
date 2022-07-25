package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.ConnexionUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Connexion() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getServletContext().getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnexionUtilisateurForm connexionUtilisateur = new ConnexionUtilisateurForm(request, response);
		connexionUtilisateur.seConnecter();

		request.getServletContext().getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

	}

}
