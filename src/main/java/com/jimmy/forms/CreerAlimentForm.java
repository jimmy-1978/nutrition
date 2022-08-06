package com.jimmy.forms;

import com.jimmy.classes.Aliment;
import com.jimmy.db.AlimentDaoImpl;
import com.jimmy.enums.TypeAliment;
import com.jimmy.enums.UniteDeMesure;
import com.jimmy.exceptions.ControleCreationAlimentException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CreerAlimentForm {

	private HttpServletRequest request;
	private AlimentForm alimentForm;

	public CreerAlimentForm(HttpServletRequest request) {

		this.request = request;

		alimentForm = initialiserAlimentForm();
		request.setAttribute("alimentForm", alimentForm);
	}

	private AlimentForm initialiserAlimentForm() {

		HttpSession session = request.getSession();

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

	private Aliment controlerDonnees() throws ControleCreationAlimentException {

		Aliment aliment = null;

		TypeAliment typeAliment = TypeAliment.valueOf(request.getParameter("type_aliment_param"));
		String nom = request.getParameter("nom_param");
		String kCal = request.getParameter("kcal_param");
		UniteDeMesure uniteDeMesure = UniteDeMesure.valueOf(request.getParameter("unite_de_mesure_param"));

		alimentForm.setTypeAliment(typeAliment);
		alimentForm.setNom(nom);
		alimentForm.setKiloCalForm(kCal);
		alimentForm.setUniteDeMesure(uniteDeMesure);

		if (nom.isEmpty()) {
			throw new ControleCreationAlimentException("Veuillez saisir un nom");
		}

		AlimentDaoImpl alimentDaoImpl = new AlimentDaoImpl();
		try {
			aliment = alimentDaoImpl.getByNom(nom);
		} catch (Exception e) {
			throw new ControleCreationAlimentException("Erreur DB recherche aliment " + nom);
		}

		if (aliment != null) {
			throw new ControleCreationAlimentException("Aliment " + nom + " existe déjà");
		}

		int nbKCal;

		try {
			nbKCal = Integer.parseUnsignedInt(kCal);
		} catch (NumberFormatException e) {

			throw new ControleCreationAlimentException("Veuillez saisir une valeur numérique, entière et positive");
		}

		if (nbKCal == 0) {
			throw new ControleCreationAlimentException("Veuillez saisir une valeur différente de 0");
		}

		aliment = new Aliment(typeAliment, nom, nbKCal, uniteDeMesure);

		return aliment;

	}
}
