package com.jimmy.classes;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class UtilisateurTest {

	@Test
	void creationDUnUtilisateurAvecNomEtMotDePasseEtDateDeNaissance() {

		Object[] tabParam = { "toto", "1234", LocalDate.now() };
		Object[] tabResultat = new Object[3];

		Utilisateur utilisateur = new Utilisateur((String) tabParam[0], (String) tabParam[1], (LocalDate) tabParam[2]);
		tabResultat[0] = utilisateur.getNom();
		tabResultat[1] = utilisateur.getMotDePasse();
		tabResultat[2] = utilisateur.getDateDeNaissance();

		assertThat(tabResultat).isEqualTo(tabParam);

	}

	@Test
	void creationDUnUtilisateurAvecIdEtNomEtMotDePasseEtDateDeNaissance() {

		Object[] tabParam = { 45, "tartempion", "pass", LocalDate.now() };
		Object[] tabResultat = new Object[4];

		Utilisateur utilisateur = new Utilisateur((int) tabParam[0], (String) tabParam[1], (String) tabParam[2],
				(LocalDate) tabParam[3]);

		tabResultat[0] = utilisateur.getId();
		tabResultat[1] = utilisateur.getNom();
		tabResultat[2] = utilisateur.getMotDePasse();
		tabResultat[3] = utilisateur.getDateDeNaissance();

		assertThat(tabResultat).isEqualTo(tabParam);

	}

}
