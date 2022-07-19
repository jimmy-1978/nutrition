package com.jimmy.classes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SolideTest {

	@Test
	void nomPasseAuConstructeurEstCeluiDuGet() {

		String nom = "Pomme";

		Solide solide = new Solide(nom);

		assertThat(solide.getNom()).isEqualTo(nom);

	}

}
