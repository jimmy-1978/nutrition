package com.jimmy.util;

import java.time.LocalDate;

public class DateUtil {

	private static String[] tabNomCourtJour = { "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim" };
	private static String[] tabNomLongJour = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi",
			"Dimanche" };
	private static String[] tabNomLongMois = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août",
			"Septembre", "Octobre", "Novembre", "Décembre" };

	public static String rechercherNomCourtJour(LocalDate date) {

		return rechercherNomCourtJour(date.getDayOfWeek().getValue());

	}

	public static String rechercherNomCourtJour(int numJour) {

		return tabNomCourtJour[numJour - 1];

	}

	public static String rechercherNomLongJour(LocalDate date) {

		return rechercherNomLongJour(date.getDayOfWeek().getValue());

	}

	public static String rechercherNomLongJour(int numJour) {

		return tabNomLongJour[numJour - 1];

	}

	public static String rechercherNomLongMois(LocalDate date) {

		return rechercherNomLongMois(date.getMonthValue());
	}

	public static String rechercherNomLongMois(int i) {

		return tabNomLongMois[i - 1];
	}

}
