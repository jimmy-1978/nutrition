package com.jimmy.classes;

import java.time.LocalDate;

import com.jimmy.enums.TypeActivite;

public class Activite {

	private int id;
	private String nomUtilisateur;
	private TypeActivite typeActivite;
	private int nbCaloriesBrulees;
	private LocalDate dateActivite;

	public Activite() {

	}

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

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setDateActivite(LocalDate dateActivite) {
		this.dateActivite = dateActivite;
	}

	public LocalDate getDateActivite() {
		return dateActivite;
	}

	public void setTypeActivite(TypeActivite typeActivite) {
		this.typeActivite = typeActivite;
	}

	public TypeActivite getTypeActivite() {
		return typeActivite;
	}

	public void setNbCaloriesBrulees(int nbCaloriesBrulees) {
		this.nbCaloriesBrulees = nbCaloriesBrulees;
	}

	public int getNbCaloriesBrulees() {
		return nbCaloriesBrulees;
	}
}
