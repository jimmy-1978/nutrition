package com.jimmy.classes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AlimentTest {

	@Test
	void creationDeLiquideEtSolideEstPossible() {

		String nomSolide = "Poire";
		String nomLiquide = "Coca";

		List<Aliment> listeAliment = new ArrayList<Aliment>();

		listeAliment.add(new Solide(nomSolide));
		listeAliment.add(new Liquide(nomLiquide));

		assertThat(listeAliment.get(0).getNom()).isEqualTo(nomSolide);
		assertThat(listeAliment.get(1).getNom()).isEqualTo(nomLiquide);

	}

}
