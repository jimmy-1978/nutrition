package com.jimmy.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jimmy.classes.Aliment;
import com.jimmy.enums.UniteDeMesure;

class AlimentDaoImplTest {

	private AlimentDaoImpl alimentDaoImpl;

	@BeforeEach
	void avantChaqueTest() {
		alimentDaoImpl = new AlimentDaoImpl();
	}

	@AfterEach
	void apresChaqueTest() {
		alimentDaoImpl = null;
	}

	@Test
	@Disabled
	void controleDeleteTable() {

		try {
			alimentDaoImpl.deleteTable();
		} catch (Exception e) {

		}

	}

	@Test
	@Disabled
	void controleCreateTable() {

		try {
			alimentDaoImpl.createTable();
		} catch (Exception e) {

		}

	}

	@Test
	void accesAUnAlimentparSonId() {

		Object[] tabParam = { "poire-" + Instant.now().toEpochMilli(), Float.valueOf("1.12345"),
				UniteDeMesure.valueOf("gramme") };
		Object[] tabResultat = new Object[3];
		Aliment aliment = new Aliment((String) tabParam[0], (float) tabParam[1], (UniteDeMesure) tabParam[2]);
		int id = 0;
		try {
			id = alimentDaoImpl.create(aliment);
			aliment = alimentDaoImpl.getById(id);

			tabResultat[0] = aliment.getNom();
			tabResultat[1] = aliment.getKCalParUniteDeMesure();
			tabResultat[2] = aliment.getUniteDeMesure();

		} catch (Exception e) {

		}
		assertThat(tabResultat).isEqualTo(tabParam);

		try {
			alimentDaoImpl.delete(id);
		} catch (Exception e) {

		}

	}

//	@Test
//	void accesAUnAlimentParSonNom() {
//
//		Object[] tabParam = { "poire-" + Instant.now().toEpochMilli(), Float.valueOf("1.12345"),
//				UniteDeMesure.valueOf("gramme") };
//		Object[] tabResultat = new Object[3];
//
//		Aliment aliment = new Aliment((String) tabParam[0], (float) tabParam[1], (UniteDeMesure) tabParam[2]);
//
//		try {
//			alimentDaoImpl.create(aliment);
//
//			aliment = alimentDaoImpl.getByNom((String) tabParam[0]);
//
//			tabResultat[0] = aliment.getNom();
//			tabResultat[1] = aliment.getKCalParUniteDeMesure();
//			tabResultat[2] = aliment.getUniteDeMesure();
//		} catch (Exception e) {
//
//		}
//
//		assertThat(tabResultat).isEqualTo(tabParam);
//
//		try {
//			alimentDaoImpl.delete(aliment.getId());
//		} catch (Exception e) {
//
//		}
//
//	}

	@Test
	void deleteDUnAliment() {

		Aliment aliment = new Aliment("poire-" + Instant.now().toEpochMilli(), Float.valueOf("1.12345"),
				UniteDeMesure.valueOf("gramme"));

		try {
			int resultat = alimentDaoImpl.create(aliment);

			alimentDaoImpl.delete(resultat);
			aliment = alimentDaoImpl.getById(resultat);

		} catch (Exception e) {

		}

		assertThat(aliment).isNull();

	}

	@Test
	void creationDUnAliment() {

		Aliment aliment = new Aliment("poire-" + Instant.now().toEpochMilli(), Float.valueOf("1.12345"),
				UniteDeMesure.valueOf("gramme"));
		int resultat = 0;
		try {
			resultat = alimentDaoImpl.create(aliment);
		} catch (Exception e) {

		}

		assertThat(resultat).isGreaterThanOrEqualTo(1);

		try {
			alimentDaoImpl.delete(resultat);
		} catch (Exception e) {

		}
	}

}
