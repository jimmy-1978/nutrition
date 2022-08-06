package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.actions.DeconnecterUtilisateurForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeconnecterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeconnecterUtilisateur() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DeconnecterUtilisateurForm deconnexionUtilisateurForm = new DeconnecterUtilisateurForm(request);
		deconnexionUtilisateurForm.seDeconnecter();

		request.getServletContext().getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

	}

}
