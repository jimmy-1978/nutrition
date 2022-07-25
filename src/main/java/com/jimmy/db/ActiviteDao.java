package com.jimmy.db;

import com.jimmy.classes.Activite;

public interface ActiviteDao {

	public void createTable();

	public void deleteTable();

	public Activite getById(int id);

	public int create(Activite activite);

	public void delete(int id);

}
