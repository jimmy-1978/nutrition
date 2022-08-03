package com.jimmy.classes;

import java.time.LocalDate;

public class Utilisateur {

	private int id;
	private String nom;
	private String motDePasse;
	private String sexe;
	private LocalDate dateDeNaissance;

	public Utilisateur() {

	}

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

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getSexe() {
		return sexe;
	}

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}
}
