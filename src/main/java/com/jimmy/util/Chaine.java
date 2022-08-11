package com.jimmy.util;

import java.util.ArrayList;
import java.util.List;

public class Chaine {

	private static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String voyellesAvecAccent = "éàèùâêîôû";
	private static String chiffres = "0123456789";
	private static char espace = ' ';
	private static char tiret = '-';
	private static String caracteresSpeciauxAutorises = "_";

	public static boolean verifierCaracteresAutorises(String typeDeControle, String chaine) {

		if (!chaine.equals(chaine.trim())) {

			return false;

		}

		char[] tabCharChaine = chaine.toCharArray();

		if (tabCharChaine[0] == tiret || tabCharChaine[tabCharChaine.length - 1] == tiret) {

			return false;

		}

		List<Character> listeCharacterChaine = new ArrayList<Character>();
		for (char caractere : tabCharChaine) {
			listeCharacterChaine.add(caractere);
		}

		char[] tabCaracteresATester = determineCaracteresATester(typeDeControle);
		List<Character> listeCharacterATester = new ArrayList<Character>();
		for (char caractere : tabCaracteresATester) {
			listeCharacterATester.add(caractere);
		}

		listeCharacterChaine.removeAll(listeCharacterATester); // On enlève tous les caractères autorisés

		if (listeCharacterChaine.size() != 0) { // Il reste des caractères non autorisés

			return false;

		}

		return true;

	}

	public static boolean verifierCaracteresAutorisesOld(String typeDeControle, String chaine) {

		char[] tabCharChaine = chaine.toCharArray();

		char[] tabCaracteresATester = determineCaracteresATester(typeDeControle);

		boolean controleOk = false;

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

			caracteresATester = alphabet + voyellesAvecAccent + espace + tiret;
			break;

		default:
			caracteresATester = alphabet + chiffres + voyellesAvecAccent + espace + tiret + caracteresSpeciauxAutorises;
		}

		char[] tabCaracteresATester = caracteresATester.toCharArray();

		return tabCaracteresATester;
	}
}
