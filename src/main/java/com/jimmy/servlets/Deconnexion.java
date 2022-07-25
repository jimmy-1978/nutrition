package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.ConnexionUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Deconnexion() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnexionUtilisateurForm connexionUtilisateur = new ConnexionUtilisateurForm(request, response);
		connexionUtilisateur.seDeconnecter();

		request.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

	}

}
