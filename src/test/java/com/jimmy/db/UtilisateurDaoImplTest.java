package com.jimmy.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jimmy.classes.Utilisateur;

class UtilisateurDaoImplTest {

	private static ConnexionDBMySql connexionDBMysql;
	private static Connection connexion;
	private UtilisateurDaoImpl utilisateurDaoImpl;

	@BeforeAll
	static void avantTousLesTest() {
		connexionDBMysql = new ConnexionDBMySql();
		connexion = connexionDBMysql.ouvrirConnexion();
	}

	@AfterAll
	static void apresTousLesTests() {
		connexionDBMysql.fermerConnexion(connexion);
	}

	@BeforeEach
	void avantChaqueTest() {
		utilisateurDaoImpl = new UtilisateurDaoImpl();
	}

	@AfterEach
	void apresChaqueTest() {
		utilisateurDaoImpl = null;
	}

	@Test
	@Disabled
	void controleDeleteTable() {

		utilisateurDaoImpl.deleteTable(connexion);

	}

	@Test
	@Disabled
	void controleCreateTable() {

		utilisateurDaoImpl.createTable(connexion);
	}

	@Test
	void accesAUnUtilisateurparSonId() {

		String[] tabParam = { "toto", "1234" };
		String[] tabResultat = new String[2];
		Utilisateur utilisateur = new Utilisateur(tabParam[0], tabParam[1]);
		int id = utilisateurDaoImpl.create(connexion, utilisateur);

		utilisateur = utilisateurDaoImpl.getById(connexion, id);

		tabResultat[0] = utilisateur.getNom();
		tabResultat[1] = utilisateur.getMotDePasse();

		assertThat(tabResultat).isEqualTo(tabParam);

		utilisateurDaoImpl.delete(connexion, id);

	}

	@Test
	void deleteDUnUtilisateur() {

		Utilisateur utilisateur = new Utilisateur("toto", "1234");
		int resultat = utilisateurDaoImpl.create(connexion, utilisateur);

		utilisateurDaoImpl.delete(connexion, resultat);
		utilisateur = utilisateurDaoImpl.getById(connexion, resultat);

		assertThat(utilisateur).isNull();

	}

	@Test
	void creationDUnUtilisateur() {

		Utilisateur utilisateur = new Utilisateur("toto", "1234");
		int resultat = utilisateurDaoImpl.create(connexion, utilisateur);

		assertThat(resultat).isGreaterThanOrEqualTo(1);

		utilisateurDaoImpl.delete(connexion, resultat);

	}
}
