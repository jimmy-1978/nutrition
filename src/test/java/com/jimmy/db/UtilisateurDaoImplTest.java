package com.jimmy.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jimmy.classes.Utilisateur;

class UtilisateurDaoImplTest {

	private UtilisateurDaoImpl utilisateurDaoImpl;

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

		try {
			utilisateurDaoImpl.deleteTable();
		} catch (Exception e) {

		}

	}

	@Test
	@Disabled
	void controleCreateTable() {

		try {
			utilisateurDaoImpl.createTable();
		} catch (Exception e) {

		}

	}

	@Test
	void accesAUnUtilisateurparSonId() {

		Object[] tabParam = { "toto-" + Instant.now().toEpochMilli(), "1234", LocalDate.now() };
		Object[] tabResultat = new Object[3];
		Utilisateur utilisateur = new Utilisateur((String) tabParam[0], (String) tabParam[1], (LocalDate) tabParam[2]);
		int id = 0;
		try {
			id = utilisateurDaoImpl.create(utilisateur);
			utilisateur = utilisateurDaoImpl.getById(id);

			tabResultat[0] = utilisateur.getNom();
			tabResultat[1] = utilisateur.getMotDePasse();
			tabResultat[2] = utilisateur.getDateDeNaissance();

		} catch (Exception e) {

		}
		assertThat(tabResultat).isEqualTo(tabParam);

		try {
			utilisateurDaoImpl.delete(id);
		} catch (Exception e) {

		}

	}

	@Test
	void accesAUnUtilisateurParSonNom() {

		Object[] tabParam = { "toto-" + Instant.now().toEpochMilli(), "1234", LocalDate.now() };
		Object[] tabResultat = new Object[3];

		Utilisateur utilisateur = new Utilisateur((String) tabParam[0], (String) tabParam[1], (LocalDate) tabParam[2]);

		try {
			utilisateurDaoImpl.create(utilisateur);

			utilisateur = utilisateurDaoImpl.getByNom((String) tabParam[0]);

			tabResultat[0] = utilisateur.getNom();
			tabResultat[1] = utilisateur.getMotDePasse();
			tabResultat[2] = utilisateur.getDateDeNaissance();
		} catch (Exception e) {

		}

		assertThat(tabResultat).isEqualTo(tabParam);

		try {
			utilisateurDaoImpl.delete(utilisateur.getId());
		} catch (Exception e) {

		}

	}

	@Test
	void deleteDUnUtilisateur() {

		Utilisateur utilisateur = new Utilisateur("toto-" + Instant.now().toEpochMilli(), "1234", LocalDate.now());

		try {
			int resultat = utilisateurDaoImpl.create(utilisateur);

			utilisateurDaoImpl.delete(resultat);
			utilisateur = utilisateurDaoImpl.getById(resultat);

		} catch (Exception e) {

		}

		assertThat(utilisateur).isNull();

	}

	@Test
	void creationDUnUtilisateur() {

		Utilisateur utilisateur = new Utilisateur("toto-" + Instant.now().toEpochMilli(), "1234", LocalDate.now());
		int resultat = 0;
		try {
			resultat = utilisateurDaoImpl.create(utilisateur);
		} catch (Exception e) {

		}

		assertThat(resultat).isGreaterThanOrEqualTo(1);

		try {
			utilisateurDaoImpl.delete(resultat);
		} catch (Exception e) {

		}
	}
}
