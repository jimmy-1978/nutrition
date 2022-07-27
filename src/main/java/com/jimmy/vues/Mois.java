package com.jimmy.vues;

import java.util.List;

public class Mois {

	private String nom;
	private int annee;
	private List<Semaine> listeSemaine;

	public Mois(String nom, int annee, List<Semaine> listeSemaine) {

		this.nom = nom;
		this.annee = annee;
		this.listeSemaine = listeSemaine;

	}

	public List<Semaine> getListeSemaine() {

		return listeSemaine;

	}

	public String getNom() {

		return nom;

	}

	public int getAnnee() {

		return annee;

	}
}
