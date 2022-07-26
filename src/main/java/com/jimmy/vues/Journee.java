package com.jimmy.vues;

import java.time.LocalDate;
import java.util.List;

import com.jimmy.classes.Activite;
import com.jimmy.classes.Aliment;
import com.jimmy.util.JourDeLaSemaine;

public class Journee {
	private String nomUtilisateur;
	private LocalDate dateJournee;
	private List<Activite> listeActivite;
	private int totalCaloriesActivites;
	private List<Aliment> listeAliment;
	private int totalCaloriesAliments;

	public Journee() {
	}

	public Journee(LocalDate dateJournee) {
		this.dateJournee = dateJournee;
	}

	public LocalDate getDateJournee() {
		return dateJournee;
	}

	public String vueDate() {

		return JourDeLaSemaine.nomCourt(dateJournee) + "/" + dateJournee.getYear() + "-" + dateJournee.getMonthValue()
				+ "-" + dateJournee.getDayOfMonth();
	}

}
