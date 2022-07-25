package com.jimmy.classes;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.jimmy.enums.TypeActivite;

class ActiviteTest {

	@Test
	void creerActiviteAvecNomUtilisateurEtTypeEtNbCaloriesBruleesEtDate() {

		Object[] tabParametre = new Object[4];
		tabParametre[0] = "Jim";
		tabParametre[1] = Date.from(Instant.now());
		tabParametre[2] = TypeActivite.vtt;
		tabParametre[3] = 350;
		Object[] tabResultat = new Object[4];

		Activite activite = new Activite((String) tabParametre[0], (Date) tabParametre[1],
				(TypeActivite) tabParametre[2], (int) tabParametre[3]);
		tabResultat[0] = activite.getNomUtilisateur();
		tabResultat[1] = activite.getDateActivite();
		tabResultat[2] = activite.getTypeActivite();
		tabResultat[3] = activite.getNbCaloriesBrulees();

		assertThat(tabResultat).isEqualTo(tabParametre);

	}

	@Test
	void creerActiviteAvecIdEtNomUtilisateurEtTypeEtNbCaloriesBruleesEtDate() {

		Object[] tabParametre = new Object[5];
		tabParametre[0] = 1;
		tabParametre[1] = "Jim";
		tabParametre[2] = Date.from(Instant.now());
		tabParametre[3] = TypeActivite.vtt;
		tabParametre[4] = 350;
		Object[] tabResultat = new Object[5];

		Activite activite = new Activite((int) tabParametre[0], (String) tabParametre[1], (Date) tabParametre[2],
				(TypeActivite) tabParametre[3], (int) tabParametre[4]);
		tabResultat[0] = activite.getId();
		tabResultat[1] = activite.getNomUtilisateur();
		tabResultat[2] = activite.getDateActivite();
		tabResultat[3] = activite.getTypeActivite();
		tabResultat[4] = activite.getNbCaloriesBrulees();

		assertThat(tabResultat).isEqualTo(tabParametre);

	}

}
