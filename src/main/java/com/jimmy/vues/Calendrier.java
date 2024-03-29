package com.jimmy.vues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.forms.classes.UtilisateurForm;
import com.jimmy.util.DateUtil;
import com.jimmy.util.GestionSession;

import jakarta.servlet.http.HttpServletRequest;

public class Calendrier {

	private HttpServletRequest request;

	public Calendrier(HttpServletRequest request) {
		this.request = request;
	}

	public void chargementDuMoisEnCours() {

		Integer anneeEnCours = (Integer) GestionSession.recupererAttribut(request, "anneeEnCours");
		Integer moisEnCours = (Integer) GestionSession.recupererAttribut(request, "moisEnCours");

		if (anneeEnCours != null && moisEnCours != null) {
			chargementDuMois(anneeEnCours, moisEnCours);
		} else {
			chargementDuMois(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
		}
	}

	public void chargementDuMoisSuivant() {

		int anneeEnCours = (int) GestionSession.recupererAttribut(request, "anneeEnCours");
		int moisEnCours = (int) GestionSession.recupererAttribut(request, "moisEnCours");

		LocalDate date = LocalDate.of(anneeEnCours, moisEnCours, 1);
		date = date.plusMonths(1);

		chargementDuMois(date.getYear(), date.getMonthValue());

	}

	public void chargementDuMoisPrecedent() {

		int anneeEnCours = (int) GestionSession.recupererAttribut(request, "anneeEnCours");
		int moisEnCours = (int) GestionSession.recupererAttribut(request, "moisEnCours");

		LocalDate date = LocalDate.of(anneeEnCours, moisEnCours, 1);
		date = date.minusMonths(1);

		chargementDuMois(date.getYear(), date.getMonthValue());

	}

	private void chargementDuMois(int annee, int mois) {

		LocalDate dateFrom = LocalDate.of(annee, mois, 1); // Le premier jour du mois
		LocalDate dateTo = dateFrom.plusMonths(1).minusDays(1); // Le dernier jour du mois

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

		chargementJournees(dateFrom, dateTo);

	}

	private void chargementJournees(LocalDate dateFrom, LocalDate dateTo) {

		UtilisateurForm utilisateurForm = (UtilisateurForm) GestionSession.recupererAttribut(request,
				"utilisateurForm");

		int numeroDeSemaine = 1;
		Journee[] tabJournee = null;
		List<Semaine> listeSemaine = new ArrayList<Semaine>();

		for (LocalDate date = dateFrom; date.isBefore(dateTo.plusDays(1)); date = date.plusDays(1)) {

			if (date.getDayOfWeek().getValue() == 1) {
				tabJournee = new Journee[7];
			}
			tabJournee[date.getDayOfWeek().getValue() - 1] = new Journee(utilisateurForm.getNom(), date);

			if (date.getDayOfWeek().getValue() == 7) {
				listeSemaine.add(new Semaine(numeroDeSemaine, tabJournee));
				numeroDeSemaine++;
			}
		}

		Mois mois = new Mois(dateFrom.plusWeeks(1).getMonthValue(),
				DateUtil.rechercherNomLongMois(dateFrom.plusWeeks(1)), dateFrom.plusWeeks(1).getYear(), listeSemaine);

		String[] tabNomLongJour = new String[7];

		for (int i = 0; i < tabNomLongJour.length; i++) {
			tabNomLongJour[i] = DateUtil.rechercherNomLongJour(i + 1);
		}

		request.setAttribute("mois", mois);
		request.setAttribute("tabNomLongJour", tabNomLongJour);

		GestionSession.ajouterAttribut(request, "anneeEnCours", mois.getAnnee());
		GestionSession.ajouterAttribut(request, "moisEnCours", mois.getNumero());

	}
}
