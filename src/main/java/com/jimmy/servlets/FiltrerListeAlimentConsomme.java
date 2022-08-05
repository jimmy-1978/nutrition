package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.forms.AjouterAlimentConsommeForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FiltrerListeAlimentConsomme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FiltrerListeAlimentConsomme() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjouterAlimentConsommeForm ajouterAlimentConsommeForm = new AjouterAlimentConsommeForm(request, true);
		request.getRequestDispatcher("/WEB-INF/ajouterAlimentConsomme.jsp").forward(request, response);

	}

}
