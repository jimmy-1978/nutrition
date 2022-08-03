package com.jimmy.forms;

import java.time.LocalDate;

import com.jimmy.classes.Activite;
import com.jimmy.db.ActiviteDaoImpl;
import com.jimmy.enums.TypeActivite;
import com.jimmy.exceptions.ExceptionControleCreationActivite;
import com.jimmy.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AjouterActiviteForm {

	private HttpServletRequest request;
	private ActiviteForm activiteForm;

	public AjouterActiviteForm(HttpServletRequest request) {

		this.request = request;
		activiteForm = initialiserActiviteForm();
		request.setAttribute("activiteForm", activiteForm);
	}

	private ActiviteForm initialiserActiviteForm() {

		// On initialise le tableau contenant les jours de la semaine concernée (dans
		// "activiteForm")

		HttpSession session = request.getSession();

		int anneeEnCours = (int) session.getAttribute("anneeEnCours");
		int moisEnCours = (int) session.getAttribute("moisEnCours");
		int numeroSemaine = (int) session.getAttribute("numeroSemaine");

		activiteForm = new ActiviteForm();
		activiteForm.setJoursDeLaSemaineForm(DateUtil.getJoursDeLaSemaine(anneeEnCours, moisEnCours, numeroSemaine));
		activiteForm.setTabTypeActivite(TypeActivite.values());

		return activiteForm;

	}

	public boolean ajouter() {

		Activite activite = null;

		try {
			activite = controlerDonnees();
		} catch (Exception e) {

			activiteForm.setErreurAjout(e.getMessage());

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

	private Activite controlerDonnees() throws ExceptionControleCreationActivite {

		Activite activite = null;

		String nbCaloriesBruleesForm = request.getParameter("nb_calories_param");
		TypeActivite typeActivite = TypeActivite.valueOf(request.getParameter("type_activite_param"));
		LocalDate dateActivite = DateUtil.conversionDateRequete(request.getParameter("date_activite_param"));

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

		UtilisateurForm utilisateurForm = (UtilisateurForm) request.getSession().getAttribute("utilisateurForm");
		activite = new Activite(utilisateurForm.getNom(), dateActivite, typeActivite, nbCaloriesBrulees);

		return activite;

	}
}
