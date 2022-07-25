package com.jimmy.db;

import java.util.List;

import com.jimmy.classes.Activite;

public interface ActiviteDao {

	public void createTable();

	public void deleteTable();

	public int create(Activite activite);

	public void delete(int id);

	public Activite getById(int id);

	public List<Activite> getByNom(String nom);

}
