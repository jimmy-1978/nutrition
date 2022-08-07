package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.actions.AjouterUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjouterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjouterUtilisateur() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterUtilisateurForm creationUtilisateurForm = new AjouterUtilisateurForm(request);

		request.getRequestDispatcher("/WEB-INF/ajouterUtilisateur.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterUtilisateurForm creationUtilisateurForm = new AjouterUtilisateurForm(request);
		boolean utilisateurCree = creationUtilisateurForm.creerUtilisateur();

		if (utilisateurCree) {

			request.getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

		} else {

			request.getRequestDispatcher("/WEB-INF/ajouterUtilisateur.jsp").forward(request, response);

		}
	}

}
