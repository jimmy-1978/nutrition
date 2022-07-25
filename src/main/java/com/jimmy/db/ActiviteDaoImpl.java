package com.jimmy.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jimmy.classes.Activite;
import com.jimmy.enums.TypeActivite;

public class ActiviteDaoImpl implements ActiviteDao {

	private ConnexionDB connexionDB;
	private Connection connexion;

	public ActiviteDaoImpl() {
		connexionDB = new ConnexionDBMySql();
	}

	@Override
	public Activite getById(int id) {

		String sql = "SELECT id, nom_utilisateur, type, nb_calories_brulees, date FROM activite WHERE id = ? ";
		ouvrirConnexion();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				String nomUtilisateur = result.getString("nom_utilisateur");
				TypeActivite typeActivite = TypeActivite.valueOf(result.getString("type"));
				int nbCaloriesBrulees = result.getInt("nb_calories_brulees");

				java.sql.Date dateSql = result.getDate("date");
				LocalDate dateActivite = dateSql.toLocalDate();

				Activite activite = new Activite(id, nomUtilisateur, dateActivite, typeActivite, nbCaloriesBrulees);

				return activite;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int create(Activite activite) {
		try {
			int id = rechercherId();
			String sql = "INSERT INTO activite (id, nom_utilisateur, type, nb_calories_brulees, date) VALUES ( ? , ? , ? , ? , ? )";
			ouvrirConnexion();

			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, activite.getNomUtilisateur());
			preparedStatement.setString(3, activite.getTypeActivite().toString());
			preparedStatement.setInt(4, activite.getNbCaloriesBrulees());

			java.sql.Date dateSql = java.sql.Date.valueOf(activite.getDateActivite());
			preparedStatement.setDate(5, dateSql);

			preparedStatement.execute();

			return id;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM activite WHERE id = ?";
		ouvrirConnexion();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createTable() {

		String sql = """
				CREATE TABLE `nutrition`.`activite` (
				  `id` INT NOT NULL,
				  `nom_utilisateur` VARCHAR(30) NOT NULL,
				  `type` VARCHAR(30) NOT NULL,
				  `nb_calories_brulees` INT NOT NULL,
				  `date` DATE NOT NULL,
				  PRIMARY KEY (`id`, `nom_utilisateur`),
				  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
										""";
		ouvrirConnexion();
		Statement stmt = null;

		try {
			stmt = connexion.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteTable() {
		String sql = "DROP TABLE `nutrition`.`activite`";
		ouvrirConnexion();

		Statement stmt = null;
		try {
			stmt = connexion.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int rechercherId() throws Exception {

		String sql = "SELECT MAX(id) from activite";
		ouvrirConnexion();

		Statement stmt = connexion.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		int id = 0;
		while (result.next()) {
			id = result.getInt(1) + 1; // Même si null, ça donne bien id 1 au final
		}

		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;

	}

	private void ouvrirConnexion() {
		connexion = connexionDB.ouvrirConnexion();
	}

	@Override
	public List<Activite> getByNom(String nom) {

		String sql = "SELECT id, nom_utilisateur, type, nb_calories_brulees, date FROM activite WHERE nom_utilisateur = ?";
		ouvrirConnexion();

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, nom);

			ResultSet result = preparedStatement.executeQuery();
			List<Activite> listeActivite = new ArrayList<Activite>();
			Activite activite = null;
			while (result.next()) {

				int id = result.getInt("id");
				String nomUtilisateur = result.getString("nom_utilisateur");
				TypeActivite typeActivite = TypeActivite.valueOf(result.getString("type"));
				int nbCaloriesBrulees = result.getInt("nb_calories_brulees");
				java.sql.Date dateSql = result.getDate("date");
				LocalDate dateActivite = dateSql.toLocalDate();

				activite = new Activite(id, nomUtilisateur, dateActivite, typeActivite, nbCaloriesBrulees);
				listeActivite.add(activite);

			}

			return listeActivite;

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
