package com.jimmy.forms;

import java.time.LocalDate;
import java.util.Enumeration;

import com.jimmy.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AjouterForm {

	public void ajouter(HttpServletRequest request) {

		Enumeration<String> enumParametreNom = request.getParameterNames();
		int numeroSemaine = 0;
		String typeAjout = null;

		String parametre = null;
		while (enumParametreNom.hasMoreElements()) {
			parametre = enumParametreNom.nextElement();
			if (parametre.contains("select_ajouter_semaine")) {
				typeAjout = request.getParameter(parametre);
				String chiffreSemaine = String.valueOf(parametre.charAt(23)); // On récupère le numéro de la semaine
																				// concernée
				numeroSemaine = Integer.valueOf(chiffreSemaine);
			}
		}

		request.setAttribute("typeAjout", typeAjout);

		HttpSession session = request.getSession();

		int anneeEnCours = (int) session.getAttribute("anneeEnCours");
		int moisEnCours = (int) session.getAttribute("moisEnCours");

		LocalDate[] joursDeLaSemaine = DateUtil.getJoursDeLaSemaine(anneeEnCours, moisEnCours, numeroSemaine);

		request.setAttribute("joursDeLaSemaine", joursDeLaSemaine);

	}
}
