package com.jimmy.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.jimmy.util.Propriete;

public class ConnexionDBMySql implements ConnexionDB {

	private static Connection connexion;

	public Connection ouvrirConnexion() {

		if (connexion != null) {

			return connexion;

		} else {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver"); // Charge le driver dans la VM

				// On recherche les paramètres de connexion à la DB MySQL

				Propriete propriete = new Propriete();

				String urlMySql = propriete.rechercheProprieteString("urlMySql");
				String utilisateurMySql = propriete.rechercheProprieteString("utilisateurMySql");
				String motDePasseMySql = propriete.rechercheProprieteString("motDePasseMySql");

				connexion = DriverManager.getConnection(urlMySql, utilisateurMySql, motDePasseMySql);
				System.out.println("Connexion établie sur " + urlMySql + " avec utilisateur " + utilisateurMySql);

				return connexion;

			} catch (Exception e) {
				System.out.println(e);

			}

			return null;
		}
	}

	public void fermerConnexion(Connection connexion) {
		try {
			connexion.close();
		} catch (Exception e) {
			System.out.println("Erreur fermeture connexion...");
			e.printStackTrace();
		}
	}

}
