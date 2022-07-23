package com.jimmy.db;

import java.sql.Connection;

import com.jimmy.classes.Utilisateur;

public interface UtilisateurDao {

	public void createTable(Connection connexion);

	public void deleteTable(Connection connexion);

	public Utilisateur getById(Connection connexion, int id);

	public int create(Connection connexion, Utilisateur utilisateur);

	public void delete(Connection connexion, int id);

}
