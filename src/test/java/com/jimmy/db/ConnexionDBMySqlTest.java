package com.jimmy.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class ConnexionDBMySqlTest {

	@Test
	void ouvrirEtFermerUneConnexionFonctionne() {

		ConnexionDB connexionDbMySql = new ConnexionDBMySql();

		Connection connexion = connexionDbMySql.ouvrirConnexion();

		assertThat(connexion).isNotNull();

		if (connexion != null) {
			connexionDbMySql.fermerConnexion(connexion);
		}

	}
}
