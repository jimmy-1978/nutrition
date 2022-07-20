package com.jimmy.util;

import java.util.ResourceBundle;

public class Propriete {

	private static ResourceBundle bundle;

	public Propriete() {
		bundle = ResourceBundle.getBundle("com.jimmy.properties.config");
		// L'extension de config doit être .properties
	}

	public String rechercheProprieteString(String nomDePropriete) {

		return bundle.getString(nomDePropriete);
	}

	public Boolean rechercheProprieteBoolean(String nomDePropriete) {

		return Boolean.parseBoolean(bundle.getString(nomDePropriete));
	}

}
