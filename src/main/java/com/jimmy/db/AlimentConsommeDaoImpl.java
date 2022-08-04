package com.jimmy.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.classes.AlimentConsomme;
import com.jimmy.exceptions.AlimentConsommeDaoException;

public class AlimentConsommeDaoImpl implements AlimentConsommeDao {

	private ConnexionDB connexionDB;
	private Connection connexion;

	public AlimentConsommeDaoImpl() {
		connexionDB = new ConnexionDBMySql();
	}

	@Override
	public void createTable() throws AlimentConsommeDaoException {

		String sql = """
				CREATE TABLE aliment_consomme (
				  id INT NOT NULL,
				  id_aliment INT NOT NULL,
				  id_utilisateur INT NOT NULL,
				  date_consommation DATE NOT NULL,
				  quantite INT NOT NULL,
				  PRIMARY KEY (id, id_utilisateur),
				  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);
										""";
		ouvrirConnexion();
		Statement stmt = null;

		try {
			stmt = connexion.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentConsommeDaoException(e.getMessage());
		}
	}

	@Override
	public void deleteTable() throws AlimentConsommeDaoException {
		String sql = "DROP TABLE aliment_consomme";
		ouvrirConnexion();

		Statement stmt = null;
		try {
			stmt = connexion.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentConsommeDaoException(e.getMessage());
		}
	}

	@Override
	public List<AlimentConsomme> getAll() throws AlimentConsommeDaoException {

		List<AlimentConsomme> listeAlimentConsomme = null;

		String sql = """
				SELECT id, id_aliment, id_utilisateur, date_consommation, quantite
				FROM aliment_consomme
				""";
		ouvrirConnexion();
		try {
			Statement stmt = connexion.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				if (listeAlimentConsomme == null) {
					listeAlimentConsomme = new ArrayList<AlimentConsomme>();
				}

				int id = result.getInt("id");
				int id_aliment = result.getInt("id_aliment");
				int id_utilisateur = result.getInt("id_utilisateur");
				Date dateSql = result.getDate("date_consommation");
				LocalDate date = dateSql.toLocalDate();
				int quantite = result.getInt("quantite");

				AlimentConsomme alimentConsomme = new AlimentConsomme();
				alimentConsomme.setIdAlimentConsomme(id);
				alimentConsomme.setId(id_aliment);
				alimentConsomme.setIdUtilisateur(id_utilisateur);
				alimentConsomme.setDate(date);
				alimentConsomme.setQuantite(quantite);

				listeAlimentConsomme.add(alimentConsomme);

			}
		} catch (Exception e) {
			e.printStackTrace();

			throw new AlimentConsommeDaoException(e.getMessage());

		}

		return listeAlimentConsomme;

	}

	@Override
	public AlimentConsomme getById(int id) throws AlimentConsommeDaoException {

		String sql = """
				SELECT id_aliment, id_utilisateur, date_consommation, quantite
				FROM aliment_consomme
				WHERE id = ?
				""";
		ouvrirConnexion();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {

				int id_aliment = result.getInt("id_aliment");
				int id_utilisateur = result.getInt("id_utilisateur");
				Date dateSql = result.getDate("date_consommation");
				LocalDate date = dateSql.toLocalDate();
				int quantite = result.getInt("quantite");

				AlimentConsomme alimentConsomme = new AlimentConsomme();
				alimentConsomme.setIdAlimentConsomme(id);
				alimentConsomme.setId(id_aliment);
				alimentConsomme.setIdUtilisateur(id_utilisateur);
				alimentConsomme.setDate(date);
				alimentConsomme.setQuantite(quantite);

				return alimentConsomme;

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentConsommeDaoException(e.getMessage());
		}

		return null;
	}

	@Override
	public List<AlimentConsomme> getByIdUtilisateurAndBetweenDateFromAndDateTo(int idUtilisateur, LocalDate dateFrom,
			LocalDate dateTo) throws AlimentConsommeDaoException {

		List<AlimentConsomme> listeAlimentConsomme = null;

		String sql = """
				SELECT id, id_aliment, id_utilisateur, date_consommation, quantite
				FROM aliment_consomme
				WHERE id_utilisateur = ? AND date_consommation >= ? AND date_consommation <= ?
				""";
		ouvrirConnexion();
		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, idUtilisateur);
			Date dateSql = Date.valueOf(dateFrom);
			preparedStatement.setDate(2, dateSql);
			dateSql = Date.valueOf(dateTo);
			preparedStatement.setDate(3, dateSql);

			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				if (listeAlimentConsomme == null) {
					listeAlimentConsomme = new ArrayList<AlimentConsomme>();
				}

				int id = result.getInt("id");
				int id_aliment = result.getInt("id_aliment");
				int id_utilisateur = result.getInt("id_utilisateur");
				dateSql = result.getDate("date_consommation");
				LocalDate date = dateSql.toLocalDate();
				int quantite = result.getInt("quantite");

				AlimentConsomme alimentConsomme = new AlimentConsomme();
				alimentConsomme.setIdAlimentConsomme(id);
				alimentConsomme.setId(id_aliment);
				alimentConsomme.setIdUtilisateur(id_utilisateur);
				alimentConsomme.setDate(date);
				alimentConsomme.setQuantite(quantite);

				listeAlimentConsomme.add(alimentConsomme);

			}
		} catch (Exception e) {
			e.printStackTrace();

			throw new AlimentConsommeDaoException(e.getMessage());

		}

		return listeAlimentConsomme;

	}

	@Override
	public int create(AlimentConsomme alimentConsomme) throws AlimentConsommeDaoException {
		try {
			int id = rechercherId();
			String sql = """
					INSERT INTO aliment_consomme
					(id, id_aliment, id_utilisateur, date_consommation, quantite)
					VALUES ( ? , ? , ? , ? , ? )
					""";
			ouvrirConnexion();

			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, alimentConsomme.getId());
			preparedStatement.setInt(3, alimentConsomme.getIdUtilisateur());
			Date date = Date.valueOf(alimentConsomme.getDate());
			preparedStatement.setDate(4, date);
			preparedStatement.setInt(5, alimentConsomme.getQuantite());

			preparedStatement.execute();

			return id;

		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentConsommeDaoException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws AlimentConsommeDaoException {
		String sql = """
				DELETE FROM aliment_consomme
				WHERE id = ?
				""";

		ouvrirConnexion();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlimentConsommeDaoException(e.getMessage());
		}

	}

	private int rechercherId() throws Exception {

		String sql = """
				SELECT MAX(id)
				FROM aliment_consomme
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
