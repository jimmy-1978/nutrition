package com.jimmy.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.jimmy.classes.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private ConnexionDB connexionDB;
	private Connection connexion;

	public UtilisateurDaoImpl() {
		connexionDB = new ConnexionDBMySql();
	}

	@Override
	public Utilisateur getById(int id) {

		String sql = """
				SELECT nom, mot_de_passe, date_de_naissance
				FROM utilisateur
				WHERE id = ?
				""";
		ouvrirConnexion();

		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				String nom = result.getString("nom");
				String motDePasse = result.getString("mot_de_passe");
				Date dateSql = result.getDate("date_de_naissance");
				LocalDate dateDeNaissance = dateSql.toLocalDate();

				return new Utilisateur(id, nom, motDePasse, dateDeNaissance);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Utilisateur getByNom(String nom) {

		String sql = """
				SELECT id, mot_de_passe, date_de_naissance
				FROM utilisateur
				WHERE nom = ?
				""";
		ouvrirConnexion();
		try {
			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setString(1, nom);

			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String motDePasse = result.getString("mot_de_passe");
				Date dateSql = result.getDate("date_de_naissance");
				LocalDate dateDeNaissance = dateSql.toLocalDate();

				return new Utilisateur(id, nom, motDePasse, dateDeNaissance);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public int create(Utilisateur utilisateur) {
		try {
			int id = rechercherId();
			String sql = """
					INSERT INTO utilisateur
					(id, nom, mot_de_passe, date_de_naissance)
					VALUES ( ? , ? , ? , ? )
					""";
			ouvrirConnexion();

			PreparedStatement preparedStatement = connexion.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, utilisateur.getNom());
			preparedStatement.setString(3, utilisateur.getMotDePasse());
			Date dateSql = Date.valueOf(utilisateur.getDateDeNaissance());
			preparedStatement.setDate(4, dateSql);

			preparedStatement.execute();

			return id;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public void delete(int id) {
		String sql = """
				DELETE FROM utilisateur
				WHERE id = ?
				""";

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
				CREATE TABLE utilisateur (
				  id INT NOT NULL,
				  nom VARCHAR(30) NOT NULL,
				  mot_de_passe VARCHAR(30) NOT NULL,
				  date_de_naissance DATE NOT NULL,
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
		}

		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteTable() {
		String sql = "DROP TABLE utilisateur";
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

		String sql = """
				SELECT MAX(id) 
				FROM utilisateur
				""";
		ouvrirConnexion();

		Statement stmt = connexion.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		int id = 0;
		while (result.next()) {
			id = result.getInt(1) + 1; // Même si null, ça donne bien id 1 au final
		}

		return id;

	}

	private void ouvrirConnexion() {
		connexion = connexionDB.ouvrirConnexion();
	}
}
