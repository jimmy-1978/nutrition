package com.jimmy.classes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LiquideTest {

	@Test
	void nomPasseAuConstructeurEstCeluiDuGet() {

		String nom = "lait";

		Liquide liquide = new Liquide(nom);

		assertThat(liquide.getNom()).isEqualTo(nom);

	}

}
