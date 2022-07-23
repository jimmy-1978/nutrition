package com.jimmy.classes;

import static org.assertj.core.api.Assertions.assertThat;

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
	void creationDUnUtilisateurAvecNomEtMotDePasse() {

		String[] tabParam = { "toto", "1234" };
		String[] tabResultat = new String[2];

		Utilisateur utilisateur = new Utilisateur(tabParam[0], tabParam[1]);
		tabResultat[0] = utilisateur.getNom();
		tabResultat[1] = utilisateur.getMotDePasse();

		assertThat(tabResultat).isEqualTo(tabParam);

	}

}
