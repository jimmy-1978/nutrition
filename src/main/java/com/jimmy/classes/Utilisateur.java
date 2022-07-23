package com.jimmy.classes;

public class Utilisateur {

	private int id;
	private String nom;
	private String motDePasse;

	public Utilisateur() {

	}

	public Utilisateur(String nom, String motDePasse) {
		setNom(nom);
		setMotDePasse(motDePasse);
	}

	public Utilisateur(int id, String nom, String motDePasse) {
		setId(id);
		setNom(nom);
		setMotDePasse(motDePasse);
	}

	public void setId(int id) {

		this.id = id;
	}

	public int getId() {

		return id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getMotDePasse() {
		return motDePasse;
	}
}
