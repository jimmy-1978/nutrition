package com.jimmy.vues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.classes.Utilisateur;

import jakarta.servlet.http.HttpServletRequest;

public class Calendrier {

	void Calendrier() {
	}

	public void chargementJournees(HttpServletRequest request, Utilisateur utilisateur) {

		// Par défaut on charge à partir de la date du jour

		LocalDate dateFrom = LocalDate.now();
		LocalDate dateTo = dateFrom.plusDays(28); // A changer par n semaines (complètes) couvrant le mois !!!!!!

		chargementJournees(request, utilisateur, dateFrom, dateTo);

	}

	public void chargementJournees(HttpServletRequest request, Utilisateur utilisateur, LocalDate dateFrom,
			LocalDate dateTo) {

		List<Journee> listeJournee = new ArrayList<Journee>();

		for (LocalDate date = dateFrom; date.isBefore(dateTo.plusDays(1)); date = date.plusDays(1)) {
			listeJournee.add(new Journee(date));
		}

		request.setAttribute("listeJournee", listeJournee);

	}
}
