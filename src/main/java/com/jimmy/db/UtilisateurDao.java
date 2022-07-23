package com.jimmy.db;

import com.jimmy.classes.Utilisateur;

public interface UtilisateurDao {

	public void createTable();

	public void deleteTable();

	public Utilisateur getById(int id);

	public int create(Utilisateur utilisateur);

	public void delete(int id);

}
