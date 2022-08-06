package com.jimmy.forms.actions;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;

public class AjouterForm {

	public String ajouter(HttpServletRequest request) {

		// On détermine sur quelle semaine on est (1, 2, 3, etc.) sur la jsp

		Enumeration<String> enumParametreNom = request.getParameterNames();
		int numeroSemaine = 0;
		String typeAjout = null;

		String parametre = null;
		while (enumParametreNom.hasMoreElements()) {
			parametre = enumParametreNom.nextElement();
			if (parametre.contains("select_ajouter_semaine")) {
				typeAjout = request.getParameter(parametre); // "Activité", etc.
				String chiffreSemaine = String.valueOf(parametre.charAt(23)); // On récupère le numéro de la semaine
				numeroSemaine = Integer.valueOf(chiffreSemaine);
			}
		}

		// Au sauvegarde en session car sur le formulaire de saisie suivant on ne sait
		// plus sur quelle semaine on est..

		request.getSession().setAttribute("numeroSemaine", numeroSemaine);

		return typeAjout;

	}
}
