package com.jimmy.classes;

import java.util.Date;

import com.jimmy.enums.TypeActivite;

public class Activite {

	private int id;
	private String nomUtilisateur;
	private TypeActivite typeActivite;
	private int nbCaloriesBrulees;
	private Date dateActivite;

	public Activite(String nomUtilisateur, Date dateActivite, TypeActivite typeActivite, int nbCaloriesBrulees) {
		this.nomUtilisateur = nomUtilisateur;
		this.dateActivite = dateActivite;
		this.typeActivite = typeActivite;
		this.nbCaloriesBrulees = nbCaloriesBrulees;
	}

	public Activite(int id, String nomUtilisateur, Date dateActivite, TypeActivite typeActivite,
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

	public Date getDateActivite() {
		return dateActivite;
	}

	public TypeActivite getTypeActivite() {
		return typeActivite;
	}

	public int getNbCaloriesBrulees() {
		return nbCaloriesBrulees;
	}
}
