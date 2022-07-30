package com.jimmy.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

class DateUtilTest {

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

	@Test
	void controleAdteEnMarsEgalMars() {

		LocalDate date = LocalDate.of(2022, Month.MARCH, 1);

		String resultat = DateUtil.rechercherNomLongMois(date);

		assertThat(resultat).isEqualTo("Mars");
	}

	@Test
	void controleDeuxEgalFevrier() {

		String resultat = DateUtil.rechercherNomLongMois(2);

		assertThat(resultat).isEqualTo("Février");
	}

	@Test
	void controleDateEnParametreDeRequeteDonneUneLocalDate() {

		String dateParamRequete = "2022-06-29";

		LocalDate resultat = DateUtil.conversionDateRequete(dateParamRequete);

		assertThat(resultat).isEqualTo(LocalDate.of(2022, 6, 29));
	}

	@Test
	void recupererJoursDeLASemaineAPartirDeAnneeMoisEtNumeroDeSemaine() {

		LocalDate[] tabDate = { LocalDate.of(2022, 7, 25), LocalDate.of(2022, 7, 26), LocalDate.of(2022, 7, 27),
				LocalDate.of(2022, 7, 28), LocalDate.of(2022, 7, 29), LocalDate.of(2022, 7, 30),
				LocalDate.of(2022, 7, 31) };

		LocalDate[] tabResultat = DateUtil.getJoursDeLaSemaine(2022, 7, 5);

		assertThat(tabResultat).isEqualTo(tabDate);
	}

}
