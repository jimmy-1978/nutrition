package com.jimmy.classes;

public abstract class Aliment {

	private String nom;

	public Aliment(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}
}
