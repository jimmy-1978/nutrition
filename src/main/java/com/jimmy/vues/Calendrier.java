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

	public void chargementDuMoisEnCours(HttpServletRequest request, Utilisateur utilisateur) {

		LocalDate dateFrom = LocalDate.now();
		LocalDate dateTo = LocalDate.now();

		// On recule jusqu'au premier du mois
		while (dateFrom.getDayOfMonth() != 1) {
			dateFrom = dateFrom.minusDays(1);
		}

		// On avance jusqu'au dernier du mois
		while (dateTo.plusDays(1).getDayOfMonth() != 1) {
			dateTo = dateTo.plusDays(1);
		}

		chargementJournees(request, utilisateur, dateFrom, dateTo);

	}

	private void chargementJournees(HttpServletRequest request, Utilisateur utilisateur, LocalDate dateFrom,
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

		Mois mois = new Mois(DateUtil.rechercherNomLongMois(dateFrom.plusWeeks(1)), dateFrom.plusWeeks(1).getYear(),
				listeSemaine);

		request.setAttribute("mois", mois);

		String[] tabNomLongJour = new String[7];

		for (int i = 0; i < tabNomLongJour.length; i++) {
			tabNomLongJour[i] = DateUtil.rechercherNomLongJour(i + 1);
		}

		request.setAttribute("tabNomLongJour", tabNomLongJour);

	}
}
