package com.jimmy.vues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.classes.Activite;
import com.jimmy.classes.Aliment;
import com.jimmy.classes.AlimentConsomme;
import com.jimmy.classes.Utilisateur;
import com.jimmy.db.ActiviteDaoImpl;
import com.jimmy.db.AlimentConsommeDaoImpl;
import com.jimmy.db.AlimentDaoImpl;
import com.jimmy.db.UtilisateurDaoImpl;

public class Journee {
	private LocalDate dateJournee;
	private List<Activite> listeActivite;
	private List<AlimentConsomme> listeAlimentConsomme;

	public Journee(String nomUtilisateur, LocalDate dateJournee) {
		this.dateJournee = dateJournee;
		ActiviteDaoImpl activiteDaoImpl = new ActiviteDaoImpl();
		listeActivite = activiteDaoImpl.getByNomAndBetweenDateFromAndDateTo(nomUtilisateur, dateJournee, dateJournee);
		AlimentConsommeDaoImpl alimentConsommeDaoImpl = new AlimentConsommeDaoImpl();
		try {
			UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();
			Utilisateur utilisateur = utilisateurDaoImpl.getByNom(nomUtilisateur);
			listeAlimentConsomme = alimentConsommeDaoImpl
					.getByIdUtilisateurAndBetweenDateFromAndDateTo(utilisateur.getId(), dateJournee, dateJournee);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public LocalDate getDateJournee() {
		return dateJournee;
	}

	public List<String> vueLigne() {
		List<String> listeLigne = new ArrayList<String>();
		String ligne = null;
		ligne = String.valueOf(dateJournee.getDayOfMonth());
		listeLigne.add(ligne);
		if (listeActivite != null && listeActivite.size() != 0) {
			for (Activite activite : listeActivite) {
				ligne = activite.getTypeActivite() + " Kcal = " + activite.getNbCaloriesBrulees();
				listeLigne.add(ligne);
			}
		}

		if (listeAlimentConsomme != null && listeAlimentConsomme.size() != 0) {
			AlimentDaoImpl alimentDaoImpl = new AlimentDaoImpl();
			Aliment aliment = null;
			for (AlimentConsomme alimentConsomme : listeAlimentConsomme) {

				try {
					aliment = alimentDaoImpl.getById(alimentConsomme.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}

				ligne = aliment.getNom() + " : " + alimentConsomme.getQuantite() + " " + aliment.getUniteDeMesure();
				listeLigne.add(ligne);
			}
		}

		return listeLigne;

	}

}
