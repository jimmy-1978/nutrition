package com.jimmy.classes;

import java.time.LocalDate;

public class Utilisateur {

	private int id;
	private String nom;
	private String motDePasse;
	private LocalDate dateDeNaissance;

	public Utilisateur() {

	}

	public Utilisateur(String nom, String motDePasse, LocalDate dateDeNaissance) {
		setNom(nom);
		setMotDePasse(motDePasse);
		setDateDeNaissance(dateDeNaissance);
	}

	public Utilisateur(int id, String nom, String motDePasse, LocalDate dateDeNaissance) {
		setId(id);
		setNom(nom);
		setMotDePasse(motDePasse);
		setDateDeNaissance(dateDeNaissance);
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

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}
}
