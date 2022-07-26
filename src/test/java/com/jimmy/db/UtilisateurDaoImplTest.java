package com.jimmy.db;

import static org.assertj.core.api.Assertions.assertThat;

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

		utilisateurDaoImpl.deleteTable();

	}

	@Test
	@Disabled
	void controleCreateTable() {

		utilisateurDaoImpl.createTable();
	}

	@Test
	void accesAUnUtilisateurparSonId() {

		Object[] tabParam = { "toto", "1234", LocalDate.now() };
		Object[] tabResultat = new Object[3];
		Utilisateur utilisateur = new Utilisateur((String) tabParam[0], (String) tabParam[1], (LocalDate) tabParam[2]);
		int id = utilisateurDaoImpl.create(utilisateur);

		utilisateur = utilisateurDaoImpl.getById(id);

		tabResultat[0] = utilisateur.getNom();
		tabResultat[1] = utilisateur.getMotDePasse();
		tabResultat[2] = utilisateur.getDateDeNaissance();

		assertThat(tabResultat).isEqualTo(tabParam);

		utilisateurDaoImpl.delete(id);

	}

	@Test
	void accesAUnUtilisateurParSonNom() {

		Object[] tabParam = { "toto", "1234", LocalDate.now() };
		Object[] tabResultat = new Object[3];

		Utilisateur utilisateur = new Utilisateur((String) tabParam[0], (String) tabParam[1], (LocalDate) tabParam[2]);
		utilisateurDaoImpl.create(utilisateur);

		utilisateur = utilisateurDaoImpl.getByNom((String) tabParam[0]);

		tabResultat[0] = utilisateur.getNom();
		tabResultat[1] = utilisateur.getMotDePasse();
		tabResultat[2] = utilisateur.getDateDeNaissance();

		assertThat(tabResultat).isEqualTo(tabParam);

		utilisateurDaoImpl.delete(utilisateur.getId());

	}

	@Test
	void deleteDUnUtilisateur() {

		Utilisateur utilisateur = new Utilisateur("toto", "1234", LocalDate.now());
		int resultat = utilisateurDaoImpl.create(utilisateur);

		utilisateurDaoImpl.delete(resultat);
		utilisateur = utilisateurDaoImpl.getById(resultat);

		assertThat(utilisateur).isNull();

	}

	@Test
	void creationDUnUtilisateur() {

		Utilisateur utilisateur = new Utilisateur("toto", "1234", LocalDate.now());
		int resultat = utilisateurDaoImpl.create(utilisateur);

		assertThat(resultat).isGreaterThanOrEqualTo(1);

		utilisateurDaoImpl.delete(resultat);

	}
}
