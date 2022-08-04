package com.jimmy.classes;

import java.time.LocalDate;

public class AlimentConsomme extends Aliment {

	int idAlimentConsomme;
	int idUtilisateur;
	LocalDate date;
	int quantite;

	public AlimentConsomme() {
		super();
	}

	public void setIdAlimentConsomme(int idAlimentConsomme) {
		this.idAlimentConsomme = idAlimentConsomme;
	}

	public int getIdAlimentConsomme() {
		return idAlimentConsomme;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getQuantite() {
		return quantite;
	}

}
