package com.jimmy.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.classes.Aliment;
import com.jimmy.enums.TypeAliment;
import com.jimmy.enums.UniteDeMesure;
import com.jimmy.exceptions.AlimentDaoException;

public class AlimentDaoImpl implements AlimentDao {

	private ConnexionDB connexionDB;
	private Connection connexion;

	public AlimentDaoImpl() {
		connexionDB = new ConnexionDBMySql();
	}

	@Override
	public void createTable() throws AlimentDaoException {

		String sql = """
				CREATE TABLE aliment (
				  id INT NOT NULL,
				  type VARCHAR(30) NOT NULL,
				  nom VARCHAR(30) NOT NULL,
				  kcal INT NOT NULL,
				  quantite_proteine DECIMAL(3,1) NOT NULL,
				  unite_de_mesure VARCHAR(10) NOT NULL,
				  PRIMARY KEY (id, nom),
				  UNIQUE INDEX nom_UNIQUE (nom ASC) VISIBLE);
										""";
		ouvrirConnexion();
		Statement stmt = null;

		try {
			stmt = connexion.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentDaoException(e.getMessage());
		}

	}

	@Override
	public void deleteTable() throws AlimentDaoException {
		String sql = "DROP TABLE aliment";
		ouvrirConnexion();

		Statement stmt = null;
		try {
			stmt = connexion.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentDaoException(e.getMessage());
		}
	}

	@Override
	public List<Aliment> getAll() throws AlimentDaoException {

		List<Aliment> listeAliment = null;

		String sql = """
				SELECT id, type, nom, kcal, quantite_proteine, unite_de_mesure
				FROM aliment
				""";
		ouvrirConnexion();
		try {
			Statement stmt = connexion.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				if (listeAliment == null) {
					listeAliment = new ArrayList<Aliment>();
				}

				int id = result.getInt("id");
				TypeAliment typeAliment = TypeAliment.valueOf(result.getString("type"));
				String nom = result.getString("nom");
				int kCalParUniteDeMesure = result.getInt("kcal");
				BigDecimal quantiteProteine = result.getBigDecimal("quantite_proteine");
				UniteDeMesure uniteDeMesure = UniteDeMesure.valueOf(result.getString("unite_de_mesure"));

				listeAliment
						.add(new Aliment(id, typeAliment, nom, kCalParUniteDeMesure, quantiteProteine, uniteDeMesure));

			}
		} catch (Exception e) {
			e.printStackTrace();

			throw new AlimentDaoException(e.getMessage());

		}

		return listeAliment;

	}

	@Override
	public Aliment getById(int id) throws AlimentDaoException {

		String sql = """
				SELECT type, nom, kcal, quantite_proteine, unite_de_mesure
				FROM aliment
				WHERE id = ?
				""";
		ouvrirConnexion();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				TypeAliment typeAliment = TypeAliment.valueOf(result.getString("type"));
				String nom = result.getString("nom");
				int kCalParUniteDeMesure = result.getInt("kcal");
				BigDecimal quantiteProteine = result.getBigDecimal("quantite_proteine");
				UniteDeMesure uniteDeMesure = UniteDeMesure.valueOf(result.getString("unite_de_mesure"));

				return new Aliment(id, typeAliment, nom, kCalParUniteDeMesure, quantiteProteine, uniteDeMesure);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentDaoException(e.getMessage());
		}

		return null;
	}

	@Override
	public Aliment getByNom(String nom) throws AlimentDaoException {

		String sql = """
				SELECT id, type, kcal, quantite_proteine, unite_de_mesure
				FROM aliment
				WHERE nom = ?
				""";
		ouvrirConnexion();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, nom);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				TypeAliment typeAliment = TypeAliment.valueOf(result.getString("type"));
				int kCalParUniteDeMesure = result.getInt("kcal");
				BigDecimal quantiteProteine = result.getBigDecimal("quantite_proteine");
				UniteDeMesure uniteDeMesure = UniteDeMesure.valueOf(result.getString("unite_de_mesure"));

				return new Aliment(id, typeAliment, nom, kCalParUniteDeMesure, quantiteProteine, uniteDeMesure);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentDaoException(e.getMessage());
		}

		return null;
	}

	@Override
	public int create(Aliment aliment) throws AlimentDaoException {
		try {
			int id = rechercherId();
			String sql = """
					INSERT INTO aliment
					(id, type, nom, kcal, quantite_proteine, unite_de_mesure)
					VALUES ( ? , ? , ? , ? , ? , ? )
					""";
			ouvrirConnexion();

			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, aliment.getTypeAliment().toString());
			preparedStatement.setString(3, aliment.getNom());
			preparedStatement.setInt(4, aliment.getKCalParUniteDeMesure());
			preparedStatement.setBigDecimal(5, aliment.getProteinesEnGrammes());
			UniteDeMesure uniteDeMesure = aliment.getUniteDeMesure();
			preparedStatement.setString(6, uniteDeMesure.toString());

			preparedStatement.execute();

			return id;

		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentDaoException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws AlimentDaoException {
		String sql = """
				DELETE FROM aliment
				WHERE id = ?
				""";

		ouvrirConnexion();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentDaoException(e.getMessage());
		}

	}

	private int rechercherId() throws Exception {

		String sql = """
				SELECT MAX(id)
				FROM aliment
				""";
		ouvrirConnexion();
		int id = 0;

		try {
			Statement stmt = connexion.createStatement();
			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				id = result.getInt(1) + 1; // Même si null, ça donne bien id 1 au final
			}

			return id;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	private void ouvrirConnexion() {
		connexion = connexionDB.ouvrirConnexion();
	}
}
