package com.jimmy.vues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.classes.Utilisateur;
import com.jimmy.util.DateUtil;

import jakarta.servlet.http.HttpServletRequest;

public class Calendrier {

	void Calendrier() {
	}

	public void chargementJournees(HttpServletRequest request, Utilisateur utilisateur) {

		// Par défaut on charge à partir de la date du jour

		LocalDate dateFrom = LocalDate.now();
		LocalDate dateTo = dateFrom.plusDays(15); // A changer par n semaines (complètes) couvrant le mois !!!!!!

		chargementJournees(request, utilisateur, dateFrom, dateTo);

	}

	public void chargementJournees(HttpServletRequest request, Utilisateur utilisateur, LocalDate dateFrom,
			LocalDate dateTo) {

		// Si besoin, on sélectionne les jours précédents afin d'avoir une semaine
		// entière pour commencer

		while (dateFrom.getDayOfWeek().getValue() != 1) {
			dateFrom = dateFrom.minusDays(1);
		}

		// Si besoin, on sélectionne les jours suivants afin d'avoir une semaine
		// entière pour terminer

		while (dateTo.getDayOfWeek().getValue() != 7) {
			dateTo = dateTo.plusDays(1);
		}

		int numeroDeSemaine = 1;
		Journee[] tabJournee = null;
		List<Semaine> listeSemaine = new ArrayList<Semaine>();

		for (LocalDate date = dateFrom; date.isBefore(dateTo.plusDays(1)); date = date.plusDays(1)) {

			if (date.getDayOfWeek().getValue() == 1) {
				tabJournee = new Journee[7];
			}
			tabJournee[date.getDayOfWeek().getValue() - 1] = new Journee(utilisateur.getNom(), date);

			if (date.getDayOfWeek().getValue() == 7) {
				listeSemaine.add(new Semaine(numeroDeSemaine, tabJournee));
				numeroDeSemaine++;
			}
		}

		request.setAttribute("listeSemaine", listeSemaine);

		String[] tabNomLongJour = new String[7];

		for (int i = 0; i < tabNomLongJour.length; i++) {
			tabNomLongJour[i] = DateUtil.rechercherNomLongJour(i + 1);
		}

		request.setAttribute("tabNomLongJour", tabNomLongJour);

	}
}
