package com.jimmy.vues;

public class Semaine {

	int numero;
	private Journee[] tabJournee;

	public Semaine(int numero, Journee[] tabJournee) {
		this.numero = numero;
		this.tabJournee = tabJournee;
	}

	public String vue() {
		String vue = "Semaine " + numero + " ";

		for (int i = 0; i < tabJournee.length; i++) {
			vue += tabJournee[i].vue() + " ";
		}

		return vue;

	}
}
