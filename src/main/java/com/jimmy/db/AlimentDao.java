package com.jimmy.db;

import com.jimmy.classes.Aliment;
import com.jimmy.exceptions.AlimentDaoException;

public interface AlimentDao {

	public void createTable() throws AlimentDaoException;

	public void deleteTable() throws AlimentDaoException;

	public Aliment getById(int id) throws AlimentDaoException;

	public int create(Aliment aliment) throws AlimentDaoException;

	public void delete(int id) throws AlimentDaoException;

}
