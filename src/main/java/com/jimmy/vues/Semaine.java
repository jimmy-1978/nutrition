package com.jimmy.vues;

public class Semaine {

	int numero;
	private Journee[] tabJournee;

	public Semaine(int numero, Journee[] tabJournee) {
		this.numero = numero;
		this.tabJournee = tabJournee;
	}

	public Journee[] getTabJournee() {
		return tabJournee;
	}

	public int getNumero() {
		return numero;
	}
}
