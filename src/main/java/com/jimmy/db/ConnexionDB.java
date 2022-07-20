package com.jimmy.db;

import java.sql.Connection;

public interface ConnexionDB {

	public Connection ouvrirConnexion();

	public void fermerConnexion(Connection connexion);

}
