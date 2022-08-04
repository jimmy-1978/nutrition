package com.jimmy.forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.classes.AlimentConsomme;

public class AlimentConsommeForm extends AlimentConsomme {

	private List<String> listeNomAliment = new ArrayList<String>();
	private LocalDate[] TabJoursDeLaSemaineForm;
	private String quantiteForm;
	private String erreurAjout;

	public AlimentConsommeForm() {
		super();
	}

	public void setListeNomAliment(List<String> listeNomAliment) {
		this.listeNomAliment = listeNomAliment;
	}

	public List<String> getListeNomAliment() {
		return listeNomAliment;
	}

	public void ajoutAlimentDansListeNomAliment(String nomAliment) {
		listeNomAliment.add(nomAliment);
	}

	public void setTabJoursDeLaSemaineForm(LocalDate[] tabJoursDeLaSemaineForm) {
		this.TabJoursDeLaSemaineForm = tabJoursDeLaSemaineForm;
	}

	public LocalDate[] getTabJoursDeLaSemaineForm() {
		return TabJoursDeLaSemaineForm;
	}

	public void setQuantiteForm(String quantiteForm) {
		this.quantiteForm = quantiteForm;
	}

	public String getQuantiteForm() {
		return quantiteForm;
	}

	public void setErreurAjout(String erreurAjout) {
		this.erreurAjout = erreurAjout;
	}

	public String getErreurAjout() {
		return erreurAjout;
	}
}
