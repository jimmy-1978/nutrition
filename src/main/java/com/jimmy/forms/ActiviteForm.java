package com.jimmy.forms;

import java.time.LocalDate;

import com.jimmy.classes.Activite;
import com.jimmy.enums.TypeActivite;

public class ActiviteForm extends Activite {

	private String nbCaloriesBruleesForm;
	private TypeActivite[] tabTypeActivite;
	private LocalDate[] joursDeLaSemaineForm;

	public ActiviteForm() {
		super();
	}

	public void setNbCaloriesBruleesForm(String nbCaloriesBruleesForm) {
		this.nbCaloriesBruleesForm = nbCaloriesBruleesForm;
	}

	public String getNbCaloriesBruleesForm() {
		return nbCaloriesBruleesForm;
	}

	public void setTabTypeActivite(TypeActivite[] tabTypeActivite) {
		this.tabTypeActivite = tabTypeActivite;
	}

	public TypeActivite[] getTabTypeActivite() {
		return tabTypeActivite;
	}

	public void setJoursDeLaSemaineForm(LocalDate[] joursDeLaSemaine) {
		this.joursDeLaSemaineForm = joursDeLaSemaine;
	}

	public LocalDate[] getJoursDeLaSemaineForm() {
		return joursDeLaSemaineForm;
	}
}
