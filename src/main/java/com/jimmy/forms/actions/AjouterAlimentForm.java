package com.jimmy.forms.actions;

import java.math.BigDecimal;

import com.jimmy.classes.Aliment;
import com.jimmy.db.AlimentDaoImpl;
import com.jimmy.enums.TypeAliment;
import com.jimmy.enums.UniteDeMesure;
import com.jimmy.exceptions.AjouterAlimentFormControleException;
import com.jimmy.forms.classes.AlimentForm;
import com.jimmy.util.Chaine;

import jakarta.servlet.http.HttpServletRequest;

public class AjouterAlimentForm {

	private HttpServletRequest request;
	private AlimentForm alimentForm;

	public AjouterAlimentForm(HttpServletRequest request) {

		this.request = request;

		alimentForm = initialiserAlimentForm();
		request.setAttribute("alimentForm", alimentForm);
	}

	private AlimentForm initialiserAlimentForm() {

		alimentForm = new AlimentForm();

		alimentForm.setTabTypeAliment(TypeAliment.values());
		alimentForm.setTabUniteDeMesure(UniteDeMesure.values());

		return alimentForm;

	}

	public boolean ajouter() {

		Aliment aliment = null;

		try {
			aliment = controlerDonnees();
		} catch (Exception e) {

			alimentForm.setErreurAjout(e.getMessage());

			return false;

		}

		if (aliment != null) {

			AlimentDaoImpl alimentDaoImpl = new AlimentDaoImpl();
			try {
				alimentDaoImpl.create(aliment);
			} catch (Exception e) {

				alimentForm.setErreurAjout(e.getMessage());

				return false;

			}

			return true;

		} else {

			return false;

		}
	}

	private Aliment controlerDonnees() throws AjouterAlimentFormControleException {

		Aliment aliment = null;

		TypeAliment typeAliment = TypeAliment.valueOf(request.getParameter("type_aliment_param"));
		String nom = request.getParameter("nom_param");
		String kCal = request.getParameter("kcal_param");
		String proteines = request.getParameter("proteines_param");
		UniteDeMesure uniteDeMesure = UniteDeMesure.valueOf(request.getParameter("unite_de_mesure_param"));

		alimentForm.setTypeAliment(typeAliment);
		alimentForm.setNom(nom);
		alimentForm.setKiloCalForm(kCal);
		alimentForm.setProteinesEnGrammesForm(proteines);
		alimentForm.setUniteDeMesure(uniteDeMesure);

		if (nom.isEmpty()) {
			throw new AjouterAlimentFormControleException("Veuillez saisir un nom");
		}

		if (!Chaine.verifierCaracteresAutorises("nomAliment", nom)) {
			throw new AjouterAlimentFormControleException("Caractères autorisés = a-z, A-Z, éàèùâêîôû et -");
		}

		AlimentDaoImpl alimentDaoImpl = new AlimentDaoImpl();
		try {
			aliment = alimentDaoImpl.getByNom(nom);
		} catch (Exception e) {
			throw new AjouterAlimentFormControleException("Erreur DB recherche aliment " + nom);
		}

		if (aliment != null) {
			throw new AjouterAlimentFormControleException("Aliment " + nom + " existe déjà");
		}

		int nbKCal;

		try {
			nbKCal = Integer.parseUnsignedInt(kCal);
		} catch (NumberFormatException e) {

			throw new AjouterAlimentFormControleException("Veuillez saisir une valeur numérique, entière et positive");
		}

		if (nbKCal == 0) {
			throw new AjouterAlimentFormControleException("Veuillez saisir une valeur différente de 0");
		}

		if (proteines.isBlank()) {
			throw new AjouterAlimentFormControleException("Veuillez saisir une valeur dans le champs proteines");
		}

		BigDecimal nbProteines = null;
		try {
			nbProteines = new BigDecimal(proteines);
		} catch (Exception e) {
			throw new AjouterAlimentFormControleException(
					"Veuillez saisir une valeur décimale valide (99.9) dans le champs protéines");
		}

		if (nbProteines.compareTo(BigDecimal.ZERO) == 0) {
			throw new AjouterAlimentFormControleException("Veuillez saisir une valeur différente de 0");
		}

		if (nbProteines.compareTo(BigDecimal.ZERO) < 0) {
			throw new AjouterAlimentFormControleException("Veuillez saisir une valeur positive");
		}

		if (nbProteines.scale() > 1) {
			throw new AjouterAlimentFormControleException("Maximum 1 décimale dans le champs protéines");
		}

		if (nbProteines.compareTo(BigDecimal.valueOf(100)) >= 0) {
			throw new AjouterAlimentFormControleException("La valeur du champs protéines doit être < 100");
		}

		aliment = new Aliment(typeAliment, nom, nbKCal, nbProteines, uniteDeMesure);

		return aliment;

	}
}
