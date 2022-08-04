package com.jimmy.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jimmy.classes.AlimentConsomme;

class AlimentConsommeDaoImplTest {

	private AlimentConsommeDaoImpl alimentConsommeDaoImpl;

	@BeforeEach
	void avantChaqueTest() {
		alimentConsommeDaoImpl = new AlimentConsommeDaoImpl();
	}

	@AfterEach
	void apresChaqueTest() {
		alimentConsommeDaoImpl = null;
	}

	@Test
	@Disabled
	void controleDeleteTable() {

		try {
			alimentConsommeDaoImpl.deleteTable();
		} catch (Exception e) {

			fail(e.getMessage());

		}
	}

	@Test
	@Disabled
	void controleCreateTable() {

		try {
			alimentConsommeDaoImpl.createTable();
		} catch (Exception e) {

			fail(e.getMessage());
		}
	}

	@Test
	void accesAUnAlimentConsommeparSonId() {

		Object[] tabParam = { 69, 70, LocalDate.now(), 125 };
		Object[] tabResultat = new Object[4];
		AlimentConsomme alimentConsomme = new AlimentConsomme();

		alimentConsomme.setId((int) tabParam[0]);
		alimentConsomme.setIdUtilisateur((int) tabParam[1]);
		alimentConsomme.setDate((LocalDate) tabParam[2]);
		alimentConsomme.setQuantite((int) tabParam[3]);

		int id = 0;
		try {
			id = alimentConsommeDaoImpl.create(alimentConsomme);
			alimentConsomme = alimentConsommeDaoImpl.getById(id);

			tabResultat[0] = alimentConsomme.getId();
			tabResultat[1] = alimentConsomme.getIdUtilisateur();
			tabResultat[2] = alimentConsomme.getDate();
			tabResultat[3] = alimentConsomme.getQuantite();

		} catch (Exception e) {

			fail(e.getMessage());

		}

		assertThat(tabResultat).isEqualTo(tabParam);

		try {
			alimentConsommeDaoImpl.delete(id);
		} catch (Exception e) {

			fail(e.getMessage());

		}
	}
}
