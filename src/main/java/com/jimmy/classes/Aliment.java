package com.jimmy.classes;

import com.jimmy.enums.UniteDeMesure;

public class Aliment {

	int id;
	private String nom;
	private float kCalParUniteDeMesure;
	private UniteDeMesure uniteDeMesure;

	public Aliment(String nom, float kCalParUniteDeMesure, UniteDeMesure uniteDeMesure) {
		setNom(nom);
		setKCalParUniteDeMesure(kCalParUniteDeMesure);
		setUniteDeMesure(uniteDeMesure);
	}

	public Aliment(int id, String nom, float kCalParUniteDeMesure, UniteDeMesure uniteDeMesure) {
		setId(id);
		setNom(nom);
		setKCalParUniteDeMesure(kCalParUniteDeMesure);
		setUniteDeMesure(uniteDeMesure);
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
		return this.nom;
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
