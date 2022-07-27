package com.jimmy.util;

import java.time.LocalDate;

public class DateUtil {

	private static String[] tabNomCourt = { "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim" };
	private static String[] tabNomLong = { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };

	public static String rechercherNomCourtJour(LocalDate date) {

		return rechercherNomCourtJour(date.getDayOfWeek().getValue());

	}

	public static String rechercherNomCourtJour(int numJour) {

		return tabNomCourt[numJour - 1];

	}

	public static String rechercherNomLongJour(LocalDate date) {

		return rechercherNomLongJour(date.getDayOfWeek().getValue());

	}

	public static String rechercherNomLongJour(int numJour) {

		return tabNomLong[numJour - 1];

	}

}
