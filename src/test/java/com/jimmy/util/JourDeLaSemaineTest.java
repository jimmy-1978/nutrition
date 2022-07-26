package com.jimmy.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class JourDeLaSemaineTest {

	@Test
	void controleJourUnEgalMercredi() {

		LocalDate date = LocalDate.now();
		while (date.getDayOfWeek().getValue() != 3) {
			date = date.plusDays(1);
		}

		String resultat = JourDeLaSemaine.nomCourt(date);

		assertThat(resultat).isEqualTo("Mer");

	}

}
