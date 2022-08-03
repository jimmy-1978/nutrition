package com.jimmy.db;

import com.jimmy.classes.Utilisateur;
import com.jimmy.exceptions.UtilisateurDaoException;

public interface UtilisateurDao {

	public void createTable() throws UtilisateurDaoException;

	public void deleteTable() throws UtilisateurDaoException;

	public Utilisateur getById(int id) throws UtilisateurDaoException;

	public Utilisateur getByNom(String nom) throws UtilisateurDaoException;

	public int create(Utilisateur utilisateur) throws UtilisateurDaoException;

	public void delete(int id) throws UtilisateurDaoException;

}
