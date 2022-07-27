package com.jimmy.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class JourDeLaSemaineTest {

	@Test
	void controleUnJourMercrediEgalMercrediEnNomCourt() {

		LocalDate date = LocalDate.now();
		while (date.getDayOfWeek().getValue() != 3) {
			date = date.plusDays(1);
		}

		String resultat = DateUtil.rechercherNomCourtJour(date);

		assertThat(resultat).isEqualTo("Mer");

	}

	@Test
	void controleUnJourMardiEgalMardiEnNomLong() {

		LocalDate date = LocalDate.now();
		while (date.getDayOfWeek().getValue() != 2) {
			date = date.plusDays(1);
		}

		String resultat = DateUtil.rechercherNomLongJour(date);

		assertThat(resultat).isEqualTo("Mardi");

	}

	@Test
	void controleSeptEgalDimancheEnNomCourt() {

		String resultat = DateUtil.rechercherNomCourtJour(7);

		assertThat(resultat).isEqualTo("Dim");

	}

	@Test
	void controleCinqEgalVendrediEnNomLong() {

		String resultat = DateUtil.rechercherNomLongJour(5);

		assertThat(resultat).isEqualTo("Vendredi");
	}

}
