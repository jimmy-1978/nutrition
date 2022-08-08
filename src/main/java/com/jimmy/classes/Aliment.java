package com.jimmy.classes;

import java.math.BigDecimal;

import com.jimmy.enums.TypeAliment;
import com.jimmy.enums.UniteDeMesure;

public class Aliment {

	private int id;
	private TypeAliment typeAliment;
	private String nom;
	private int kCalParUniteDeMesure;
	private UniteDeMesure uniteDeMesure;
	private BigDecimal proteinesEnGrammes;

	public Aliment() {

	}

	public Aliment(TypeAliment typeAliment, String nom, int kCalParUniteDeMesure, BigDecimal proteinesEnGrammes,
			UniteDeMesure uniteDeMesure) {
		setTypeAliment(typeAliment);
		setNom(nom);
		setKCalParUniteDeMesure(kCalParUniteDeMesure);
		setProteinesEnGrammes(proteinesEnGrammes);
		setUniteDeMesure(uniteDeMesure);
	}

	public Aliment(int id, TypeAliment typeAliment, String nom, int kCalParUniteDeMesure, BigDecimal proteinesEnGrammes,
			UniteDeMesure uniteDeMesure) {
		this(typeAliment, nom, kCalParUniteDeMesure, proteinesEnGrammes, uniteDeMesure);
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

	public void setKCalParUniteDeMesure(int kCalParUniteDeMesure) {
		this.kCalParUniteDeMesure = kCalParUniteDeMesure;
	}

	public int getKCalParUniteDeMesure() {
		return kCalParUniteDeMesure;
	}

	public void setUniteDeMesure(UniteDeMesure uniteDeMesure) {
		this.uniteDeMesure = uniteDeMesure;
	}

	public UniteDeMesure getUniteDeMesure() {
		return uniteDeMesure;
	}

	public void setProteinesEnGrammes(BigDecimal proteinesEnGrammes) {
		this.proteinesEnGrammes = proteinesEnGrammes;
	}

	public BigDecimal getProteinesEnGrammes() {
		return proteinesEnGrammes;
	}
}
