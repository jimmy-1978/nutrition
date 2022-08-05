package com.jimmy.forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.classes.AlimentConsomme;
import com.jimmy.enums.TypeAliment;

public class AlimentConsommeForm extends AlimentConsomme {

	private TypeAliment[] tabTypeAliment;
	private List<String> listeNomAliment;
	private LocalDate[] TabJoursDeLaSemaineForm;
	private String quantiteForm;
	private String erreurAjout;

	public AlimentConsommeForm() {
		super();
		listeNomAliment = new ArrayList<String>();
	}

	public void setTabTypeAliment(TypeAliment[] tabTypeAliment) {
		this.tabTypeAliment = tabTypeAliment;
	}

	public TypeAliment[] getTabTypeAliment() {
		return tabTypeAliment;
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
