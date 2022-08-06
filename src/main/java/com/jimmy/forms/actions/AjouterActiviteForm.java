package com.jimmy.forms.actions;

import java.time.LocalDate;

import com.jimmy.classes.Activite;
import com.jimmy.db.ActiviteDaoImpl;
import com.jimmy.enums.TypeActivite;
import com.jimmy.exceptions.AjouterActiviteFormControleException;
import com.jimmy.forms.classes.ActiviteForm;
import com.jimmy.forms.classes.UtilisateurForm;
import com.jimmy.util.DateUtil;
import com.jimmy.util.GestionSession;

import jakarta.servlet.http.HttpServletRequest;

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

		int anneeEnCours = (int) GestionSession.recupererAttribut(request, "anneeEnCours");
		int moisEnCours = (int) GestionSession.recupererAttribut(request, "moisEnCours");
		int numeroSemaine = (int) GestionSession.recupererAttribut(request, "numeroSemaine");

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

	private Activite controlerDonnees() throws AjouterActiviteFormControleException {

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

			throw new AjouterActiviteFormControleException("Veuillez saisir une valeur numérique, entière et positive");
		}

		if (nbCaloriesBrulees == 0) {
			throw new AjouterActiviteFormControleException("Veuillez saisir une valeur différente de 0");
		}

		UtilisateurForm utilisateurForm = (UtilisateurForm) GestionSession.recupererAttribut(request,
				"utilisateurForm");
		activite = new Activite(utilisateurForm.getNom(), dateActivite, typeActivite, nbCaloriesBrulees);

		return activite;

	}
}
