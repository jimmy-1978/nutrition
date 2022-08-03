package com.jimmy.forms;

import java.util.List;

import com.jimmy.classes.Utilisateur;

public class UtilisateurForm extends Utilisateur {

	private List<String> listeGenre;
	private String erreurCreation;
	private boolean connecte;

	public UtilisateurForm() {
		super();
	}

	public void setListeGenre(List<String> listeGenre) {
		this.listeGenre = listeGenre;
	}

	public List<String> getListeGenre() {
		return listeGenre;
	}

	public void setErreurCreation(String erreurCreation) {
		this.erreurCreation = erreurCreation;
	}

	public String getErreurCreation() {
		return erreurCreation;
	}

	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

	public boolean getConnecte() {
		return connecte;
	}

}
