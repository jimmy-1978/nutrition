package com.jimmy.enums;

public enum TypeAliment {

	solide("Solide"), liquide("Liquide");

	private String libelle;

	TypeAliment(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}
}
