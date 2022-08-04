package com.jimmy.db;

import java.util.List;

import com.jimmy.classes.Aliment;
import com.jimmy.exceptions.AlimentDaoException;

public interface AlimentDao {

	public void createTable() throws AlimentDaoException;

	public void deleteTable() throws AlimentDaoException;

	public List<Aliment> getAll() throws AlimentDaoException;

	public Aliment getById(int id) throws AlimentDaoException;

	public Aliment getByNom(String nom) throws AlimentDaoException;

	public int create(Aliment aliment) throws AlimentDaoException;

	public void delete(int id) throws AlimentDaoException;

}
