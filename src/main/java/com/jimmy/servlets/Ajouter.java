package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.AjouterActiviteForm;
import com.jimmy.forms.AjouterAlimentConsommeForm;
import com.jimmy.forms.AjouterForm;
import com.jimmy.forms.CreerAlimentForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Ajouter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ajouter() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Si un jour on ajoute autre chose qu'un aliment il faudra :
		// - soit passer un paramètre
		// - soit créer des servlets dédiées

		CreerAlimentForm creerAlimentForm = new CreerAlimentForm(request);

		request.getRequestDispatcher("/WEB-INF/creerAliment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterForm ajouterForm = new AjouterForm();
		String typeAjout = ajouterForm.ajouter(request);

		switch (typeAjout) {

		case "Activité":

			AjouterActiviteForm ajouterActiviteForm = new AjouterActiviteForm(request);
			request.getRequestDispatcher("/WEB-INF/ajouterActivite.jsp").forward(request, response);

			break;

		case "AlimentConsomme":

			AjouterAlimentConsommeForm ajouterAlimentConsommeForm = new AjouterAlimentConsommeForm(request, false);
			request.getRequestDispatcher("/WEB-INF/ajouterAlimentConsomme.jsp").forward(request, response);

			break;

		}
	}
}
