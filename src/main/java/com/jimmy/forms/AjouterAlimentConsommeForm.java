package com.jimmy.forms;

import java.time.LocalDate;
import java.util.List;

import com.jimmy.classes.Aliment;
import com.jimmy.classes.AlimentConsomme;
import com.jimmy.classes.Utilisateur;
import com.jimmy.db.AlimentConsommeDaoImpl;
import com.jimmy.db.AlimentDaoImpl;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.enums.TypeAliment;
import com.jimmy.exceptions.ControleCreationAlimentConsommeException;
import com.jimmy.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AjouterAlimentConsommeForm {

	private HttpServletRequest request;
	private AlimentConsommeForm alimentConsommeForm;
	TypeAliment filtreListeAliment;

	public AjouterAlimentConsommeForm(HttpServletRequest request, boolean filtrer) {

		this.request = request;

		if (filtrer) {
			filtreListeAliment = TypeAliment.valueOf(request.getParameter("filtre_param"));
			request.getSession().setAttribute("filtreListeAlimentConsomme", filtreListeAliment.toString());
		} else {
			String filtre = (String) request.getSession().getAttribute("filtreListeAlimentConsomme");
			if (filtre != null) {
				filtreListeAliment = TypeAliment.valueOf(filtre);
			} else {
				filtreListeAliment = null;
			}
		}

		alimentConsommeForm = initialiserAlimentConsommeForm();
		request.setAttribute("alimentConsommeForm", alimentConsommeForm);
	}

	private AlimentConsommeForm initialiserAlimentConsommeForm() {

		// On initialise le tableau contenant les jours de la semaine concernée (dans
		// "activiteForm")

		HttpSession session = request.getSession();

		int anneeEnCours = (int) session.getAttribute("anneeEnCours");
		int moisEnCours = (int) session.getAttribute("moisEnCours");
		int numeroSemaine = (int) session.getAttribute("numeroSemaine");

		alimentConsommeForm = new AlimentConsommeForm();
		alimentConsommeForm
				.setTabJoursDeLaSemaineForm(DateUtil.getJoursDeLaSemaine(anneeEnCours, moisEnCours, numeroSemaine));
		AlimentDaoImpl alimentDaoImpl = new AlimentDaoImpl();

		try {
			List<Aliment> listeAliment = alimentDaoImpl.getAll();
			for (Aliment aliment : listeAliment) {
				if (filtreListeAliment == null
						|| (filtreListeAliment != null && aliment.getTypeAliment().equals(filtreListeAliment))) {
					alimentConsommeForm.ajoutAlimentDansListeNomAliment(aliment.getNom());
				}
			}

		} catch (Exception e) {

			alimentConsommeForm.setErreurAjout(e.getMessage());

		}
		return alimentConsommeForm;

	}

	public boolean ajouter() {

		AlimentConsomme alimentConsomme = null;

		try {
			alimentConsomme = controlerDonnees();

		} catch (Exception e) {

			alimentConsommeForm.setErreurAjout(e.getMessage());

			return false;

		}

		if (alimentConsomme == null) {

			return false;

		}

		AlimentConsommeDaoImpl alimentConsommeDaoImpl = new AlimentConsommeDaoImpl();

		try {
			alimentConsommeDaoImpl.create(alimentConsomme);
		} catch (Exception e) {

			alimentConsommeForm.setErreurAjout("Erreur DB lors de la tentative de création de l'aliment consommé");

			return false;
		}

		return true;

	}

	private AlimentConsomme controlerDonnees() throws ControleCreationAlimentConsommeException {

		AlimentConsomme alimentConsomme = null;

		String nomAliment = request.getParameter("nom_aliment_param");
		LocalDate date = DateUtil.conversionDateRequete(request.getParameter("date_aliment_param"));
		String quantiteForm = request.getParameter("quantite_aliment_param");

		alimentConsommeForm.setNom(nomAliment);
		alimentConsommeForm.setDate(date);
		alimentConsommeForm.setQuantiteForm(quantiteForm);

		int quantite = 0;
		try {
			quantite = Integer.valueOf(quantiteForm);
		} catch (Exception e) {
			throw new ControleCreationAlimentConsommeException("Veuillez saisir un nombre entier");
		}

		if (quantite == 0) {
			throw new ControleCreationAlimentConsommeException("Veuillez saisir un nombre entier différent de zéro");
		}

		UtilisateurForm utilisateurForm = (UtilisateurForm) request.getSession().getAttribute("utilisateurForm");
		UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurDaoImpl.getByNom(utilisateurForm.getNom());
		} catch (Exception e) {

			throw new ControleCreationAlimentConsommeException(
					"Erreur DB recherche utilisateur " + utilisateurForm.getNom());

		}

		if (utilisateur == null) {

			throw new ControleCreationAlimentConsommeException(
					"Utilisateur " + utilisateurForm.getNom() + " inexistant en DB");
		}

		AlimentDaoImpl alimentDaoImpl = new AlimentDaoImpl();
		Aliment aliment = null;
		try {
			aliment = alimentDaoImpl.getByNom(nomAliment);
		} catch (Exception e) {

			throw new ControleCreationAlimentConsommeException("Erreur DB recherche aliment " + nomAliment);

		}

		if (aliment == null) {

			throw new ControleCreationAlimentConsommeException("Aliment " + nomAliment + " inexistant en DB");
		}

		alimentConsomme = new AlimentConsomme();
		alimentConsomme.setId(aliment.getId());
		alimentConsomme.setIdUtilisateur(utilisateur.getId());
		alimentConsomme.setDate(date);
		alimentConsomme.setQuantite(quantite);

		return alimentConsomme;

	}

}
