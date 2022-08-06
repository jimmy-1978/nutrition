package com.jimmy.forms;

import com.jimmy.classes.Aliment;
import com.jimmy.enums.TypeAliment;
import com.jimmy.enums.UniteDeMesure;

public class AlimentForm extends Aliment {

	private TypeAliment[] tabTypeAliment;
	private UniteDeMesure[] tabUniteDeMesure;
	private String kiloCalForm; // Visiblement ne supporte pas le nom kCal... (erreur E.L. javabean/propriété
								// introuvable) !!!
	private String erreurAjout;

	public AlimentForm() {
		super();
	}

	public void setTabTypeAliment(TypeAliment[] tabTypeAliment) {
		this.tabTypeAliment = tabTypeAliment;
	}

	public TypeAliment[] getTabTypeAliment() {
		return tabTypeAliment;
	}

	public void setTabUniteDeMesure(UniteDeMesure[] tabUniteDeMesure) {
		this.tabUniteDeMesure = tabUniteDeMesure;
	}

	public UniteDeMesure[] getTabUniteDeMesure() {
		return tabUniteDeMesure;
	}

	public void setKiloCalForm(String kiloCalForm) {
		this.kiloCalForm = kiloCalForm;
	}

	public String getKiloCalForm() {
		return kiloCalForm;
	}

	public void setErreurAjout(String erreurAjout) {
		this.erreurAjout = erreurAjout;
	}

	public String getErreurAjout() {
		return erreurAjout;
	}

}
