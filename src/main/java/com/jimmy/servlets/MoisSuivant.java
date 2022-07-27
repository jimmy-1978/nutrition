package com.jimmy.servlets;

import java.io.IOException;

import com.jimmy.vues.Calendrier;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MoisSuivant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MoisSuivant() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Calendrier calendrier = new Calendrier();
		calendrier.chargementDuMoisSuivant(request);

		request.getServletContext().getRequestDispatcher("/WEB-INF/nutrition.jsp").forward(request, response);

	}

}
