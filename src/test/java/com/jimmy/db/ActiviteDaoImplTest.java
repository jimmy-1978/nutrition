package com.jimmy.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jimmy.classes.Activite;
import com.jimmy.enums.TypeActivite;

class ActiviteDaoImplTest {

	private ActiviteDao activiteDao;

	@BeforeEach
	void avantChaqueTest() {
		activiteDao = new ActiviteDaoImpl();
	}

	@AfterEach
	void apresChaqueTest() {
		activiteDao = null;
	}

	@Test
	@Disabled
	void controleCreateTable() {
		activiteDao.createTable();
	}

	@Test
	@Disabled
	void controleDeleteTable() {
		activiteDao.deleteTable();
	}

	@Test
	void creationActivite() {

		Object[] tabParam = { "Jim", LocalDate.of(2022, 7, 25), TypeActivite.vtt, 369 };

		Activite activite = new Activite((String) tabParam[0], (LocalDate) tabParam[1], (TypeActivite) tabParam[2],
				(int) tabParam[3]);

		int resultat = activiteDao.create(activite);

		assertThat(resultat).isGreaterThanOrEqualTo(1);

	}

	@Test
	void rechercheActiviteParId() {

		Date maDate = Date.from(Instant.now());

		Object[] tabParam = { "Jim", LocalDate.of(2022, 7, 25), TypeActivite.vtt, 369 };

		Activite activite = new Activite((String) tabParam[0], (LocalDate) tabParam[1], (TypeActivite) tabParam[2],
				(int) tabParam[3]);
		int id = activiteDao.create(activite);

		activite = activiteDao.getById(id);

		Object[] tabResultat = new Object[4];
		tabResultat[0] = activite.getNomUtilisateur();
		tabResultat[1] = activite.getDateActivite();
		tabResultat[2] = activite.getTypeActivite();
		tabResultat[3] = activite.getNbCaloriesBrulees();

		assertThat(tabResultat).isEqualTo(tabParam);
	}

	@Test
	void deleteActivite() {

		Object[] tabParam = { "Jim", LocalDate.of(2022, 7, 25), TypeActivite.vtt, 369 };

		Activite activite = new Activite((String) tabParam[0], (LocalDate) tabParam[1], (TypeActivite) tabParam[2],
				(int) tabParam[3]);
		int id = activiteDao.create(activite);

		activiteDao.delete(id);

		activite = activiteDao.getById(id);

		assertThat(activite).isNull();

	}

	@Test
	void rechercheListeDesActivitesParNom() {

		Object[] tabParam = { "Tom", LocalDate.of(2022, 7, 25), TypeActivite.vtt, 369 };

		Activite activite = new Activite((String) tabParam[0], (LocalDate) tabParam[1], (TypeActivite) tabParam[2],
				(int) tabParam[3]);
		int id = activiteDao.create(activite);

		List<Activite> listeActivite = activiteDao.getByNom("Tom");

		assertThat(listeActivite).isNotNull();
	}
}
