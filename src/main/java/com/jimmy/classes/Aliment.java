package com.jimmy.classes;

import com.jimmy.enums.TypeAliment;
import com.jimmy.enums.UniteDeMesure;

public class Aliment {

	private int id;
	private TypeAliment typeAliment;
	private String nom;
	private float kCalParUniteDeMesure;
	private UniteDeMesure uniteDeMesure;

	public Aliment() {

	}

	public Aliment(TypeAliment typeAliment, String nom, float kCalParUniteDeMesure, UniteDeMesure uniteDeMesure) {
		setTypeAliment(typeAliment);
		setNom(nom);
		setKCalParUniteDeMesure(kCalParUniteDeMesure);
		setUniteDeMesure(uniteDeMesure);
	}

	public Aliment(int id, TypeAliment typeAliment, String nom, float kCalParUniteDeMesure,
			UniteDeMesure uniteDeMesure) {
		this(typeAliment, nom, kCalParUniteDeMesure, uniteDeMesure);
		setId(id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTypeAliment(TypeAliment typeAliment) {
		this.typeAliment = typeAliment;
	}

	public TypeAliment getTypeAliment() {
		return typeAliment;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setKCalParUniteDeMesure(float kCalParUniteDeMesure) {
		this.kCalParUniteDeMesure = kCalParUniteDeMesure;
	}

	public float getKCalParUniteDeMesure() {
		return kCalParUniteDeMesure;
	}

	public void setUniteDeMesure(UniteDeMesure uniteDeMesure) {
		this.uniteDeMesure = uniteDeMesure;
	}

	public UniteDeMesure getUniteDeMesure() {
		return uniteDeMesure;
	}
}
