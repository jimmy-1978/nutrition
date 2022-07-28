package com.jimmy.classes;

import java.time.LocalDate;

public class Utilisateur {

	private int id;
	private String nom;
	private String motDePasse;
	private String sexe;
	private LocalDate dateDeNaissance;

	public Utilisateur(String nom, String motDePasse) {
		this.nom = nom;
		this.motDePasse = motDePasse;
	}

	public Utilisateur(String nom, String motDePasse, String sexe, LocalDate dateDeNaissance) {
		this.nom = nom;
		this.motDePasse = motDePasse;
		this.sexe = sexe;
		this.dateDeNaissance = dateDeNaissance;
	}

	public Utilisateur(String nom, String motDePasse, LocalDate dateDeNaissance) {
		this(nom, motDePasse);
		this.dateDeNaissance = dateDeNaissance;
	}

	public Utilisateur(int id, String nom, String motDePasse, LocalDate dateDeNaissance) {
		this(nom, motDePasse, dateDeNaissance);
		this.id = id;
	}

	public int getId() {

		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public String getSexe() {
		return sexe;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}
}
