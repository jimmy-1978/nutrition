package com.jimmy.util;

public class Chaine {

	private static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String voyellesAvecAccent = "éàèùâêîôû";
	private static String chiffres = "0123456789";
	private static String tiret = "-";
	private static String caracteresSpeciauxAutorises = "_";

	public static boolean verifierCaracteresAutorises(String typeDeControle, String chaine) {

		char[] tabCharChaine = chaine.toCharArray();

		char[] tabCaracteresATester = determineCaracteresATester(typeDeControle);

		boolean controleOk;

		for (int i = 0; i < tabCharChaine.length; i++) {

			controleOk = false;

			for (int j = 0; j < tabCaracteresATester.length; j++) {

				if (tabCharChaine[i] == tabCaracteresATester[j]) {

					controleOk = true;
				}
			}

			if (!controleOk) {

				return false;

			}
		}

		return true;

	}

	private static char[] determineCaracteresATester(String typeDeControle) {

		String caracteresATester = null;
		switch (typeDeControle) {

		case "nomUtilisateur":
			caracteresATester = alphabet + chiffres + voyellesAvecAccent + tiret + caracteresSpeciauxAutorises;
			break;

		case "nomAliment":

			caracteresATester = alphabet + voyellesAvecAccent + tiret;
			break;

		default:
			caracteresATester = alphabet + chiffres + voyellesAvecAccent + tiret + caracteresSpeciauxAutorises;
		}

		char[] tabCaracteresATester = caracteresATester.toCharArray();

		return tabCaracteresATester;
	}
}
