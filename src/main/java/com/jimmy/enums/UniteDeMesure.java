package com.jimmy.enums;

public enum UniteDeMesure {
	millilitre("ml"), gramme("gr");

	private String nomCourt;

	private UniteDeMesure(String nomCourt) {
		this.nomCourt = nomCourt;
	}
}
