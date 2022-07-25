package com.jimmy.classes;

import java.time.LocalDate;

import com.jimmy.enums.TypeActivite;

public class Activite {

	private int id;
	private String nomUtilisateur;
	private TypeActivite typeActivite;
	private int nbCaloriesBrulees;
	private LocalDate dateActivite;

	public Activite(String nomUtilisateur, LocalDate dateActivite, TypeActivite typeActivite, int nbCaloriesBrulees) {
		this.nomUtilisateur = nomUtilisateur;
		this.dateActivite = dateActivite;
		this.typeActivite = typeActivite;
		this.nbCaloriesBrulees = nbCaloriesBrulees;
	}

	public Activite(int id, String nomUtilisateur, LocalDate dateActivite, TypeActivite typeActivite,
			int nbCaloriesBrulees) {
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.dateActivite = dateActivite;
		this.typeActivite = typeActivite;
		this.nbCaloriesBrulees = nbCaloriesBrulees;
	}

	public int getId() {
		return id;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public LocalDate getDateActivite() {
		return dateActivite;
	}

	public TypeActivite getTypeActivite() {
		return typeActivite;
	}

	public int getNbCaloriesBrulees() {
		return nbCaloriesBrulees;
	}
}
