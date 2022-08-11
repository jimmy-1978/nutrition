package com.jimmy.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChaineTest {

	@ParameterizedTest(name = "Format utilisateur {0} valide")
	@ValueSource(strings = { "toto", "toto-1", "jim", "u", "jim_the_best",
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", "éàèùâêîôû", "0123456789" })
	void nomsUtilisateurValides(String nomUtilisateur) {

		boolean resultat = Chaine.verifierCaracteresAutorises("nomUtilisateur", nomUtilisateur);

		assertThat(resultat).isTrue();

	}

	@Test
	void nomsUtilisateurNonValides(String nomUtilisateur) {
		fail("A implementer");
	}

	@ParameterizedTest(name = "Format aliment {0} valide") // !!! Ne marche pas quand name contient "'" !!!
	@ValueSource(strings = { "pomme", "poire", "haricots mange-tout", "chou-navet", "céréales", "pâtes" })
	void nomsAlimentsValides(String nomAliment) {

		boolean resultat = Chaine.verifierCaracteresAutorises("nomAliment", nomAliment);

		assertThat(resultat).isTrue();
	}

	@ParameterizedTest(name = "Format aliment {0} non valide")
	@ValueSource(strings = { "chou_navet", "  poire  ", " pomme", "cerise ", "+cerise", "-cerise", "pâtes-", "gal&ttes",
			"caf$" })
	void nomsAlimentsNonValides(String nomAliment) {

		boolean resultat = Chaine.verifierCaracteresAutorises("nomAliment", nomAliment);

		assertThat(resultat).isFalse();

	}

}
