package com.jimmy.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProprieteTest {

	private Propriete propriete;

	@BeforeEach

	void avantChaqueTest() {
		propriete = new Propriete();
	}

	@AfterEach
	void apresChaqueTest() {
		propriete = null;
	}

	@Test
	void parametreDbAReinitialiserExiste() {

		String nomParametre = "dbAReinitialiser";

		Boolean resultat = propriete.rechercheProprieteBoolean(nomParametre);

		assertThat(resultat).isNotNull();

	}

	@Test
	void parametreUrlMySqlExiste() {
		String nomParametre = "urlMySql";

		String resultat = propriete.rechercheProprieteString(nomParametre);

		assertThat(resultat).isNotEmpty();
	}

	@Test
	void parametreUtilisateurmySqlExiste() {

		String nomParametre = "utilisateurMySql";

		String resultat = propriete.rechercheProprieteString(nomParametre);

		assertThat(resultat).isNotEmpty();

	}

	@Test
	void parametreMotDePasseMySqlExiste() {

		String nomParametre = "motDePasseMySql";

		String resultat = propriete.rechercheProprieteString(nomParametre);

		assertThat(resultat).isNotEmpty();
	}

}
