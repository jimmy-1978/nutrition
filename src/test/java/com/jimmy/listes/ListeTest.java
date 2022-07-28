package com.jimmy.listes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ListeTest {

	@Test
	void listeGenreContientMFEtNa() {

		List<String> listeGenre = new ArrayList();
		listeGenre.add("M");
		listeGenre.add("F");
		listeGenre.add("N/A");

		List<String> resultat = Liste.getListeGenre();

		assertThat(resultat).isEqualTo(listeGenre);

	}

}
