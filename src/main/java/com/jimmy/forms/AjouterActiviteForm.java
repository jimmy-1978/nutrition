package com.jimmy.forms;

import java.time.LocalDate;

import com.jimmy.classes.Activite;
import com.jimmy.classes.Utilisateur;
import com.jimmy.db.ActiviteDaoImpl;
import com.jimmy.enums.TypeActivite;
import com.jimmy.exceptions.ExceptionControleCreationActivite;
import com.jimmy.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;

public class AjouterActiviteForm {
	public boolean ajouter(HttpServletRequest request) {

		Activite activite = null;

		try {
			activite = controlerDonnees(request);
		} catch (Exception e) {

			request.setAttribute("erreurAjouterActivite", e.getMessage());

			return false;

		}

		if (activite != null) {

			ActiviteDaoImpl activiteDaoImpl = new ActiviteDaoImpl();
			activiteDaoImpl.create(activite);

			return true;

		} else {

			return false;

		}

	}

	private Activite controlerDonnees(HttpServletRequest request) throws ExceptionControleCreationActivite {

		Activite activite = null;

		String nbCaloriesBruleesForm = request.getParameter("nb_calories_param");
		TypeActivite typeActivite = TypeActivite.valueOf(request.getParameter("type_activite_param"));
		LocalDate dateActivite = DateUtil.conversionDateRequete(request.getParameter("date_activite_param"));

		ActiviteForm activiteForm = (ActiviteForm) request.getSession().getAttribute("activiteForm");
		activiteForm.setNbCaloriesBruleesForm(nbCaloriesBruleesForm);
		activiteForm.setTypeActivite(typeActivite);
		activiteForm.setDateActivite(dateActivite);

		int nbCaloriesBrulees;

		try {
			nbCaloriesBrulees = Integer.parseUnsignedInt(nbCaloriesBruleesForm);
		} catch (NumberFormatException e) {

			throw new ExceptionControleCreationActivite("Veuillez saisir une valeur numérique, entière et positive");
		}

		if (nbCaloriesBrulees == 0) {
			throw new ExceptionControleCreationActivite("Veuillez saisir une valeur différente de 0");
		}

		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		activite = new Activite(utilisateur.getNom(), dateActivite, typeActivite, nbCaloriesBrulees);

		return activite;

	}
}
