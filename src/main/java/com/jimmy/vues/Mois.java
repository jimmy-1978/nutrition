package com.jimmy.vues;

import java.util.List;

public class Mois {

	private int numero;
	private String nom;
	private int annee;
	private List<Semaine> listeSemaine;

	public Mois(int numero, String nom, int annee, List<Semaine> listeSemaine) {

		this.numero = numero;
		this.nom = nom;
		this.annee = annee;
		this.listeSemaine = listeSemaine;

	}

	public List<Semaine> getListeSemaine() {

		return listeSemaine;

	}

	public int getNumero() {

		return numero;

	}

	public String getNom() {

		return nom;

	}

	public int getAnnee() {

		return annee;

	}
}
