package com.jimmy.vues;

import java.util.List;

public class Mois {

	private String nom;
	private List<Semaine> listeSemaine;

	public Mois(String nom, List<Semaine> listeSemaine) {

		this.nom = nom;
		this.listeSemaine = listeSemaine;

	}
}
