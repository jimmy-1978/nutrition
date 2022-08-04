package com.jimmy.listes;

import java.util.ArrayList;
import java.util.List;

public class Liste {

	private static List<String> listeGenre;
	static {
		listeGenre = new ArrayList<String>();
		listeGenre.add("M");
		listeGenre.add("F");
		listeGenre.add("N/A");
	}

	private static List<String> listeTypeAjout;
	static {
		listeTypeAjout = new ArrayList<String>();
		listeTypeAjout.add("Activité");
		listeTypeAjout.add("AlimentConsomme");
	}

	public static List<String> getListeGenre() {

		return listeGenre;

	}

	public static List<String> getListeTypeAjout() {

		return listeTypeAjout;

	}
}
