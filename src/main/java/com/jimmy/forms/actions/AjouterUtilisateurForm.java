package com.jimmy.forms.actions;

import java.time.LocalDate;

import com.jimmy.classes.Utilisateur;
import com.jimmy.db.UtilisateurDaoImpl;
import com.jimmy.exceptions.AjouterUtilisateurFormControleException;
import com.jimmy.forms.classes.UtilisateurForm;
import com.jimmy.listes.Liste;
import com.jimmy.util.Chaine;
import com.jimmy.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;

public class AjouterUtilisateurForm {

	private HttpServletRequest request;
	private UtilisateurForm utilisateurForm;

	public AjouterUtilisateurForm(HttpServletRequest request) {

		this.request = request;
		utilisateurForm = initialiserUtilisateurForm();
		request.setAttribute("utilisateurForm", utilisateurForm);
	}

	private UtilisateurForm initialiserUtilisateurForm() {

		UtilisateurForm utilisateurForm = new UtilisateurForm();

		utilisateurForm.setListeGenre(Liste.getListeGenre());

		return utilisateurForm;

	}

	public boolean creerUtilisateur() {

		Utilisateur utilisateur = null;

		try {
			utilisateur = controleDonneesFormulaire();
		} catch (AjouterUtilisateurFormControleException e) {

			utilisateurForm.setErreurCreation(e.getMessage());

			return false;

		}

		UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();
		try {
			utilisateurDaoImpl.create(utilisateur);
		} catch (Exception e) {

			utilisateurForm.setErreurCreation(
					"Erreur DB lors de la tentative de création de l'utilisateur " + utilisateur.getNom());

			return false;

		}

		request.setAttribute("messageConnexion", "Utilisateur " + utilisateur.getNom() + " créé");

		return true;

	}

	private Utilisateur controleDonneesFormulaire() throws AjouterUtilisateurFormControleException {

		Utilisateur utilisateur = null;

		String nom = request.getParameter("nom_param");
		String motDePasse = request.getParameter("mot_de_passe_param");
		String confMotDePasse = request.getParameter("conf_mot_de_passe_param");
		String sexe = request.getParameter("sexe_param");
		String dateParam = request.getParameter("date_de_naissance_param"); // Ex. : 2022-06-29
		LocalDate dateDeNaissance = DateUtil.conversionDateRequete(dateParam);

		utilisateurForm.setNom(nom);
		utilisateurForm.setMotDePasse(motDePasse);
		utilisateurForm.setSexe(sexe);
		utilisateurForm.setDateDeNaissance(dateDeNaissance);

		if (nom.isBlank()) {
			throw new AjouterUtilisateurFormControleException("Le nom est obligatoire");
		}

		if (!Chaine.verifierCaracteresAutorises("nomUtilisateur", nom)) {
			throw new AjouterUtilisateurFormControleException("Caractères autorisés = a-z, A-Z, 0-9, éàèùâêîôû et -_");
		}

		if (motDePasse.isBlank()) {
			throw new AjouterUtilisateurFormControleException("Le mot de passe est obligatoire");
		}

		if (!motDePasse.equals(confMotDePasse)) {
			throw new AjouterUtilisateurFormControleException("Les deux mots de passe saisis ne correspondent pas");
		}

		if (dateDeNaissance == null) {
			throw new AjouterUtilisateurFormControleException("La date de naissance est obligatoire");
		}

		UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();

		try {
			utilisateur = utilisateurDaoImpl.getByNom(nom);
		} catch (Exception e) {
			throw new AjouterUtilisateurFormControleException("Erreur technique accès DB");
		}

		if (utilisateur != null) {
			throw new AjouterUtilisateurFormControleException("L'utilisateur existe déjà");
		}

		utilisateur = new Utilisateur(nom, motDePasse, sexe, dateDeNaissance);

		return utilisateur;

	}

}
