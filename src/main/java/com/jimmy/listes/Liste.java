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

	public static List<String> getListeGenre() {

		return listeGenre;

	}
}
