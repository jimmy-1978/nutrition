package com.jimmy.forms;

import java.util.Enumeration;

import com.jimmy.enums.TypeActivite;
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

		ActiviteForm activiteForm = new ActiviteForm();
		activiteForm.setJoursDeLaSemaineForm(DateUtil.getJoursDeLaSemaine(anneeEnCours, moisEnCours, numeroSemaine));
		activiteForm.setTabTypeActivite(TypeActivite.values());

		request.getSession().setAttribute("activiteForm", activiteForm);

	}
}
