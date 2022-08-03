package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.AjouterActiviteForm;
import com.jimmy.forms.AjouterForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Ajouter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ajouter() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterForm ajouterForm = new AjouterForm();
		String typeAjout = ajouterForm.ajouter(request);

		if (typeAjout.equals("Activité")) {

			AjouterActiviteForm ajouterActiviteForm = new AjouterActiviteForm(request);

			request.getRequestDispatcher("/WEB-INF/ajouterActivite.jsp").forward(request, response);
		}

	}

}
