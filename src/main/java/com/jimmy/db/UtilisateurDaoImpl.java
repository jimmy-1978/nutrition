package com.jimmy.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.jimmy.classes.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	@Override
	public Utilisateur getById(Connection connexion, int id) {

		String sql = "SELECT nom, mot_de_passe FROM utilisateur WHERE id = ? ";
		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				String nom = result.getString("nom");
				String motDePasse = result.getString("mot_de_passe");

				return new Utilisateur(id, nom, motDePasse);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int create(Connection connexion, Utilisateur utilisateur) {
		try {
			int id = rechercherId(connexion);
			String sql = "INSERT INTO utilisateur (id, nom, mot_de_passe) VALUES ( ? , ? , ? )";
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, utilisateur.getNom());
			preparedStatement.setString(3, utilisateur.getMotDePasse());

			preparedStatement.execute();

			return id;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public void delete(Connection connexion, int id) {
		String sql = "DELETE FROM utilisateur WHERE id = ?";

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createTable(Connection connexion) {

		String sql = """
				CREATE TABLE `nutrition`.`utilisateur` (
				  `id` INT NOT NULL,
				  `nom` VARCHAR(30) NOT NULL,
				  `mot_de_passe` VARCHAR(30) NULL,
				  PRIMARY KEY (`id`, `nom`),
				  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC) VISIBLE);
										""";
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
	public void deleteTable(Connection connexion) {
		String sql = "DROP TABLE `nutrition`.`utilisateur`";

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

	private int rechercherId(Connection connexion) throws Exception {

		String sql = "SELECT MAX(id) from utilisateur";
		Statement stmt = connexion.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		int id = 0;
		while (result.next()) {
			id = result.getInt(1) + 1; // Même si null, ça donne bien id 1 au final
		}

		return id;

	}
}
