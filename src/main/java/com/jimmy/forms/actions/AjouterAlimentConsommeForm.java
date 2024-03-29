package com.jimmy.forms.actions;

import java.time.LocalDate;
import java.util.List;

import com.jimmy.classes.Aliment;
import com.jimmy.classes.AlimentConsomme;
import com.jimmy.classes.Utilisateur;
import com.jimmy.db.AlimentConsommeDaoImpl;
import com.jimmy.db.AlimentDaoImpl;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.enums.TypeAliment;
import com.jimmy.exceptions.AjouterAlimentConsommeFormControleException;
import com.jimmy.forms.classes.AlimentConsommeForm;
import com.jimmy.forms.classes.UtilisateurForm;
import com.jimmy.util.DateUtil;
import com.jimmy.util.GestionSession;

import jakarta.servlet.http.HttpServletRequest;

public class AjouterAlimentConsommeForm {

	private HttpServletRequest request;
	private AlimentConsommeForm alimentConsommeForm;
	TypeAliment filtreListeAliment;

	public AjouterAlimentConsommeForm(HttpServletRequest request, boolean filtreMaj) {

		this.request = request;

		if (filtreMaj) { // Soit on vient de modifier le filtre
			filtreListeAliment = TypeAliment.valueOf(request.getParameter("filtre_param"));
			GestionSession.ajouterAttribut(request, "filtreListeAlimentConsomme", filtreListeAliment.toString());

		} else {
			String filtre = (String) GestionSession.recupererAttribut(request, "filtreListeAlimentConsomme");
			if (filtre != null) { // Soit il est déjà en session
				filtreListeAliment = TypeAliment.valueOf(filtre);
			} else {
				TypeAliment[] tabTypeAliment = TypeAliment.values();
				filtreListeAliment = tabTypeAliment[0]; // Soit on prend le filtre par défaut (1er du tableau)
				GestionSession.ajouterAttribut(request, "filtreListeAlimentConsomme", filtreListeAliment.toString());
			}
		}

		alimentConsommeForm = initialiserAlimentConsommeForm();
		request.setAttribute("alimentConsommeForm", alimentConsommeForm);
	}

	private AlimentConsommeForm initialiserAlimentConsommeForm() {

		// On initialise le tableau contenant les jours de la semaine concernée (dans
		// "activiteForm")

		int anneeEnCours = (int) GestionSession.recupererAttribut(request, "anneeEnCours");
		int moisEnCours = (int) GestionSession.recupererAttribut(request, "moisEnCours");
		int numeroSemaine = (int) GestionSession.recupererAttribut(request, "numeroSemaine");

		alimentConsommeForm = new AlimentConsommeForm();

		alimentConsommeForm.setTabTypeAliment(TypeAliment.values());

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

		GestionSession.enleverAttribut(request, "filtreListeAlimentConsomme");

		return true;

	}

	private AlimentConsomme controlerDonnees() throws AjouterAlimentConsommeFormControleException {

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
			throw new AjouterAlimentConsommeFormControleException("Veuillez saisir un nombre entier");
		}

		if (quantite == 0) {
			throw new AjouterAlimentConsommeFormControleException("Veuillez saisir un nombre entier différent de zéro");
		}

		UtilisateurForm utilisateurForm = (UtilisateurForm) GestionSession.recupererAttribut(request,
				"utilisateurForm");
		UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurDaoImpl.getByNom(utilisateurForm.getNom());
		} catch (Exception e) {

			throw new AjouterAlimentConsommeFormControleException(
					"Erreur DB recherche utilisateur " + utilisateurForm.getNom());

		}

		if (utilisateur == null) {

			throw new AjouterAlimentConsommeFormControleException(
					"Utilisateur " + utilisateurForm.getNom() + " inexistant en DB");
		}

		AlimentDaoImpl alimentDaoImpl = new AlimentDaoImpl();
		Aliment aliment = null;
		try {
			aliment = alimentDaoImpl.getByNom(nomAliment);
		} catch (Exception e) {

			throw new AjouterAlimentConsommeFormControleException("Erreur DB recherche aliment " + nomAliment);

		}

		if (aliment == null) {

			throw new AjouterAlimentConsommeFormControleException("Aliment " + nomAliment + " inexistant en DB");
		}

		alimentConsomme = new AlimentConsomme();
		alimentConsomme.setId(aliment.getId());
		alimentConsomme.setIdUtilisateur(utilisateur.getId());
		alimentConsomme.setDate(date);
		alimentConsomme.setQuantite(quantite);

		return alimentConsomme;

	}

}
