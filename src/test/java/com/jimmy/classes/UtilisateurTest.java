package com.jimmy.classes;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilisateurTest {

	private Utilisateur utilisateur;

	@BeforeEach
	void avantChaqueTest() {
		utilisateur = new Utilisateur();
	}

	@AfterEach
	void apresChaqueTest() {
		utilisateur = null;
	}

	@Test
	void setNomDonneLeNom() {

		String nom = "toto";

		utilisateur.setNom(nom);

		String resultat = utilisateur.getNom();

		assertThat(resultat).isEqualTo(nom);
	}

	@Test
	void setMotDePasseDonneLeMotDePasse() {

		String motDePasse = "1234";

		utilisateur.setMotDePasse(motDePasse);

		String resultat = utilisateur.getMotDePasse();

		assertThat(resultat).isEqualTo(motDePasse);
	}

	@Test
	void setIdDonneId() {

		int id = 45;

		utilisateur.setId(id);

		int resultat = utilisateur.getId();

		assertThat(resultat).isEqualTo(resultat);
	}

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
